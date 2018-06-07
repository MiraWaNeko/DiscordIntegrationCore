package chikachi.discord.core.config.validator.rules;

import chikachi.discord.core.config.Configuration;
import chikachi.discord.core.config.validator.IConfigurationValidationRule;
import chikachi.discord.core.config.validator.ValidationResult;
import com.google.common.base.Joiner;

import java.util.Map;

public class ChannelRelayChatTrueButMessageEmptyRule implements IConfigurationValidationRule {
    private String getHint() {
        return "For at least one channel there is 'relayChat' set to true but the 'messages.chatMessage' entry is empty. " +
            "Either set 'relayChat' to false or set a value to 'messages.chatMessage' (e.g. '[{USER}] {MESSAGE}').";
    }

    @Override
    public ValidationResult validate() {
        Object[] channelIDs = Configuration.getConfig().discord.channels.channels.entrySet()
            .stream()
            .filter(entry -> entry.getValue() != null && entry.getKey() != null)
            .filter(entry -> !entry.getValue().relayChat.isDisabled())
            .filter(entry -> entry.getValue().messages.chatMessage.normal.equals(""))
            .map(Map.Entry::getKey)
            .toArray();

        if (channelIDs.length == 0) {
            return new ValidationResult(true, null);
        } else {
            return new ValidationResult(
                false,
                getHint() + " Channel(s): " + Joiner.on(", ").join(channelIDs)
            );
        }
    }
}
