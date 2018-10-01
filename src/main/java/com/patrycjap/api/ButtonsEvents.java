package com.patrycjap.api;

import com.patrycjap.data.Animal;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public interface ButtonsEvents {

    void addButtonClicked(TableView<Animal> table, TextField name,
                          TextField type, TextField desc);

    void deleteButtonClicked(TableView<Animal> table);

    int statusButtonClicked(String file);

    void reportButtonClicked(TableView<Animal> table);

}
