package ru.job4j.cars.model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "history_owner")
/**
 * Class HistoryOwner - История владения.
 * Решение задач уровня Middle. Части 3.3. Hibernate.
 * Модели и связи. Машины и владельцы [#4744]
 *
 * @author Viacheslav Pesterev (pesterevvv@gmail.com)
 * @since 11.04.2023
 * @version 1
 */
public class HistoryOwner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id;
    private String name;
    private int carId;
    private int ownerId;
    private LocalDateTime startDt = LocalDateTime.now();
    private LocalDateTime endDt;
}