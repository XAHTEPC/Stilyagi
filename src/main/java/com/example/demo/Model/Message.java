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
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Message {
    String id;
    String title;
    String description;
    String status;
    String from;
    String date;
    String to;

    public Message(String id, String title, String description, String status, String from, String date, String to) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.from = from;
        this.date = date;
        this.to = to;
    }

    public static Pane getPane(Pane pane, ScrollPane scrollPane, String id) throws SQLException, FileNotFoundException, ClassNotFoundException {
        pane = new Pane();
        Pane res = pane;

        Button add = new Button("Добавить заявку");
        add.setLayoutX(300);
        add.setLayoutY(10);
        res.getChildren().add(add);

        Text num = new Text("#");
        num.setLayoutX(20);
        num.setLayoutY(60);
        num.setFont(Font.font("Verdana", 13));

        Text em = new Text("Название");
        em.setLayoutX(70);
        em.setLayoutY(60);
        em.setFont(Font.font("Verdana", 13));

        Text pos = new Text("Описание");
        pos.setLayoutX(230);
        pos.setLayoutY(60);
        pos.setFont(Font.font("Verdana", 13));

        Text exp = new Text("Статус");
        exp.setLayoutX(335);
        exp.setLayoutY(60);
        exp.maxWidth(100);
        exp.minWidth(100);
        exp.setFont(Font.font("Verdana", 13));

        Text sal = new Text("Заявка от");
        sal.setLayoutX(445);
        sal.setLayoutY(60);
        sal.minWidth(150);
        sal.maxWidth(150);
        sal.setFont(Font.font("Verdana", 13));

        Text inf = new Text("Дата");
        inf.setLayoutX(570);
        inf.setLayoutY(60);
        inf.setFont(Font.font("Verdana", 13));

        Text kar = new Text("Ответственный");
        kar.setLayoutX(670);
        kar.setLayoutY(60);
        kar.setFont(Font.font("Verdana", 13));

        pane.getChildren().addAll(inf, sal,exp, kar);
        Message[] mas = DataBase.getMessage(id);
        int u = 80;
        for (int i = 0; i < mas.length; i++, u += 70) {
            if (mas[i] == null)
                continue;
            Employee_Local emp_from = DataBase.getEmployee_byID(mas[i].from);
            Employee_Local emp_to = DataBase.getEmployee_byID(mas[i].to);
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
            em_text.setText(mas[i].title);
            em_text.setEditable(false);
            em_text.setLayoutX(50);
            em_text.setLayoutY(0 + u);
            em_text.setMaxHeight(40);
            em_text.setMaxWidth(160);
            em_text.setMinWidth(160);

            TextArea post_text = new TextArea();
            post_text.setText(mas[i].description);
            post_text.setEditable(false);
            post_text.setLayoutX(220);
            post_text.setLayoutY(0 + u);
            post_text.setMaxHeight(40);
            post_text.setMaxWidth(100);
            post_text.setMinHeight(40);
            post_text.setMinWidth(100);

            TextArea exp_text = new TextArea();
            exp_text.setText(mas[i].status);
            exp_text.setEditable(false);
            exp_text.setLayoutX(330);
            exp_text.setLayoutY(0 + u);
            exp_text.setMaxHeight(40);
            exp_text.setMaxWidth(30);
            exp_text.setMinHeight(40);
            exp_text.setMinWidth(100);

            TextArea sal_text = new TextArea();
            sal_text.setText(emp_from.name);
            sal_text.setEditable(false);
            sal_text.setLayoutX(440);
            sal_text.setLayoutY(0 + u);
            sal_text.setMaxHeight(40);
            sal_text.setMaxWidth(100);

            TextArea info_text = new TextArea();
            info_text.setText(mas[i].date);
            info_text.setEditable(false);
            info_text.setLayoutX(550);
            info_text.setLayoutY(0 + u);
            info_text.setMaxHeight(40);
            info_text.setMaxWidth(100);
            info_text.setMinHeight(40);
            info_text.setMinWidth(80);

            TextArea age_text = new TextArea();
            age_text.setText(emp_to.name);
            age_text.setEditable(false);
            age_text.setLayoutX(660);
            age_text.setLayoutY(0 + u);
            age_text.setMaxHeight(40);
            age_text.setMaxWidth(80);
            age_text.setMinHeight(40);
            age_text.setMinWidth(80);


            final String mess_id = mas[i].id;

            FileInputStream Url = new FileInputStream("png/pen.png");
            Image url = new Image(Url);
            ImageView pen = new ImageView(url);

            Button edit = new Button();
            edit.setGraphic(pen);
            edit.setLayoutX(750);
            edit.setLayoutY(0 + u);

            Pane finalPane = pane;
            edit.setOnAction(r -> {
                try {
                    change(finalPane, id, scrollPane, mess_id);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } catch (FileNotFoundException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            });

            pane.getChildren().addAll(num_text, em_text, post_text, exp_text, sal_text, info_text, age_text, edit);
        }

        Pane finalPane1 = pane;
        add.setOnAction(r -> {
            try {
                add(finalPane1,id,scrollPane);
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

    public static void change(Pane pane, String emp_id, ScrollPane scrollPane, String mess_id) throws SQLException, FileNotFoundException, ClassNotFoundException {
        Group root_add = new Group();
        Message mess = DataBase.getMessage_byID(mess_id);
        Scene scene_add = new Scene(root_add, 400, 500);
        Stage newWindow = new Stage();
        newWindow.initStyle(StageStyle.UTILITY);
        //newWindow.initStyle(StageStyle.UTILITY);

        FileInputStream Url = new FileInputStream("png/front2.jpg");
        Image url = new Image(Url);
        ImageView front = new ImageView(url);
        front.setX(0);
        front.setY(0);
        //root_add.getChildren().add(front);

        Text title = new Text("Редактирование заявки");
        title.setLayoutX(100);
        title.setLayoutY(20);
        title.setFont(Font.font("Verdana", 16));

        Text name_text = new Text("Название:");
        name_text.setLayoutX(10);
        name_text.setLayoutY(55);
        name_text.setFont(Font.font("Verdana", 13));

        TextArea name = new TextArea();
        name.setText(mess.title);
        name.setEditable(false);
        name.setLayoutX(160);
        name.setLayoutY(30);
        name.setMaxHeight(40);
        name.setMaxWidth(200);

        Text desc_text = new Text("Описание:");
        desc_text.setLayoutX(10);
        desc_text.setLayoutY(105);
        desc_text.setFont(Font.font("Verdana", 13));

        TextArea desc = new TextArea();
        desc.setText(mess.description);
        desc.setEditable(false);
        desc.setLayoutX(160);
        desc.setLayoutY(80);
        desc.setMaxHeight(80);
        desc.setMaxWidth(200);

        Text work_text = new Text("Статус:");
        work_text.setLayoutX(10);
        work_text.setLayoutY(205);
        work_text.setFont(Font.font("Verdana", 13));

        ObservableList<String> work2 = FXCollections.observableArrayList("Готово", "В обработке");
        ComboBox<String> comboBox2 = new ComboBox<String>(work2);
        comboBox2.setValue(mess.status);
        comboBox2.setMaxWidth(150);
        comboBox2.setLayoutX(160);
        comboBox2.setLayoutY(180);
        comboBox2.setEditable(false);

        Text from_text = new Text("Поступила от:");
        from_text.setLayoutX(10);
        from_text.setLayoutY(255);
        from_text.setFont(Font.font("Verdana", 13));
        Employee_Local em_from = DataBase.getEmployee_byID(mess.from);
        Employee_Local em_to = DataBase.getEmployee_byID(mess.to);

        TextArea from = new TextArea();
        from.setText(em_from.name);
        from.setEditable(false);
        from.setLayoutX(160);
        from.setLayoutY(230);
        from.setMaxHeight(80);
        from.setMaxWidth(200);

        Text date_text = new Text("Дата поступления:");
        date_text.setLayoutX(10);
        date_text.setLayoutY(295);
        date_text.setFont(Font.font("Verdana", 13));

        TextArea date = new TextArea();
        date.setText(mess.date);
        date.setEditable(false);
        date.setLayoutX(160);
        date.setLayoutY(270);
        date.setMaxHeight(40);
        date.setMaxWidth(200);

        Text to_text = new Text("Ответсвенный:");
        to_text.setLayoutX(10);
        to_text.setLayoutY(345);
        to_text.setFont(Font.font("Verdana", 13));

        TextArea to = new TextArea();
        to.setText(em_to.name);
        to.setEditable(false);
        to.setLayoutX(160);
        to.setLayoutY(330);
        to.setMaxHeight(40);
        to.setMaxWidth(200);

        Button save = new Button("СОХРАНИТЬ");
        save.setLayoutX(100);
        save.setLayoutY(400);


        save.setOnAction(x -> {
            String t;
            String finalId = mess_id;
            t = comboBox2.getSelectionModel().getSelectedItem();
            try {
                DataBase.UpdateMessage(finalId, t);
                Pane p = Message.getPane(pane, scrollPane, emp_id);
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


        root_add.getChildren().addAll(save, title, name, name_text, desc_text, desc, work_text, comboBox2, from_text, from);
        root_add.getChildren().addAll(date_text, date, to, to_text);
        newWindow.setTitle("Редактирование заявки");
        newWindow.setScene(scene_add);
        newWindow.show();
    }

    public static void add(Pane pane, String emp_id, ScrollPane scrollPane) throws SQLException, FileNotFoundException, ClassNotFoundException {
        Group root_add = new Group();
        Scene scene_add = new Scene(root_add, 400, 500);
        Stage newWindow = new Stage();
        String mess_id = DataBase.getLastMessage();
        int m =0;
        if(mess_id != null)
            m = Integer.parseInt(mess_id);
        m++;
        mess_id = String.valueOf(m);
        newWindow.initStyle(StageStyle.UTILITY);
        //newWindow.initStyle(StageStyle.UTILITY);

        FileInputStream Url = new FileInputStream("png/front2.jpg");
        Image url = new Image(Url);
        ImageView front = new ImageView(url);
        front.setX(0);
        front.setY(0);
        //root_add.getChildren().add(front);

        Text title = new Text("Создание заявки");
        title.setLayoutX(100);
        title.setLayoutY(20);
        title.setFont(Font.font("Verdana", 16));

        Text name_text = new Text("Название:");
        name_text.setLayoutX(10);
        name_text.setLayoutY(55);
        name_text.setFont(Font.font("Verdana", 13));

        TextArea name = new TextArea();
        name.setPromptText("название");
        name.setLayoutX(160);
        name.setLayoutY(30);
        name.setMaxHeight(40);
        name.setMaxWidth(200);

        Text desc_text = new Text("Описание:");
        desc_text.setLayoutX(10);
        desc_text.setLayoutY(105);
        desc_text.setFont(Font.font("Verdana", 13));

        TextArea desc = new TextArea();
        desc.setPromptText("описание");
        desc.setLayoutX(160);
        desc.setLayoutY(80);
        desc.setMaxHeight(80);
        desc.setMaxWidth(200);

        Text work_text = new Text("Статус:");
        work_text.setLayoutX(10);
        work_text.setLayoutY(205);
        work_text.setFont(Font.font("Verdana", 13));

        ObservableList<String> work2 = FXCollections.observableArrayList("Готово", "В обработке");
        ComboBox<String> comboBox2 = new ComboBox<String>(work2);
        comboBox2.setValue("В обработке");
        comboBox2.setMaxWidth(150);
        comboBox2.setLayoutX(160);
        comboBox2.setLayoutY(180);
        comboBox2.setEditable(false);

        Text from_text = new Text("Поступила от:");
        from_text.setLayoutX(10);
        from_text.setLayoutY(255);
        from_text.setFont(Font.font("Verdana", 13));
        System.out.println(emp_id);
        Employee_Local em = DataBase.getEmployee_byID(emp_id);

        TextArea from = new TextArea();
        from.setText(em.name);
        from.setEditable(false);
        from.setLayoutX(160);
        from.setLayoutY(230);
        from.setMaxHeight(80);
        from.setMaxWidth(200);

        Text date_text = new Text("Дата поступления:");
        date_text.setLayoutX(10);
        date_text.setLayoutY(295);
        date_text.setFont(Font.font("Verdana", 13));

        TextArea date = new TextArea();
        Format formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date dat = new Date();
        String string = formatter.format(dat);
        date.setText(string);
        date.setEditable(false);
        date.setLayoutX(160);
        date.setLayoutY(270);
        date.setMaxHeight(40);
        date.setMaxWidth(200);

        Text to_text = new Text("Ответсвенный:");
        to_text.setLayoutX(10);
        to_text.setLayoutY(345);
        to_text.setFont(Font.font("Verdana", 13));
        String[] mas = DataBase.getEmployee_name();

        ObservableList<String> work = FXCollections.observableArrayList(mas);
        ComboBox<String> comboBox = new ComboBox<String>(work);
        //comboBox.setValue("В обработке");
        comboBox.setMaxWidth(150);
        comboBox.setLayoutX(160);
        comboBox.setLayoutY(330);

        Button save = new Button("СОХРАНИТЬ");
        save.setLayoutX(100);
        save.setLayoutY(400);

        String finalMess_id = mess_id;
        save.setOnAction(x -> {
            String t1,t2,t3,t4,t5,t6;
            String t = finalMess_id;
            t1 = name.getText();
            t2 = desc.getText();
            t3 = comboBox2.getSelectionModel().getSelectedItem();
            t4 = emp_id;
            t5 = date.getText();
            t6 = comboBox.getSelectionModel().getSelectedItem();
            try {
                DataBase.addMessage(t,t1,t2,t3,t4,t5,t6);
                Pane p = Message.getPane(pane, scrollPane, emp_id);
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


        root_add.getChildren().addAll(save, title, name, name_text, desc_text, desc, work_text, comboBox2, from_text, from);
        root_add.getChildren().addAll(date_text, date, comboBox, to_text);
        newWindow.setTitle("Редактирование заявки");
        newWindow.setScene(scene_add);
        newWindow.show();
    }
}

