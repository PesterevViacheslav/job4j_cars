package ru.job4j.cars;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.jupiter.api.Test;
import ru.job4j.cars.model.Photo;
import ru.job4j.cars.model.repository.CrudRepository;
import ru.job4j.cars.model.repository.PhotoRepository;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

public class PhotoRepositoryTest {
    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();
    private final CrudRepository crudRepository = new CrudRepository(sf);
    private final PhotoRepository photoRepository = new PhotoRepository(crudRepository);
    @Test
    public void whenCreatePhotoThenFound() {
        Photo photo1 = new Photo();
        photo1.setName("photo_test1");
        photoRepository.create(photo1);
        Photo photo2 = new Photo();
        photo2.setName("photo_test2");
        photoRepository.create(photo2);
        var result = photoRepository.findAll();
        assertThat(result).isEqualTo(List.of(photo1, photo2));
    }
    @Test
    public void whenDeletePhotoThenNotFound() {
        Photo photo = new Photo();
        photo.setName("photo_test");
        photoRepository.create(photo);
        photoRepository.delete(photo.getId());
        var result = photoRepository.findById(photo.getId());
        assertThat(result.isEmpty());
    }
    @Test
    public void whenFindByIdThenFound() {
        Photo photo = new Photo();
        photo.setName("photo_test");
        photoRepository.create(photo);
        var result = photoRepository.findById(photo.getId());
        assertThat(result.get()).isEqualTo(photo);
    }
}
