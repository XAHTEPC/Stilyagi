package com.example.demo.Model;

import com.example.demo.Crypto.MD5;
import com.example.demo.Database.DataBase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.SQLException;

public class Work_Admin {
    String id;
    String name;
    String structure;

    public Work_Admin(String id, String name, String structure) {
        this.id = id;
        this.name = name;
        this.structure = structure;
    }
    public static Pane getPane(Pane pane, ScrollPane scrollPane) throws SQLException, FileNotFoundException, ClassNotFoundException {
        pane = new Pane();
        Pane res = pane;

        Button add = new Button("Добавить место работы");
        add.setLayoutX(300);
        add.setLayoutY(10);
        res.getChildren().add(add);

        Text num = new Text("#");
        num.setLayoutX(20);
        num.setLayoutY(60);
        num.setFont(Font.font("Verdana",13));

        Text em = new Text("ФИО");
        em.setLayoutX(70);
        em.setLayoutY(60);
        em.setFont(Font.font("Verdana",13));

        Text pos = new Text("Место работы");
        pos.setLayoutX(230);
        pos.setLayoutY(60);
        pos.setFont(Font.font("Verdana",13));

        Work_Admin[] mas = DataBase.getAllWork();
        int u = 80;
        for(int i=0; i<mas.length;i++, u+=70){
            if(mas[i] == null)
                continue;
            TextArea num_text = new TextArea();
            num_text.setEditable(false);
            num_text.setText(mas[i].id);
            num_text.setLayoutX(10);
            num_text.setLayoutY(0 + u);
            num_text.setMaxHeight(40);
            num_text.setMaxWidth(30);
            num_text.setMinHeight(40);
            num_text.setMinWidth(30);

            TextArea em_text = new TextArea();
            em_text.setText(mas[i].name);
            em_text.setEditable(false);
            em_text.setLayoutX(50);
            em_text.setLayoutY(0 + u);
            em_text.setMaxHeight(40);
            em_text.setMaxWidth(160);
            em_text.setMinWidth(160);

            TextArea str_text = new TextArea();
            str_text.setText(mas[i].structure);
            str_text.setEditable(false);
            str_text.setLayoutX(220);
            str_text.setLayoutY(0 + u);
            str_text.setMaxHeight(40);
            str_text.setMaxWidth(100);
            str_text.setMinHeight(40);
            str_text.setMinWidth(100);

            final String a = mas[i].id;

            FileInputStream Url = new FileInputStream("png/pen.png");
            Image url = new Image(Url);
            ImageView pen = new ImageView(url);

            Button edit = new Button();
            edit.setGraphic(pen);
            edit.setLayoutX(330);
            edit.setLayoutY(0+u);

            Pane finalPane = pane;
            String id = mas[i].id;
            edit.setOnAction(r ->{
                try {
                    change(finalPane,id,scrollPane);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } catch (FileNotFoundException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            });

            pane.getChildren().addAll(num_text,em_text, str_text,edit);
        }
        add.setOnAction(v ->{
            try {
                add(res, scrollPane);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });

        res.getChildren().addAll(num, em, pos);
        return res;
    }

    public static void change(Pane pane, String id,  ScrollPane scrollPane) throws SQLException, FileNotFoundException, ClassNotFoundException {
        Group root_add = new Group();
        String[] work = DataBase.getWork_byID(id);
        Scene scene_add = new Scene(root_add, 400, 300);
        Stage newWindow = new Stage();
        newWindow.initStyle(StageStyle.UTILITY);
        //newWindow.initStyle(StageStyle.UTILITY);

        FileInputStream Url = new FileInputStream("png/front2.jpg");
        Image url = new Image(Url);
        ImageView front = new ImageView(url);
        front.setX(0);
        front.setY(0);
        //root_add.getChildren().add(front);

        Text title = new Text("Редактирование места работы");
        title.setLayoutX(100);
        title.setLayoutY(20);
        title.setFont(Font.font("Verdana",16));

        Text name_text = new Text("ФИО:");
        name_text.setLayoutX(10);
        name_text.setLayoutY(55);
        name_text.setFont(Font.font("Verdana",13));

        TextArea name = new TextArea();
        name.setText(work[2]);
        name.setLayoutX(160);
        name.setLayoutY(30);
        name.setMaxHeight(40);
        name.setMaxWidth(200);

        Text work_text = new Text("Место работы:");
        work_text.setLayoutX(10);
        work_text.setLayoutY(145);
        work_text.setFont(Font.font("Verdana",13));
        String[] mas = DataBase.getStructure_name();

        ObservableList<String> work2= FXCollections.observableArrayList(mas);
        ComboBox<String> comboBox2 = new ComboBox<String>(work2);
        comboBox2.setValue(work[1]);
        comboBox2.setMaxWidth(150);
        comboBox2.setLayoutX(160);
        comboBox2.setLayoutY(130);

        Button save = new Button("СОХРАНИТЬ");
        save.setLayoutX(100);
        save.setLayoutY(200);


        Button del = new Button("УДАЛИТЬ");
        del.setLayoutX(250);
        del.setLayoutY(200);


        save.setOnAction(x ->{
            String t1,t2;
            String finalId = id;
            t1 = name.getText();
            t2 = comboBox2.getSelectionModel().getSelectedItem();

            try {
                DataBase.UpdateWork(finalId,t1,t2);
                Pane p = Work_Admin.getPane(pane,scrollPane);
                scrollPane.setContent(p);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            newWindow.close();
        });

        del.setOnAction(v ->{
            try {
                DataBase.delWork(id);
                Pane p = Work_Admin.getPane(pane,scrollPane);
                scrollPane.setContent(p);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            newWindow.close();
        });
        root_add.getChildren().addAll(save,title,name,name_text,del);
        root_add.getChildren().addAll(work_text, comboBox2);
        newWindow.setTitle("Редактирование клиента");
        newWindow.setScene(scene_add);
        newWindow.show();
    }

    public static void add(Pane pane,  ScrollPane scrollPane) throws SQLException, FileNotFoundException, ClassNotFoundException {
        Group root_add = new Group();
        String id = DataBase.getLastWork();
        int kol = Integer.parseInt(id);
        kol++;
        id = String.valueOf(kol);
        Scene scene_add = new Scene(root_add, 400, 300);
        Stage newWindow = new Stage();
        newWindow.initStyle(StageStyle.UTILITY);
        //newWindow.initStyle(StageStyle.UTILITY);

        FileInputStream Url = new FileInputStream("png/front2.jpg");
        Image url = new Image(Url);
        ImageView front = new ImageView(url);
        front.setX(0);
        front.setY(0);
        //root_add.getChildren().add(front);

        Text title = new Text("Добавление места работы");
        title.setLayoutX(100);
        title.setLayoutY(20);
        title.setFont(Font.font("Verdana",16));

        Text name_text = new Text("ФИО:");
        name_text.setLayoutX(10);
        name_text.setLayoutY(55);
        name_text.setFont(Font.font("Verdana",13));
        String[] mas = DataBase.getEmployee_name();

        ObservableList<String> emp= FXCollections.observableArrayList(mas);
        ComboBox<String> comboBox2 = new ComboBox<String>(emp);
        //comboBox2.setValue(work[1]);
        comboBox2.setMaxWidth(150);
        comboBox2.setLayoutX(160);
        comboBox2.setLayoutY(80);

        Text work_text = new Text("Место работы:");
        work_text.setLayoutX(10);
        work_text.setLayoutY(145);
        work_text.setFont(Font.font("Verdana",13));
        String[] mas2 = DataBase.getStructure_name();

        ObservableList<String> work2= FXCollections.observableArrayList(mas2);
        ComboBox<String> comboBox3 = new ComboBox<String>(work2);
        //comboBox2.setValue();
        comboBox3.setMaxWidth(150);
        comboBox3.setLayoutX(160);
        comboBox3.setLayoutY(130);

        Button save = new Button("СОХРАНИТЬ");
        save.setLayoutX(170);
        save.setLayoutY(200);


        String finalId1 = id;
        save.setOnAction(x ->{
            String t1,t2;
            String finalId = finalId1;
            t1 = comboBox2.getSelectionModel().getSelectedItem();
            t2 = comboBox3.getSelectionModel().getSelectedItem();

            try {
                DataBase.addWork(finalId,t1,t2);
                Pane p = Work_Admin.getPane(pane,scrollPane);
                scrollPane.setContent(p);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            newWindow.close();
        });
        root_add.getChildren().addAll(save,title,comboBox3,name_text);
        root_add.getChildren().addAll(work_text, comboBox2);
        newWindow.setTitle("Добавление места работы");
        newWindow.setScene(scene_add);
        newWindow.show();
    }
}
