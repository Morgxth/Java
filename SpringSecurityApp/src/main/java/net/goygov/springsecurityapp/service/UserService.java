package net.goygov.springsecurityapp.service;

import net.goygov.springsecurityapp.model.User;

public interface UserService {

    void save(User user);

    User findByUsername(String username);
}
