package ru.job4j.cars;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.jupiter.api.Test;
import ru.job4j.cars.model.Owner;
import ru.job4j.cars.model.repository.CrudRepository;
import ru.job4j.cars.model.repository.OwnerRepository;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

public class OwnerRepositoryTest {
    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();
    private final CrudRepository crudRepository = new CrudRepository(sf);
    private final OwnerRepository ownerRepository = new OwnerRepository(crudRepository);
    @Test
    public void whenCreateOwnerThenFound() {
        Owner owner1 = new Owner();
        owner1.setName("photo_test1");
        ownerRepository.create(owner1);
        Owner owner2 = new Owner();
        owner2.setName("photo_test2");
        ownerRepository.create(owner2);
        var result = ownerRepository.findAllOwnerByName();
        assertThat(result).isEqualTo(List.of(owner1, owner2));
    }
    @Test
    public void whenDeleteOwnerThenNotFound() {
        Owner owner = new Owner();
        owner.setName("owner_test");
        ownerRepository.create(owner);
        ownerRepository.delete(owner.getId());
        var result = ownerRepository.findById(owner.getId());
        assertThat(result.isEmpty());
    }
    @Test
    public void whenFindByIdThenFound() {
        Owner owner = new Owner();
        owner.setName("owner_test");
        ownerRepository.create(owner);
        var result = ownerRepository.findById(owner.getId());
        assertThat(result.get()).isEqualTo(owner);
    }
}