package component.Panel;

import java.awt.CardLayout;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JPanel;

public abstract class CardLayoutManager extends JPanel {
    private static final Map<Class<? extends CardLayoutManager>, CardLayoutManager> instances = new HashMap<>();
    private final Map<CardPanelSingleton, String> cardPanels = new HashMap<>();
    
    // Constructor: Abstract implementation
    protected CardLayoutManager() {
        instances.put(this.getClass(), (CardLayoutManager) this);
        this.setLayout(new CardLayout());
    }
    
    /**
     * Get the instance of the panel
     * 
     * @param <Panel>   take the class blueprint
     * @param clazz     find the static class of the card panel
     * @return CardLayoutManager
     */
    public static <Panel extends CardLayoutManager> Panel getInstance(final Class<Panel> clazz) {
        CardLayoutManager instance = instances.get(clazz);
        if (instance != null)   return clazz.cast(instance);
        throw new IllegalStateException(clazz.getName() + " instance not initialized yet.");
    }
    
    /**
     * Check if the card panel of a specific subclass is initialized
     * 
     * @param clazz  The subclass type
     * @param <T>    The type of the subclass
     * @return Boolean
     */
    public static <T extends CardLayoutManager> boolean isInstanced(final Class<T> clazz) {
        return instances.containsKey(clazz);
    }
    
    /**
     * Registers a panel with the CardLayoutManager.
     *
     * @param panelClass  CardPanelSingleton the card singleton to be added
     * @param name       A unique name for the panel in the CardLayout.
     */
    public void registerPanel(final CardPanelSingleton panelClass, final String name) {
        if (cardPanels.containsKey(panelClass))
            throw new IllegalArgumentException("Panel class already registered: " + panelClass.getName());
        cardPanels.put(panelClass, name);
    }
    
    /**
     * Displays the panel associated with the specified class.
     *
     * @param panelClass The class of the panel to display.
     */
    public void showPanel(final CardPanelSingleton panelClass) {
        final String name = cardPanels.get(panelClass);
        if (name == null)
            throw new IllegalArgumentException("Panel class not registered: " + panelClass.getName());
        CardLayout card = (CardLayout) this.getLayout();
        card.show(this, name);
    }

    /**
     * Gets the name associated with a panel class.
     *
     * @param panelClass The class of the panel.
     * @return The name of the panel in the CardLayout.
     */
    public String getPanelName(final CardPanelSingleton panelClass) {
        return cardPanels.get(panelClass);
    }
}
