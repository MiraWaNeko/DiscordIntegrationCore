package chikachi.discord.core.test.config.validator.rules;

import chikachi.discord.core.config.Configuration;
import chikachi.discord.core.config.discord.DiscordChannelConfig;
import chikachi.discord.core.config.validator.ValidationResult;
import chikachi.discord.core.config.validator.rules.ChannelCommandPrefixEmptyRule;
import chikachi.discord.core.test.config.AbstractConfigurationTest;
import org.junit.*;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

@RunWith(Enclosed.class)
public class ChannelCommandPrefixEmptyRuleTest {
    // Use enclosed to ignore @Before of the ValueTests
    public static class EmptyChannelList extends AbstractConfigurationTest {
        @Test
        public void canValidateEmptyChannelList() {
            ValidationResult validate = new ChannelCommandPrefixEmptyRule().validate();
            Assert.assertTrue(validate.successful);
        }
    }

    public static class ValueTests extends AbstractConfigurationTest {
        private final ChannelCommandPrefixEmptyRule rule = new ChannelCommandPrefixEmptyRule();

        private DiscordChannelConfig testConfig;

        @Before
        public void setUpInternal() throws Exception {
            testConfig = new DiscordChannelConfig();
            testConfig.fillFields();

            Configuration.getConfig().discord.channels.channels.put(
                123456789L,
                testConfig
            );
        }

        @After
        public void tearDownInternal() throws Exception {
            testConfig = null;
        }

        @Test
        public void expectValidWithTrueAndPrefix() {
            testConfig.commandPrefix = "!";
            testConfig.canExecuteCommands = true;
            Assert.assertTrue(rule.validate().successful);
        }

        @Test
        public void expectValidWithFalseAndEmptyPrefix() {
            testConfig.commandPrefix = "";
            testConfig.canExecuteCommands = false;
            Assert.assertTrue(rule.validate().successful);
        }

        @Test
        public void expectInvalidWithTrueAndEmptyPrefix() {
            testConfig.commandPrefix = "";
            testConfig.canExecuteCommands = true;

            ValidationResult validate = rule.validate();
            Assert.assertFalse(validate.successful);
            Assert.assertTrue(validate.hint.contains("Channel(s): 123456789"));
        }

        @Test
        public void invalidWithGenericSettings() {
            testConfig.canExecuteCommands = true;
            Configuration.getConfig().discord.channels.generic.commandPrefix = "";

            ValidationResult validate = rule.validate();
            Assert.assertFalse(validate.successful);
            Assert.assertTrue(validate.hint.contains("Channel(s): 123456789"));
        }
    }
}
