package com.patrycjap;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Main extends Application {

    private Stage window;
    private TableView<Animal> table;
    private TextField nameInput, typeInput, descInput;
    private Button addButton, deleteButton;


    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        window.setTitle("Animal Shelter Manager 2.0");

        TableColumn<Animal, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setMinWidth(100);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Animal, String> typeColumn = new TableColumn<>("Type");
        typeColumn.setMinWidth(100);
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));

        TableColumn<Animal, String> descriptionColumn = new TableColumn<>("Description");
        descriptionColumn.setMinWidth(150);
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));

        nameInput = new TextField();
        nameInput.setPromptText("name");
        nameInput.setMinWidth(100);

        typeInput = new TextField();
        typeInput.setPromptText("type");
        typeInput.setMinWidth(100);

        descInput = new TextField();
        descInput.setPromptText("description");
        descInput.setMinWidth(100);

        addButton = new Button("Add");
        addButton.setOnAction(e -> addButtonClicked());

        deleteButton = new Button("Delete");
        deleteButton.setOnAction(e -> deleteButtonClicked());

        HBox hBox = new HBox();
        hBox.setPadding(new Insets(10,10,10,10));
        hBox.setSpacing(10);
        hBox.getChildren().addAll(nameInput, typeInput, descInput, addButton, deleteButton);


        table = new TableView<>();
        table.setItems(getAnimal());
        table.getColumns().addAll(nameColumn, typeColumn, descriptionColumn);


        VBox vBox = new VBox();
        vBox.getChildren().addAll(table, hBox);

        Scene scene = new Scene(vBox);
        window.setScene(scene);
        window.show();


    }
    private void addButtonClicked() {
        Animal animal = new Animal();
        animal.setName(nameInput.getText());
        animal.setType(typeInput.getText());
        animal.setDescription(descInput.getText());
        table.getItems().add(animal);
        nameInput.clear();
        typeInput.clear();
        descInput.clear();
    }

    private void deleteButtonClicked() {
        ObservableList<Animal> animalsSelected, allAnimals;
        allAnimals = table.getItems();
        animalsSelected = table.getSelectionModel().getSelectedItems();

        animalsSelected.forEach(allAnimals::remove);
    }

    private ObservableList<Animal> getAnimal() {
        ObservableList<Animal> animals = FXCollections.observableArrayList();
        animals.add(new Animal("Rex", "dog", "puppy"));
        animals.add(new Animal("Lion", "cat", "old"));
        animals.add(new Animal("Sam", "dog", "very old"));

        return animals;

    }


}
