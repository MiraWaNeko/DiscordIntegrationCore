package chikachi.discord.core.config.validator.rules;

import chikachi.discord.core.config.Configuration;
import chikachi.discord.core.config.validator.IConfigurationValidationRule;

import java.util.ArrayList;

public class ChannelDescriptionsEnabledButEmptyRule implements IConfigurationValidationRule {
    @Override
    public String getHint() {
        return "For at least one channel there is 'updateDescription' set to true but the 'descriptions' list is empty. " +
            "Either set 'updateDescription' to false or add values to the 'descriptions' list.";
    }

    @Override
    public boolean validate() {
        return Configuration.getConfig().discord.channels.channels.values()
            .stream()
            .filter(discordChannelConfig -> discordChannelConfig.updateDescription)
            .noneMatch(discordChannelConfig -> discordChannelConfig.descriptions.size() == 0);
    }
}
