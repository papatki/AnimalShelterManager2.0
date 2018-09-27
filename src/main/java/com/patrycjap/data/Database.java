package com.patrycjap.data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class Database {

    public static ObservableList<Animal> loadDataFromFile() {
        try {
            File file = new File("file.txt");
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            List<Animal> list = (List<Animal>) objectInputStream.readObject();

            return FXCollections.observableArrayList(list);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return FXCollections.emptyObservableList();
    }

    public static void saveDataToFile(String saveData) {
        ObservableList<Animal> list = FXCollections.observableArrayList();
        try {
            File file = new File(saveData);
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeObject(new ArrayList<>(list));
            objectOutputStream.flush();
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
