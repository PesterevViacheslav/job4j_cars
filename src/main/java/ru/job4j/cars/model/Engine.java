package ru.job4j.cars.model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.persistence.*;
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "engine")
/**
 * Class Engine - Двигатель.
 * Решение задач уровня Middle. Части 3.3. Hibernate.
 * Модели и связи. Машины и владельцы [#4744]
 *
 * @author Viacheslav Pesterev (pesterevvv@gmail.com)
 * @since 11.04.2023
 * @version 1
 */
public class Engine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id;
    private String name;
}