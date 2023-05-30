package com.example.demo.Model;

import com.example.demo.Database.DataBase;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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

public class Visit_Staff {
    String id;
    String client_name;
    String structure_name;
    String service_name;
    String data;
    String employee_name;
    String summa;
    String points;

    public Visit_Staff(String id, String client_id, String structure_id,
                       String service_id, String data, String employee_id, String summa, String points) {
        this.id = id;
        this.client_name = client_id;
        this.structure_name = structure_id;
        this.service_name = service_id;
        this.data = data;
        this.employee_name = employee_id;
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

            pane.getChildren().addAll(num_text,cl_text,emp_text,ser_text,price_text,str_text,point_text, data_text);
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

    /*public static void add(Pane pane,  ScrollPane scrollPane) throws SQLException, FileNotFoundException {
        Group root_add = new Group();
        String id = DataBase.getLastVisit();
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

        Text title = new Text("Новое посещение");
        title.setLayoutX(100);
        title.setLayoutY(20);
        title.setFont(Font.font("Verdana",16));

        Text cl_text = new Text("ФИО Клиента:");
        cl_text.setLayoutX(10);
        cl_text.setLayoutY(55);
        cl_text.setFont(Font.font("Verdana",13));

        TextArea cl = new TextArea();
        cl.setPromptText("Я тут хз пока что");
        cl.setLayoutX(160);
        cl.setLayoutY(30);
        cl.setMaxHeight(13);
        cl.setMaxWidth(200);

        Text em_text = new Text("ФИО Сотрудника:");
        em_text.setLayoutX(10);
        em_text.setLayoutY(55);
        em_text.setFont(Font.font("Verdana",13));

        TextArea em = new TextArea();
        em.setPromptText("Как-то надо спиздеть логин без хардкора");
        em.setLayoutX(160);
        em.setLayoutY(30);
        em.setMaxHeight(13);
        em.setMaxWidth(200);

        Text ser_text = new Text("Услуга");
        ser_text.setLayoutX(10);
        ser_text.setLayoutY(55);
        ser_text.setFont(Font.font("Verdana",13));

        TextArea ser = new TextArea();
        ser.setPromptText("Как-то надо спиздеть логин без хардкора");
        ser.setLayoutX(160);
        ser.setLayoutY(30);
        ser.setMaxHeight(13);
        ser.setMaxWidth(200);

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
        newWindow.setTitle("Редактирование клиента");
        newWindow.setScene(scene_add);
        newWindow.show();

    }*/
}


