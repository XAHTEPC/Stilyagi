package com.example.demo.Model;

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

public class Structure_Local {
    String id;
    String name;
    String address;
    String specialty_name;
    String post;
    String tel;
    String num;

    public Structure_Local(String id, String name, String address, String specialty_name, String post, String tel, String num) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.specialty_name = specialty_name;
        this.post = post;
        this.tel = tel;
        this.num = num;
    }
    public static Pane getPane(Pane pane, ScrollPane scrollPane) throws SQLException, FileNotFoundException, ClassNotFoundException {
        pane = new Pane();
        Pane res = pane;

        Text num = new Text("#");
        num.setLayoutX(20);
        num.setLayoutY(60);
        num.setFont(Font.font("Verdana",13));

        Text cl = new Text("Название");
        cl.setLayoutX(70);
        cl.setLayoutY(60);
        cl.setFont(Font.font("Verdana",13));

        Text em = new Text("Адрес");
        em.setLayoutX(220);
        em.setLayoutY(60);
        em.setFont(Font.font("Verdana",13));

        Text ser = new Text("Специализация");
        ser.setLayoutX(430);
        ser.setLayoutY(60);
        ser.setFont(Font.font("Verdana",13));

        Text price = new Text("Индекс");
        price.setLayoutX(550);
        price.setLayoutY(60);
        price.setFont(Font.font("Verdana",13));

        Text str = new Text("Телефон");
        str.setLayoutX(620);
        str.setLayoutY(60);
        str.setFont(Font.font("Verdana",13));

        Text point = new Text("Кол-во \nсотрудников");
        point.setLayoutX(690);
        point.setLayoutY(60);
        point.setFont(Font.font("Verdana",13));

        Structure_Local[] mas = DataBase.getStructure2();
        int u = 80;
        for(int i=0; i<mas.length;i++, u+=70){
            if(mas[i] == null)
                continue;
            //System.out.println("i:" + i + "/"+ mas[i].id);
            TextArea num_text = new TextArea();
            num_text.setEditable(false);
            num_text.setText(mas[i].id);
            num_text.setLayoutX(10);
            num_text.setLayoutY(0 + u);
            num_text.setMaxHeight(40);
            num_text.setMaxWidth(30);
            num_text.setMinHeight(40);
            num_text.setMinWidth(30);

            TextArea cl_text = new TextArea();
            cl_text.setText(mas[i].name);
            cl_text.setEditable(false);
            cl_text.setLayoutX(50);
            cl_text.setLayoutY(0 + u);
            cl_text.setMaxHeight(40);
            cl_text.setMaxWidth(140);
            cl_text.setMinWidth(140);

            TextArea emp_text = new TextArea();
            emp_text.setText(mas[i].address);
            emp_text.setEditable(false);
            emp_text.setLayoutX(200);
            emp_text.setLayoutY(0 + u);
            emp_text.setMaxHeight(40);
            emp_text.setMaxWidth(220);
            emp_text.setMinHeight(40);
            emp_text.setMinWidth(220);

            TextArea ser_text = new TextArea();
            ser_text.setText(mas[i].specialty_name);
            ser_text.setEditable(false);
            ser_text.setLayoutX(430);
            ser_text.setLayoutY(0 + u);
            ser_text.setMaxHeight(40);
            ser_text.setMaxWidth(100);
            ser_text.setMinHeight(40);
            ser_text.setMinWidth(100);

            TextArea price_text = new TextArea();
            price_text.setText(mas[i].post);
            price_text.setEditable(false);
            price_text.setLayoutX(540);
            price_text.setLayoutY(0 + u);
            price_text.setMaxHeight(40);
            price_text.setMaxWidth(70);

            TextArea str_text = new TextArea();
            str_text.setText(mas[i].tel);
            str_text.setEditable(false);
            str_text.setLayoutX(620);
            str_text.setLayoutY(0 + u);
            str_text.setMaxHeight(40);
            str_text.setMaxWidth(60);
            str_text.setMinHeight(40);
            str_text.setMinWidth(60);

            TextArea point_text = new TextArea();
            point_text.setText(mas[i].num);
            point_text.setEditable(false);
            point_text.setLayoutX(690);
            point_text.setLayoutY(0 + u);
            point_text.setMaxHeight(40);
            point_text.setMaxWidth(40);
            point_text.setMinHeight(40);
            point_text.setMinWidth(40);
            final String a = mas[i].id;

            FileInputStream Url = new FileInputStream("png/pen.png");
            Image url = new Image(Url);
            ImageView pen = new ImageView(url);

            Button edit = new Button();
            edit.setGraphic(pen);
            edit.setLayoutX(740);
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

            pane.getChildren().addAll(num_text,cl_text,emp_text,ser_text,price_text,str_text,point_text, edit);
        }

        res.getChildren().addAll(num, cl, em, ser, price, str, point);
        return res;
    }

    public static void change(Pane pane, String id,  ScrollPane scrollPane) throws SQLException, FileNotFoundException, ClassNotFoundException {
        Group root_add = new Group();
        Structure_Local el = DataBase.getStructure_byID(id);
        Scene scene_add = new Scene(root_add, 400, 400);
        Stage newWindow = new Stage();
        newWindow.initStyle(StageStyle.TRANSPARENT);
        //newWindow.initStyle(StageStyle.UTILITY);

        FileInputStream Url = new FileInputStream("png/front2.jpg");
        Image url = new Image(Url);
        ImageView front = new ImageView(url);
        front.setX(0);
        front.setY(0);
        //root_add.getChildren().add(front);

        Text title = new Text("Редактирование заведения");
        title.setLayoutX(100);
        title.setLayoutY(20);
        title.setFont(Font.font("Verdana",16));

        Text name_text = new Text("Название:");
        name_text.setLayoutX(10);
        name_text.setLayoutY(55);
        name_text.setFont(Font.font("Verdana",13));

        TextArea name = new TextArea();
        name.setText(el.name);
        name.setLayoutX(160);
        name.setLayoutY(30);
        name.setMaxHeight(40);
        name.setMaxWidth(200);

        Text address_text = new Text("Адресс:");
        address_text.setLayoutX(10);
        address_text.setLayoutY(95);
        address_text.setFont(Font.font("Verdana",13));

        TextArea address = new TextArea();
        address.setText(el.address);
        address.setLayoutX(160);
        address.setLayoutY(75);
        address.setMaxHeight(40);
        address.setMaxWidth(200);

        Text sp_text = new Text("Специализация:");
        sp_text.setLayoutX(10);
        sp_text.setLayoutY(145);
        sp_text.setFont(Font.font("Verdana",13));

//        TextArea sp = new TextArea();
//        sp.setText(el.specialty_name);
//        sp.setEditable(false);
//        sp.setLayoutX(160);
//        sp.setLayoutY(30);
//        sp.setMaxHeight(13);
//        sp.setMaxWidth(200);

        ObservableList<String> type = FXCollections.observableArrayList("Салон красоты", "Маникюрный салон");
        ComboBox<String> comboBox = new ComboBox<String>(type);
        comboBox.setValue(el.specialty_name);
        comboBox.setLayoutX(160);
        comboBox.setLayoutY(125);

        Text post_text = new Text("Индекс:");
        post_text.setLayoutX(10);
        post_text.setLayoutY(195);
        post_text.setFont(Font.font("Verdana",13));

        TextArea post = new TextArea();
        post.setText(el.post);
        post.setLayoutX(160);
        post.setLayoutY(170);
        post.setMaxHeight(20);
        post.setMaxWidth(200);

        Text tel_text = new Text("Телефон");
        tel_text.setLayoutX(10);
        tel_text.setLayoutY(245);
        tel_text.setFont(Font.font("Verdana",13));

        TextArea tel = new TextArea();
        tel.setText(el.tel);
        tel.setLayoutX(160);
        tel.setLayoutY(220);
        tel.setMaxHeight(40);
        tel.setMaxWidth(200);

        Text num_text = new Text("Кол-во сотрудников");
        num_text.setLayoutX(10);
        num_text.setLayoutY(295);
        num_text.setFont(Font.font("Verdana",13));

        TextArea num = new TextArea();
        num.setText(el.num);
        num.setLayoutX(160);
        num.setLayoutY(270);
        num.setMaxHeight(40);
        num.setMaxWidth(200);

        Button save = new Button("СОХРАНИТЬ");
        save.setLayoutX(170);
        save.setLayoutY(350);

        String finalId = id;
        save.setOnAction(x ->{
            String t1,t2,t3,t4,t5, t6;
            t1 = name.getText();
            t2 = address.getText();
            t3 = comboBox.getSelectionModel().getSelectedItem();
            if(t3.equals("Салон красоты"))
                t3 = "1";
            else
                t3 = "2";
            t4 = post.getText();
            t5 = tel.getText();
            t6 = num.getText();

            try {
                DataBase.UpdateStructure(finalId,t1,t2,t3,t4,t5,t6);
                Pane p = Structure_Local.getPane(pane,scrollPane);
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
        root_add.getChildren().addAll(save,title,name,name_text,address,address_text,sp_text,comboBox,post_text,post,tel,tel_text);
        root_add.getChildren().addAll(num,num_text);
        newWindow.setTitle("Редактирование клиента");
        newWindow.setScene(scene_add);
        newWindow.show();

    }

}
