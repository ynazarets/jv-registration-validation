package core.basesyntax.service;

import core.basesyntax.dao.StorageDao;
import core.basesyntax.dao.StorageDaoImpl;
import core.basesyntax.model.User;

public class RegistrationServiceImpl implements RegistrationService {
    private static final Integer MIN_AGE = 18;
    private static final Integer MIN_PASSWORD_SIZE = 6;
    private final StorageDao storageDao = new StorageDaoImpl();

    @Override
    public User register(User user) {
        validateLogin(user.getLogin());
        validateAge(user.getAge());
        validatePassword(user.getPassword());
        return storageDao.add(user);
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
