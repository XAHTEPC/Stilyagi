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

public class Employee_Local {
    String id;
    String name;
    String post;
    String exp;
    String sal;
    String inf;
    String age;
    String score;
    String num;

    public Employee_Local(String id, String name, String post, String exp,
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

        Text num = new Text("#");
        num.setLayoutX(20);
        num.setLayoutY(60);
        num.setFont(Font.font("Verdana",13));

        Text em = new Text("ФИО");
        em.setLayoutX(70);
        em.setLayoutY(60);
        em.setFont(Font.font("Verdana",13));

        Text pos = new Text("Должность");
        pos.setLayoutX(230);
        pos.setLayoutY(60);
        pos.setFont(Font.font("Verdana",13));

        Text exp = new Text("Стаж");
        exp.setLayoutX(325);
        exp.setLayoutY(60);
        exp.setFont(Font.font("Verdana",13));

        Text sal = new Text("Зарплата");
        sal.setLayoutX(365);
        sal.setLayoutY(60);
        sal.setFont(Font.font("Verdana",13));

        Text inf = new Text("Информ");
        inf.setLayoutX(440);
        inf.setLayoutY(60);
        inf.setFont(Font.font("Verdana",13));

        Text age = new Text("Возраст");
        age.setLayoutX(530);
        age.setLayoutY(60);
        age.setFont(Font.font("Verdana",13));

        Text sc = new Text("Баллы");
        sc.setLayoutX(590);
        sc.setLayoutY(60);
        sc.setFont(Font.font("Verdana",13));

        Text tel = new Text("Номер");
        tel.setLayoutX(650);
        tel.setLayoutY(60);
        tel.setFont(Font.font("Verdana",13));

        Employee_Local[] mas = DataBase.getAllEmployee2();
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

            pane.getChildren().addAll(num_text,em_text, post_text, exp_text, sal_text, info_text,age_text,score_text,tel_text, edit);
        }

        res.getChildren().addAll(num, em, pos, exp, sal, inf, age, sc, tel);
        return res;
    }

    public static void change(Pane pane, String id,  ScrollPane scrollPane) throws SQLException, FileNotFoundException, ClassNotFoundException {
        Group root_add = new Group();
        Employee_Local el = DataBase.getEmployee_byID(id);
        Scene scene_add = new Scene(root_add, 400, 500);
        Stage newWindow = new Stage();
        newWindow.initStyle(StageStyle.TRANSPARENT);
        //newWindow.initStyle(StageStyle.UTILITY);

        FileInputStream Url = new FileInputStream("png/front2.jpg");
        Image url = new Image(Url);
        ImageView front = new ImageView(url);
        front.setX(0);
        front.setY(0);
        //root_add.getChildren().add(front);

        Text title = new Text("Редактирование сотрудника");
        title.setLayoutX(100);
        title.setLayoutY(20);
        title.setFont(Font.font("Verdana",16));

        Text name_text = new Text("ФИО:");
        name_text.setLayoutX(10);
        name_text.setLayoutY(55);
        name_text.setFont(Font.font("Verdana",13));

        TextArea name = new TextArea();
        name.setText(el.name);
        name.setLayoutX(160);
        name.setLayoutY(30);
        name.setMaxHeight(40);
        name.setMaxWidth(200);

        Text post_text = new Text("Должность:");
        post_text.setLayoutX(10);
        post_text.setLayoutY(95);
        post_text.setFont(Font.font("Verdana",13));

        ObservableList<String> type = FXCollections.observableArrayList("Парикмахер-стилист", "Визажист",
                "Мастер маникюра", "Мастер педикюра", "Стилист", "Администратор", "Аналитик", "Менеджер","Управляющий" );
        ComboBox<String> comboBox = new ComboBox<String>(type);
        comboBox.setValue(el.post);
        comboBox.setLayoutX(160);
        comboBox.setLayoutY(80);

        Text exp_text = new Text("Стаж:");
        exp_text.setLayoutX(10);
        exp_text.setLayoutY(145);
        exp_text.setFont(Font.font("Verdana",13));

        TextArea exp = new TextArea();
        exp.setText(el.exp);
        exp.setLayoutX(160);
        exp.setLayoutY(120);
        exp.setMaxHeight(13);
        exp.setMaxWidth(200);

        Text sal_text = new Text("Зарплата:");
        sal_text.setLayoutX(10);
        sal_text.setLayoutY(195);
        sal_text.setFont(Font.font("Verdana",13));

        TextArea sal = new TextArea();
        sal.setText(el.sal);
        sal.setLayoutX(160);
        sal.setLayoutY(170);
        sal.setMaxHeight(20);
        sal.setMaxWidth(200);
        sal.setEditable(false);

        Text inf_text = new Text("Информация:");
        inf_text.setLayoutX(10);
        inf_text.setLayoutY(245);
        inf_text.setFont(Font.font("Verdana",13));

        TextArea inf = new TextArea();
        inf.setText(el.inf);
        inf.setLayoutX(160);
        inf.setLayoutY(220);
        inf.setMaxHeight(80);
        inf.setMaxWidth(200);

        Text age_text = new Text("Возраст");
        age_text.setLayoutX(10);
        age_text.setLayoutY(345);
        age_text.setFont(Font.font("Verdana",13));

        TextArea age = new TextArea();
        age.setText(el.age);
        age.setLayoutX(160);
        age.setLayoutY(310);
        age.setMaxHeight(40);
        age.setMaxWidth(200);

        Text sc_text = new Text("Рейтинг:");
        sc_text.setLayoutX(10);
        sc_text.setLayoutY(385);
        sc_text.setFont(Font.font("Verdana",13));

        TextArea sc = new TextArea();
        sc.setText(el.score);
        sc.setLayoutX(160);
        sc.setLayoutY(360);
        sc.setMaxHeight(40);
        sc.setMaxWidth(200);
        sc.setEditable(false);

        Text tel_text = new Text("Телефон:");
        tel_text.setLayoutX(10);
        tel_text.setLayoutY(435);
        tel_text.setFont(Font.font("Verdana",13));

        TextArea tel = new TextArea();
        tel.setText(el.num);
        tel.setLayoutX(160);
        tel.setLayoutY(410);
        tel.setMaxHeight(40);
        tel.setMaxWidth(200);

        Button save = new Button("СОХРАНИТЬ");
        save.setLayoutX(170);
        save.setLayoutY(470);

        String finalId = id;
        save.setOnAction(x ->{
            String t1,t2,t3,t4,t5,t6,t7,t8;
            t1 = name.getText();
            t2 = comboBox.getSelectionModel().getSelectedItem();
            if(t2.equals("Парикмахер-стилист"))
                t2 = "1";
            else if(t2.equals("Визажист"))
                t2 = "2";
            else if(t2.equals("Мастер маникюра"))
                t2 = "3";
            else if(t2.equals("Мастер педикюра"))
                t2 = "4";
            else if(t2.equals("Стилист"))
                t2 = "5";
            else if(t2.equals("Администратор"))
                t2 = "6";
            else if(t2.equals("Аналитик"))
                t2 = "7";
            else if(t2.equals("Менеджер"))
                t2 = "8";
            else
                t2 = "9";
            t3 = exp.getText();
            t4 = sal.getText();
            t5 = inf.getText();
            t6 = age.getText();
            t7 = sc.getText();
            t8 = tel.getText();

            try {
                DataBase.UpdateEmployee(finalId,t1,t2,t3,t4,t5,t6,t7,t8);
                Pane p = Employee_Local.getPane(pane,scrollPane);
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
        root_add.getChildren().addAll(save,title,name,name_text, post_text,comboBox,exp,exp_text,sal_text,sal);
        root_add.getChildren().addAll(inf,inf_text,age,age_text,sc,sc_text,tel,tel_text);
        newWindow.setTitle("Редактирование клиента");
        newWindow.setScene(scene_add);
        newWindow.show();

    }

}

