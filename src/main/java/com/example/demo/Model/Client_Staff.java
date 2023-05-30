package com.example.demo.Model;

import com.example.demo.Database.DataBase;
import com.example.demo.HelloApplication;
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

public class Client_Staff {
    String id;
    String name;
    String status;
    String bonus;

    public Client_Staff(String id, String name, String status, String bonus) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.bonus = bonus;
    }
    public static Pane getPane(Pane pane, ScrollPane scrollPane) throws SQLException, FileNotFoundException, ClassNotFoundException {
        pane = new Pane();
        Pane res = pane;

        TextArea name_find = new TextArea();
        name_find.setPromptText("ФИО клиента");
        name_find.setLayoutX(20);
        name_find.setLayoutY(10);
        name_find.setMaxHeight(40);
        name_find.setMaxWidth(310);
        name_find.setMinWidth(310);
        res.getChildren().addAll(name_find);

        Button find = new Button("Поиск клиента");
        find.setLayoutX(350);
        find.setLayoutY(10);
        res.getChildren().add(find);

        Button add = new Button("Добавить клиента");
        add.setLayoutX(470);
        add.setLayoutY(10);
        res.getChildren().add(add);

        Text num = new Text("#");
        num.setLayoutX(30);
        num.setLayoutY(80);
        num.setFont(Font.font("Verdana",13));

        Text date = new Text("ФИО");
        date.setLayoutX(70);
        date.setLayoutY(80);
        date.setFont(Font.font("Verdana",13));

        Text description = new Text("Статус");
        description.setLayoutX(400);
        description.setLayoutY(80);
        description.setFont(Font.font("Verdana",13));

        Text price = new Text("Бонус");
        price.setLayoutX(530);
        price.setLayoutY(80);
        price.setFont(Font.font("Verdana",13));

        Client_Staff[] mas = DataBase.getAllClient_Staff();
        int u = 100;
        for(int i=0; i<mas.length;i++, u+=70){
            if(mas[i]==null)
                continue;
            //System.out.println("i:" + i + "/"+ mas[i].id);
            TextArea num_text = new TextArea();
            num_text.setEditable(false);
            num_text.setText(mas[i].id);
            num_text.setLayoutX(20);
            num_text.setLayoutY(0 + u);
            num_text.setMaxHeight(40);
            num_text.setMaxWidth(30);
            num_text.setMinHeight(40);
            num_text.setMinWidth(30);

            TextArea DATA = new TextArea();
            DATA.setText(mas[i].name);
            DATA.setEditable(false);
            DATA.setLayoutX(60);
            DATA.setLayoutY(0 + u);
            DATA.setMaxHeight(40);
            DATA.setMaxWidth(310);
            DATA.setMinWidth(310);

            TextArea description_text = new TextArea();
            description_text.setText(mas[i].status);
            description_text.setEditable(false);
            description_text.setLayoutX(400);
            description_text.setLayoutY(0 + u);
            description_text.setMaxHeight(40);
            description_text.setMaxWidth(100);
            description_text.setMinHeight(40);
            description_text.setMinWidth(80);

            TextArea price_text = new TextArea();
            price_text.setText(mas[i].bonus);
            price_text.setEditable(false);
            price_text.setLayoutX(520);
            price_text.setLayoutY(0 + u);
            price_text.setMaxHeight(40);
            price_text.setMaxWidth(80);

            Button pos = new Button("Добавить посещение");
            pos.setLayoutX(600);
            pos.setLayoutY(0 + u);

            final String id = mas[i].id;
            Pane finalPane1 = pane;
            pos.setOnAction(val ->{
                try {
                    add_pos(finalPane1, scrollPane, id);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            });

            pane.getChildren().addAll(pos,description_text, price_text, DATA, num_text);
        }
        Pane finalPane = pane;
        find.setOnAction(value ->{
            String t = name_find.getText();
            System.out.println(t);
            String[][] client = null;
            try {
                client = DataBase.getClient_byNAME(t);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            if(client == null){
                name_find.setText("Такого клиента не существует");
                return;
            }
            try {

                print(finalPane,scrollPane, client);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        });

        add.setOnAction(value ->{
            try {
                add(res, scrollPane);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        });

        res.getChildren().addAll(price, num, description,date);
        return res;
    }
    public static void add(Pane pane,  ScrollPane scrollPane) throws SQLException, FileNotFoundException {
        Group root_add = new Group();
        String id = DataBase.getLastClient();
        int t = Integer.parseInt(id);
        t++;
        id = String.valueOf(t);
        Scene scene_add = new Scene(root_add, 400, 300);
        Stage newWindow = new Stage();
        newWindow.initStyle(StageStyle.UTILITY);

        FileInputStream Url = new FileInputStream("png/front2.jpg");
        Image url = new Image(Url);
        ImageView front = new ImageView(url);
        front.setX(0);
        front.setY(0);
        //root_add.getChildren().add(front);

        Text title = new Text("Редактирование клиента");
        title.setLayoutX(100);
        title.setLayoutY(20);
        title.setFont(Font.font("Verdana",16));

        Text name_text = new Text("ФИО:");
        name_text.setLayoutX(10);
        name_text.setLayoutY(55);
        name_text.setFont(Font.font("Verdana",13));

        TextArea name = new TextArea();
        name.setPromptText("ФИО");
        name.setLayoutX(160);
        name.setLayoutY(30);
        name.setMaxHeight(13);
        name.setMaxWidth(200);

        Text number_text = new Text("Статус:");
        number_text.setLayoutX(10);
        number_text.setLayoutY(105);
        number_text.setFont(Font.font("Verdana",13));

        TextArea number = new TextArea();
        number.setText("Обычный");
        number.setEditable(false);
        number.setLayoutX(160);
        number.setLayoutY(80);
        number.setMaxHeight(20);
        number.setMaxWidth(200);

        Text vin_text = new Text("Бонус:");
        vin_text.setLayoutX(10);
        vin_text.setLayoutY(155);
        vin_text.setFont(Font.font("Verdana",13));

        TextArea vin = new TextArea();
        vin.setText("0");
        vin.setEditable(false);
        vin.setLayoutX(160);
        vin.setLayoutY(130);
        vin.setMaxHeight(40);
        vin.setMaxWidth(200);

        Button save = new Button("СОХРАНИТЬ");
        save.setLayoutX(170);
        save.setLayoutY(250);


        String finalId = id;
        save.setOnAction(x ->{
            String t1,t2,t3,t4,t5;
            t1 = name.getText();
            t2 = number.getText();
            t3 = vin.getText();

            try {
                DataBase.addClient(finalId,t1,t2,t3);
                Pane p = Client_Staff.getPane(pane,scrollPane);
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
        root_add.getChildren().addAll(save,title,name,name_text, vin, vin_text, number_text, number);
        newWindow.setTitle("Добавление клиента");
        newWindow.setScene(scene_add);
        newWindow.show();
    }

    public static void add_pos(Pane pane,  ScrollPane scrollPane, String id) throws SQLException, FileNotFoundException, ClassNotFoundException {
        Group root_add = new Group();
        String id_visit = DataBase.getLastVisit();
        int t = Integer.parseInt(id_visit);
        t++;
        id_visit = String.valueOf(t);
        String[] mas = DataBase.getClient_byID(id);
        Scene scene_add = new Scene(root_add, 400, 400);
        Stage newWindow = new Stage();
        newWindow.initStyle(StageStyle.UTILITY);

        FileInputStream Url = new FileInputStream("png/front2.jpg");
        Image url = new Image(Url);
        ImageView front = new ImageView(url);
        front.setX(0);
        front.setY(0);
        //root_add.getChildren().add(front);

        Text title = new Text("Добавление посещения");
        title.setLayoutX(100);
        title.setLayoutY(20);
        title.setFont(Font.font("Verdana",16));

        Text name_text = new Text("ФИО Клиента:");
        name_text.setLayoutX(10);
        name_text.setLayoutY(55);
        name_text.setFont(Font.font("Verdana",13));

        TextArea name = new TextArea();
        name.setText(mas[1]);
        name.setLayoutX(160);
        name.setLayoutY(30);
        name.setMaxHeight(13);
        name.setMaxWidth(200);

        Text str_text = new Text("Заведение:");
        str_text.setLayoutX(10);
        str_text.setLayoutY(105);
        str_text.setFont(Font.font("Verdana",13));
        String[] mas1 = DataBase.getStructure_name();

        ObservableList<String> work = FXCollections.observableArrayList(mas1);
        ComboBox<String> comboBox2 = new ComboBox<String>(work);
        //comboBox2.setValue(el.post);
        comboBox2.setMaxWidth(150);
        comboBox2.setLayoutX(160);
        comboBox2.setLayoutY(80);

        Text service_text = new Text("Услуга:");
        service_text.setLayoutX(10);
        service_text.setLayoutY(155);
        service_text.setFont(Font.font("Verdana",13));

        String[] mas2 = DataBase.getService_name();
        ObservableList<String> service = FXCollections.observableArrayList(mas2);
        ComboBox<String> comboBox3 = new ComboBox<String>(service);
        //comboBox2.setValue(el.post);
        comboBox3.setMaxWidth(150);
        comboBox3.setLayoutX(160);
        comboBox3.setLayoutY(130);

        Text date_text = new Text("Дата");
        date_text.setLayoutX(10);
        date_text.setLayoutY(205);
        date_text.setFont(Font.font("Verdana",13));

        TextArea DATA = new TextArea();
        Format formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date();
        String string = formatter.format(date);
        DATA.setText(string);
        DATA.setLayoutX(160);
        DATA.setLayoutY(180);
        DATA.setMaxHeight(40);
        DATA.setMaxWidth(200);

        Text emp_text = new Text("Сотрудник:");
        emp_text.setLayoutX(10);
        emp_text.setLayoutY(255);
        emp_text.setFont(Font.font("Verdana",13));
        String u = DataBase.getCurrent_User();

        TextArea emp = new TextArea();
        emp.setText(u);
        emp.setEditable(false);
        emp.setLayoutX(160);
        emp.setLayoutY(230);
        emp.setMaxHeight(40);
        emp.setMaxWidth(200);

        Text score_text = new Text("Рейтинг:");
        score_text.setLayoutX(10);
        score_text.setLayoutY(305);
        score_text.setFont(Font.font("Verdana",13));

        TextArea vin = new TextArea();
        vin.setText("0.0");
        vin.setLayoutX(160);
        vin.setLayoutY(280);
        vin.setMaxHeight(40);
        vin.setMaxWidth(200);

        Button save = new Button("СОХРАНИТЬ");
        save.setLayoutX(170);
        save.setLayoutY(330);

        String finalId_visit = id_visit;
        save.setOnAction(x ->{
            String t1,t2,t3,t4,t5,t6,t7 = "";
            t1 = name.getText();
            t2 = comboBox2.getSelectionModel().getSelectedItem();
            t3 = comboBox3.getSelectionModel().getSelectedItem();
            t4 = DATA.getText();
            t5 = emp.getText();
            t6 = vin.getText();
            try {
                t7 = DataBase.getPrice(t3);
                System.out.println(t7);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }

            try {
                DataBase.addVisit(finalId_visit,id,t2,t3,t4,t5,t7,t6);
                Pane p = Client_Staff.getPane(pane,scrollPane);
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
        root_add.getChildren().addAll(save,title,name,name_text, vin, score_text,emp,emp_text,comboBox2,comboBox3);
        root_add.getChildren().addAll(DATA,date_text,service_text,str_text);
        newWindow.setTitle("Добавление посещения");
        newWindow.setScene(scene_add);
        newWindow.show();
    }
    public static void print(Pane pane, ScrollPane scrollPane, String[][] mas) throws SQLException {
        pane = new Pane();
        Pane res = pane;

        TextArea name_find = new TextArea();
        name_find.setPromptText("ФИО клиента");
        name_find.setLayoutX(20);
        name_find.setLayoutY(10);
        name_find.setMaxHeight(40);
        name_find.setMaxWidth(310);
        name_find.setMinWidth(310);
        res.getChildren().addAll(name_find);

        Button find = new Button("Поиск клиента");
        find.setLayoutX(350);
        find.setLayoutY(10);
        res.getChildren().add(find);

        Button add = new Button("Добавить клиента");
        add.setLayoutX(470);
        add.setLayoutY(10);
        res.getChildren().add(add);

        Button back = new Button("Назад");
        back.setLayoutX(600);
        back.setLayoutY(10);
        res.getChildren().add(back);

        Text num = new Text("#");
        num.setLayoutX(30);
        num.setLayoutY(80);
        num.setFont(Font.font("Verdana",13));

        Text date = new Text("ФИО");
        date.setLayoutX(70);
        date.setLayoutY(80);
        date.setFont(Font.font("Verdana",13));

        Text description = new Text("Статус");
        description.setLayoutX(400);
        description.setLayoutY(80);
        description.setFont(Font.font("Verdana",13));

        Text price = new Text("Бонус");
        price.setLayoutX(530);
        price.setLayoutY(80);
        price.setFont(Font.font("Verdana",13));
        res.getChildren().addAll(price,date,description,num);

        int u = 100;
        for(int i=0;i<mas[0].length;i++, u+=50) {
            if(mas[0][i] == null)
                continue;
            TextArea num_text = new TextArea();
            num_text.setEditable(false);
            num_text.setText(mas[0][i]);
            num_text.setLayoutX(20);
            num_text.setLayoutY(0 + u);
            num_text.setMaxHeight(40);
            num_text.setMaxWidth(30);
            num_text.setMinHeight(40);
            num_text.setMinWidth(30);

            TextArea DATA = new TextArea();
            DATA.setText(mas[1][i]);
            DATA.setEditable(false);
            DATA.setLayoutX(60);
            DATA.setLayoutY(0 + u);
            DATA.setMaxHeight(40);
            DATA.setMaxWidth(310);
            DATA.setMinWidth(310);

            TextArea description_text = new TextArea();
            description_text.setText(mas[2][i]);
            description_text.setEditable(false);
            description_text.setLayoutX(400);
            description_text.setLayoutY(0 + u);
            description_text.setMaxHeight(40);
            description_text.setMaxWidth(100);
            description_text.setMinHeight(40);
            description_text.setMinWidth(80);

            TextArea price_text = new TextArea();
            price_text.setText(mas[3][i]);
            price_text.setEditable(false);
            price_text.setLayoutX(520);
            price_text.setLayoutY(0 + u);
            price_text.setMaxHeight(40);
            price_text.setMaxWidth(80);
            pane.getChildren().addAll(description_text, price_text, DATA, num_text);
        }
        scrollPane.setContent(pane);
        final Pane pane2 = pane;
        back.setOnAction(value ->{
            Pane pane1;
            try {
                pane1 = Client_Staff.getPane(pane2, scrollPane);
                scrollPane.setContent(pane1);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
        Pane finalPane = pane;
        find.setOnAction(value ->{
            String t = name_find.getText();
            String[][] client = null;
            try {
                client = DataBase.getClient_byNAME(t);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            if(client == null){
                name_find.setText("Такого клиента не существует");
                return;
            }
            try {
                print(finalPane,scrollPane, client);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        });

        add.setOnAction(value ->{
            try {
                add(res, scrollPane);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
    }
}


