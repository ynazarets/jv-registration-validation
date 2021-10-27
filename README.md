# Registration Service

Your task is to implement a registration service and write unit tests for it.

Registration service has one method `register(User user)`, that accepts some user (the User.class has already been given to you).
This method should register a user (by adding it to Storage) only if the user meets the following criteria:
- there is no user with such login in the Storage yet
- the user is at least 18 years old
- user password is at least 6 characters

In case of invalid data you should throw RuntimeException.

`Storage` and class `StorageDaoImpl` for working with the Storage has already been implemented. It has two methods:
- `User add (User user)`  - which adds a user to the storage
- `User get (String login)` - which gets the user from the storage by login or returns null if there is no such user.

Your task is to write as many tests as possible to cover all possible options.
