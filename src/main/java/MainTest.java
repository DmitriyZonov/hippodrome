import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import static java.util.concurrent.TimeUnit.SECONDS;

public class MainTest {

    @Disabled
    @Test
    @Timeout(value = 22, unit = SECONDS)
    public void mainTimeTest() {
        String[] args = null;
        try {
            Main.main(args);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
