package utilities;

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public final class FontManager {
    
    // Default Variables
    public static final Font DEFAULT_FONT = new Font("Segoe UI Black", Font.BOLD, 12);
    
    // Font Directory
    private static final String FONTS_LOCATION = "src/resources/fonts/";
    
    // Fonts Enum Names
    public static enum FontName {
        POPPINS_BLACK("Poppins Black"),
        POPPINS_BOLD("Poppins Bold"),
        POPPINS_EXTRABOLD("Poppins ExtraBold"),
        POPPINS_EXTRALIGHT("Poppins ExtraLight"),
        POPPINS_LIGHT("Poppins Light"),
        POPPINS_MEDIUM("Poppins Medium"),
        POPPINS_REGULAR("Poppins Regular"),
        POPPINS_SEMIBOLD("Poppins SemiBold"),
        POPPINS_THIN("Poppins Thin"),;
        
        private final String fontName;

        private FontName(final String fontName) {
            this.fontName = fontName;
        }
        
        private String getFontName() {
            return fontName;
        }
    }
    
    public static enum FontType {
        POPPINS(loadFontsFromFolder("Poppins"));
        
        private final Map<String, Font> FONTS;
        
        private FontType(final Map<String, Font> fonts) {
            this.FONTS = fonts;
        }
        
        private Map<String, Font> getFontList() {
            return this.FONTS;
        }
    }

    // Loader
    public static Map<String, Font> loadFontsFromFolder(final String fontType) {
        final HashMap<String, Font> fontsFolder = new HashMap<>();
        final File folder = new File(FONTS_LOCATION + fontType);
        
        if (folder.isDirectory()) {
            File[] fontFiles = folder.listFiles((dir, name) -> name.toLowerCase().endsWith(".ttf"));

            if (fontFiles != null) {
                for (final File fontFile : fontFiles) {
                    try (final java.io.FileInputStream fis = new java.io.FileInputStream(fontFile)) {
                        
                        // Make the file a font
                        final Font font = Font.createFont(Font.TRUETYPE_FONT, fis);
                        
                        // Get the font file name
                        final String fontName = font.getFontName();
                        
                        // Store the font name and file
                        fontsFolder.put(fontName, font);
                        
                        // Register the font in the local graphic environment
                        GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(font);
                        
                    } catch (Exception e) {
                        System.err.println("Failed to load font: " + fontFile.getName() + " - Using default font.");
                    }
                }
            }
        } 
        
        return fontsFolder;
    }

    // Getters: get the font using fontName [enum FontName] and size
    public static Font getFont(final FontName fontName, final FontType fontType, float size) {
        final Map<String, Font> fontList = fontType.getFontList();
        final Font font = fontList.getOrDefault(fontName.getFontName(), DEFAULT_FONT);
        return font.deriveFont(size);
    }
    
    // Constructor: Prevent Instantiation
    private FontManager() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated.");
    }
}
