package ioStream.EX1;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileReadWriteExample {
    public static void main(String[] args) {
        String inputFile = "input.txt";
        String outputFile = "output.txt";

        try {
            // Reading from input file using FileInputStream and InputStream
            FileInputStream inputStream = new FileInputStream(inputFile);

            // Writing to output file using FileOutputStream and OutputStream
            FileOutputStream outputStream = new FileOutputStream(outputFile);

            // Reading and Writing
            readWrite(inputStream, outputStream);

            // Closing streams
            inputStream.close();
            outputStream.close();

            System.out.println("File has been read from and written to successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void readWrite(InputStream inputStream, OutputStream outputStream) throws IOException {
        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }
    }
}