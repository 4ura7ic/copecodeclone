package ku.cs.app.services;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.*;

public class DefaultImageHardCode {

    private File destDir;

    public void getDefault() {
        Path src = Paths.get("classes/ku/cs/defaultImage/default.jpg");
        destDir = new File("data/images/user");
        if (!destDir.exists()) {
            destDir.mkdirs();
            Path target = Paths.get("data/images/user/default.jpg");

            try{
                Files.copy(src,target,StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        destDir = new File("data/images/admin");
        if (!destDir.exists()){
            destDir.mkdirs();
            Path target = Paths.get("data/images/admin/default.jpg");

            try{
                Files.copy(src,target,StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        destDir = new File("data/images/officer");
        if (!destDir.exists()){
            destDir.mkdirs();
            Path target = Paths.get("data/images/officer/default.jpg");

            try{
                Files.copy(src,target,StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        destDir = new File("data/images/reportImage");
        if (!destDir.exists()){
            destDir.mkdirs();
            Path target = Paths.get("data/images/reportImage/default.jpg");

            try{
                Files.copy(src,target,StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
