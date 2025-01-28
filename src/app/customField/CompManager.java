package app.customField;

import java.awt.Component;
import utilities.FontManager;

public class CompManager {
    
    public static void setPoppinsFont(final Component comp, final FontManager.FontName fontName) {
        comp.setFont(FontManager.getFont(
            fontName, FontManager.FontType.POPPINS, comp.getFont().getSize()));
    }
    
    // Constructor: Prevent Instantiation
    private CompManager() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated.");
    }
}
