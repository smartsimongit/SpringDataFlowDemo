package com.smartsimon;

import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MyFile {
    private static final String filePath = "src/test/resources/";
    private String fileName;

    public MyFile(String fileName) {
        this.fileName = filePath + fileName;
    }

    public String getFileName() {
        return fileName;
    }

    public String readFile() throws NoFileNameException, FileNotFoundException {
        if (StringUtils.isEmpty(getFileName())) {
            throw new NoFileNameException();
        }
        String file = fileName;
        Scanner scanner = new Scanner(new File(file));
        scanner.useDelimiter(" ");


        String line = "";
        while (scanner.hasNext()) {
            line = line + " " + scanner.next();

        }
        scanner.close();
        return line;


    }
}
