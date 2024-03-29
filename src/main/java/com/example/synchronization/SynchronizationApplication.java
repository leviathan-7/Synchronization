package com.example.synchronization;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

public class SynchronizationApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        VBox root = new VBox();
        HBox hBoxButtons = new HBox();
        var listB = new ArrayList<Button>();
        listB.add(new Button("Размеры окна"));
        listB.add(new Button("Шрифты"));
        listB.add(new Button("Тексты"));

        for (int i = 0; i < 3; i++)
            makeStyleForButtons(listB.get(i));
        hBoxButtons.getChildren().addAll(listB);
        hBoxButtons.setSpacing(15);
        root.getChildren().add(hBoxButtons);

        VBox listVBox = new VBox();
        var listTextField = new ArrayList<TextField>();
        var listLabel = new ArrayList<Label>();
        for (int i = 0; i < 6; i++) {
            var hBox = makeHBox(i+1, listTextField, listLabel);
            listVBox.getChildren().add(hBox);
        }
        listVBox.setSpacing(10);
        root.getChildren().add(listVBox);

        TextArea textArea = new TextArea();
        textArea.setMaxWidth(330);
        textArea.setPrefRowCount(15);
        root.getChildren().add(textArea);

        root.setSpacing(10);
        root.setPadding(new Insets(15.0, 0.0, 0.0, 25.0));

        listB.get(0).setOnAction( event -> {
            new SizeWindow(stage, textArea);
        });
        listB.get(1).setOnAction( event -> {
            new TextWindow(listTextField, textArea);
        });
        listB.get(2).setOnAction( event -> {
            new NewDelTextWindow(listVBox, listTextField, listLabel, textArea);
        });

        Scene scene = new Scene(root, 1000, 700);
        stage.setTitle("Синхронизация полей ввода и флажков");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public static HBox makeHBox(int i, ArrayList<TextField> listTextField, ArrayList<Label> listLabel){
        HBox hBox = new HBox();
        Label l = new Label("" + i);
        listLabel.add(l);
        hBox.getChildren().add(l);
        TextField t = new TextField("" + i);
        t.setMinWidth(310);
        listTextField.add(t);
        hBox.getChildren().add(t);
        hBox.setSpacing(15);
        return hBox;
    }

    public static void makeStyleForButtons(Button b){
        b.setMinWidth(100);
        b.setMinHeight(40);
    }
}