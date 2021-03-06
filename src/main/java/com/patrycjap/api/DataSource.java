package com.patrycjap.api;

import com.patrycjap.data.Animal;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

public interface DataSource {

    boolean open();

    void close();

    ObservableList<Animal> queryAnimal(TableView<Animal> table);

    void addNewAnimal(String name, String type, String desc);

    void deleteAnimal(String itemToRemove);

    void updateDatabase(String itemToUpdate, String name, String type, String desc);


}
