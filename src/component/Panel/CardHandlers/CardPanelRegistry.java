package component.Panel.CardHandlers;

import java.util.HashMap;
import java.util.Map;
import javax.swing.*;

/**
 * Represents a registry for card panels that ensures only one instance
 * of each panel class exists and handles initialization and management.
 */
public abstract class CardPanelRegistry extends JPanel {
    private static final Map<Class<? extends CardPanelRegistry>, CardPanelRegistry> INSTANCES = new HashMap<>();
    private static final boolean DESIGN_TIME = java.beans.Beans.isDesignTime();

    /**
     * Protected constructor to initialize and register the singleton instance.
     * Skip singleton enforcement during design time
     */
    protected CardPanelRegistry() {
        if (!DESIGN_TIME && INSTANCES.containsKey(this.getClass())) {
            throw new IllegalStateException("Instance of " + this.getClass().getName() + " already exists.");
        }
        else if (!DESIGN_TIME)
            INSTANCES.put(this.getClass(), (CardPanelRegistry) this);
    }

    /**
     * Retrieves the singleton instance of a specific panel class.
     *
     * @param clazz The class of the panel to retrieve.
     * @param <T>   The type of the panel.
     * @return The singleton instance of the specified panel class.
     * @throws IllegalStateException if the instance is not initialized yet.
     */
    public static synchronized <T extends CardPanelRegistry> T getInstance(final Class<T> clazz) {
        final CardPanelRegistry instance = INSTANCES.get(clazz);
        if (instance != null) return clazz.cast(instance);
        throw new IllegalStateException(clazz.getName() + " instance not initialized yet.");
    }

    /**
     * Checks if the singleton instance of a specific panel class is initialized.
     *
     * @param clazz The class of the panel to check.
     * @param <T>   The type of the panel.
     * @return True if the instance is initialized; false otherwise.
     */
    public static <T extends CardPanelRegistry> boolean isInstanced(final Class<T> clazz) {
        return INSTANCES.containsKey(clazz);
    }

    /**
     * Performs any initialization logic specific to the panel.
     * Subclasses should call this method to redefine their behavior.
     */
    public void initialize() {
        this.setName(getPanelName());
        configurePanel();
    }

    /**
     * Configures the panel settings during initialization.
     * To be implemented by subclasses for specific configurations.
     */
    protected abstract void configurePanel();

    /**
     * Retrieves the unique name of the panel.
     * To be implemented by subclasses for providing the panel's name.
     *
     * @return The name of the panel.
     */
    public abstract String getPanelName();
}
