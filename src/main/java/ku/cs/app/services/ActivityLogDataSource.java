package ku.cs.app.services;

import ku.cs.app.models.Activity;
import ku.cs.app.models.ActivityLog;

import java.io.*;

public class ActivityLogDataSource implements DataSource<ActivityLog>{
    //-------------------------------------------- instance

    private String directoryName;
    private String fileName;

    //-------------------------------------------- constructor

    public ActivityLogDataSource(String directoryName, String fileName) {
        this.directoryName = directoryName;
        this.fileName = fileName;
        checkIfFileExisted();
    }
    //-------------------------------------------- reader

    public ActivityLog readData() {
        ActivityLog log = new ActivityLog();
        String filePath = directoryName + File.separator + fileName;
        File file = new File(filePath);
        FileReader reader = null;
        BufferedReader buffer = null;

        try {
            reader = new FileReader(file);
            buffer = new BufferedReader(reader);

            String line = "";
            while ((line = buffer.readLine()) != null) {
                String[] data = line.split(",");
                Activity activity = new Activity(
                        data[0].trim(),
                        data[1].trim(),
                        data[2].trim()
                );
                log.addLog(activity);
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
        return log;
    }

    //-------------------------------------------- writer

    public void writeData(ActivityLog log) {
        String filePath = directoryName + File.separator + fileName;
        File file = new File(filePath);

        Writer writer = null;
        BufferedWriter buffer = null;

        try {
            writer = new FileWriter(file);
            buffer = new BufferedWriter(writer);

            for (Activity data : log.getLog()) {
                String line = data.getDateTime() + ","
                        + data.getActivity() + ","
                        + data.getMessage();

                buffer.append(line);
                buffer.newLine();
            }

            buffer.close();

        }catch (IOException e) {
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
            writer = new FileWriter(file);
            writer.write("");
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
