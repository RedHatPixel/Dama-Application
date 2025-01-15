package com.dama.gameGui;

import java.awt.Image;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.ImageIcon;

public final class GuiUtils {

    // Constructor: Prevent Instantiation
    private GuiUtils() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated.");
    }

    // Static Variables
    private static final String FILE_DIRECTORY = "src/resources/images/pieces/";
    private static final List<Integer> SPECIFIED_SIZES = 
            Arrays.asList(10, 20, 30, 40, 50, 60, 70, 80, 
                    90, 100, 110, 120, 130,140, 150, 160);
    private static final Map<String, ImageIcon> IMAGE_CACHE = new HashMap<>();
    private static final Map<String, ImageIcon> IMAGE_SIZE_CACHE = new HashMap<>();
    
    /**
     * Get original Image from IMAGE_CACHE.
     * If the image does not exist, it will try to load it.
     * @param name String
     * @return ImageIcon
     */
    public static ImageIcon getImage(final String name) {
        return IMAGE_CACHE.computeIfAbsent(name, GuiUtils::loadImage);
    }

    /**
     * Get resized ImageIcon with the closest specified size from IMAGE_SIZE_CACHE.
     * If the resized image does not exist, it will create one.
     * @param name String
     * @param requestedSize int
     * @return ImageIcon
     */
    public static ImageIcon getResizedImage(final String name, final int requestedSize) {
        final int closestSize = findClosestSize(requestedSize);
        final String cacheKey = name + "_" + closestSize;
        return IMAGE_SIZE_CACHE.computeIfAbsent(cacheKey, (key) -> {
            return loadAndResizeImage(name, closestSize);
        });
    }

    /**
     * Find the closest size from the specified sizes.
     * @param requestedSize int
     * @return int
     */
    private static int findClosestSize(final int requestedSize) {
        int nearest = SPECIFIED_SIZES.get(0);
        int smallestDifference = Math.abs(nearest - requestedSize);
        for (final int size : SPECIFIED_SIZES) {
            final int currentDifference = Math.abs(size - requestedSize);
            if (currentDifference < smallestDifference) {
                nearest = size;
                smallestDifference = currentDifference;
            }
        }
        return nearest;
    }

    /**
     * Load and resize the image to the specified size.
     * @param name String
     * @param size int
     * @return ImageIcon
     */
    private static ImageIcon loadAndResizeImage(final String name, final int size) {
        try {
            final ImageIcon original = getImage(name);
            if (original == null) return null;
            final Image resizedImage = original.getImage().getScaledInstance(size, size, Image.SCALE_SMOOTH);
            return new ImageIcon(resizedImage);
        } catch (Exception e) {
            System.err.println("Failed to load or resize image for: " + FILE_DIRECTORY + name + ".png");
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
            return new ImageIcon(FILE_DIRECTORY + name + ".png");
        } catch (Exception e) {
            System.err.println("Failed to load image for: " + FILE_DIRECTORY + name + ".png");
            e.printStackTrace();
            return null;
        }
    }
}
