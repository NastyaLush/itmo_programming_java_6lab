package test.laba.common.commands;


import test.laba.common.exception.VariableException;

/**
 * abstract class, contains getters and setters
 */
public abstract class AbstractCommand implements AbstractCommandInterface {
    private final String description;
    private final String name;
    protected AbstractCommand(String name, String description) {
        this.name = name;
        this.description = description;
    }
    @Override
    public String toString() {
        return "AbstructCommand{"
                + "description='" + description + '\''
                + ", name='" + name + '\''
                + '}';
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }
    public abstract String execute(String arguments, Root root);
}
