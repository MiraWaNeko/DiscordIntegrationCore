package chikachi.discord.core.config.validator.rules;

import chikachi.discord.core.config.Configuration;
import chikachi.discord.core.config.validator.IConfigurationValidationRule;

public class IMCEnabledAndBlacklistEmptyRule implements IConfigurationValidationRule {
    @Override
    public String getHint() {
        return "IMC is enabled and set to blacklist, but the blacklist is empty. Every mod can use IMC.";
    }

    @Override
    public boolean validate() {
        return !(Configuration.getConfig().imc.enabled
            && Configuration.getConfig().imc.mode.equals("blacklist")
            && Configuration.getConfig().imc.list.size() == 0);
    }
}
