package utilities;

import java.awt.Image;
import javax.swing.ImageIcon;

public class ImageFiles {
    
    public static final String IMAGE_DIRECTORIES = "src/resources/images/";
    public static final String DAMA_LOGO_DIR = IMAGE_DIRECTORIES + "selection_icon/DamaLogo.png";
    
    public static final String WHITE_SELECTION_DIR = IMAGE_DIRECTORIES + "game_icon/WhiteSelection.png";
    public static final String BLACK_SELECTION_DIR = IMAGE_DIRECTORIES + "game_icon/BlackSelection.png";
    
    public static final String LOSE_ICON_DIR = IMAGE_DIRECTORIES + "result_icon/loseIcon.png";
    public static final String WIN_ICON_DIR = IMAGE_DIRECTORIES + "result_icon/winIcon.png";
    
    public static final String BOLT_DIR = IMAGE_DIRECTORIES + "game_icon/bolt.png";
    public static final String BULLET_DIR = IMAGE_DIRECTORIES + "game_icon/bullet.png";
    public static final String STOP_WATCH_DIR = IMAGE_DIRECTORIES + "game_icon/stopwatch.png";
    public static final String LINK_DIR = IMAGE_DIRECTORIES + "game_icon/link.png";
    
    public static final String CHECK_DIR = IMAGE_DIRECTORIES + "game_icon/checkSetting.png";
    public static final String PREVENT_DIR = IMAGE_DIRECTORIES + "game_icon/preventSetting.png";
    
    public static final String LEARN_DIR = IMAGE_DIRECTORIES + "image_design/learn.png";
    public static final String ABOUT_DIR = IMAGE_DIRECTORIES + "image_design/about.png";
    public static final String LOGINSIGNUP_BG_DIR = IMAGE_DIRECTORIES + "image_design/loginSignUpBg.jpg";
            
    public static final String LOADING_DIR = IMAGE_DIRECTORIES + "gif/Loading.gif";
    
    public static Image DAMA_LOGO;
    public static ImageIcon WHITE_SELECTION;
    public static ImageIcon BLACK_SELECTION;
    public static ImageIcon LOSE_ICON;
    public static ImageIcon WIN_ICON;
    public static ImageIcon BOLT;
    public static ImageIcon BULLET;
    public static ImageIcon STOP_WATCH;
    public static ImageIcon LINK;
    public static ImageIcon CHECK;
    public static ImageIcon PREVENT;
    public static ImageIcon LOADING;
    
    static {
        try {
            DAMA_LOGO = ImageManager.getImage(DAMA_LOGO_DIR).getImage();
            
            WHITE_SELECTION = ImageManager.getResizedImage(WHITE_SELECTION_DIR, 28, 28);
            BLACK_SELECTION = ImageManager.getResizedImage(BLACK_SELECTION_DIR, 28, 28);
            
            LOSE_ICON = ImageManager.getImage(LOSE_ICON_DIR);
            WIN_ICON = ImageManager.getImage(WIN_ICON_DIR);
            
            BOLT = ImageManager.getImage(BULLET_DIR);
            BULLET = ImageManager.getImage(BOLT_DIR);
            STOP_WATCH = ImageManager.getImage(STOP_WATCH_DIR);
            LINK = ImageManager.getImage(LINK_DIR);
            
            CHECK = ImageManager.getImage(CHECK_DIR);
            PREVENT = ImageManager.getImage(PREVENT_DIR);
            
            LOADING = ImageManager.getImage(LOADING_DIR);
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
            System.err.println("Error: ImaageFiles - Can't load image files");
        }
    }
   
}
