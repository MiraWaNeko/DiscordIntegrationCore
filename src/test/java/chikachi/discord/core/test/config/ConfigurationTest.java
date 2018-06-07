package chikachi.discord.core.test.config;

import chikachi.discord.core.CoreConstants;
import chikachi.discord.core.config.Configuration;
import chikachi.discord.core.config.discord.DiscordChannelConfig;
import chikachi.discord.core.config.validator.ValidationResult;
import chikachi.discord.core.config.validator.rules.ChannelCommandPrefixEmptyRule;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ConfigurationTest extends AbstractConfigurationTest {
    @Test
    public void shouldCreateNewConfigurationFiles() {
        File[] files = folder.getRoot().listFiles();

        Assert.assertNotNull(files);
        Assert.assertEquals(2, files.length);

        List<String> list = new ArrayList<>();
        for (File file : files) {
            String name = file.getName();
            list.add(name);
        }

        Assert.assertTrue(list.contains(CoreConstants.MODID + ".json"));
        Assert.assertTrue(list.contains(CoreConstants.MODID + "_links.json"));
    }

    @Test
    public void shouldCreateDefaultConfigurationObjects() {
        Assert.assertNotNull(Configuration.getConfig());
        Assert.assertNotNull(Configuration.getLinking());
    }

    @Test
    public void shouldBeAbleToSaveAndReloadChanges() {
        String randomToken = UUID.randomUUID().toString();
        Configuration.getConfig().discord.token = randomToken;

        Configuration.saveConfig();
        // Clear the token so we can validate that the value has been loaded from the file
        Configuration.getConfig().discord.token = "";

        Configuration.loadConfig();
        Assert.assertEquals(randomToken, Configuration.getConfig().discord.token);
    }

    @Test
    public void shouldCreateDefaultConfigurationOnInvalidFile() throws IOException {
        File[] files = folder.getRoot().listFiles((dir, name) -> name.equals(CoreConstants.MODID + ".json"));

        Assert.assertNotNull(files);
        Assert.assertTrue(files.length > 0);

        File file = files[0];
        try (FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write("INVALID");
            fileWriter.flush();
        }

        Configuration.loadConfig();

        Assert.assertNotNull(Configuration.getConfig());
    }

    @Test
    public void shouldCreateDefaultChannelValues() {
        Configuration.getConfig().discord.channels.channels.put(123L, new DiscordChannelConfig());
        Configuration.getConfig().discord.channels.fillFields();

        DiscordChannelConfig discordChannelConfig = Configuration.getConfig().discord.channels.channels.get(123L);
        Assert.assertNotNull(discordChannelConfig.messages);
        Assert.assertNotNull(discordChannelConfig.commands);

        Assert.assertNotNull(discordChannelConfig.webhook);
        // Boolean
        Assert.assertNotNull(discordChannelConfig.relayChat);

        // Description
        Assert.assertNotNull(discordChannelConfig.updateDescription);
        Assert.assertNotNull(discordChannelConfig.descriptions);
    }
}
