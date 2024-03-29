package com.example.synchronization;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.ArrayList;

public class TextWindow {
    public TextWindow(ArrayList<TextField> listTextField, TextArea textArea){
        textArea.setText(textArea.getText() + "\nШрифты: Открытие окна");
        VBox root = new VBox();
        var listLabel = new ArrayList<Label>();
        for (int i = 0; i < (long) listTextField.size(); i++) {
            HBox hBox = new HBox();
            Label l = new Label(listTextField.get(i).getText());
            listLabel.add(l);
            hBox.getChildren().add(l);
            CheckBox cb = new CheckBox();
            Font font = listTextField.get(i).getFont();
            if (font.equals(Font.font(font.getFamily(), FontWeight.BOLD, font.getSize()))){
                cb.setSelected(true);
            }
            int finalI = i;
            cb.setOnAction(event -> {
                if(listTextField.size() > finalI){
                    if (cb.isSelected()){
                        listTextField.get(finalI).setFont(Font.font(font.getFamily(), FontWeight.BOLD, font.getSize()));
                        textArea.setText(textArea.getText() + "\nШрифты: Жирность повышена у №" + (finalI+1));
                    }
                    else{
                        listTextField.get(finalI).setFont(Font.font(font.getFamily(), FontWeight.NORMAL, font.getSize()));
                        textArea.setText(textArea.getText() + "\nШрифты: Жирность понижена у №" + (finalI+1));
                    }
                }
                else{
                    textArea.setText(textArea.getText() + "\nШрифты: Ошибка у №" + (finalI+1));
                }

            });
            hBox.getChildren().add(cb);
            hBox.setSpacing(15);
            root.getChildren().add(hBox);
        }
        root.setSpacing(10);
        root.setPadding(new Insets(15.0, 0.0, 0.0, 25.0));
        Scene scene = new Scene(root, 500, 500);
        Stage stage = new Stage();
        stage.setTitle("Жирность текстов");
        scene.setOnMouseMoved(event -> {
            for (int i = 0; i < listLabel.size(); i++) {
                if(listTextField.size() > i)
                    listLabel.get(i).setText(listTextField.get(i).getText());
                else
                    listLabel.get(i).setText("УДАЛЁН");
            }
        });
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(event -> {
            textArea.setText(textArea.getText() + "\nШрифты: Закрытие окна");
        });
    }

}