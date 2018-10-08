package com.patrycjap.data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

import java.sql.*;

public class DataSourceImpl implements com.patrycjap.api.DataSource {

    public static final String CONNECTION_STRING = "jdbc:sqlite:/home/patrycja/IdeaProjects/AnimalShelterManager2.0/src/main/resources/data/animals_database";

    public static final String TABLE_ANIMALS = "Animals";
    public static final String COLUMN_NAME = "Name";
    public static final String COLUMN_TYPE = "Type";
    public static final String COLUMN_DESCRIPTION = "Description";

    public static final int INDEX_NAME = 1;
    public static final int INDEX_TYPE = 2;
    public static final int INDEX_DESCRIPTION = 3;

    private Connection connection;


    @Override
    public boolean open() {
        try {
            connection = DriverManager.getConnection(CONNECTION_STRING);
            return true;
        } catch (SQLException e) {
            e.getMessage();
            return false;
        }
    }

    @Override
    public void close() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.getMessage();
        }
    }

    @Override
    public ObservableList<Animal> queryAnimal(TableView<Animal> table) {


        ObservableList<Animal> animals = FXCollections.observableArrayList();

        try {
            String stringSQL = "SELECT * FROM " + TABLE_ANIMALS + " ORDER BY " + COLUMN_NAME;
            ResultSet resultSet = connection.createStatement().executeQuery(stringSQL);

            while (resultSet.next()) {
                Animal animal = new Animal();
                animal.setName(resultSet.getString(INDEX_NAME));
                animal.setType(resultSet.getString(INDEX_TYPE));
                animal.setDescription(resultSet.getString(INDEX_DESCRIPTION));
                animals.add(animal);
                table.setItems(animals);
            }
            return animals;
        } catch (SQLException e) {
            e.getMessage();
            return null;
        }
    }

    @Override
    public void addNewAnimal(String name, String type, String desc) {
        try (Statement statement = connection.createStatement()) {
            statement.execute("INSERT INTO " + TABLE_ANIMALS +
                    " (" + COLUMN_NAME + ", " + COLUMN_TYPE + ", " + COLUMN_DESCRIPTION + " ) " +
                    "VALUES( '" + name + "','" + type + "',' " + desc + "')");
        } catch (SQLException e) {
            e.getMessage();
        }

    }

    @Override
    public void deleteAnimal(String itemToRemove) {
        try (Statement statement = connection.createStatement()) {
            statement.execute("DELETE FROM " + TABLE_ANIMALS + " WHERE " + COLUMN_NAME + " = '" +
                    itemToRemove + "'");

        } catch (SQLException e) {
            e.getMessage();
        }

    }

    @Override
    public void updateDatabase(String itemToUpdate, String name, String type, String desc) {
        try (Statement statement = connection.createStatement()) {
            statement.executeQuery("UPDATE " + TABLE_ANIMALS + " SET " + COLUMN_NAME + " = '" +
                    name + "', " + COLUMN_TYPE + " = '" + type + "', " + COLUMN_DESCRIPTION + " = '" + desc +
                    "' WHERE " + COLUMN_NAME + " = '" + itemToUpdate + "'");

        } catch (SQLException e) {
            e.getMessage();
        }

    }

}
