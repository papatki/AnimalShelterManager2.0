package com.patrycjap.data;

import javafx.scene.control.TableView;
import java.io.*;
;

public class Database {

    public static void loadDataFromFile(TableView<Animal> table) {
        try {
            File file = new File("file.txt");
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line;
            String[] array;

            while ((line = bufferedReader.readLine()) != null) {
                array = line.split(" ");
                Animal animal = new Animal();
                animal.setName(array[0]);
                animal.setType(array[1]);
                animal.setDescription(array[2]);
                table.getItems().add(animal);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveDataToFile(String saveData, TableView<Animal> table) {
        try {
            File file = new File(saveData);
            PrintWriter printWriter = new PrintWriter(file);
            String[] array = new String[table.getItems().size()];


            for (int i = 0; i < table.getItems().size(); i++) {
                for (int j = 0; j < table.getColumns().size(); j++) {
                    if (table.getColumns().get(j).getCellData(i) != null) {
                        array[i] = table.getColumns().get(j).getCellData(i).toString();
                    } else {
                        array[i] = "";
                    }
                }
            }

            for (int i = 0; i < array.length; i++) {
                printWriter.println(array[i]);
            }

            printWriter.close();

        } catch (FileNotFoundException e) {
           e.printStackTrace();
        }

    }

}
