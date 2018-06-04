package chikachi.discord.core.config.validator;

import chikachi.discord.core.DiscordIntegrationLogger;
import chikachi.discord.core.config.validator.rules.*;

import java.util.ArrayList;

public class ConfigurationValidator {
    private ArrayList<IConfigurationValidationRule> rules = new ArrayList<>();

    public ConfigurationValidator() {
        this.addRule(new DiscordTokenMustBeSetRule());
        this.addRule(new DuplicatedCommandOrAliasRule());
        this.addRule(new ChannelDescriptionsEnabledButEmptyRule());
        this.addRule(new ChannelRelayChatTrueButMessageEmptyRule());
        this.addRule(new MinecraftChatPrefixTooLongRule());
        this.addRule(new IMCEnabledAndBlacklistEmptyRule());
    }

    public void addRule(IConfigurationValidationRule rule) {
        this.rules.add(rule);
    }

    public void validateAll() {
        int successfulTests = this.rules.size();
        DiscordIntegrationLogger.Log("Validating the configuration..");

        for (IConfigurationValidationRule rule : this.rules) {
            if (!rule.validate()) {
                successfulTests--;
                DiscordIntegrationLogger.Log(String.format("[HINT] %s (%s)", rule.getHint(), rule.getClass().getSimpleName()));
            }
        }

        DiscordIntegrationLogger.Log(String.format(
            "Configuration validated. %d of %d rules were successful.",
            successfulTests,
            this.rules.size()
        ));
    }
}
