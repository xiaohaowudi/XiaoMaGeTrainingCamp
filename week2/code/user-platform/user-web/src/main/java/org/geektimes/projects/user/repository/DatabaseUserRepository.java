package org.geektimes.projects.user.repository;

import org.geektimes.projects.user.domain.User;

import org.geektimes.projects.user.sql.SqlExecutor;
import org.geektimes.projects.user.sql.DBConnectionManager;


import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;

public class DatabaseUserRepository implements UserRepository {

    private final SqlExecutor sqlExecutor;

    private static final String CREATE_TABLE_DDL_SQL = "create table if not exists users (\n" +
            "    id int not null unique auto_increment primary key,\n" +
            "    name varchar(16) not null unique,\n" +
            "    password varchar(64) not null,\n" +
            "    email varchar(64) not null,\n" +
            "    phoneNumber varchar(32) not null\n" +
            ")";

    private static final String INSERT_USERS_DML_SQL = "insert into users(id, name, password, email, phoneNumber) values (?,?,?,?,?);";
    private static final String QUERY_ALL_USERS_DML_SQL = "select id,name,password,email,phoneNumber from users;";
    private static final String QUERY_USERS_BY_NAME_PASSWORD_SQL = "select id,name,password,email,phoneNumber from users where name=? and password=?;";
    private static final String QUERY_USERS_BY_ID_SQL = "select id,name,password,email,phoneNumber from users where id=?;";
    private static final String UPDATE_USERS_SQL = "update users set name=?, password=?,email=?,phoneNumber=? where id=?;";
    private static final String DELETE_USER_BY_ID_SQL = "delete from users where id=?;";

    static Map<Class, String> fieldType2ResultSetMethodNameMapping = new HashMap<>();
    static Map<String, String> fieldName2columnNameMapping = new HashMap<>();

    static {
        fieldType2ResultSetMethodNameMapping.put(Long.class, "getLong");
        fieldType2ResultSetMethodNameMapping.put(String.class, "getString");

        fieldName2columnNameMapping.put("id", "id");
        fieldName2columnNameMapping.put("name", "name");
        fieldName2columnNameMapping.put("password", "password");
        fieldName2columnNameMapping.put("email", "email");
        fieldName2columnNameMapping.put("phoneNumber", "phoneNumber");
    }

    private static final Consumer<Throwable> DEFAULT_EXCEPTION_HANDLER = Throwable::printStackTrace;

    private static final Function<ResultSet, Collection<User>> QUERY_USER_AS_COLLECTION_HANDLER = (rs) -> {
        List<User> userList = new ArrayList<>();
        try {
            BeanInfo userBeanInfo = Introspector.getBeanInfo(User.class);
            while (rs.next()) {
                User user = new User();

                for (PropertyDescriptor pd : userBeanInfo.getPropertyDescriptors()) {
                    String fieldName = pd.getName();
                    Class fieldType = pd.getPropertyType();
                    String MethodName = fieldType2ResultSetMethodNameMapping.get(fieldType);
                    if (MethodName == null) {
                        continue;
                    }

                    String columnName = fieldName2columnNameMapping.get(fieldName);
                    Method getFieldMethod = ResultSet.class.getMethod(MethodName, String.class);
                    Object fieldVal = getFieldMethod.invoke(rs, columnName);

                    pd.getWriteMethod().invoke(user, fieldVal);
                }

                userList.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return userList;
    };

    public DatabaseUserRepository(DBConnectionManager dbMng) {
        sqlExecutor = new SqlExecutor(dbMng);

//        try {
//            Connection connection = dbMng.getConnection();
//            Statement stat = connection.createStatement();
//            stat.execute(CREATE_TABLE_DDL_SQL);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    @Override
    public boolean save(User user) {
        return sqlExecutor.executeUpdate(
                INSERT_USERS_DML_SQL,
                DEFAULT_EXCEPTION_HANDLER,
                user.getId(),
                user.getName(),
                user.getPassword(),
                user.getEmail(),
                user.getPhoneNumber()
        ) == 1;
    }

    @Override
    public boolean deleteById(Long userId) {
        return sqlExecutor.executeUpdate(
                DELETE_USER_BY_ID_SQL,
                DEFAULT_EXCEPTION_HANDLER,
                userId
        ) == 1;
    }

    @Override
    public boolean update(User user) {
        return sqlExecutor.executeUpdate(
                UPDATE_USERS_SQL,
                DEFAULT_EXCEPTION_HANDLER,
                user.getName(),
                user.getPassword(),
                user.getEmail(),
                user.getPhoneNumber(),
                user.getId()
        ) == 1;
    }

    @Override
    public User getById(Long userId) {
        Collection<User> users = sqlExecutor.executeQuery(
                QUERY_USERS_BY_ID_SQL,
                QUERY_USER_AS_COLLECTION_HANDLER,
                DEFAULT_EXCEPTION_HANDLER,
                userId
        );

        // 简单起见，直接返回第一个
        if (users.size() > 0) {
            return users.stream().findFirst().get();
        }
        return null;
    }

    @Override
    public User getByNameAndPassword(String userName, String password) {
        Collection<User> users = sqlExecutor.executeQuery(
                QUERY_USERS_BY_NAME_PASSWORD_SQL,
                QUERY_USER_AS_COLLECTION_HANDLER,
                DEFAULT_EXCEPTION_HANDLER,
                userName,
                password
        );

        // 简单起见，直接返回第一个
        if (users.size() > 0) {
            return users.stream().findFirst().get();
        }
        return null;
    }

    @Override
    public Collection<User> getAll() {
        return sqlExecutor.executeQuery(
                QUERY_ALL_USERS_DML_SQL,
                QUERY_USER_AS_COLLECTION_HANDLER,
                DEFAULT_EXCEPTION_HANDLER
        );
    }
}
