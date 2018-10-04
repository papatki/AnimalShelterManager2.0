package com.patrycjap.service;

import com.patrycjap.api.ConfirmBox;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ConfirmBoxImpl implements ConfirmBox {
    static boolean answer;

    public boolean confirm(String message, String title) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);

        Label label = new Label();
        label.setText(message);

        Button yesButton = new Button("OK");

        yesButton.setOnAction(e -> {
            answer = true;
            window.close();
        });


        VBox vBox = new VBox(10);
        vBox.setPadding(new Insets(10, 10, 10, 10));
        vBox.getChildren().addAll(label, yesButton);
        vBox.setAlignment(Pos.CENTER);

        Scene scene = new Scene(vBox);
        scene.getStylesheets().add(getClass().getResource("/confirmboxstyle.css").toExternalForm());
        window.setScene(scene);
        window.showAndWait();
        return answer;
    }
}