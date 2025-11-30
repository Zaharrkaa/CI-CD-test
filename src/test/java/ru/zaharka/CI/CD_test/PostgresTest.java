package ru.zaharka.CI.CD_test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;
import ru.zaharka.CI.CD_test.entity.User;
import ru.zaharka.CI.CD_test.repo.UserRepo;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Testcontainers
public class PostgresTest {

    private final UserRepo userRepo;
    @Container
    private static final PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(DockerImageName.parse("postgres:15"));

    @Autowired
    public PostgresTest(UserRepo userRepo) {
        this.userRepo = userRepo;
    }


    @DynamicPropertySource
    static void configure(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
        registry.add("spring.jpa.generate-ddl", () -> true);
    }

    @BeforeEach
    void setUp() {
        userRepo.deleteAll();
    }

    @Test
    void save(){
        User user = new User("testName", "testmail@mail.com");
        userRepo.save(user);
        System.out.println("checking...");
        assertThat(userRepo.findAll()).hasSize(1);
    }
}
