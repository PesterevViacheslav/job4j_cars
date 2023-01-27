package ru.job4j.cars.model;
import lombok.Data;
import javax.persistence.*;
/**
 * Class User - Пользователь. Решение задач уровня Middle.
 * Категория : 3.3. HibernateТема Тема : 3.3.3. Mapping
 *
 * @author Viacheslav Pesterev (pesterevvv@gmail.com)
 * @since 27.01.2023
 * @version 1
 */
@Data
@Entity
@Table(name = "auto_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "login")
    private String login;
    @Column(name = "password")
    private String password;
}