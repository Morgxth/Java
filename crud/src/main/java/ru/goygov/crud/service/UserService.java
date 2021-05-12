package ru.goygov.crud.service;

import org.springframework.beans.factory.annotation.Autowired;
import ru.goygov.crud.model.User;
import ru.goygov.crud.repository.UserRepository;

import java.util.List;

public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findById(Long id){
        return userRepository.getOne(id);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
