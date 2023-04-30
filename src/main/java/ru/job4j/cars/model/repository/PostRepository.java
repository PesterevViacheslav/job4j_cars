package ru.job4j.cars.model.repository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Post;
import java.util.List;
import java.util.Map;
@Repository
@AllArgsConstructor
public class PostRepository {
    private final CrudRepository crudRepository;

    /**
     * Объявления за последний день
     * @return объявления.
     */
    public List<Post> findAllForLastDay() {
        return crudRepository.query(
                """
                        SELECT DISTINCT p FROM Post p
                        JOIN FETCH p.auto_user u
                        JOIN FETCH p.car c
                        LEFT JOIN FETCH p.photo f
                        WHERE p.created >= CURRENT_DATE
                        """,
                Post.class);
    }
    /**
     * Объявления с фото
     * @return объявления.
     */
    public List<Post> findAllWithPhoto() {
        return crudRepository.query(
                """
                        SELECT DISTINCT p FROM Post p
                        JOIN FETCH p.auto_user u
                        JOIN FETCH p.car c
                        JOIN FETCH p.photo f
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
                        SELECT DISTINCT p FROM Post p
                        JOIN FETCH p.auto_user u
                        JOIN FETCH p.car c
                        LEFT JOIN FETCH p.photo f
                        WHERE c.name := fAutoName
                        """,
                Post.class,
                Map.of("fAutoName", brand));
    }
}