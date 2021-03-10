package org.geektimes.projects.user.service;

import org.geektimes.projects.user.domain.User;
import org.geektimes.projects.user.repository.DatabaseUserRepository;
import org.geektimes.projects.user.repository.UserRepository;
import org.geektimes.projects.user.sql.DBConnectionManager;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.sql.DataSource;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;
import java.util.logging.Logger;

public class UserServiceImpl implements UserService {

    @Resource(name = "jdbc/UserPlatformDB")
    private DataSource dataSource;

    @Resource(name = "bean/Validator")
    private Validator validator;

    @Resource(name = "bean/DBConnectionManager")
    private DBConnectionManager connectionManager;

    private UserRepository userRepository;

    private static final Logger logger = Logger.getLogger(UserServiceImpl.class.getName());

    @PostConstruct
    public void init() {
        userRepository = new DatabaseUserRepository(connectionManager);
    }

    @Override
    public boolean register(User user) {

        Set<ConstraintViolation<User>> violationSet = validator.validate(user);
        if (violationSet.size() != 0) {
            for (ConstraintViolation<User> violation : violationSet) {
                logger.info(violation.getMessage());
            }

            return false;
        }

        return userRepository.save(user);
    }

    @Override
    public boolean deregister(User user) {
        return userRepository.deleteById(user.getId());
    }

    @Override
    public boolean update(User user) {
        return userRepository.update(user);
    }

    @Override
    public User queryUserById(Long id) {
        return userRepository.getById(id);
    }

    @Override
    public User queryUserByNameAndPassword(String name, String password) {
        return userRepository.getByNameAndPassword(name, password);
    }
}
