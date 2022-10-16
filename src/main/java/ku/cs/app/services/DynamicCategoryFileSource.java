package ku.cs.app.services;

import ku.cs.app.models.UserSuspension;
import ku.cs.app.models.list.DynamicCategory;
import ku.cs.app.models.list.UserSuspensionList;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class DynamicCategoryFileSource  implements DataSource<DynamicCategory>{
    private String directoryName;
    private String fileName;

    //-------------------------------------------- constructor

    public DynamicCategoryFileSource(String directoryName, String fileName) {
        this.directoryName = directoryName;
        this.fileName = fileName;
        checkIfFileExisted();
    }

    //-------------------------------------------- reader

    public DynamicCategory readData() {
        DynamicCategory list = new DynamicCategory();
        String filePath = directoryName + File.separator + fileName;
        File file = new File(filePath);
        FileReader reader = null;
        BufferedReader buffer = null;

        try {
            reader = new FileReader(file, StandardCharsets.UTF_8);
            buffer = new BufferedReader(reader);

            String line = "";
            while ((line = buffer.readLine()) != null) {
                String[] data = line.split(",");
                for (String s: data) {
                    list.addCategory(s);
                }
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                buffer.close();
                reader.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return list;
    }

    //-------------------------------------------- writer

    public void writeData(DynamicCategory list) {
        String filePath = directoryName + File.separator + fileName;
        File file = new File(filePath);

        Writer writer = null;
        BufferedWriter buffer = null;
        try {
            writer = new FileWriter(file, StandardCharsets.UTF_8);
            buffer = new BufferedWriter(writer);

            String line = "";
            for (String s: list.getAllCategory()) {
                line += s + ",";
            }
            buffer.append(line);

            buffer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //-------------------------------------------- method

    private void checkIfFileExisted() {
        File file = new File(directoryName);
        if(!file.exists()) {
            file.mkdirs();
        }

        String filePath = directoryName + File.separator + fileName;
        file = new File(filePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
                DynamicCategory defaultCategory = new DefaultCategoryHardCode().getDefaultCategory();
                writeData(defaultCategory);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void clearData() {
        String filePath = directoryName + File.separator + fileName;
        File file = new File(filePath);

        Writer writer = null;

        try {
            writer = new FileWriter(file, StandardCharsets.UTF_8);
            writer.write("");
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
