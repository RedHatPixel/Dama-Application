package utilities;

import javax.swing.*;
import java.awt.*;

public final class Configurations {
    
    // Constructor: Prevent Instantiation
    private Configurations() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated.");
    }
    
    /**
     * Resizes the icon of a given component (e.g., JLabel) to the specified width and height.
     *
     * @param icon      The component with an icon (e.g., JLabel, JButton).
     * @param width     The desired width of the resized icon.
     * @param height    The desired height of the resized icon.
     * @return ImageIcon
     */
    public static ImageIcon resizeIcon(ImageIcon icon, int width, int height) {
        try {
            if (icon != null) {
                // Scale the image
                Image scaledImage = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
                // Set the new resized icon to the component
                return new ImageIcon(scaledImage);
            }
            System.err.println("The component does not have an ImageIcon or its icon is null.");
        }
        catch(Exception e) {
            System.err.println(e.getMessage());
            System.err.println("Something went wrong: Configurations - resizeIcon method");
        }
        return icon;
    }
    
    /**
     * Set the file Directory into an Image
     * @param fileName  The string name of the file directory
     * @return ImageIcon
     */
    public static Image loadImage(String fileName) {
        try {
            // Load an image for the icon
            Image image = Toolkit.getDefaultToolkit().getImage(fileName);
            if (image != null) return image;
            System.err.println("The directory seems can't find an image");
        }
        catch(Exception e) {
            System.err.println(e.getMessage());
            System.err.println("Something went wrong: PropertiesUtils - setTextAsImage method");
        }
        return null;
    }
}
