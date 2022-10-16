package ku.cs.app.services;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.*;

public class DefaultImageHardCode {

    private File destDir;

    public void getDefault() throws IOException, URISyntaxException {
        URL inputUrl = getClass().getResource("/ku/cs/images/default.jpg");
        destDir = new File("data/images/user");
        if (!destDir.exists()) {
            destDir.mkdirs();
        }
        Path target = FileSystems.getDefault().getPath(destDir.getAbsolutePath()+System.getProperty("file.separator")+"default.jpg");
        try {
            Files.copy(Paths.get(inputUrl.toURI()).toFile().toPath(),target, StandardCopyOption.REPLACE_EXISTING);
        } catch (URISyntaxException | IOException e) {
            throw new RuntimeException(e);
        }

//        URL copyPath = getClass().getResource("/ku/cs/images/default.jpg");

//        Path copyPath = Paths.get("src/main/resources/ku/cs/images/default.jpg");
//        destDir = new File("data/images/user");
//        if (!destDir.exists()) {
//            destDir.mkdirs();
//        }
//        String filename = "default.jpg";
//
//        Path target = FileSystems.getDefault().getPath(destDir.getAbsolutePath()+System.getProperty("file.separator") + filename);
//        System.out.println("==========");
//        System.out.println(target);
//        System.out.println(copyPath);
////            Files.copy(Paths.get(copyPath.toURI()).toFile().toPath(),target,StandardCopyOption.REPLACE_EXISTING);
//        Files.copy(copyPath,target,StandardCopyOption.REPLACE_EXISTING);
//        System.out.println("=========");
////            System.out.println(Paths.get(copyPath.toURI()).toFile().toPath());
    }
}
