import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Spy;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class HippodromeTest {
    @Spy
    Hippodrome hippodrome;

    @Test
    public void whenHorsesIsNull() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                hippodrome = new Hippodrome(null));
        Assertions.assertEquals("Horses cannot be null.", exception.getMessage());
    }
    @Test
    public void whenListOfHorsesIsEmpty() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
        {
            List<Horse> horses = new ArrayList<>();
            hippodrome = new Hippodrome(horses);
        });
        Assertions.assertEquals("Horses cannot be empty.", exception.getMessage());
    }
    @Test
    public void getHorses() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            horses.add(mock(Horse.class));
        }
        hippodrome = new Hippodrome(horses);
        assertEquals(horses, hippodrome.getHorses());
    }
    @Test
    public void move() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            horses.add(mock(Horse.class));
        }
        hippodrome = new Hippodrome(horses);
        hippodrome.move();
        for (Horse tempHorse : horses) {
            verify(tempHorse).move();
        }
    }
    @Test
    public void getWinner() {
        Horse horse1 = new Horse("some1", 10.2, 12.5);
        Horse horse2 = new Horse("some2", 10.2, 12.1);
        Horse horse3 = new Horse("some3", 10.2, 12.3);
        Horse horse4 = new Horse("some4", 10.2, 12.4);
        hippodrome = new Hippodrome(List.of(horse1, horse2, horse3, horse4));

        assertSame(horse1, hippodrome.getWinner());
    }
}
