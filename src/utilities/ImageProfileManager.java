package utilities;

import java.util.List;
import javax.swing.ImageIcon;

public class ImageProfileManager {
    
    public static final String IMAGE_DIRECTORIES = "resources/images/profile_icon/";
    
    public static final List<String> PROFILE_IMAGES = List.of(
        "beardedman", "bussinessman", "girl", "housekeeper", "user", "youngman"
    );
    
    public static ImageIcon getProfilePicture(final String name) {
        if (PROFILE_IMAGES.contains(name)) {
            return ImageLoader.getImage(IMAGE_DIRECTORIES + name + ".png");
        }
        return ImageLoader.getImage(IMAGE_DIRECTORIES + "user.png");
    }
}
