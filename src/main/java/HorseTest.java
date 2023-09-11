import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.Spy;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class HorseTest {
    @Spy
    Horse horse;
    @Test
    public void whenFirstArgumentInHorseConstructorIsNull() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                horse = new Horse(null, 10.1, 12.2));

        Assertions.assertEquals("Name cannot be null.", exception.getMessage());
    }
    @ParameterizedTest
    @MethodSource("nameArgs")
    public void whenFirstArgumentInHorseConstructorIsBlank(String name) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                horse = new Horse(name, 10.1, 12.2));
        Assertions.assertEquals("Name cannot be blank.", exception.getMessage());
    }
    static Stream<String> nameArgs() {
        return Stream.of("", "\n", "\t", "\r");
    }
    @Test
    public void whenHorseSpeedIsNegative() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                horse = new Horse("someName", -10.1, 12.2));
        Assertions.assertEquals("Speed cannot be negative.", exception.getMessage());
    }
    @Test
    public void whenHorseDistanceIsNegative() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                horse = new Horse("SomeName", 10.1, -12.2));
        Assertions.assertEquals("Distance cannot be negative.", exception.getMessage());
    }
    @Test
    public void getName() {
        Horse horse = new Horse("someName", 10.5, 12.5);
        String name = horse.getName();
        assertEquals("someName", name);
    }
    @Test
    public void getSpeed() {
        Horse horse = new Horse("someName", 10.5, 12.5);
        double speed = horse.getSpeed();
        assertEquals(10.5, speed);
    }
    @Test
    public void getDistance() {
        Horse firstHorse = new Horse("someName", 10.5, 12.5);
        double distance = firstHorse.getDistance();
        assertEquals(12.5, distance);

        Horse secondHorse = new Horse("someName", 10.5);
        double zeroDistance = secondHorse.getDistance();
        assertEquals(0.0, zeroDistance);
    }
    @Test
    public void moveVerifyGetRandomDouble() {
        try(MockedStatic<Horse> mockedRandom = Mockito.mockStatic(Horse.class)) {
            horse = new Horse("SomeName", 10.2, 12.2);
            horse.move();
            mockedRandom.verify(() -> Horse.getRandomDouble(0.2, 0.9));

        }
    }
    @ParameterizedTest
    @ValueSource(doubles = {0.5})
    public void move(double random) {
        try(MockedStatic<Horse> horseMockedStatic = Mockito.mockStatic(Horse.class)) {
            horseMockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(random);
            horse = new Horse("SomeName", 10.2, 12.2);
            double resultDistance = horse.getDistance() + horse.getSpeed() * Horse.getRandomDouble(0.2, 0.9);
            horse.move();

            assertEquals(resultDistance, horse.getDistance());
        }

    }

}
