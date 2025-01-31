package utilities;

import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import javax.swing.ImageIcon;

public class ImageLoader {
    
    // Constructor: Prevent Instantiation
    private ImageLoader() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated.");
    }
    
    // Static Variables    
    private static final Map<String, ImageIcon> IMAGE_CACHE = new HashMap<>();
    private static final Map<String, ImageIcon> IMAGE_SIZE_CACHE = new HashMap<>();
    
    /**
     * Get original Image from IMAGE_CACHE.
     * If the image does not exist, it will try to load it.
     * @param name String
     * @return ImageIcon
     */
    public static ImageIcon getImage(final String name) {
        return IMAGE_CACHE.computeIfAbsent(name, ImageLoader::loadImage);
    }

    /**
     * Get resized ImageIcon with the closest specified size from IMAGE_SIZE_CACHE.
     * If the resized image does not exist, it will create one.
     * @param name String
     * @param width
     * @param height
     * @return ImageIcon
     */
    public static ImageIcon getResizedImage(final String name, final int width, final int height) {
        final String cacheKey = name + "_" + width + height;
        return IMAGE_SIZE_CACHE.computeIfAbsent(cacheKey, (key) -> {
            return loadAndResizeImage(name, width, height);
        });
    }

    /**
     * Load and resize the image to the specified size.
     * @param name String
     * @param size int
     * @return ImageIcon
     */
    private static ImageIcon loadAndResizeImage(final String name, final int width, final int height) {
        try {
            final ImageIcon original = getImage(name);
            if (original == null) return null;
            final Image resizedImage = original.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
            return new ImageIcon(resizedImage);
        } catch (Exception e) {
            System.err.println("Failed to load or resize image for: " + name + ".png");
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Load the image in its original size.
     * @param name String
     * @return ImageIcon
     */
    private static ImageIcon loadImage(final String name) {
        try {
            final URL imageUrl = ImageLoader.class.getClassLoader().getResource(name);
            if (imageUrl == null) {
                throw new IOException("Image file not found: " + name);
            }
            return new ImageIcon(imageUrl);
        } catch (IOException e) {
            System.err.println("Failed to load image for: " + name);
            e.printStackTrace();
            return null;
        }
    }
}
