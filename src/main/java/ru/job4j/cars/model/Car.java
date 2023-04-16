package ru.job4j.cars.model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "car")
/**
 * Class Car - Автомобиль.
 * Решение задач уровня Middle. Части 3.3. Hibernate.
 * Модели и связи. Машины и владельцы [#4744]
 *
 * @author Viacheslav Pesterev (pesterevvv@gmail.com)
 * @since 11.04.2023
 * @version 1
 */
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "engine_id")
    private Engine engineId;
    @ManyToMany
    @JoinTable(name = "history_owner",
            joinColumns = {@JoinColumn(name = "owner_id", nullable = false)},
            inverseJoinColumns = {@JoinColumn(name = "car_id", nullable = false)})
    private List<Owner> owners = new ArrayList<>();
}