package chikachi.discord.core.config.validator.rules;

import chikachi.discord.core.config.Configuration;
import chikachi.discord.core.config.discord.DiscordChannelConfig;
import chikachi.discord.core.config.validator.IConfigurationValidationRule;
import chikachi.discord.core.config.validator.ValidationResult;
import com.google.common.base.Joiner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ChannelCommandPrefixEmptyRule implements IConfigurationValidationRule {
    private String getHint() {
        return "For at least one channel there is 'canExecuteCommands' set to true and the 'commandPrefix' is set to an empty string. " +
            "You should always specify a prefix.";
    }

    @Override
    public ValidationResult validate() {
        List<Long> list = new ArrayList<>();
        for (Map.Entry<Long, DiscordChannelConfig> entry : Configuration.getConfig().discord.channels.channels.entrySet()) {
            if (entry.getValue().canExecuteCommands
                && entry.getValue().commandPrefix != null
                && entry.getValue().commandPrefix.trim().equals("")
                ) {
                Long key = entry.getKey();
                list.add(key);
            }
        }

        if (list.size() == 0) {
            return new ValidationResult(true, null);
        } else {
            return new ValidationResult(
                false,
                getHint() + " Channel(s): " + Joiner.on(", ").join(list)
            );
        }
    }
}
