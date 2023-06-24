package ru.job4j.cars;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.jupiter.api.Test;
import ru.job4j.cars.model.*;
import ru.job4j.cars.model.repository.*;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PostRepositoryTest {
    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();
    private final CrudRepository crudRepository = new CrudRepository(sf);
    private final UserRepository userRepository = new UserRepository(crudRepository);
    private final EngineRepository engineRepository = new EngineRepository(crudRepository);
    private final CarRepository carRepository = new CarRepository(crudRepository);
    private final PhotoRepository photoRepository = new PhotoRepository(crudRepository);
    private final PostRepository postRepository = new PostRepository(crudRepository);

    @Test
    void whenFindAllForLastDayThenFound() {
        Engine engine = new Engine();
        engine.setName("Engine_test");
        engineRepository.create(engine);
        Car car = new Car();
        car.setName("Car_test");
        car.setEngine(engine);
        carRepository.create(car);
        User user = new User();
        user.setLogin("Login_test");
        user.setPassword("Login_password");
        userRepository.create(user);
        Post post = new Post();
        post.setText("Post_test");
        post.setCar(car);
        post.setUser(user);
        post.setCreated(LocalDateTime.now());
        postRepository.create(post);
        List<Post> postList = List.of(post);
        List<Post> result = postRepository.findAllForLastDay();
        assertThat(result).isEqualTo(postList);
    }
    @Test
    void whenFindAllWithPhotoThenFound() {
        Engine engine = new Engine();
        engine.setName("Engine_test");
        engineRepository.create(engine);
        Car car = new Car();
        car.setName("Car_test");
        car.setEngine(engine);
        carRepository.create(car);
        User user = new User();
        user.setLogin("Login_test");
        user.setPassword("Login_password");
        userRepository.create(user);
        Photo photo = new Photo();
        photo.setName("Photo_test");
        photoRepository.create(photo);
        Post post = new Post();
        post.setText("Post_test");
        post.setCar(car);
        post.setUser(user);
        PostPhoto postPhoto = new PostPhoto();
        postPhoto.setPhotoId(photo.getId());
        postPhoto.setPostId(post.getId());
        post.setPostPhotos(List.of(postPhoto));
        post.setCreated(LocalDateTime.now().minusDays(10));
        postRepository.create(post);
        List<Post> postList = List.of(post);
        List<Post> result = postRepository.findAllWithPhoto();
        assertThat(result).isEqualTo(postList);
    }
    @Test
    void whenFindAllByBrandThenFound() {
        Engine engine = new Engine();
        engine.setName("Engine_test");
        engineRepository.create(engine);
        Car car = new Car();
        car.setName("Car_test");
        car.setEngine(engine);
        carRepository.create(car);
        User user = new User();
        user.setLogin("Login_test");
        user.setPassword("Login_password");
        userRepository.create(user);
        Post post = new Post();
        post.setText("Post_test");
        post.setCar(car);
        post.setUser(user);
        post.setCreated(LocalDateTime.now().minusDays(10));
        postRepository.create(post);
        List<Post> postList = List.of(post);
        List<Post> result = postRepository.findAllByBrand(car.getName());
        assertThat(result).isEqualTo(postList);
    }
}