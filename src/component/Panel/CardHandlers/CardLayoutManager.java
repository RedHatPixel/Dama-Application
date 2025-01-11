package component.Panel.CardHandlers;

import java.util.HashMap;
import java.util.Map;

public interface CardLayoutManager {
    static final boolean DESIGN_TIME = java.beans.Beans.isDesignTime();
    
    // Registers a panel with the CardLayoutManager
    void registerPanel(final CardPanelRegistry panelClass, final String name);

    // Displays the panel associated with the specified class
    void showPanel(final CardPanelRegistry panelClass);

    // Gets the name associated with a panel class
    String getPanelName(final CardPanelRegistry panelClass);

    // Checks if a specific subclass is initialized
    static boolean isInstanced(final Class<? extends CardLayoutManager> clazz) {
        return CardLayoutInstances.isInstanced(clazz);
    }

    // Gets the instance of the CardLayoutManager subclass
    static <T extends CardLayoutManager> T getInstance(final Class<T> clazz) {
        return CardLayoutInstances.getInstance(clazz);
    }
    
    // Internal helper to manage instances
    final class CardLayoutInstances {
        private static final Map<Class<? extends CardLayoutManager>, CardLayoutManager> INSTANCES = new HashMap<>();
        
        static <T extends CardLayoutManager> T getInstance(final Class<T> clazz) {
            final CardLayoutManager instance = INSTANCES.get(clazz);
            if (instance != null) return clazz.cast(instance);
            throw new IllegalStateException(clazz.getName() + " instance not initialized yet.");
        }

        static boolean isInstanced(final Class<? extends CardLayoutManager> clazz) {
            return INSTANCES.containsKey(clazz);
        }

        static void registerInstance(final Class<? extends CardLayoutManager> clazz, final CardLayoutManager instance) {
            INSTANCES.put(clazz, instance);
        }
    }
}
