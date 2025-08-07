package core.basesyntax.service;

import core.basesyntax.dao.StorageDao;
import core.basesyntax.dao.StorageDaoImpl;
import core.basesyntax.model.User;

public class RegistrationServiceImpl implements RegistrationService {
    public static final int MIN_AGE = 18;
    public static final int MIN_LENGTH_PASSWORD_AND_LOGIN = 6;
    private static final String[] popularPasswords = new String[]{"123456789", "qwerty"};
    private final StorageDao storageDao = new StorageDaoImpl();

    @Override
    public User register(User user) throws NotValidData {
        if (user == null) {
            throw new NotValidData("user can`t be null. Create another User.");
        }
        if (user.getPassword() == null
                || user.getPassword().length() < MIN_LENGTH_PASSWORD_AND_LOGIN
                || user.getPassword().trim().isEmpty()) {
            throw new NotValidData("Can`t created new profile. Your password is too short.");
        }
        for (String currentPassword : popularPasswords) {
            if (currentPassword.equals(user.getPassword())) {
                throw new NotValidData("Using this password it`s bad practice, "
                        + " you must creating something new.");
            }
        }
        if (user.getAge() == null || user.getAge() < MIN_AGE) {
            throw new NotValidData("Your age must be more than " + MIN_AGE + " years old.");
        }
        if (user.getLogin() == null || user.getLogin().length() < MIN_LENGTH_PASSWORD_AND_LOGIN
                || user.getLogin().trim().isEmpty()) {
            throw new NotValidData("Can`t created new profile. Your login is too short.");
        }
        User userWasExist = storageDao.get(user.getLogin());
        if (userWasExist != null) {
            throw new NotValidData("Profile with this login already exists.");
        }
        storageDao.add(user);
        return user;
    }
}
