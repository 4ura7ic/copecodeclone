package ku.cs.app.services;

import ku.cs.app.models.Report;
import ku.cs.app.models.list.ReportList;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class ReportListFileDataSource implements DataSource<ReportList>{
    //-------------------------------------------- instance

    private String directoryName;
    private String fileName;

    //-------------------------------------------- constructor

    public ReportListFileDataSource(String directoryName, String fileName){
        this.directoryName = directoryName;
        this.fileName = fileName;
        checkIfFileExisted();
    }

    //-------------------------------------------- reader

    public ReportList readData(){
        ReportList list  = new ReportList();
        String filePath = directoryName + File.separator + fileName;
        File file = new File(filePath);
        FileReader reader = null;
        BufferedReader buffer = null;

        try {
            reader = new FileReader(file, StandardCharsets.UTF_8);
            buffer = new BufferedReader(reader);

            String line = "";
            while ((line = buffer.readLine()) != null){
                String[] data = line.split("\\|");
                if (data.length == 10) {
                    Report report = new Report(
                            data[0].trim(),
                            data[1].trim(),
                            data[2].trim(),
                            data[3].trim(),
                            data[4].trim(),
                            data[5].trim(),
                            data[6].trim(),
                            data[7].trim(),
                            Integer.parseInt(data[8]),
                            Boolean.parseBoolean(data[9])
                    );
                    list.addReport(report);
                }
                else if (data.length == 11) {

                    Report report = new Report(
                            data[0].trim(),
                            data[1].trim(),
                            data[2].trim(),
                            data[3].trim(),
                            data[4].trim(),
                            data[5].trim(),
                            data[6].trim(),
                            data[7].trim(),
                            Integer.parseInt(data[8]),
                            Boolean.parseBoolean(data[9])
                    );
                    ArrayList<String> allVotedUser = new ArrayList<>();
                    String[] votedUserData = data[10].split(",");
                    for (String s : votedUserData) {
                        allVotedUser.add(s);
                    }
                    report.addAllVotedUser(allVotedUser);
                    list.addReport(report);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try{
                buffer.close();
                reader.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return list;
    }

    //-------------------------------------------- writer

    public void writeData(ReportList list){
        String filePath = directoryName + File.separator + fileName;
        File file = new File(filePath);

        Writer writer = null;
        BufferedWriter buffer = null;

        try {
            writer = new FileWriter(file, StandardCharsets.UTF_8);
            buffer = new BufferedWriter(writer);

            for(Report report: list.getAllRpt()){
                String line = report.getAuthorUser()+"|"
                        + report.getTopic()+"|"
                        + report.getDate()+"|"
                        + report.getCategory()+"|"
                        + report.getDescription()+"|"
                        + report.getPhoto()+"|"
                        + report.getSolution()+"|"
                        + report.getService()+"|"
                        + report.getVote()+"|"
                        + report.isCheck() + "|";
                    for (String s : report.getVotedUser()) {
                        line += s + ",";
                    }
                buffer.append(line);
                buffer.newLine();

            }
            buffer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    //-------------------------------------------- method

    private void checkIfFileExisted(){
        File file = new File(directoryName);
        if(!file.exists())
            file.mkdirs();

        String filePath = directoryName + File.separator + fileName;
        file = new File(filePath);
        if(!file.exists()){
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
            writer = new FileWriter(file, StandardCharsets.UTF_8);
            writer.write("");
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
