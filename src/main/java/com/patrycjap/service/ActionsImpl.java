package com.patrycjap.service;

import com.patrycjap.api.Actions;
import com.patrycjap.data.Animal;
import com.patrycjap.data.DataSourceImpl;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileOutputStream;


public class ActionsImpl implements Actions {

    private DataSourceImpl dataSource = new DataSourceImpl();

    public void addButtonClicked(TableView<Animal> table, TextField name,
                                 TextField type, TextField desc) {
        Animal animal = new Animal();
        animal.setName(name.getText());
        animal.setType(type.getText());
        animal.setDescription(desc.getText());
        dataSource.open();
        dataSource.addNewAnimal(name.getText(), type.getText(), desc.getText());
        table.getItems().add(animal);
        name.clear();
        type.clear();
        desc.clear();
    }

    public void deleteButtonClicked(TableView<Animal> table) {
        dataSource.open();
        ObservableList<Animal> animalsSelected, allAnimals;
        String itemToRemove;
        allAnimals = table.getItems();
        animalsSelected = table.getSelectionModel().getSelectedItems();
        itemToRemove = table.getSelectionModel().getSelectedItem().getName();
        dataSource.deleteAnimal(itemToRemove);
        animalsSelected.forEach(allAnimals::remove);
    }

    public int statusButtonClicked(TableView<Animal> table) {
        return table.getItems().size();
    }

    // get a report about shelter in xml file -> export TableView to Excel (Apache POI)
    public void reportButtonClicked(TableView<Animal> table) {
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

    @Override
    public void saveChanges(TableView<Animal> table) {
        dataSource.open();
        String name = table.getSelectionModel().getSelectedItem().getName();
        String type = table.getSelectionModel().getSelectedItem().getType();
        String desc = table.getSelectionModel().getSelectedItem().getDescription();
        dataSource.updateDatabase(name, name, type, desc);
    }
}
