package com.example.demo.Model;

import com.example.demo.Database.DataBase;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.SQLException;

public class Structure_Analyst {
    String id;
    String name;
    String address;
    String specialty_name;
    String post;
    String tel;
    String num;

    public Structure_Analyst(String id, String name, String address, String specialty_name, String post, String tel, String num) {
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

        Button b1 = new Button("Общий доход");
        b1.setLayoutX(200);
        b1.setLayoutY(20);
        b1.setOnAction(v ->{
            try {
                DataBase.task3();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });

        Button b2 = new Button("Доход каждого заведения");
        b2.setLayoutX(400);
        b2.setLayoutY(20);
        b2.setOnAction(v ->{
            try {
                DataBase.task4();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
        res.getChildren().addAll(b1,b2);

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

        Structure_Local[] mas = DataBase.getStructure();
        int u = 80;
        System.out.println(mas.length);
        for(int i=0; i<mas.length;i++, u+=70){
            if(mas[i] == null){
                System.out.println("null");
                continue;

            }

            System.out.println("i:" + i + "/"+ mas[i].id);
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
            pane.getChildren().addAll(num_text,cl_text,emp_text,ser_text,price_text,str_text,point_text);
        }

        res.getChildren().addAll(num, cl, em, ser, price, str, point);
        return res;
    }

}
