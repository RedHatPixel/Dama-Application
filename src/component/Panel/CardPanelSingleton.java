package component.Panel;

import java.util.HashMap;
import java.util.Map;
import javax.swing.JPanel;

public abstract class CardPanelSingleton extends JPanel {
    private static final Map<Class<? extends CardPanelSingleton>, CardPanelSingleton> instances = new HashMap<>();
    
    // Constructor: Abstract implementation
    protected CardPanelSingleton() {
        instances.put(this.getClass(), (CardPanelSingleton) this);
    }
    
    /**
     * Get the instance of the panel
     * @param <Panel>   take the class blueprint
     * @param clazz     find the static class of the card panel
     * @return CardPanelSingleton
     */
    public static synchronized <Panel extends CardPanelSingleton> Panel getInstance(final Class<Panel> clazz) {
        CardPanelSingleton instance = instances.get(clazz);
        if (instance != null)   return clazz.cast(instance);
        throw new IllegalStateException(clazz.getName() + " instance not initialized yet.");
    }
    
    /**
    * Check if the card panel of a specific subclass is initialized
    * @param clazz  The subclass type
    * @param <T>    The type of the subclass
    * @return Boolean
    */
   public static <T extends CardPanelSingleton> boolean isInstanced(final Class<T> clazz) {
       return instances.containsKey(clazz);
   }
    
    /**
     * Initializes the panel after instantiation.
     * This method must be called explicitly after the subclass is instantiated.
     */
    public void initialize() {
        this.setName(getDirectName());
        setDirectName();
    }
    
    /**
     * Set the name of the card panel
     */
    protected abstract void setDirectName();
    
    /**
     * Get the name of the card panel
     * @return String
     */
    public abstract String getDirectName();
}
