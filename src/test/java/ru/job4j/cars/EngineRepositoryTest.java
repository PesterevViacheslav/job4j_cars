package ru.job4j.cars;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.jupiter.api.Test;
import ru.job4j.cars.model.Engine;
import ru.job4j.cars.model.repository.CrudRepository;
import ru.job4j.cars.model.repository.EngineRepository;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

public class EngineRepositoryTest {
    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();
    private final CrudRepository crudRepository = new CrudRepository(sf);
    private final EngineRepository engineRepository = new EngineRepository(crudRepository);
    @Test
    public void whenCreateEngineThenFound() {
        Engine engine1 = new Engine();
        engine1.setName("engine_test1");
        engineRepository.create(engine1);
        Engine engine2 = new Engine();
        engine2.setName("engine_test2");
        engineRepository.create(engine2);
        var result = engineRepository.findAll();
        assertThat(result).isEqualTo(List.of(engine1, engine2));
    }
    @Test
    public void whenDeleteEngineThenNotFound() {
        Engine engine = new Engine();
        engine.setName("engine_test");
        engineRepository.create(engine);
        engineRepository.delete(engine.getId());
        var result = engineRepository.findById(engine.getId());
        assertThat(result.isEmpty());
    }
    @Test
    public void whenFindByIdThenFound() {
        Engine engine = new Engine();
        engine.setName("engine_test");
        engineRepository.create(engine);
        var result = engineRepository.findById(engine.getId());
        assertThat(result.get()).isEqualTo(engine);
    }
}