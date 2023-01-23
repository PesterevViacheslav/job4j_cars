# job4j_cars
Приложение "Прототип сайта по продаже машин".

## Описание проекта
- На сайте размещаются объявления.
- В объявлении присутствует: описание, марка машины, тип кузова, фото.
- Объявление имеет статус продано или нет.

## Стек технологий
- Java 17
- Hibernate 5.5.3
- PostgreSQL 14
- Maven 3.8
- Spring boot 2.7
- Bootstrap 4.4

## Требования к окружению
- JDK 17
- Maven
- PostgreSQL

## Запуск проекта
- ```git clone git@github.com/PesterevViacheslav/job4j_cars.git```
- Postgres. ```create database cars;```
- Прописать креды в ```src/main/resources/db.properties```
- ```mvn install```
- перейти по [http://localhost:8080/cars]

## Взаимодействие с приложением