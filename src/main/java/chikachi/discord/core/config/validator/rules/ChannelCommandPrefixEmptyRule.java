package chikachi.discord.core.config.validator.rules;

import chikachi.discord.core.config.Configuration;
import chikachi.discord.core.config.validator.IConfigurationValidationRule;
import chikachi.discord.core.config.validator.ValidationResult;
import com.google.common.base.Joiner;

import java.util.Map;

public class ChannelCommandPrefixEmptyRule implements IConfigurationValidationRule {
    private String getHint() {
        return "For at least one channel there is 'canExecuteCommands' set to true and the 'commandPrefix' is set to an empty string. " +
            "You should always specify a prefix.";
    }

    @Override
    public ValidationResult validate() {
        Long[] invalidElements = Configuration.getConfig().discord.channels.channels
            .entrySet()
            .stream()
            .filter(entry -> entry.getValue() != null && entry.getKey() != null)
            .filter(entry -> entry.getValue().canExecuteCommands)
            .filter(entry -> entry.getValue().commandPrefix != null)
            .filter(entry -> entry.getValue().commandPrefix.trim().equals(""))
            .map(Map.Entry::getKey)
            .toArray(Long[]::new);

        if (invalidElements.length == 0) {
            return new ValidationResult(true, null);
        } else {
            return new ValidationResult(
                false,
                getHint() + " Channel(s): " + Joiner.on(", ").join(invalidElements)
            );
        }
    }
}
