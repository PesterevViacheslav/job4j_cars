package ru.job4j.cars.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import javax.persistence.*;
/**
 * Class Participates - Подписка на объявление. Связь Пользователь - Объявление. Решение задач уровня Middle.
 * Категория : 3.3. HibernateТема Тема : 3.3.3. Mapping
 *
 * @author Viacheslav Pesterev (pesterevvv@gmail.com)
 * @since 01.02.2023
 * @version 1
 */
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "participates")
public class Participates {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id;
    @Column(name = "user_id")
    private int userId;
    @Column(name = "post_id")
    private int postId;
}
