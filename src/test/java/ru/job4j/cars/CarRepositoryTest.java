package ru.job4j.cars;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.jupiter.api.Test;
import ru.job4j.cars.model.Car;
import ru.job4j.cars.model.Engine;
import ru.job4j.cars.model.repository.CarRepository;
import ru.job4j.cars.model.repository.CrudRepository;
import ru.job4j.cars.model.repository.EngineRepository;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

public class CarRepositoryTest {
    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();
    private final CrudRepository crudRepository = new CrudRepository(sf);
    private final CarRepository carRepository = new CarRepository(crudRepository);
    private final EngineRepository engineRepository = new EngineRepository(crudRepository);
    @Test
    public void whenCreateCarThenFound() {
        Engine engine1 = new Engine();
        engine1.setName("engine_test1");
        engineRepository.create(engine1);
        Engine engine2 = new Engine();
        engine2.setName("engine_test2");
        engineRepository.create(engine2);
        Car car1 = new Car();
        car1.setName("car_test1");
        car1.setEngine(engine1);
        carRepository.create(car1);
        Car car2 = new Car();
        car2.setName("car_test2");
        car2.setEngine(engine2);
        carRepository.create(car2);
        var result = carRepository.findAll();
        assertThat(result).isEqualTo(List.of(car1, car2));
    }
    @Test
    public void whenDeleteCarThenNotFound() {
        Engine engine = new Engine();
        engine.setName("engine_test");
        engineRepository.create(engine);
        Car car = new Car();
        car.setName("car_test");
        car.setEngine(engine);
        carRepository.create(car);
        carRepository.delete(car.getId());
        var result = carRepository.findById(car.getId());
        assertThat(result.isEmpty());
    }
    @Test
    public void whenFindByIdThenFound() {
        Engine engine = new Engine();
        engine.setName("engine_test");
        engineRepository.create(engine);
        Car car = new Car();
        car.setName("car_test");
        car.setEngine(engine);
        carRepository.create(car);
        var result = carRepository.findById(car.getId());
        assertThat(result.get()).isEqualTo(car);
    }
}