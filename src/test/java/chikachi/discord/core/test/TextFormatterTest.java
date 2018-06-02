package chikachi.discord.core.test;

import chikachi.discord.core.TextFormatter;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class TextFormatterTest {
    private String testString = "test {TEST} {USER}";

    @Test
    public void canBeConstructedWithArguments() {
        HashMap<String, String> hm = new HashMap<>();
        hm.put("TEST", "success");

        String result = new TextFormatter(hm)
            .addArgument("USER", "testee")
            .format(testString);
        assertEquals("test success testee", result);

        // The input-map should not be modified when we add something
        assertEquals(1, hm.size());
    }

    @Test
    public void inputAndOutputShouldBeEqual() {
        assertEquals(testString, new TextFormatter().format(testString));
    }

    @Test
    public void textShouldBeReplacedCorrectly() {

        String result = new TextFormatter()
            .addArgument("TEST", "success")
            .format(testString);
        assertEquals("test success {USER}", result);

        result = new TextFormatter()
            .addArgument("USER", "testee")
            .format(testString);
        assertEquals("test {TEST} testee", result);

        result = new TextFormatter()
            .addArgument("TEST", "success")
            .addArgument("USER", "testee")
            .format(testString);
        assertEquals("test success testee", result);
    }

    @Test
    public void argumentsShouldBeCleaned() {
        TextFormatter tf = new TextFormatter();
        tf.addArgument("TEST", "success");
        assertEquals("test success {USER}", tf.format(testString));

        tf.clearArguments();
        assertEquals(testString, tf.format(testString));

        tf.addArgument("USER", "testee");
        assertEquals("test {TEST} testee", tf.format(testString));
    }
}
