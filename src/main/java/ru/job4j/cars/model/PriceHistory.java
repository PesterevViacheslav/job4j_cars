package ru.job4j.cars.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.LocalDateTime;
/**
 * Class PriceHistory - История цены. Решение задач уровня Middle.
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
@Table(name = "price_history")
public class PriceHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id;
    private int before;
    private int after;
    private LocalDateTime created;
}
