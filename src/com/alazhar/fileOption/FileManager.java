package com.alazhar.fileOption;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class FileManager {

    private UiCallback callback;

    public FileManager(UiCallback callback) {
        this.callback = callback;
    }

    public void copyFile(String filePath, String pathDestination) throws Exception {

        callback.onProcessStarted();
        File file = new File(filePath);
        InputStream input = new FileInputStream(file);
        OutputStream output = new FileOutputStream(pathDestination);

        long fileLength = file.length();
        double currentRead = 0;
        byte[] buf = new byte[1024];
        int count;

        while ((count = input.read(buf)) > 0) {

            output.write(buf, 0, count);
            currentRead += count;
            int progressValue
                    = (int) Math.round((currentRead / fileLength) * 100);
            callback.updateProgress(progressValue);
        }

        input.close();
        output.flush();
        output.close();

        callback.onProcessEnd();
    }

    public void cutFile(String filePath, String pathDestination) throws Exception {

        callback.onProcessStarted();
        File file = new File(filePath);
        InputStream input = new FileInputStream(file);
        OutputStream output = new FileOutputStream(pathDestination);

        long fileLength = file.length();
        double currentRead = 0;
        byte[] buf = new byte[1024];
        int count;

        while ((count = input.read(buf)) > 0) {

            output.write(buf, 0, count);
            currentRead += count;
            int progressValue
                    = (int) Math.round((currentRead / fileLength) * 100);
            callback.updateProgress(progressValue);
        }

        input.close();
        output.flush();
        output.close();
        file.delete();

        callback.onProcessEnd();
    }

}
