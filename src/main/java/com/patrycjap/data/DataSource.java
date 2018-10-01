package com.patrycjap.data;

import com.patrycjap.api.Model;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSource implements Model {

    private static final String DB_NAME = "animals_database.db";
    private  static final String CONNECTION_STRING = "jdbc:sqlite:/home/patrioshka/IdeaProjects/AnimalShelterManager2.0";

    private static final String TABLE_ANIMALS ="Animals";
    private static final String COLUMN_NAME = "Name";
    private static final String COLUMN_TYPE = "Type";
    private static final String COLUMN_DESCRIPTION = "Description";

    private static final int INDEX_NAME = 1;
    private static final int INDEX_TYPE = 2;
    private static final int INDEX_DESCRIPTION = 3;

    private static final int ORDER_BY_NONE = 1;

    private Connection connection;


    @Override
    public boolean open() {
       try {
           connection = DriverManager.getConnection(CONNECTION_STRING);
           return true;
       }catch (SQLException e) {
           e.getMessage();
           return false;
       }
    }

    @Override
    public void close() {
        try {
            if(connection != null) {
                connection.close();
            }
        }catch (SQLException  e) {
            e.getMessage();
        }
    }

    @Override
    public ObservableList<Animal> queryAnimal() {
        return null;
    }

    @Override
    public void addNewAnimal() {

    }

    @Override
    public void deleteAnimal() {

    }

    @Override
    public void getCountAnimals() {

    }
}
