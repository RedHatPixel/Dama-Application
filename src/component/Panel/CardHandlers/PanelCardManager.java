package component.Panel.CardHandlers;

import java.awt.*;
import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public abstract class PanelCardManager extends JPanel implements CardLayoutManager {
    private final Map<CardPanelRegistry, String> cardPanels = new HashMap<>();
    
    protected PanelCardManager() {
        CardLayoutInstances.registerInstance(this.getClass(), (CardLayoutManager) this);
        this.setLayout(new CardLayout());
    }
    
    @Override
    public void registerPanel(final CardPanelRegistry panelClass, final String name) {
        if (cardPanels.containsKey(panelClass))
            throw new IllegalArgumentException("Panel class already registered: " + panelClass.getName());
        cardPanels.put(panelClass, name);
    }

    @Override
    public void showPanel(final CardPanelRegistry panelClass) {
        final String name = cardPanels.get(panelClass);
        if (name == null)
            throw new IllegalArgumentException("Panel class not registered: " + panelClass.getName());
        CardLayout card = (CardLayout) this.getLayout();
        card.show(this, name);
    }

    @Override
    public String getPanelName(final CardPanelRegistry panelClass) {
        return cardPanels.get(panelClass);
    }
}
