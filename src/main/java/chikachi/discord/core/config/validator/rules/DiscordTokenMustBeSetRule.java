package chikachi.discord.core.config.validator.rules;

import chikachi.discord.core.config.Configuration;
import chikachi.discord.core.config.validator.IConfigurationValidationRule;

public class DiscordTokenMustBeSetRule implements IConfigurationValidationRule {
    @Override
    public String getHint() {
        return "You must add a Discord token in order to use the bot.";
    }

    @Override
    public boolean validate() {
        return Configuration.getConfig().discord.token != null && !Configuration.getConfig().discord.token.equals("");
    }
}
