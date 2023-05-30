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

public class Client_Analyst {
    String client_name;
    String id;
    String client_status;
    String getClient_bonus;

    public Client_Analyst(String client_name, String id, String client_status, String getClient_bonus) {
        this.client_name = client_name;
        this.id = id;
        this.client_status = client_status;
        this.getClient_bonus = getClient_bonus;
    }

    public static Pane getPane(Pane pane, ScrollPane scrollPane) throws SQLException, FileNotFoundException, ClassNotFoundException {
        pane = new Pane();
        Pane res = pane;

//        Button add = new Button("Добавить клиента");
//        add.setLayoutX(300);
//        add.setLayoutY(10);
//        res.getChildren().add(add);

        Text num = new Text("#");
        num.setLayoutX(30);
        num.setLayoutY(60);
        num.setFont(Font.font("Verdana", 13));

        Text date = new Text("ФИО");
        date.setLayoutX(70);
        date.setLayoutY(60);
        date.setFont(Font.font("Verdana", 13));

        Text description = new Text("Статус");
        description.setLayoutX(400);
        description.setLayoutY(60);
        description.setFont(Font.font("Verdana", 13));

        Text price = new Text("Бонус");
        price.setLayoutX(530);
        price.setLayoutY(60);
        price.setFont(Font.font("Verdana", 13));

        Client_Staff[] mas = DataBase.getAllClient_Staff();
        int u = 80;
        for (int i = 0; i < mas.length; i++, u += 70) {
            if (mas[i] == null)
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

            pane.getChildren().addAll(description_text, price_text, DATA, num_text);
        }
        res.getChildren().addAll(price, num, description,date);
        return res;
    }
}


