package utilities;

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
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
    
    private static final Map<String, String[]> PoppinsFile = Map.of(
        "Poppins", new String[] {
            "Poppins-Black.ttf", "Poppins-Bold.ttf", "Poppins-ExtraBold.ttf",
            "Poppins-ExtraLight.ttf", "Poppins-Light.ttf", "Poppins-Medium.ttf",
            "Poppins-Regular.ttf", "Poppins-SemiBold.ttf", "Poppins-Thin.ttf"
        }
    );
    
    public static enum FontType {
        POPPINS(loadFontsFromFolder("Poppins", PoppinsFile.get("Poppins")));
        
        private final Map<String, Font> FONTS;
        private FontType(final Map<String, Font> fonts) {
            this.FONTS = fonts;
        }
        
        private Map<String, Font> getFontList() {
            return this.FONTS;
        }
    }

    // Loaders
    public static Map<String, Font> loadFontsFromFolder(final String fontType, final String[] fontFiles) {
        final HashMap<String, Font> fontsFolder = new HashMap<>();

        try {
            final ClassLoader classLoader = FontManager.class.getClassLoader();
            final String fontPath = "resources/fonts/" + fontType + "/";

            for (final String fontFileName : fontFiles) {
                try (final InputStream is = classLoader.getResourceAsStream(fontPath + fontFileName)) {
                    if (is == null) {
                        System.err.println("Font not found: " + fontPath + fontFileName);
                        continue;
                    }

                    Font font = Font.createFont(Font.TRUETYPE_FONT, is);
                    font = font.deriveFont(Font.PLAIN, 12);
                    final String fontName = font.getFontName();

                    fontsFolder.put(fontName, font);
                    GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(font);
                } catch (Exception e) {
                    System.err.println("Failed to load font: " + fontFileName + " - Using default font.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
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