package dataLayer;

import java.io.File;
import java.io.IOException;

/**
 * The type File writer.
 */
public class FileWriter {

    /**
     * Create file.
     */
    public void createFile() {
        try {
            File myFile = new File("bill.txt");
            if (myFile.createNewFile()) {
                System.out.println("File created:" + myFile.getName());
            } else
                System.out.println("File exists!");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
    public void writeFile(String s) {
        try {
            java.io.FileWriter fileWriter = new java.io.FileWriter("bill.txt");
            fileWriter.write(s);
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Something happened");
            e.printStackTrace();
        }
    }

    /**
     * Create file for report.
     *
     * @param fileName the file name
     */
    public void createFileForReport(String fileName) {
        try {
            File myFile = new File(fileName);
            if (myFile.createNewFile()) {
                System.out.println("File created:" + myFile.getName());
            } else
                System.out.println("File exists!");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    /**
     * Write file for report.
     *
     * @param s        the s
     * @param fileName the file name
     */
    public void writeFileForReport(String s,String fileName) {
        try {
            java.io.FileWriter fileWriter = new java.io.FileWriter(fileName);
            fileWriter.write(s);
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Something happened");
            e.printStackTrace();
        }
    }
}
