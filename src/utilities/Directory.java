package utilities;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;

public final class Directory {
    
    private static List<Component> parents = new ArrayList<>();
    
    public static void insertParent(Component component) { 
        
       parents.add(component);
    }
    
    public static JPanel getComponent(ParentName parentName, Panel panelName) {
        
        for (Component parent : parents) {
            
            if (parent.getName().equals(parentName.getName())) {
                
                if (parent instanceof JFrame jFrame) {
                    if (jFrame == null)  return null;
                
                    for (Component comp : jFrame.getComponents()) {

                        JPanel panel = (JPanel) comp;

                        if (panelName.getName().equals(panel.getName()))
                            return panel;
                    
                    }
                }
                
                if (parent instanceof JPanel jPanel) {
                    if (jPanel == null)  return null;
                
                    for (Component comp : jPanel.getComponents()) {

                        JPanel panel = (JPanel) comp;

                        if (panelName.getName().equals(panel.getName()))
                            return panel;
                    
                    }
                }
            }
        }
        
        System.err.println("Cannot find the component for " + panelName.getName());
        return null;
    }
    
    public static JPanel getComponent(JFrame parent, Panel panelName) {

        for (Component comp : parent.getComponents()) {

            JPanel panel = (JPanel) comp;

            if (panelName.getName().equals(panel.getName()))
                return panel;
        }
        
        System.err.println("Cannot find the component for " + panelName.getName());
        return null;
    }
    
    public static Component getParent(ParentName parentName) {
        
        for (Component parent : parents) {
            
            if (parentName.getName().equals(parent.getName()))
                return parent;
        }
        
        System.err.println("Cannot find the parent for " + parentName.getName());
        return null;
    }
    
    public enum ParentName {
        MAIN_FRAME("MainFrame");
        
        private String name;
        
        ParentName(String name) {
            this.name = name;
        }
        
        public String getName() {
            return name;
        }
    }
    
    public enum Panel {
        MAIN_MENU("MainMenu"),
        TUTORIAL("Tutorial"),
        GAME_PLAY("Game Play");
        
        private String name;
        
        Panel(String name) {
            this.name = name;
        }
        
        public String getName() {
            return name;
        }
    }
}
