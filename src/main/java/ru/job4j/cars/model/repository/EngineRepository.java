package ru.job4j.cars.model.repository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Engine;
import ru.job4j.cars.model.User;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@Repository
@AllArgsConstructor
public class EngineRepository {
    private final CrudRepository crudRepository;
    /**
     * Сохранить в базе.
     *
     * @param engine двигатель.
     * @return двигатель с id.
     */
    public Engine create(Engine engine) {
        crudRepository.run(session -> session.persist(engine));
        return engine;
    }
    /**
     * Обновить в базе пользователя.
     * @param user пользователь.
     */
    public void update(User user) {
        crudRepository.run(session -> session.merge(user));
    }
    /**
     * Удалить двигатель по id.
     * @param engineId ID
     */
    public void delete(int engineId) {
        crudRepository.run(
                "delete from Engine where id = :fId",
                Map.of("fId", engineId)
        );
    }
    /**
     * Найти двигатель по ID
     * @return двигатель.
     */
    public Optional<Engine> findById(int engineId) {
        return crudRepository.optional(
                "from Engine where id = :fId", Engine.class,
                Map.of("fId", engineId)
        );
    }
    /**
     * Найти все двигатели
     * @return двигатели.
     */
    public List<Engine> findAll() {
        return crudRepository.query("from Engine order by name", Engine.class);
    }
}
