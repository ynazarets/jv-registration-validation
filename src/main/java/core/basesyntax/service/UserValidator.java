package core.basesyntax.service;

import core.basesyntax.dao.StorageDao;
import core.basesyntax.dao.StorageDaoImpl;
import core.basesyntax.model.User;

public class UserValidator {
    private static final Integer MIN_AGE = 18;
    private static final Integer MIN_PASSWORD_SIZE = 6;
    StorageDao storageDao = new StorageDaoImpl();

    public boolean validateUser(User user) {
        return validateAge(user.getAge())
                && validatePassword(user.getPassword())
                && validateLogin(user.getLogin());
    }

    private boolean validateLogin(String login) {
        User user = storageDao.get(login);
        if (user != null) {
            throw new RuntimeException(
                    "User with such login already registered. Login: " + login);
        }
        return true;
    }

    private boolean validatePassword(String password) {
        if (password == null || password.length() < MIN_PASSWORD_SIZE) {
            throw new RuntimeException(
                    "Password shouldn't be less than " + MIN_PASSWORD_SIZE);
        }
        return true;
    }

    private boolean validateAge(Integer age) {
        if (age < MIN_AGE) {
            throw new RuntimeException(
                    "User must be at least " + MIN_PASSWORD_SIZE + " years old");
        }
        return true;
    }
}
