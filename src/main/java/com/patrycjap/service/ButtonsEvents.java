package com.patrycjap.service;

import com.patrycjap.data.Animal;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Scanner;


public class ButtonsEvents {
    public static void addButtonClicked(TableView<Animal> table, TextField name,
                                        TextField type, TextField desc) {
        Animal animal = new Animal();
        animal.setName(name.getText());
        animal.setType(type.getText());
        animal.setDescription(desc.getText());
        table.getItems().add(animal);
        name.clear();
        type.clear();
        desc.clear();
    }

    public static void deleteButtonClicked(TableView<Animal> table) {
        ObservableList<Animal> animalsSelected, allAnimals;
        allAnimals = table.getItems();
        animalsSelected = table.getSelectionModel().getSelectedItems();

        animalsSelected.forEach(allAnimals::remove);
    }

    public static int statusButtonClicked(String file) {
        try {
            Scanner scanner = new Scanner(new File(file));
            int count = 0;
            while (scanner.hasNextLine()) {
               count++;
               scanner.nextLine();
            }
            return count;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }

    // get a report about shelter in xml file -> export TableView to Excel (Apache POI)
    public static void reportButtonClicked(TableView<Animal> table) {
        Workbook workbook = new HSSFWorkbook();
        Sheet spreadsheet = workbook.createSheet("sample");

        Row row = spreadsheet.createRow(0);
        for (int i = 0; i < table.getColumns().size(); i++) {
            row.createCell(i).setCellValue(table.getColumns().get(i).getText());
        }

        for (int i = 0; i < table.getItems().size(); i++) {
            row = spreadsheet.createRow(i + 1);
            for (int j = 0; j < table.getColumns().size(); j++) {
                if (table.getColumns().get(j).getCellData(i) != null) {
                    row.createCell(j).setCellValue(table.getColumns().get(j).getCellData(i).toString());
                } else {
                    row.createCell(j).setCellValue("");
                }
            }
        }

        try (FileOutputStream fileOutput = new FileOutputStream("report.xls")) {
            workbook.write(fileOutput);
            workbook.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
