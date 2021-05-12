package ru.goygov.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.goygov.crud.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
