package utilities;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

public final class CommonConstants {
    
    // JFrame Configuration
    public static final Font DEFAULT_FONT = FontManager.getFont(
            FontManager.FontName.POPPINS_MEDIUM, FontManager.FontType.POPPINS, 12);
    public static final Dimension MAX_SIZE = new Dimension(1200, 700);
    public static final Dimension MIN_SIZE = new Dimension(950, 600);
    public static final Dimension PREFFERED_SIZE = new Dimension(950, 600);

    // Panel Background Colors
    public static final Color NAVIGATOR_BG = new Color(38,37,34);
    public static final Color BODY_BG = new Color(48, 46, 43);

    // Button Color, Padding and Border
    public static final Color BUTTON_BG = new Color(38,37,34);
    public static final Color SHADOW_BG = new Color(29, 28, 26);
    
    // Constructor: Prevent Instantiation
    private CommonConstants() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated.");
    }
}
