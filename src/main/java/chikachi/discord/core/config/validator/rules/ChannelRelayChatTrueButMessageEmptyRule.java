package chikachi.discord.core.config.validator.rules;

import chikachi.discord.core.config.Configuration;
import chikachi.discord.core.config.validator.IConfigurationValidationRule;

public class ChannelRelayChatTrueButMessageEmptyRule implements IConfigurationValidationRule {
    @Override
    public String getHint() {
        return "For at least one channel there is 'relayChat' set to true but the 'messages.chatMessage' entry is empty. " +
            "Either set 'relayChat' to false or set a value to 'messages.chatMessage' (e.g. '[{USER}] {MESSAGE}').";
    }

    @Override
    public boolean validate() {
        return Configuration.getConfig().discord.channels.channels.values()
            .stream()
            .filter(discordChannelConfig -> !discordChannelConfig.relayChat.isDisabled())
            .noneMatch(discordChannelConfig -> discordChannelConfig.messages.chatMessage.normal.equals(""));
    }
}
