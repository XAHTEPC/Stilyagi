package com.example.demo;

import com.example.demo.Database.DataBase;
import com.example.demo.Model.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Scanner;

public class HelloApplication extends Application {


    int i=0;
    static String id="";
    static String user_role = "";
    @Override
    public void start(Stage stage) throws IOException, SQLException, ClassNotFoundException {
        Group root = new Group();
        Scene scene = new Scene(root, 1200, 800);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.getStyle();
        FileInputStream Url;
        Image url;

        Url = new FileInputStream("png/front.jpg");
        url = new Image(Url);
        ImageView front = new ImageView(url);
        front.setX(0);
        front.setY(0);
        root.getChildren().add(front);

        Url = new FileInputStream("png/tick.png");
        url = new Image(Url);
        ImageView tick = new ImageView(url);
        Button close = new Button();
        close.setBackground(null);
        close.setGraphic(tick);
        close.setLayoutY(5);
        close.setLayoutX(1120);
        root.getChildren().add(close);

        Url = new FileInputStream("png/title.png");
        url = new Image(Url);
        ImageView title = new ImageView(url);
        title.setX(430);
        title.setY(205);

        Rectangle rect2 = new Rectangle(400, 200, 400, 70);
        rect2.setFill(Color.rgb(219, 210, 203));
        rect2.setStrokeWidth(4);
        rect2.setStroke(Color.rgb(62, 61, 66));
        rect2.setArcWidth(35);
        rect2.setArcHeight(35);
        root.getChildren().addAll(rect2, title);

        Url = new FileInputStream("png/login.png");
        url = new Image(Url);
        ImageView login = new ImageView(url);
        login.setX(400);
        login.setY(280);
        root.getChildren().add(login);

        Rectangle rect2_3 = new Rectangle(400, 350, 400, 70);
        rect2_3.setFill(Color.rgb(219, 210, 203));
        rect2_3.setStrokeWidth(4);
        rect2_3.setStroke(Color.rgb(62, 61, 66));
        rect2_3.setArcWidth(35);
        rect2_3.setArcHeight(35);
        root.getChildren().addAll(rect2_3);

        Line line = new Line(410,400,790,400);
        line.setStrokeWidth(2);
        root.getChildren().add(line);

        TextField  input_login = new TextField();
        input_login.setMinWidth(400);
        input_login.setMaxWidth(400);
        input_login.setMinHeight(70);
        input_login.setMaxHeight(70);
        input_login.setLayoutX(400);
        input_login.setLayoutY(350);
        input_login.setPromptText("login");
        input_login.setFont(Font.font("Verdana", 20));
        input_login.setBackground(null);
        root.getChildren().add(input_login);

        Url = new FileInputStream("png/password.png");
        url = new Image(Url);
        ImageView pass = new ImageView(url);
        pass.setX(400);
        pass.setY(430);
        root.getChildren().add(pass);

        Rectangle rect2_4 = new Rectangle(400, 500, 400, 70);
        rect2_4.setFill(Color.rgb(219, 210, 203));
        rect2_4.setStrokeWidth(4);
        rect2_4.setStroke(Color.rgb(62, 61, 66));
        rect2_4.setArcWidth(35);
        rect2_4.setArcHeight(35);
        root.getChildren().addAll(rect2_4);

        Line line1 = new Line(410,550,790,550);
        line1.setStrokeWidth(2);
        root.getChildren().add(line1);

        PasswordField passwordField = new PasswordField();
        passwordField.setPrefSize(400,70);
        passwordField.setBackground(null);
        passwordField.setPromptText("password");
        passwordField.setLayoutX(400);
        passwordField.setLayoutY(500);
        passwordField.setFont(Font.font("Verdana", 20));
        root.getChildren().add(passwordField);

        Rectangle rect2_5 = new Rectangle(450, 590, 300, 70);
        rect2_5.setFill(Color.rgb(255, 196, 119));
        rect2_5.setStrokeWidth(4);
        rect2_5.setStroke(Color.rgb(62, 61, 66));
        rect2_5.setArcWidth(35);
        rect2_5.setArcHeight(35);
        root.getChildren().addAll(rect2_5);

        Url = new FileInputStream("png/input.png");
        url = new Image(Url);
        ImageView input = new ImageView(url);
        input.setX(400);
        input.setY(595);
        root.getChildren().add(input);

        Button enter = new Button();
        enter.setLayoutX(450);
        enter.setLayoutY(590);
        enter.setMinWidth(300);
        enter.setMinHeight(70);
        enter.setBackground(null);
        root.getChildren().add(enter);

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setLayoutX(200);
        scrollPane.setLayoutY(300);
        scrollPane.setMaxHeight(400);
        scrollPane.setMaxWidth(800);
        scrollPane.setMinHeight(400);
        scrollPane.setMinWidth(800);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

        Pane pane = new Pane();
        scrollPane.setContent(pane);


        enter.setOnAction(v -> {
                    String log = input_login.getText();
                    String pas = passwordField.getText();
                    //String log = "postgres";
                    //String pas = "123";
                    int lvl;
                    String rol = "";
                    DataBase dataBase;
                    try {
                        dataBase = new DataBase(log, pas);
                        rol = dataBase.getRole();
                        lvl = getLVL(rol);
                        System.out.println(lvl);
                        System.out.println(rol);
                    } catch (SQLException e) {
                        System.out.println("ERROR");
                        passwordField.setText("");
                        input_login.setText("Неверный логин или пароль");
                        return;
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
            FileInputStream Url2 = null;
                    ImageView mess;
                    try {
                        Url2 = new FileInputStream("png/mes.png");
                        Image url2 = new Image(Url2);
                        mess = new ImageView(url2);
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                    Button message = new Button();
                    message.setBackground(null);
                    message.setGraphic(mess);
                    message.setLayoutY(10);
                    message.setLayoutX(420);
                    root.getChildren().addAll(message);

                    root.getChildren().removeAll(enter, rect2_3, rect2_4, rect2_5, login, passwordField, pass, line, line1, input_login, input);
                    Rectangle rect1 = new Rectangle(10, 10, 400, 64);
                    rect1.setFill(Color.rgb(255, 196, 119));
                    rect1.setStrokeWidth(4);
                    rect1.setStroke(Color.rgb(62, 61, 66));
                    rect1.setArcWidth(35);
                    rect1.setArcHeight(35);
                    root.getChildren().add(rect1);

                    Text logins = new Text(log);
                    logins.setLayoutY(33);
                    logins.setLayoutX(20);
                    logins.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
                    root.getChildren().add(logins);

                    Text role = new Text(user_role);
                    role.setLayoutY(60);
                    role.setLayoutX(20);
                    role.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
                    root.getChildren().add(role);
                    ImageView title1, title2, title3, title4, title5;

                    rect2.setY(100);
                    title.setY(105);


                    if (lvl == 1) {///staff
                        Button back = new Button();
                        back. setLayoutY(100);
                        back.setLayoutX(400);
                        back.setMinHeight(70);
                        back.setMinWidth(400);
                        back.setBackground(null);
                        root.getChildren().add(back);

                        try {
                            FileInputStream Url1 = new FileInputStream("png/title3.png");
                            Image url1 = new Image(Url1);
                            title1 = new ImageView(url1);
                            Url1 = new FileInputStream("png/title4.png");
                            url1 = new Image(Url1);
                            title2 = new ImageView(url1);
                        } catch (FileNotFoundException e) {
                            throw new RuntimeException(e);
                        }

                        title1.setX(450);
                        title1.setY(205);

                        Rectangle rect3 = new Rectangle(400, 200, 400, 70);
                        rect3.setFill(Color.rgb(255, 196, 119));
                        rect3.setStrokeWidth(4);
                        rect3.setStroke(Color.rgb(62, 61, 66));
                        rect3.setArcWidth(35);
                        rect3.setArcHeight(35);
                        root.getChildren().addAll(rect3, title1);

                        Button client = new Button();
                        client.setLayoutX(400);
                        client.setLayoutY(200);
                        client.setMinHeight(70);
                        client.setMinWidth(400);
                        client.setBackground(null);
                        root.getChildren().add(client);

                        title2.setX(470);
                        title2.setY(310);

                        Rectangle rect4 = new Rectangle(400, 300, 400, 70);
                        rect4.setFill(Color.rgb(255, 196, 119));
                        rect4.setStrokeWidth(4);
                        rect4.setStroke(Color.rgb(62, 61, 66));
                        rect4.setArcWidth(35);
                        rect4.setArcHeight(35);
                        root.getChildren().addAll(rect4, title2);

                        Button visit = new Button();
                        visit.setLayoutX(400);
                        visit.setLayoutY(300);
                        visit.setMinHeight(70);
                        visit.setMinWidth(400);
                        visit.setBackground(null);
                        root.getChildren().add(visit);

                        client.setOnAction(value ->{
                            root.getChildren().removeAll(rect4, visit, client, title2);
                            rect3.setY(200);
                            title1.setY(205);

                            Pane pane1 = new Pane();
                            root.getChildren().add(scrollPane);
                            try {
                                pane1 = Client_Staff.getPane(pane, scrollPane);
                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                            } catch (FileNotFoundException e) {
                                throw new RuntimeException(e);
                            } catch (ClassNotFoundException e) {
                                throw new RuntimeException(e);
                            }
                            scrollPane.setContent(pane1);
                            i=1;
                        });
                        visit.setOnAction(value ->{
                            root.getChildren().removeAll(rect4,title2, client,visit, title1);
                            rect3.setY(200);
                            title2.setY(205);

                            Pane pane1 = new Pane();
                            root.getChildren().addAll(scrollPane, title2);
                            try {
                                pane1 = Visit_Staff.getPane(pane, scrollPane);
                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                            } catch (FileNotFoundException e) {
                                throw new RuntimeException(e);
                            } catch (ClassNotFoundException e) {
                                throw new RuntimeException(e);
                            }
                            scrollPane.setContent(pane1);
                            i=2;
                        });

                        back.setOnAction(value ->{
                            root.getChildren().removeAll(scrollPane, title2, title1, rect4, visit,client,rect3);
                            root.getChildren().addAll(rect4,title2,rect3, title1,  visit, client);
                            title2.setY(310);
                        });

                    } else
                        if (lvl == 2) {///manager
                        Button back = new Button();
                        back. setLayoutY(100);
                        back.setLayoutX(400);
                        back.setMinHeight(70);
                        back.setMinWidth(400);
                        back.setBackground(null);
                        root.getChildren().add(back);

                        try {
                            FileInputStream Url1 = new FileInputStream("png/title3.png");
                            Image url1 = new Image(Url1);
                            title1 = new ImageView(url1);
                            Url1 = new FileInputStream("png/title4.png");
                            url1 = new Image(Url1);
                            title2 = new ImageView(url1);
                        } catch (FileNotFoundException e) {
                            throw new RuntimeException(e);
                        }

                        title1.setX(450);
                        title1.setY(205);

                        Rectangle rect3 = new Rectangle(400, 200, 400, 70);
                        rect3.setFill(Color.rgb(255, 196, 119));
                        rect3.setStrokeWidth(4);
                        rect3.setStroke(Color.rgb(62, 61, 66));
                        rect3.setArcWidth(35);
                        rect3.setArcHeight(35);
                        root.getChildren().addAll(rect3, title1);

                        Button client = new Button();
                        client.setLayoutX(400);
                        client.setLayoutY(200);
                        client.setMinHeight(70);
                        client.setMinWidth(400);
                        client.setBackground(null);

                        title2.setX(470);
                        title2.setY(310);

                        Rectangle rect4 = new Rectangle(400, 300, 400, 70);
                        rect4.setFill(Color.rgb(255, 196, 119));
                        rect4.setStrokeWidth(4);
                        rect4.setStroke(Color.rgb(62, 61, 66));
                        rect4.setArcWidth(35);
                        rect4.setArcHeight(35);
                        root.getChildren().addAll(rect4, title2);

                        Button visit = new Button();
                        visit.setLayoutX(400);
                        visit.setLayoutY(300);
                        visit.setMinHeight(70);
                        visit.setMinWidth(400);
                        visit.setBackground(null);
                        root.getChildren().addAll(visit,client);

                        client.setOnAction(value -> {
                            root.getChildren().removeAll(rect4, visit, client, title2);
                            rect3.setY(200);
                            title1.setY(205);

                            Pane pane1 = new Pane();
                            root.getChildren().add(scrollPane);
                            try {
                                pane1 = Client_Manage.getPane(pane, scrollPane);
                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                            } catch (FileNotFoundException e) {
                                throw new RuntimeException(e);
                            } catch (ClassNotFoundException e) {
                                throw new RuntimeException(e);
                            }
                            scrollPane.setContent(pane1);
                            i=1;
                        });

                        visit.setOnAction(value -> {
                            root.getChildren().removeAll(rect4, client,visit, title1, title2);
                            rect3.setY(200);
                            title2.setY(205);

                            Pane pane1 = new Pane();
                            root.getChildren().addAll(scrollPane, title2);
                            try {
                                pane1 = Visit_Manage.getPane(pane, scrollPane);
                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                            } catch (FileNotFoundException e) {
                                throw new RuntimeException(e);
                            } catch (ClassNotFoundException e) {
                                throw new RuntimeException(e);
                            }
                            scrollPane.setContent(pane1);
                            i=2;
                        });

                        back.setOnAction(value ->{
                            root.getChildren().removeAll(scrollPane, title2, title1, rect4, visit,client,rect3);
                            root.getChildren().addAll(rect4,title2,rect3, title1,  visit, client);
                            title2.setY(310);
                        });



                    } else
                        if (lvl == 3) {///local_manager
                        Button back = new Button();
                        back. setLayoutY(100);
                        back.setLayoutX(400);
                        back.setMinHeight(70);
                        back.setMinWidth(400);
                        back.setBackground(null);
                        root.getChildren().add(back);

                        try {
                            FileInputStream Url1 = new FileInputStream("png/title5.png");
                            Image url1 = new Image(Url1);
                            title1 = new ImageView(url1);
                            Url1 = new FileInputStream("png/title2.png");
                            url1 = new Image(Url1);
                            title2 = new ImageView(url1);
                        } catch (FileNotFoundException e) {
                            throw new RuntimeException(e);
                        }

                        title1.setX(410);
                        title1.setY(205);

                        Rectangle rect3 = new Rectangle(400, 200, 400, 70);
                        rect3.setFill(Color.rgb(255, 196, 119));
                        rect3.setStrokeWidth(4);
                        rect3.setStroke(Color.rgb(62, 61, 66));
                        rect3.setArcWidth(35);
                        rect3.setArcHeight(35);
                        root.getChildren().addAll(rect3, title1);

                        Button structure = new Button();
                        structure.setLayoutX(400);
                        structure.setLayoutY(200);
                        structure.setMinHeight(70);
                        structure.setMinWidth(400);
                        structure.setBackground(null);

                        title2.setX(410);
                        title2.setY(310);

                        Rectangle rect4 = new Rectangle(400, 300, 400, 70);
                        rect4.setFill(Color.rgb(255, 196, 119));
                        rect4.setStrokeWidth(4);
                        rect4.setStroke(Color.rgb(62, 61, 66));
                        rect4.setArcWidth(35);
                        rect4.setArcHeight(35);
                        root.getChildren().addAll(rect4, title2);


                        Button employee = new Button();
                        employee.setLayoutX(400);
                        employee.setLayoutY(300);
                        employee.setMinHeight(70);
                        employee.setMinWidth(400);
                        employee.setBackground(null);
                        root.getChildren().addAll(employee,structure);
                        structure.setOnAction(e -> {
                            root.getChildren().removeAll(structure, title1, title2, rect3, employee);
                            root.getChildren().removeAll(rect4);
                            rect2.setY(100);
                            title.setY(105);
                            root.getChildren().addAll(rect3, title1);
                            rect3.setY(200);
                            title1.setY(205);

                            Pane pane1 = new Pane();
                            root.getChildren().add(scrollPane);
                            try {
                                pane1 = Structure_Local.getPane(pane, scrollPane);
                            } catch (SQLException e1) {
                                throw new RuntimeException(e1);
                            } catch (FileNotFoundException e1) {
                                throw new RuntimeException(e1);
                            } catch (ClassNotFoundException e1) {
                                throw new RuntimeException(e1);
                            }
                            scrollPane.setContent(pane1);
                            i=1;

                        });

                        employee.setOnAction(e -> {
                            root.getChildren().removeAll(structure, title1, title2, rect3, employee);
                            root.getChildren().removeAll(rect4);
                            rect2.setY(100);
                            title.setY(105);
                            root.getChildren().addAll(rect3, title2);
                            rect3.setY(200);
                            title2.setY(205);

                            Pane pane1 = new Pane();
                            root.getChildren().add(scrollPane);
                            try {
                                pane1 = Employee_Local.getPane(pane, scrollPane);
                            } catch (SQLException e1) {
                                throw new RuntimeException(e1);
                            } catch (FileNotFoundException e1) {
                                throw new RuntimeException(e1);
                            } catch (ClassNotFoundException e1) {
                                throw new RuntimeException(e1);
                            }
                            scrollPane.setContent(pane1);

                        });

                        back.setOnAction(value ->{
                            root.getChildren().removeAll(scrollPane, title2, title1, rect4, employee,structure,rect3);
                            root.getChildren().addAll(rect4,title2,rect3, title1,  structure, employee);
                            title2.setY(310);
                        });

                    } else if (lvl == 4) {///analyst
                        Button back = new Button();
                        back. setLayoutY(100);
                        back.setLayoutX(400);
                        back.setMinHeight(70);
                        back.setMinWidth(400);
                        back.setBackground(null);
                        root.getChildren().add(back);

                        try {
                            FileInputStream Url1 = new FileInputStream("png/title1.png");
                            Image url1 = new Image(Url1);
                            title1 = new ImageView(url1);
                            Url1 = new FileInputStream("png/title2.png");
                            url1 = new Image(Url1);
                            title2 = new ImageView(url1);
                            Url1 = new FileInputStream("png/title3.png");
                            url1 = new Image(Url1);
                            title3 = new ImageView(url1);
                            Url1 = new FileInputStream("png/title4.png");
                            url1 = new Image(Url1);
                            title4 = new ImageView(url1);
                            Url1 = new FileInputStream("png/title5.png");
                            url1 = new Image(Url1);
                            title5 = new ImageView(url1);
                        } catch (FileNotFoundException e) {
                            throw new RuntimeException(e);
                        }


                        Rectangle rect8 = new Rectangle(400, 200, 400, 70);
                        rect8.setFill(Color.rgb(255, 196, 119));
                        rect8.setStrokeWidth(4);
                        rect8.setStroke(Color.rgb(62, 61, 66));
                        rect8.setArcWidth(35);
                        rect8.setArcHeight(35);
                        root.getChildren().addAll(rect8, title5);

                        Button structure = new Button();
                        structure.setLayoutX(400);
                        structure.setLayoutY(200);
                        structure.setMinWidth(400);
                        structure.setMinHeight(70);
                        structure.setBackground(null);

                        title5.setX(410);
                        title5.setY(205);
                        title1.setX(450);
                        title1.setY(305);

                        Rectangle rect3 = new Rectangle(400, 300, 400, 70);
                        rect3.setFill(Color.rgb(255, 196, 119));
                        rect3.setStrokeWidth(4);
                        rect3.setStroke(Color.rgb(62, 61, 66));
                        rect3.setArcWidth(35);
                        rect3.setArcHeight(35);
                        root.getChildren().addAll(rect3, title1);

                        Button service = new Button();
                        service.setLayoutX(400);
                        service.setLayoutY(300);
                        service.setMinHeight(70);
                        service.setMinWidth(400);
                        service.setBackground(null);

                        title2.setX(410);
                        title2.setY(410);

                        Rectangle rect4 = new Rectangle(400, 400, 400, 70);
                        rect4.setFill(Color.rgb(255, 196, 119));
                        rect4.setStrokeWidth(4);
                        rect4.setStroke(Color.rgb(62, 61, 66));
                        rect4.setArcWidth(35);
                        rect4.setArcHeight(35);
                        root.getChildren().addAll(rect4, title2);


                        Button employee = new Button();
                        employee.setLayoutX(400);
                        employee.setLayoutY(400);
                        employee.setMinHeight(70);
                        employee.setMinWidth(400);
                        employee.setBackground(null);

                        title3.setX(440);
                        title3.setY(505);

                        Rectangle rect5 = new Rectangle(400, 500, 400, 70);
                        rect5.setFill(Color.rgb(255, 196, 119));
                        rect5.setStrokeWidth(4);
                        rect5.setStroke(Color.rgb(62, 61, 66));
                        rect5.setArcWidth(35);
                        rect5.setArcHeight(35);
                        root.getChildren().addAll(rect5, title3);


                        Button client = new Button();
                        client.setLayoutX(400);
                        client.setLayoutY(500);
                        client.setMinHeight(70);
                        client.setMinWidth(400);
                        client.setBackground(null);

                        title4.setX(450);
                        title4.setY(610);

                        Rectangle rect6 = new Rectangle(400, 600, 400, 70);
                        rect6.setFill(Color.rgb(255, 196, 119));
                        rect6.setStrokeWidth(4);
                        rect6.setStroke(Color.rgb(62, 61, 66));
                        rect6.setArcWidth(35);
                        rect6.setArcHeight(35);
                        root.getChildren().addAll(rect6, title4);

                        Button visit = new Button();
                        visit.setLayoutX(400);
                        visit.setLayoutY(600);
                        visit.setMinHeight(70);
                        visit.setMinWidth(400);
                        visit.setBackground(null);
                        root.getChildren().addAll(visit, employee, client, service, structure);

                        service.setOnAction(e -> {
                            root.getChildren().removeAll(service, title1, title2, title3,title5, title4, rect3, visit,structure, employee, client);
                            root.getChildren().removeAll(rect4, rect5, rect6);
                            rect2.setY(100);
                            title.setY(105);
                            root.getChildren().addAll(title1, scrollPane);
                            title1.setY(205);
                            Pane pane1 = new Pane();
                            try {
                                pane1 = Service_Analyst.getPane(pane, scrollPane);
                            } catch (SQLException e1) {
                                throw new RuntimeException(e1);
                            } catch (FileNotFoundException e1) {
                                throw new RuntimeException(e1);
                            } catch (ClassNotFoundException e1) {
                                throw new RuntimeException(e1);
                            }
                            scrollPane.setContent(pane1);
                        });

                        employee.setOnAction(e -> {
                            root.getChildren().removeAll(service, title1, title2,title5, title3, title4, rect3, visit, employee, client);
                            root.getChildren().removeAll(rect4, rect5, rect6);
                            rect2.setY(100);
                            title.setY(105);
                            root.getChildren().addAll(title2, scrollPane);
                            title2.setY(205);
                            Pane pane1 = new Pane();
                            try {
                                pane1 = Employee_Analyst.getPane(pane, scrollPane);
                            } catch (SQLException e1) {
                                throw new RuntimeException(e1);
                            } catch (FileNotFoundException e1) {
                                throw new RuntimeException(e1);
                            } catch (ClassNotFoundException e1) {
                                throw new RuntimeException(e1);
                            }
                            scrollPane.setContent(pane1);
                        });

                        client.setOnAction(e -> {
                            root.getChildren().removeAll(service, title1, title2, title3,title5, title4, rect3, visit, employee, client);
                            root.getChildren().removeAll(rect4, rect5, rect6);
                            rect2.setY(100);
                            title.setY(105);
                            root.getChildren().addAll(title3, scrollPane);
                            title3.setY(205);
                            Pane pane1 = new Pane();
                            try {
                                pane1 = Client_Analyst.getPane(pane, scrollPane);
                            } catch (SQLException er) {
                                throw new RuntimeException(er);
                            } catch (FileNotFoundException er) {
                                throw new RuntimeException(er);
                            } catch (ClassNotFoundException er) {
                                throw new RuntimeException(er);
                            }
                            scrollPane.setContent(pane1);
                        });

                        visit.setOnAction(e -> {
                            root.getChildren().removeAll(service, title1, title2, title3,title5, title4, rect3, visit, employee, client);
                            root.getChildren().removeAll(rect4, rect5, rect6);
                            rect2.setY(100);
                            title.setY(105);
                            root.getChildren().addAll(title4, scrollPane);
                            title4.setY(205);
                            Pane pane1 = new Pane();
                            try {
                                pane1 = Visit_Analyst.getPane(pane, scrollPane);
                            } catch (SQLException er) {
                                throw new RuntimeException(er);
                            } catch (FileNotFoundException er) {
                                throw new RuntimeException(er);
                            } catch (ClassNotFoundException er) {
                                throw new RuntimeException(er);
                            }
                            scrollPane.setContent(pane1);

                        });
                        structure.setOnAction(e -> {
                            root.getChildren().removeAll(service, title1, title2, title3,title5, title4, rect3, structure,visit, employee, client);
                            root.getChildren().removeAll(rect4, rect5, rect6);
                            rect2.setY(100);
                            title.setY(105);
                            root.getChildren().addAll(title5, scrollPane);
                            title5.setY(205);
                            Pane pane1 = new Pane();
                            try {
                                pane1 = Structure_Analyst.getPane(pane, scrollPane);
                            } catch (SQLException e1) {
                                throw new RuntimeException(e1);
                            } catch (FileNotFoundException e1) {
                                throw new RuntimeException(e1);
                            } catch (ClassNotFoundException e1) {
                                throw new RuntimeException(e1);
                            }
                            scrollPane.setContent(pane1);
                            i=1;

                        });

                        back.setOnAction(value ->{
                            root.getChildren().removeAll(scrollPane, title2, title1, title3, title4, title5, rect4, rect5, rect3, rect6,client, service,employee, structure, visit);
                            root.getChildren().addAll(rect4,rect5, rect3,rect6, title2, title1,title4, title5, title3,visit,client,service,employee,structure);
                            title1.setY(305);
                            title2.setY(410);
                            title3.setY(505);
                            title4.setY(610);
                            title5.setY(205);
                        });

                    } else if (lvl == 5) {///admin
                        Button back = new Button();
                        back. setLayoutY(100);
                        back.setLayoutX(400);
                        back.setMinHeight(70);
                        back.setMinWidth(400);
                        back.setBackground(null);
                        root.getChildren().add(back);
                        try {
                            FileInputStream Url1 = new FileInputStream("png/title2.png");
                            Image url1 = new Image(Url1);
                            title1 = new ImageView(url1);
                            Url1 = new FileInputStream("png/work.png");
                            url1 = new Image(Url1);
                            title2 = new ImageView(url1);
                        } catch (FileNotFoundException e) {
                            throw new RuntimeException(e);
                        }
                        title1.setX(410);
                        title1.setY(205);

                        Rectangle rect3 = new Rectangle(400, 200, 400, 70);
                        rect3.setFill(Color.rgb(255, 196, 119));
                        rect3.setStrokeWidth(4);
                        rect3.setStroke(Color.rgb(62, 61, 66));
                        rect3.setArcWidth(35);
                        rect3.setArcHeight(35);
                        root.getChildren().addAll(rect3, title1);

                        Button employee = new Button();
                        employee.setLayoutX(400);
                        employee.setLayoutY(200);
                        employee.setMinHeight(70);
                        employee.setMinWidth(400);
                        employee.setBackground(null);
                        root.getChildren().addAll(employee);

                        title2.setX(410);
                        title2.setY(310);

                        Rectangle rect4 = new Rectangle(400, 300, 400, 70);
                        rect4.setFill(Color.rgb(255, 196, 119));
                        rect4.setStrokeWidth(4);
                        rect4.setStroke(Color.rgb(62, 61, 66));
                        rect4.setArcWidth(35);
                        rect4.setArcHeight(35);
                        root.getChildren().addAll(rect4, title2);

                        Button work = new Button();
                        work.setLayoutX(400);
                        work.setLayoutY(300);
                        work.setMinHeight(70);
                        work.setMinWidth(400);
                        work.setBackground(null);
                        root.getChildren().addAll(work);
                            work.setOnAction(e ->{
                                System.out.println("1");
                                root.getChildren().removeAll(employee, title1, rect4);
                                rect2.setY(100);
                                title.setY(105);
                                root.getChildren().addAll(scrollPane);
                                rect3.setY(200);
                                title2.setY(205);
                                Pane pane1 = new Pane();
                                try {
                                    pane1 = Work_Admin.getPane(pane,scrollPane);
                                } catch (SQLException ex) {
                                    throw new RuntimeException(ex);
                                } catch (FileNotFoundException ex) {
                                    throw new RuntimeException(ex);
                                } catch (ClassNotFoundException ex) {
                                    throw new RuntimeException(ex);
                                }
                                scrollPane.setContent(pane1);
                            });

                            employee.setOnAction(e -> {
                            root.getChildren().removeAll(employee,work, rect4, title2);
                            rect2.setY(100);
                            title.setY(105);
                            root.getChildren().addAll(scrollPane);
                            rect3.setY(200);
                            title1.setY(205);
                            Pane pane1 = new Pane();
                            try {
                                pane1 = Employee_Admin.getPane(pane,scrollPane);
                            } catch (SQLException ex) {
                                throw new RuntimeException(ex);
                            } catch (FileNotFoundException ex) {
                                throw new RuntimeException(ex);
                            } catch (ClassNotFoundException ex) {
                                throw new RuntimeException(ex);
                            }
                            scrollPane.setContent(pane1);
                        });

                        back.setOnAction(value ->{
                            root.getChildren().removeAll(scrollPane, title1, rect3,rect4,work,title2, employee);
                            root.getChildren().addAll(rect3,title1,employee,rect4, title2, work);
                            title2.setY(305);
                            title1.setY(205);
                        });


                    }
                        message.setOnAction(qv ->{
                            root.getChildren().addAll(scrollPane);
                            Pane pane1 = new Pane();
                            try {
                                pane1 = Message.getPane(pane,scrollPane,id);
                            } catch (SQLException ex) {
                                throw new RuntimeException(ex);
                            } catch (FileNotFoundException ex) {
                                throw new RuntimeException(ex);
                            } catch (ClassNotFoundException ex) {
                                throw new RuntimeException(ex);
                            }
                            scrollPane.setContent(pane1);
                        });
                });




            close.setOnAction(v ->{
                stage.close();
            });

            stage.setTitle("Stilyagi");
            stage.setScene(scene);
            stage.show();



    }

    public static void main(String[] args) throws SQLException {
        //DataBase db = new DataBase("postgres", "123");
        //db.CreateUserAcc("IgorStepanov@stilyagi.ru","Stepanov", "Staff");
        //System.out.println("OK");
        launch();
    }

    public static int getLVL(String r) throws SQLException, FileNotFoundException, ClassNotFoundException {
        int lvl = 7;
        System.out.println(r);
        String user = DataBase.getCurrent_User();
        id = DataBase.getEmployee_byNAME(user);
        System.out.println("id:"+id);
        if(r.equals("local_manager       ")){
            lvl = 3;
            user_role = "Управляющий";
        }
        else if(r.equals("staff               ")){
            lvl = 1;
            user_role = "Персонал";
        }
        else if(r.equals("manager             ")){
            lvl = 2;
            user_role = "Менеджер";
        }
        else if(r.equals("analyst             ")){
            lvl = 4;
            user_role = "Аналитик";
            System.out.println(user_role);
        }
        else {
            lvl = 5;
            user_role = "Администратор";
        }
        return lvl;
    }
    public static void getMessage(){

    }
}