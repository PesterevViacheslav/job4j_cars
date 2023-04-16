package ru.job4j.cars.model.repository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Car;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@Repository
@AllArgsConstructor
public class CarRepository {
    private final CrudRepository crudRepository;
    /**
     * Сохранить в базе.
     *
     * @param car автомобиль.
     * @return автомобиль с id.
     */
    public Car create(Car car) {
        crudRepository.run(session -> session.persist(car));
        return car;
    }
    /**
     * Обновить в базе автомобиль.
     * @param car автомобиль.
     */
    public void update(Car car) {
        crudRepository.run(session -> session.merge(car));
    }
    /**
     * Удалить автомобиль по id.
     * @param carId ID
     */
    public void delete(int carId) {
        crudRepository.run(
                "delete from Car where id = :fId",
                Map.of("fId", carId)
        );
    }
    /**
     * Найти автомобиль по ID
     * @return автомобиль.
     */
    public Optional<Car> findById(int carId) {
        return crudRepository.optional(
                "from Car where id = :fId", Car.class,
                Map.of("fId", carId)
        );
    }
    /**
     * Найти все автомобили
     * @return автомобили.
     */
    public List<Car> findAll() {
        return crudRepository.query("from Car order by name", Car.class);
    }
}