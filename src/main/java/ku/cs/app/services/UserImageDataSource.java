package ku.cs.app.services;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class UserImageDataSource {
    //-------------------------------------------- instance

    private File filePath;
    private String imagePath;
    String fs = File.separator ;

    //-------------------------------------------- method

    public String chooseImage(String fileName){

        String userDirectoryString = System.getProperty("user.home") + "\\Pictures";
        File userDirectory = new File(userDirectoryString);

        if (!userDirectory.canRead()) {
            userDirectory = new File("c:/");
        }
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(userDirectory);
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("images PNG JPG", "*.png", "*.jpg", "*.jpeg"));
        this.filePath = fileChooser.showOpenDialog(new Stage());

        try {
            File destDir = new File("data/images");
            if (!destDir.exists()) {
                destDir.mkdirs();
            }

            if(filePath!=null) {
                //Rename
                String[] fileSplit = filePath.getName().split("\\.");
                String filename = fileName + "." + fileSplit[fileSplit.length - 1];
                Path target = FileSystems.getDefault().getPath(destDir.getAbsolutePath() + System.getProperty("file.separator") + filename);

                //ก็อบรูปที่เลือกมาใส่ไว้ในไฟล์
                Files.copy(filePath.toPath(), target, StandardCopyOption.REPLACE_EXISTING);
                return filename;

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "rickroll.gif";
    }

}
