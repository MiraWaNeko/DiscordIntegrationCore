package chikachi.discord.core;

import java.util.HashMap;
import java.util.Map;

public class TextFormatter {
    private HashMap<String, String> arguments = new HashMap<>();

    public TextFormatter() {
    }

    public TextFormatter(HashMap<String, String> arguments) {
        this.addArguments(arguments);
    }

    public TextFormatter addArgument(String key, String value) {
        this.arguments.put(key, value);
        return this;
    }

    public TextFormatter addArguments(HashMap<String, String> newArguments) {
        this.arguments.putAll(newArguments);
        return this;
    }

    public TextFormatter clearArguments() {
        this.arguments.clear();
        return this;
    }

    public String format(String message) {
        for (Map.Entry<String, String> entry : this.arguments.entrySet()) {
            if (entry == null || entry.getKey() == null || entry.getValue() == null) {
                continue;
            }
            message = message.replace("{" + entry.getKey() + "}", entry.getValue());
        }
        return message;
    }
}
