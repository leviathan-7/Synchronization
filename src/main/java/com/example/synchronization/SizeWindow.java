package com.example.synchronization;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class SizeWindow {
    public SizeWindow(Stage mainStage, TextArea textArea){
        textArea.setText(textArea.getText() + "\nИзменение размера: Открытие окна");
        VBox root = new VBox();
        TextField t = new TextField();
        t.addEventFilter(KeyEvent.KEY_TYPED, event -> {
            if (!event.getCharacter().matches("[\\d*.]")) {
                event.consume();
            }
        });
        t.setMaxWidth(200);
        root.getChildren().add(t);
        Button b = new Button("Поменять размер");
        SynchronizationApplication.makeStyleForButtons(b);
        b.setOnAction( event -> {
            double k = 1;
            try {
                k = Double.parseDouble(t.getCharacters().toString());
                if (k == 0){
                    k = 1;
                    textArea.setText(textArea.getText() + "\nИзменение размера: Ошибка ввода, коэффиент = 0");
                }else {
                    textArea.setText(textArea.getText() + "\nИзменение размера: размер изменен");
                }
            }
            catch(Exception e) {
                textArea.setText(textArea.getText() + "\nИзменение размера: Ошибка ввода!");
            }

            mainStage.setHeight(mainStage.getHeight() * k);
            mainStage.setWidth(mainStage.getWidth() * k);
        });
        root.getChildren().add(b);
        root.setSpacing(10);
        root.setPadding(new Insets(15.0, 0.0, 0.0, 25.0));
        Scene scene = new Scene(root, 500, 500);
        Stage stage = new Stage();
        stage.setTitle("Изменить размер исходного окна");
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(event -> {
            textArea.setText(textArea.getText() + "\nИзменение размера: Закрытие окна");
        });
    }

}