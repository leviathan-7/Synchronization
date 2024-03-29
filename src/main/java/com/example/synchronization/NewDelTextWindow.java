package com.example.synchronization;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class NewDelTextWindow {
    public NewDelTextWindow(VBox listVBox, ArrayList<TextField> listTextField, ArrayList<Label> listLabel, TextArea textArea){
        textArea.setText(textArea.getText() + "\nДобавление и удаление: Открытие окна");
        VBox root = new VBox();
        Button b1 = new Button("Добавить поле");
        SynchronizationApplication.makeStyleForButtons(b1);
        b1.setOnAction( event -> {
            listVBox.getChildren().add(SynchronizationApplication.makeHBox(listTextField.size() + 1, listTextField, listLabel));
            textArea.setText(textArea.getText() + "\nДобавление и удаление: добавлен текст");
        });
        root.getChildren().add(b1);

        HBox hBox = new HBox();
        Button b2 = new Button("Удалить поле");
        SynchronizationApplication.makeStyleForButtons(b2);
        hBox.getChildren().add(b2);
        TextField t = new TextField();
        t.setMaxWidth(200);
        t.addEventFilter(KeyEvent.KEY_TYPED, event -> {
            if (!event.getCharacter().matches("\\d*")) {
                event.consume();
            }
        });
        b2.setOnAction( event -> {
            try {
                int k = Integer.parseInt(t.getCharacters().toString());
                k--;
                if (k < 0 || k >= listTextField.size()){
                    textArea.setText(textArea.getText() + "\nДобавление и удаление: Нет соотвествующего текста!");
                }
                else {
                    listTextField.remove(k);
                    listLabel.remove(k);
                    listVBox.getChildren().remove(k);
                    for (int i = k; i < listLabel.size(); i++) {
                        listLabel.get(i).setText("" + (i+1));
                    }
                    textArea.setText(textArea.getText() + "\nДобавление и удаление: удалён №" + (k+1));
                }
            }
            catch(Exception e) {
                textArea.setText(textArea.getText() + "\nДобавление и удаление: Ошибка ввода!");
            }
        });
        hBox.getChildren().add(t);
        hBox.setSpacing(15);
        hBox.setAlignment(Pos.CENTER_LEFT);
        root.getChildren().add(hBox);

        root.setSpacing(10);
        root.setPadding(new Insets(15.0, 0.0, 0.0, 25.0));
        Scene scene = new Scene(root, 500, 500);
        Stage stage = new Stage();
        stage.setTitle("Добавление и удаление текстов");
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(event -> {
            textArea.setText(textArea.getText() + "\nДобавление и удаление: Закрытие окна");
        });
    }

}