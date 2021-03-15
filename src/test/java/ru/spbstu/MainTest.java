package ru.spbstu;
import org.junit.Test;
import static org.junit.Assert.*;

public class MainTest {
    @Test public void testMainHasGreeting() {
        Main app = new Main();
        assertNotNull("Main app should have a greeting", app.getGreeting());
    }
}
