package az.company.service;

import az.company.model.User;

public interface UserService {
    void createUser(User user);

    User findByUsername(String username);
}
