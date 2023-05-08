package ru.job4j.cars.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
/**
 * Class Post - Объявление. Решение задач уровня Middle.
 * Категория : 3.3. HibernateТема Тема : 3.3.3. Mapping
 *
 * @author Viacheslav Pesterev (pesterevvv@gmail.com)
 * @since 27.01.2023
 * @version 1
 */
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "auto_post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id;
    private String text;
    private LocalDateTime created;
    @ManyToOne
    @JoinColumn(name = "auto_user_id")
    private User user;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "post_id")
    private List<PriceHistory> priceHistory = new ArrayList<>();
    @ManyToMany
    @JoinTable(
            name = "participates",
            joinColumns = { @JoinColumn(name = "post_id") },
            inverseJoinColumns = { @JoinColumn(name = "user_id") }
    )
    private List<User> participates = new ArrayList<>();
    @OneToOne
    @JoinColumn(name = "car_id")
    private Car car;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "auto_post_id")
    private List<PostPhoto> postPhotos = new ArrayList<>();
}