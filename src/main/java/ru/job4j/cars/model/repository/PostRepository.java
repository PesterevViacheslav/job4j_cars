package ru.job4j.cars.model.repository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Engine;
import ru.job4j.cars.model.Post;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
@Repository
@RequiredArgsConstructor
public class PostRepository {
    @NonNull
    private final CrudRepository crudRepository;
    private LocalDateTime created = LocalDateTime.now().minus(1, ChronoUnit.DAYS);
    /**
     * Объявления за последний день
     * @return объявления.
     */
    public Post create(Post post) {
        crudRepository.run(session -> session.persist(post));
        return post;
    }
    public List<Post> findAllForLastDay() {
        return crudRepository.query(
                """
                        FROM Post p
                        JOIN FETCH p.car.engine e
                        JOIN FETCH p.user u
                        LEFT JOIN FETCH p.postPhotos f
                        WHERE p.created >= :fCreated
                        """,
                Post.class,
                Map.of("fCreated", created));
    }
    /**
     * Объявления с фото
     * @return объявления.
     */
    public List<Post> findAllWithPhoto() {
        return crudRepository.query(
                """
                        FROM Post p
                        JOIN FETCH p.user u
                        JOIN FETCH p.car.engine e
                        JOIN FETCH p.postPhotos f
                        """,
                Post.class);
    }
    /**
     * Объявления определенной марки
     * @return объявления.
     */
    public List<Post> findAllByBrand(String brand) {
        return crudRepository.query(
                """
                        FROM Post p
                        JOIN FETCH p.user u
                        JOIN FETCH p.car c
                        LEFT JOIN FETCH p.postPhotos f
                        WHERE c.name = :fAutoName
                        """,
                Post.class,
                Map.of("fAutoName", brand));
    }
}