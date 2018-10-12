package com.qa.commons;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DataProvider {
    public List<String> readData(String fileName) throws FileNotFoundException {

        List<String> rows = new ArrayList<String>();
        //Get file from resources folder
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());
        Scanner scanner = new Scanner(file);
        scanner.nextLine();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            rows.add(line);
        }
        scanner.close();
        return rows;

    }


}
