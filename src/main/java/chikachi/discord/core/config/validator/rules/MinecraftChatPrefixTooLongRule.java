package chikachi.discord.core.config.validator.rules;

import chikachi.discord.core.config.Configuration;
import chikachi.discord.core.config.validator.IConfigurationValidationRule;

public class MinecraftChatPrefixTooLongRule implements IConfigurationValidationRule {

    public static final int MAX_LEN = 30;

    @Override
    public String getHint() {
        return String.format("At least one dimension has a 'chatPrefix' that is longer than %d characters." +
            " You should trim it to a shorter value.", MAX_LEN);
    }

    @Override
    public boolean validate() {
        return (Configuration.getConfig().minecraft.dimensions.generic.chatPrefix.length() <= MAX_LEN)
            && Configuration.getConfig().minecraft.dimensions.dimensions.values().stream()
            .allMatch(dim -> dim.chatPrefix.length() <= MAX_LEN);
    }
}
