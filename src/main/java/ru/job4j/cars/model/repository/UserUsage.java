package ru.job4j.cars.model.repository;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.cars.model.User;

public class UserUsage {
    public static void main(String[] args) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try (SessionFactory sf = new MetadataSources(registry)
                .buildMetadata().buildSessionFactory()) {
            var userRepository = new UserRepository(sf);
            var usr = new User();
            usr.setLogin("admin");
            usr.setPassword("admin");
            var user = userRepository.create(usr);
            userRepository.findAllOrderById()
                    .forEach(System.out::println);
            userRepository.findByLikeLogin("e")
                    .forEach(System.out::println);
            userRepository.findById(user.get().getId())
                    .ifPresent(System.out::println);
            userRepository.findByLogin("admin")
                    .ifPresent(System.out::println);
            user.get().setPassword("password");
            userRepository.update(user.get());
            userRepository.findById(user.get().getId())
                    .ifPresent(System.out::println);
            userRepository.delete(user.get().getId());
            userRepository.findAllOrderById()
                    .forEach(System.out::println);
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}