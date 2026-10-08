import com.boxing.util.TimeUtil;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TimeUtilTest {
    @Test
    public void zero() {
        assertEquals("00:00", TimeUtil.format(0));
    }

    @Test
    public void underMinute() {
        assertEquals("00:45", TimeUtil.format(45));
    }

    @Test
    public void exactMinute() {
        assertEquals("01:00", TimeUtil.format(60));
    }

    @Test
    public void defaultRound() {
        assertEquals("03:00", TimeUtil.format(180));
    }

    @Test
    public void tenMinutes() {
        assertEquals("10:00", TimeUtil.format(600));
    }
}