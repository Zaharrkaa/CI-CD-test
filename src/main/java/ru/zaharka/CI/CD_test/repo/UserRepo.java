package ru.zaharka.CI.CD_test.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.zaharka.CI.CD_test.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
}
