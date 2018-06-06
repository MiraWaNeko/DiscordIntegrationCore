package chikachi.discord.core.config.validator.rules;

import chikachi.discord.core.config.Configuration;
import chikachi.discord.core.config.discord.CommandConfig;
import chikachi.discord.core.config.discord.DiscordChannelConfig;
import chikachi.discord.core.config.validator.IConfigurationValidationRule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DuplicatedCommandOrAliasRule implements IConfigurationValidationRule {
    @Override
    public String getHint() {
        return "At least one command or alias is used twice. Please use every command only once. ";
    }

    /**
     * Checks for duplicated commands
     * @return true, if no commands are duplicated
     */
    @Override
    public boolean validate() {
        // Check the global commands first and fill them into the checkMap
        ArrayList<CommandConfig> commands = Configuration.getConfig().discord.channels.generic.commands;
        HashMap<String, Integer> globalCheckMap = new HashMap<>();

        for (CommandConfig commandConfig : commands) {
            if (!validateCommand(globalCheckMap, commandConfig)) {
                return false;
            }
        }

        // Now test the channel configurations.
        for (Map.Entry<Long, DiscordChannelConfig> entry : Configuration.getConfig().discord.channels.channels.entrySet()) {
            HashMap<String, Integer> localCheckMap = new HashMap<>(globalCheckMap);

            for (CommandConfig command : entry.getValue().commands) {
                if (!validateCommand(localCheckMap, command))
                    return false;
            }
        }
        return true;
    }

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    private boolean validateCommand(HashMap<String, Integer> checkMap, CommandConfig command) {
        String name = command.getName();
        int newVal = checkMap.getOrDefault(name, 0) + 1;
        if (newVal > 1)
            return false;

        checkMap.put(name, newVal);

        // Check for aliases
        for (String alias : command.getAliases()) {
            int aliasVal = checkMap.getOrDefault(alias, 0) + 1;
            if (aliasVal > 1)
                return false;
            checkMap.put(alias, aliasVal);
        }
        return true;
    }
}
