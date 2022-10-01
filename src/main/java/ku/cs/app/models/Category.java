package ku.cs.app.models;

import java.io.*;
import java.util.ArrayList;

public class Category {
    //-------------------------------------------- instance

    private ArrayList<String> category;

    private String directoryName;

    private String fileName;

    //-------------------------------------------- constructor

    public Category() {
        category = new ArrayList<>();
    }

    //-------------------------------------------- method

    public void addFilePath(String directoryName, String fileName){
        this.directoryName = directoryName;
        this.fileName = fileName;
        checkIfFileExisted();
    }

    public void readData(){
        String filePath = directoryName + File.separator + fileName;
        File file = new File(filePath);
        FileReader reader = null;
        BufferedReader buffer = null;

        try {
            reader = new FileReader(file);
            buffer = new BufferedReader(reader);

            String line = "";
            line = buffer.readLine();
            category.add(line);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                buffer.close();
                reader.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void writeData(Category category, String addCat){
        String filePath = directoryName + File.separator + fileName;
        File file = new File(filePath);

        Writer writer = null;
        BufferedWriter buffer = null;

        try {
            writer = new FileWriter(file);
            buffer = new BufferedWriter(writer);

            ArrayList<String> line = category.getCategory();
            line.add(addCat);

            buffer.append(line.toString());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

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
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public ArrayList<String> getCategory() {
        return category;
    }
    public void addCategoryName(String categoryName) {
        this.category.add(categoryName);
    }

}
