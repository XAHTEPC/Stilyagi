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

public class Visit_Manage {
    String id;
    String client_name;
    String structure_name;
    String service_name;
    String data;
    String employee_name;
    String summa;
    String points;

    public Visit_Manage(String id, String client_name, String structure_name,
                        String service_name, String data, String employee_name, String summa, String points) {
        this.id = id;
        this.client_name = client_name;
        this.structure_name = structure_name;
        this.service_name = service_name;
        this.data = data;
        this.employee_name = employee_name;
        this.summa = summa;
        this.points = points;
    }

    public static Pane getPane(Pane pane, ScrollPane scrollPane) throws SQLException, FileNotFoundException, ClassNotFoundException {
        pane = new Pane();
        Pane res = pane;

        Text num = new Text("#");
        num.setLayoutX(20);
        num.setLayoutY(60);
        num.setFont(Font.font("Verdana",13));

        Text cl = new Text("Клиент");
        cl.setLayoutX(70);
        cl.setLayoutY(60);
        cl.setFont(Font.font("Verdana",13));

        Text em = new Text("Сотрудник");
        em.setLayoutX(220);
        em.setLayoutY(60);
        em.setFont(Font.font("Verdana",13));

        Text ser = new Text("Услуга");
        ser.setLayoutX(360);
        ser.setLayoutY(60);
        ser.setFont(Font.font("Verdana",13));

        Text price = new Text("Цена");
        price.setLayoutX(460);
        price.setLayoutY(60);
        price.setFont(Font.font("Verdana",13));

        Text str = new Text("Заведение");
        str.setLayoutX(510);
        str.setLayoutY(60);
        str.setFont(Font.font("Verdana",13));

        Text point = new Text("Оценка");
        point.setLayoutX(600);
        point.setLayoutY(60);
        point.setFont(Font.font("Verdana",13));

        Text date = new Text("Дата");
        date.setLayoutX(680);
        date.setLayoutY(60);
        date.setFont(Font.font("Verdana",13));

        Visit_Staff[] mas = DataBase.getAllVisit_Staff();
        int u = 80;
        for(int i=0; i<mas.length;i++, u+=70){
            if(mas[i] == null)
                continue;
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
            cl_text.setText(mas[i].client_name);
            cl_text.setEditable(false);
            cl_text.setLayoutX(50);
            cl_text.setLayoutY(0 + u);
            cl_text.setMaxHeight(40);
            cl_text.setMaxWidth(140);
            cl_text.setMinWidth(140);

            TextArea emp_text = new TextArea();
            emp_text.setText(mas[i].employee_name);
            emp_text.setEditable(false);
            emp_text.setLayoutX(200);
            emp_text.setLayoutY(0 + u);
            emp_text.setMaxHeight(40);
            emp_text.setMaxWidth(140);
            emp_text.setMinHeight(40);
            emp_text.setMinWidth(140);

            TextArea ser_text = new TextArea();
            ser_text.setText(mas[i].service_name);
            ser_text.setEditable(false);
            ser_text.setLayoutX(350);
            ser_text.setLayoutY(0 + u);
            ser_text.setMaxHeight(40);
            ser_text.setMaxWidth(100);
            ser_text.setMinHeight(40);
            ser_text.setMinWidth(100);

            TextArea price_text = new TextArea();
            price_text.setText(mas[i].summa);
            price_text.setEditable(false);
            price_text.setLayoutX(460);
            price_text.setLayoutY(0 + u);
            price_text.setMaxHeight(40);
            price_text.setMaxWidth(40);

            TextArea str_text = new TextArea();
            str_text.setText(mas[i].structure_name);
            str_text.setEditable(false);
            str_text.setLayoutX(510);
            str_text.setLayoutY(0 + u);
            str_text.setMaxHeight(40);
            str_text.setMaxWidth(80);
            str_text.setMinHeight(40);
            str_text.setMinWidth(80);

            TextArea point_text = new TextArea();
            point_text.setText(mas[i].points);
            point_text.setEditable(false);
            point_text.setLayoutX(600);
            point_text.setLayoutY(0 + u);
            point_text.setMaxHeight(40);
            point_text.setMaxWidth(40);
            point_text.setMinHeight(40);
            point_text.setMinWidth(40);

            TextArea data_text = new TextArea();
            data_text.setText(mas[i].data);
            data_text.setEditable(false);
            data_text.setLayoutX(650);
            data_text.setLayoutY(0 + u);
            data_text.setMaxHeight(40);
            data_text.setMaxWidth(80);
            data_text.setMinHeight(40);
            data_text.setMinWidth(80);

            final String a = mas[i].id;

            FileInputStream Url = new FileInputStream("png/pen.png");
            Image url = new Image(Url);
            ImageView pen = new ImageView(url);

            Button edit = new Button();
            edit.setGraphic(pen);
            edit.setLayoutX(740);
            edit.setLayoutY(0+u);

            final String id = mas[i].id;
            Pane finalPane = pane;
            edit.setOnAction(v ->{
                try {
                    edit_pos(finalPane,scrollPane,id);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            });

            pane.getChildren().addAll(num_text,cl_text,emp_text,ser_text,price_text,str_text,point_text, data_text, edit);
        }

//        add.setOnAction(value ->{
//            try {
//                add(res, scrollPane);
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            } catch (FileNotFoundException e) {
//                throw new RuntimeException(e);
//            }
//        });
        res.getChildren().addAll(num, cl, em, ser, price, str, point, date);
        return res;
    }

    public static void edit_pos(Pane pane,  ScrollPane scrollPane, String id) throws SQLException, FileNotFoundException, ClassNotFoundException {
        Group root_add = new Group();
        String[] visit = DataBase.getVisit_byID(id);
        String[] cl = DataBase.getClient_byID(visit[0]);
        Scene scene_add = new Scene(root_add, 400, 400);
        Stage newWindow = new Stage();
        newWindow.initStyle(StageStyle.UTILITY);

        FileInputStream Url = new FileInputStream("png/front2.jpg");
        Image url = new Image(Url);
        ImageView front = new ImageView(url);
        front.setX(0);
        front.setY(0);
        //root_add.getChildren().add(front);

        Text title = new Text("Редактирование посещения");
        title.setLayoutX(100);
        title.setLayoutY(20);
        title.setFont(Font.font("Verdana",16));

        Text name_text = new Text("ФИО Клиента:");
        name_text.setLayoutX(10);
        name_text.setLayoutY(55);
        name_text.setFont(Font.font("Verdana",13));

        TextArea name = new TextArea();
        name.setText(cl[1]);
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
        comboBox2.setValue(visit[1]);
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
        comboBox3.setValue(visit[2]);
        comboBox3.setMaxWidth(150);
        comboBox3.setLayoutX(160);
        comboBox3.setLayoutY(130);

        Text date_text = new Text("Дата");
        date_text.setLayoutX(10);
        date_text.setLayoutY(205);
        date_text.setFont(Font.font("Verdana",13));

        TextArea DATA = new TextArea();
        DATA.setText(visit[3]);
        DATA.setLayoutX(160);
        DATA.setLayoutY(180);
        DATA.setMaxHeight(40);
        DATA.setMaxWidth(200);

        Text emp_text = new Text("Сотрудник:");
        emp_text.setLayoutX(10);
        emp_text.setLayoutY(255);
        emp_text.setFont(Font.font("Verdana",13));

        TextArea emp = new TextArea();
        emp.setText(visit[4]);
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
        vin.setText(visit[6]);
        vin.setLayoutX(160);
        vin.setLayoutY(280);
        vin.setMaxHeight(40);
        vin.setMaxWidth(200);

        Button save = new Button("СОХРАНИТЬ");
        save.setLayoutX(100);
        save.setLayoutY(330);

        Button del = new Button("Удалить");
        del.setLayoutX(300);
        del.setLayoutY(330);
        root_add.getChildren().addAll(del);
        Pane finalPane = pane;

        String finalId_visit = id;
        save.setOnAction(x ->{
            String t1,t2,t3,t4,t5,t6,t7 = "";
            t1 = name.getText();
            t1 = cl[0];
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
                DataBase.UpdateVisit(finalId_visit,t1,t2,t3,t4,t5,t7,t6);
                Pane p = Visit_Manage.getPane(pane,scrollPane);
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

        del.setOnAction(va ->{
            try {
                DataBase.delVisit(id);
                Pane p = Visit_Manage.getPane(finalPane,scrollPane);
                scrollPane.setContent(p);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            newWindow.close();
        });
        root_add.getChildren().addAll(save,title,name,name_text, vin, score_text,emp,emp_text,comboBox2,comboBox3);
        root_add.getChildren().addAll(DATA,date_text,service_text,str_text);
        newWindow.setTitle("Редактирование посещения");
        newWindow.setScene(scene_add);
        newWindow.show();
    }

}
