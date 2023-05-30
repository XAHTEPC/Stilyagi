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

public class Employee_Analyst {

    String id;
    String name;
    String post;
    String exp;
    String sal;
    String inf;
    String age;
    String score;
    String num;

    public Employee_Analyst(String id, String name, String post, String exp,
                            String sal, String inf, String age, String score, String num) {
        this.id = id;
        this.name = name;
        this.post = post;
        this.exp = exp;
        this.sal = sal;
        this.inf = inf;
        this.age = age;
        this.score = score;
        this.num = num;
    }
    public static Pane getPane(Pane pane, ScrollPane scrollPane) throws SQLException, FileNotFoundException, ClassNotFoundException {
        pane = new Pane();
        Pane res = pane;

        Button b2 = new Button("Лучшие сотрудники");
        b2.setLayoutX(400);
        b2.setLayoutY(20);
        b2.setOnAction(v ->{
            try {
                DataBase.task5();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
        res.getChildren().addAll(b2);

        Text num = new Text("#");
        num.setLayoutX(20);
        num.setLayoutY(60);
        num.setFont(Font.font("Verdana", 13));

        Text em = new Text("ФИО");
        em.setLayoutX(70);
        em.setLayoutY(60);
        em.setFont(Font.font("Verdana", 13));

        Text pos = new Text("Должность");
        pos.setLayoutX(230);
        pos.setLayoutY(60);
        pos.setFont(Font.font("Verdana", 13));

        Text exp = new Text("Стаж");
        exp.setLayoutX(325);
        exp.setLayoutY(60);
        exp.setFont(Font.font("Verdana", 13));

        Text sal = new Text("Зарплата");
        sal.setLayoutX(365);
        sal.setLayoutY(60);
        sal.setFont(Font.font("Verdana", 13));

        Text inf = new Text("Информ");
        inf.setLayoutX(440);
        inf.setLayoutY(60);
        inf.setFont(Font.font("Verdana", 13));

        Text age = new Text("Возраст");
        age.setLayoutX(530);
        age.setLayoutY(60);
        age.setFont(Font.font("Verdana", 13));

        Text sc = new Text("Баллы");
        sc.setLayoutX(590);
        sc.setLayoutY(60);
        sc.setFont(Font.font("Verdana", 13));

        Text tel = new Text("Номер");
        tel.setLayoutX(650);
        tel.setLayoutY(60);
        tel.setFont(Font.font("Verdana", 13));

        Employee_Local[] mas = DataBase.getAllEmployee();
        int u = 80;
        for (int i = 0; i < mas.length; i++, u += 70) {
            if (mas[i] == null)
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

            TextArea em_text = new TextArea();
            em_text.setText(mas[i].name);
            em_text.setEditable(false);
            em_text.setLayoutX(50);
            em_text.setLayoutY(0 + u);
            em_text.setMaxHeight(40);
            em_text.setMaxWidth(160);
            em_text.setMinWidth(160);

            TextArea post_text = new TextArea();
            post_text.setText(mas[i].post);
            post_text.setEditable(false);
            post_text.setLayoutX(220);
            post_text.setLayoutY(0 + u);
            post_text.setMaxHeight(40);
            post_text.setMaxWidth(100);
            post_text.setMinHeight(40);
            post_text.setMinWidth(100);

            TextArea exp_text = new TextArea();
            exp_text.setText(mas[i].exp);
            exp_text.setEditable(false);
            exp_text.setLayoutX(330);
            exp_text.setLayoutY(0 + u);
            exp_text.setMaxHeight(40);
            exp_text.setMaxWidth(30);
            exp_text.setMinHeight(40);
            exp_text.setMinWidth(30);

            TextArea sal_text = new TextArea();
            sal_text.setText(mas[i].sal);
            sal_text.setEditable(false);
            sal_text.setLayoutX(370);
            sal_text.setLayoutY(0 + u);
            sal_text.setMaxHeight(40);
            sal_text.setMaxWidth(50);

            TextArea info_text = new TextArea();
            info_text.setText(mas[i].inf);
            info_text.setEditable(false);
            info_text.setLayoutX(430);
            info_text.setLayoutY(0 + u);
            info_text.setMaxHeight(40);
            info_text.setMaxWidth(100);
            info_text.setMinHeight(40);
            info_text.setMinWidth(100);

            TextArea age_text = new TextArea();
            age_text.setText(mas[i].age);
            age_text.setEditable(false);
            age_text.setLayoutX(540);
            age_text.setLayoutY(0 + u);
            age_text.setMaxHeight(40);
            age_text.setMaxWidth(30);
            age_text.setMinHeight(40);
            age_text.setMinWidth(30);

            TextArea score_text = new TextArea();
            score_text.setText(mas[i].score);
            score_text.setEditable(false);
            score_text.setLayoutX(590);
            score_text.setLayoutY(0 + u);
            score_text.setMaxHeight(40);
            score_text.setMaxWidth(40);
            score_text.setMinHeight(40);
            score_text.setMinWidth(40);

            TextArea tel_text = new TextArea();
            tel_text.setText(mas[i].num);
            tel_text.setEditable(false);
            tel_text.setLayoutX(640);
            tel_text.setLayoutY(0 + u);
            tel_text.setMaxHeight(40);
            tel_text.setMaxWidth(90);
            tel_text.setMinHeight(40);
            tel_text.setMinWidth(90);

            pane.getChildren().addAll(num_text, em_text, post_text, exp_text, sal_text,
                    info_text, age_text, score_text, tel_text);
        }
        res.getChildren().addAll(num, em, pos, exp, sal, inf, age, sc, tel);
        return res;
    }
}
