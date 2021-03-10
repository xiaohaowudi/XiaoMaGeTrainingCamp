package org.geektimes.projects.user.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

public class SqlExecutor {

    private final DBConnectionManager dbConnMng;

    public SqlExecutor(DBConnectionManager mng) {
        dbConnMng = mng;
    }

    public int executeUpdate(String sql, Consumer<Throwable> exceptionConsumer, Object... args) {
        try {
            return __executeUpdate(sql, dbConnMng.getConnection(), true, exceptionConsumer, args);
        } catch (Exception e) {
            exceptionConsumer.accept(e);
        }

        return 0;
    }

    public int executeUpdateWithoutCommit(String sql, Connection connection, Consumer<Throwable> exceptionConsumer, Object... args) {
        return __executeUpdate(sql, connection, false, exceptionConsumer, args);
    }

    private int __executeUpdate(String sql, Connection connection, boolean closeConnection, Consumer<Throwable> exceptionConsumer, Object... args) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            int idx = 1;
            for (Object arg : args) {
                preparedStatement.setObject(idx, arg);
                idx += 1;
            }

            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            exceptionConsumer.accept(e);
        } finally {
            if (closeConnection) {
                dbConnMng.releaseConnection(connection);
            }
        }

        return 0;
    }

    public <T> T executeQuery(String sql, Function<ResultSet, T> resultHandler, Consumer<Throwable> exceptionConsumer, Object... args) {
        try {
            return __executeQuery(sql, dbConnMng.getConnection(), true, resultHandler, exceptionConsumer, args);
        } catch (SQLException e) {
            exceptionConsumer.accept(e);
        }
        return null;
    }

    public <T> T executeQueryWithoutCommit(String sql, Connection connection, Function<ResultSet, T> resultHandler, Consumer<Throwable> exceptionConsumer, Object... args) {
        return __executeQuery(sql, connection, false, resultHandler, exceptionConsumer, args);
    }

    private <T> T __executeQuery(String sql, Connection connection, boolean closeConnection, Function<ResultSet, T> resultHandler, Consumer<Throwable> exceptionConsumer, Object... args) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            int idx = 1;
            for (Object arg : args) {
                preparedStatement.setObject(idx, arg);
                idx += 1;
            }

            ResultSet rs = preparedStatement.executeQuery();
            return resultHandler.apply(rs);

        } catch (SQLException e) {
            exceptionConsumer.accept(e);
        } finally {
            if (closeConnection) {
                dbConnMng.releaseConnection(connection);
            }
        }

        return null;
    }
}