package com.example.demo.Model;

import com.example.demo.Database.DataBase;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.FileNotFoundException;
import java.sql.SQLException;

public class Visit_Analyst {
    String id;
    String client_name;
    String structure_name;
    String service_name;
    String data;
    String employee_name;
    String summa;
    String points;

    public Visit_Analyst(String id, String client_name, String structure_name,
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

        res.getChildren().addAll(num, cl, em, ser, price, str, point, date);
        return res;
    }

}
