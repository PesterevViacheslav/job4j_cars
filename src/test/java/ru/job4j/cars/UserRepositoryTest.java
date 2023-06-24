package ru.job4j.cars;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.jupiter.api.Test;
import ru.job4j.cars.model.User;
import ru.job4j.cars.model.repository.CrudRepository;
import ru.job4j.cars.model.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;

public class UserRepositoryTest {
    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();
    private final CrudRepository crudRepository = new CrudRepository(sf);
    private final UserRepository userRepository = new UserRepository(crudRepository);

    @Test
    void whenCreateUserThanCreated() {
        User user = new User();
        user.setLogin("admin_login_test");
        user.setPassword("admin_password_test");
        userRepository.create(user);
        Optional<User> result = userRepository.findById(user.getId());
        assertThat(result.get()).isEqualTo(user);
    }
    @Test
    void whenFindUserByLoginThanFound() {
        User user = new User();
        user.setLogin("admin_login_test");
        user.setPassword("admin_password_test");
        userRepository.create(user);
        Optional<User> result = userRepository.findByLogin(user.getLogin());
        assertThat(result.get()).isEqualTo(user);
    }
    @Test
    void whenFindAllUserThanAllFound() {
        User user = new User();
        user.setLogin("admin_login_test");
        user.setPassword("admin_password_test");
        userRepository.create(user);
        User user2 = new User();
        user2.setLogin("admin_login_test2");
        user2.setPassword("admin_password_test2");
        userRepository.create(user2);
        List<User> lst = List.of(user, user2);
        List<User> result = userRepository.findAllOrderById();
        assertThat(result).isEqualTo(lst);
    }
}