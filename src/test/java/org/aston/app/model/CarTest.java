package org.aston.app.model;

import org.aston.app.exception.CarValidationException;
import org.aston.app.validator.CarValidator;
import org.aston.app.validator.ModelValidator;
import org.aston.app.validator.PowerValidator;
import org.aston.app.validator.YearValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;


@DisplayName("Car Class Tests") // Название группы тестов для отображения в отчете JUnit
class CarTest {

    private Car.Builder carBuilder;
    private List<CarValidator> validators = new ArrayList<>(){{
        add(new ModelValidator());
        add(new PowerValidator());
        add(new YearValidator());
    }};

    @BeforeEach
    void setUp() {
        carBuilder = new Car.Builder()
                .setPower(150)
                .setModel("ModelName")
                .setYear(2020);
    }

    @Test
    @DisplayName("Должен успешно построить автомобиль с действительными данными")
    void shouldBuildCarSuccessfully() {
        Car car = carBuilder.build();

        assertEquals(150, car.getPower());
        assertEquals("ModelName", car.getModel());
        assertEquals(2020, car.getYear());
    }

    @Test
    @DisplayName("Следует считать автомобили равными, когда все поля одинаковы")
    void equalsShouldReturnTrueForSameValues() {
        Car c1 = carBuilder.build();
        Car c2 = carBuilder.build();

        assertEquals(c1, c2);
        assertEquals(c1.hashCode(), c2.hashCode());
    }

    @Test
    @DisplayName("Должно возвращать ненулевое и непустое строковое представление.")
    void toStringShouldContainFields() {
        Car car = carBuilder.build();
        String s = car.toString();

        assertNotNull(s);
        assertFalse(s.trim().isEmpty());
    }


    @Test
    @DisplayName("Должен выдавать исключение CarValidationException, когда power не установлено.")
    void failWhenHorsePowerIsEmpty() {
        assertThrows(CarValidationException.class, () ->
            new Car.Builder()
                .setModel("ModelName")
                .setYear(2020)
                .addValidator(validators)
                .build()
        );
    }

    @Test
    @DisplayName("Должен выдавать исключение CarValidationException, если model не установлена.")
    void failWhenModelNameIsEmpty() {
        assertThrows(CarValidationException.class, () ->
            new Car.Builder()
                .setPower(120)
                .setYear(2020)
                .addValidator(validators)
                .build()
        );
    }


    @Test
    @DisplayName("Должен выдавать исключение CarValidationException, если year не установлен.")
    void failWhenProductionYearIsEmpty() {
        assertThrows(CarValidationException.class, () ->
            new Car.Builder()
                .setPower(120)
                .setModel("ModelName")
                .addValidator(validators)
                .build()
        );
    }


    @Test
    @DisplayName("Должен выдавать исключение CarValidationException, когда год выходит за пределы допустимого диапазона.")
    void failWhenProductionYearInvalid() {
        assertThrows(CarValidationException.class, () ->
            new Car.Builder()
                .setPower(120)
                .setModel("ModelName")
                .setYear(1700)
                .addValidator(validators)
                .build()
        );
    }


    @Test
    @DisplayName("Должен выдавать исключение CarValidationException, когда мощность равна нулю")
    void failWhenHorsePowerIsZero() {
        assertThrows(CarValidationException.class, () ->
            new Car.Builder()
                .setPower(0)
                .setModel("ModelName")
                .setYear(2020)
                .addValidator(validators)
                .build()
        );
    }

    @Test
    @DisplayName("Должен выдавать исключение CarValidationException, когда модель содержит только пробелы.")
    void buildShouldFailWhenModelNameIsWhitespaces() {
        assertThrows(CarValidationException.class, () ->
            new Car.Builder()
                .setPower(120)
                .setModel(" ")
                .setYear(2020)
                .addValidator(validators)
                .build()
        );
    }

    @Test
    @DisplayName("Должен выдавать исключение CarValidationException, когда модель равна нулю.")
    void shouldThrowWhenModelIsNull() {
        assertThrows(CarValidationException.class, () ->
                new Car.Builder()
                        .setPower(120)
                        .setModel(null)
                        .setYear(2020)
                        .addValidator(validators)
                        .build()
        );
    }

    @Test
    @DisplayName("Должен выдавать исключение CarValidationException, когда мощность отрицательная")
    void shouldThrowWhenPowerIsNegative() {
        assertThrows(CarValidationException.class, () ->
                new Car.Builder()
                        .setPower(-50)
                        .setModel("ModelName")
                        .setYear(2020)
                        .addValidator(validators)
                        .build()
        );
    }

    @Test
    @DisplayName("Следует реализовать естественный порядок по модели, затем по мощности, затем по году.")
    void testCarNaturalOrdering() {
        // Создание автомобилей для тестирования
        Car c1 = new Car.Builder().setModel("A").setPower(90).setYear(1986).addValidator(validators).build();
        Car c2 = new Car.Builder().setModel("A").setPower(120).setYear(2010).addValidator(validators).build();
        Car c3 = new Car.Builder().setModel("B").setPower(100).setYear(2000).addValidator(validators).build();
        Car c4 = new Car.Builder().setModel("A").setPower(200).setYear(1995).addValidator(validators).build();
        Car c5 = new Car.Builder().setModel("A").setPower(110).setYear(2025).addValidator(validators).build();

        List<Car> cars = new ArrayList<>(List.of(c1, c2, c3, c4, c5)); // Список автомобилей
        cars.sort(null); // Сортировка по умолчанию с использованием естественного порядка Car

        List<Car> expectedOrder = List.of(c1, c5, c2, c4, c3); // Ожидаемый порядок сортировки
        assertEquals(expectedOrder, cars); // Проверка на соответствие ожидаемому порядку
    }

    @Test
    @DisplayName("Должен возвращать false при сравнении с нулем")
    void shouldNotBeEqualToNull() {
        Car car = carBuilder.build();
        assertFalse(car.equals(null));
    }

    @Test
    @DisplayName("Должен возвращать false при сравнении с другим классом")
    void shouldNotBeEqualToDifferentClass() {
        Car car = carBuilder.build();
        Object obj = "Some string";
        assertFalse(car.equals(obj));
    }
}