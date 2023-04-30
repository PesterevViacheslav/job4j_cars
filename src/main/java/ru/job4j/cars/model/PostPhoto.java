package ru.job4j.cars.model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.persistence.*;
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "auto_post_photo")
/**
 * Class PostPhoto - Фото - объявление.
 * Решение задач уровня Middle. Части 3.3. Hibernate.
 * Модели и связи. Машины и владельцы [#4744]
 *
 * @author Viacheslav Pesterev (pesterevvv@gmail.com)
 * @since 30.04.2023
 * @version 1
 */
public class PostPhoto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id;
    private int postId;
    private int photoId;
}