package com.patrycjap.api;

import com.patrycjap.data.Animal;
import javafx.collections.ObservableList;

public interface Model {

    boolean open();

    void close();

    ObservableList<Animal> queryAnimal();

    void addNewAnimal();

    void deleteAnimal();

    void getCountAnimals();


}
