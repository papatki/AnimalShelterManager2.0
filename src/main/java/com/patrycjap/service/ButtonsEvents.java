package com.patrycjap.service;

import com.patrycjap.data.Animal;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

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

    public static void statusButtonClicked() {

    }

    public static void reportButtonClicked() {

    }
}
