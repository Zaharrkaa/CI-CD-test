package ru.zaharka.CI.CD_test.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.zaharka.CI.CD_test.entity.User;
import ru.zaharka.CI.CD_test.repo.UserRepo;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepo userRepo;

    public List<User> findAll() {
        return userRepo.findAll();
    }
}
