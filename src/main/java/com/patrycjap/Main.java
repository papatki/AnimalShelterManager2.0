package com.patrycjap;

import com.patrycjap.data.Animal;
import com.patrycjap.data.Database;
import com.patrycjap.service.ButtonsEvents;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Main extends Application {

    private Stage window;
    private TableView<Animal> table;
    private TextField nameInput, typeInput, descInput;
    private Button addButton, deleteButton, statusButton, reportButton;


    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        Database.loadDataFromFile();
        window = primaryStage;
        window.setTitle("Animal Shelter Manager 2.0");


        TableColumn<Animal, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setMinWidth(100);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        nameColumn.setOnEditCommit(
                event -> event.getTableView().getItems().get(
                        event.getTablePosition().getRow()).setName(event.getNewValue())
        );

        TableColumn<Animal, String> typeColumn = new TableColumn<>("Type");
        typeColumn.setMinWidth(100);
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        typeColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        typeColumn.setOnEditCommit(
                event -> event.getTableView().getItems().get(
                        event.getTablePosition().getRow()).setType(event.getNewValue())
        );

        TableColumn<Animal, String> descriptionColumn = new TableColumn<>("Description");
        descriptionColumn.setMinWidth(500);
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        descriptionColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        descriptionColumn.setOnEditCommit(
                event -> event.getTableView().getItems().get(
                        event.getTablePosition().getRow()).setDescription(event.getNewValue())
        );

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
        addButton.setOnAction(e -> ButtonsEvents.addButtonClicked(table, nameInput, typeInput, descInput));

        deleteButton = new Button("Delete");
        deleteButton.setOnAction(e -> ButtonsEvents.deleteButtonClicked(table));

        statusButton = new Button("Status");
//        statusButton.setOnAction(e -> ButtonsEvents.statusButtonClicked());

        reportButton = new Button("Report");
        reportButton.setOnAction(e -> ButtonsEvents.reportButtonClicked(table));

        HBox hBox = new HBox();
        hBox.setPadding(new Insets(10, 10, 10, 10));
        hBox.setSpacing(10);
        hBox.getChildren().addAll(nameInput, typeInput, descInput, addButton, deleteButton);

        HBox hBox2 = new HBox();
        hBox2.setPadding(new Insets(10, 10, 10, 10));
        hBox2.setSpacing(10);
        hBox2.getChildren().addAll(statusButton, reportButton);


        table = new TableView<>();
        table.getColumns().addAll(nameColumn, typeColumn, descriptionColumn);

        Database.saveDataToFile("file.txt");
        table.setEditable(true);

        VBox vBox = new VBox();
        vBox.getChildren().addAll(table, hBox, hBox2);

        Scene scene = new Scene(vBox);
        window.setScene(scene);
        window.show();

    }

}
