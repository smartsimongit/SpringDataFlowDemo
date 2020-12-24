package com.smartsimon;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Deprecated
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostPackage {
    private int index;
    private String destination;


    public static void main(String[] args) throws IOException {
        File resource = new ClassPathResource(
                "test01.txt").getFile();
        Scanner scanner = new Scanner(resource);
        scanner.useDelimiter(" ");
        List<String> stringList = new ArrayList<>();

        while (scanner.hasNext()) {
            stringList.add(scanner.next());
        }
        scanner.close();
        System.out.println(stringList.size() + " size");
    }
}
