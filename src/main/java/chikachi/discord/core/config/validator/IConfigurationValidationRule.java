package chikachi.discord.core.config.validator;

public interface IConfigurationValidationRule {
    /**
     * @return A hint for the user what he should change in his configuration.
     */
    String getHint();

    /**
     * @return true, if the config is valid in the view of this rule
     */
    boolean validate();
}
