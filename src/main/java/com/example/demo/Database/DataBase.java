package com.example.demo.Database;

import com.example.demo.Model.*;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;

import java.io.FileNotFoundException;
import java.sql.*;

public class DataBase {

    static Connection connection;
    static Connection admin_connection;
    static Statement statmt;
    static Statement admin_statmt;
    static ResultSet resSet;
    static ResultSet admin_resSet;
    static String login;
    String pass;

    public DataBase(String login, String pass) throws SQLException {
        this.login = login;
        this.pass = pass;
        connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/stilyagi",login,pass);
        statmt = connection.createStatement();
        admin_connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/stilyagi","postgres","123");
    }

    public static String getRole() throws SQLException {
        String role;
        admin_statmt = admin_connection.createStatement();
        admin_resSet = admin_statmt.executeQuery("SELECT level_name \n" +
                "FROM \"user\" join level using(level_id) where login = '"+ login + "';");
        admin_resSet.next();
        role = admin_resSet.getString("level_name");
        System.out.println("role: " + role);
        return role;
    }

    public static String getLastClient() throws SQLException {
        String id;
        statmt = connection.createStatement();
        resSet = statmt.executeQuery("SELECT max(client_id) FROM client;");
        resSet.next();
        id = resSet.getString("max");

        System.out.println("maxID_client: " + id);
        return id;
    }

    public static String getLastVisit() throws SQLException {
        String id;
        statmt = connection.createStatement();
        resSet = statmt.executeQuery("SELECT max(visit_id) FROM visit;");
        resSet.next();
        id = resSet.getString("max");
        //System.out.println("maxID_client: " + id);
        return id;
    }
    public static String getLastStructure() throws SQLException {
        String id;
        statmt = admin_connection.createStatement();
        resSet = statmt.executeQuery("SELECT max(structure_id) FROM structure;");
        resSet.next();
        id = resSet.getString("max");
        //System.out.println("maxID_client: " + id);
        return id;
    }
    public static String getLastStructure2() throws SQLException {
        String id;
        statmt = connection.createStatement();
        resSet = statmt.executeQuery("SELECT max(structure_id) FROM structure;");
        resSet.next();
        id = resSet.getString("max");
        //System.out.println("maxID_client: " + id);
        return id;
    }
    public static String getLastEmployee() throws SQLException {
        String id;
        statmt = admin_connection.createStatement();
        resSet = statmt.executeQuery("SELECT max(employee_id) FROM employee;");
        resSet.next();
        id = resSet.getString("max");
        //System.out.println("maxID_client: " + id);
        return id;
    }
    public static String getLastEmployee2() throws SQLException {
        String id;
        statmt = connection.createStatement();
        resSet = statmt.executeQuery("SELECT max(employee_id) FROM employee;");
        resSet.next();
        id = resSet.getString("max");
        //System.out.println("maxID_client: " + id);
        return id;
    }
    public static String getLastMessage() throws SQLException {
        String id;
        statmt = connection.createStatement();
        resSet = statmt.executeQuery("SELECT max(application_id) FROM application;");
        resSet.next();
        id = resSet.getString("max");
        //System.out.println("maxID_client: " + id);
        return id;
    }
    public static String getLastService() throws SQLException {
        String id;
        statmt = connection.createStatement();
        resSet = statmt.executeQuery("SELECT max(service_id) FROM service;");
        resSet.next();
        id = resSet.getString("max");
        //System.out.println("maxID_client: " + id);
        return id;
    }

    public static String getLastUser() throws SQLException {
        String id;
        statmt = connection.createStatement();
        resSet = statmt.executeQuery("SELECT max(user_id) FROM \"user\";");
        resSet.next();
        id = resSet.getString("max");
        //System.out.println("maxID_client: " + id);
        return id;
    }
    public static String getLastWork() throws SQLException {
        String id;
        statmt = connection.createStatement();
        resSet = statmt.executeQuery("SELECT max(work_id) FROM work;");
        resSet.next();
        id = resSet.getString("max");
        //System.out.println("maxID_client: " + id);
        return id;
    }

    public static String[] getClient_byID(String id) throws SQLException {

        statmt = connection.createStatement();
        resSet = statmt.executeQuery("SELECT * FROM client WHERE client_id = '" + id + "';");
        String[] mas = new String[4];
        resSet.next();
        mas[0] = resSet.getString("client_id");
        mas[1] = resSet.getString("client_name");
        mas[2] = resSet.getString("client_status");
        mas[3] = resSet.getString("client_bonus");
        return mas;
    }

    public static String[] getVisit_byID(String id) throws SQLException {

        statmt = connection.createStatement();
        resSet = admin_statmt.executeQuery("SELECT * FROM visit\n" +
                "JOIN client using(client_id)\n" +
                "JOIN structure using(structure_id)\n" +
                "JOIN service using(service_id)\n" +
                "JOIN employee using(employee_id)" +
                " WHERE visit_id =" + id + ";");
        int i=0;
        String[] mas = new String[7];
        resSet.next();
        mas[0] = resSet.getString("client_id");
        mas[1] = resSet.getString("structure_name");
        mas[3] = resSet.getString("data");
        mas[2] = resSet.getString("service_name");
        mas[4] = resSet.getString("employee_name");
        mas[5] = resSet.getString("summa");
        mas[6] = resSet.getString("points");
        return mas;
    }

    public static String[][] getClient_byNAME(String name) throws SQLException {

        statmt = connection.createStatement();
        String id = DataBase.getLastClient();
        int kol = Integer.parseInt(id);
        String[][] mas = new String[4][kol];
        resSet = statmt.executeQuery("SELECT * FROM client \n" +
                "WHERE position('" + name + "' in client_name)>0;");
        int i = 0;
        if(resSet == null)
            System.out.println(kol);
        while(resSet.next()) {
            System.out.println("i/"+i);
            mas[0][i] = resSet.getString("client_id");
            mas[1][i] = resSet.getString("client_name");
            mas[2][i] = resSet.getString("client_status");
            mas[3][i] = resSet.getString("client_bonus");
            i++;
        }
        return mas;
    }


    public static void UpdateClient(String t1, String t2, String t3, String t4) throws SQLException {
        System.out.println(t1+"|"+t2+"|"+ t3+"|" + t4);
        statmt.execute("UPDATE client " +
                "set client_id = "  + t1 + "," +
                " client_name = '"+ t2 + "'," +
                " client_status = '" + t3 + "'," +
                " client_bonus = " + t4 +
                " where client_id = " + t1+";");
    }

    public static void UpdateMessage(String id, String t) throws SQLException {
        statmt.execute("UPDATE application " +
                "set application_status = '"  + t +
                "' where application_id = " + id+";");
    }

    public static void UpdateWork(String t1, String t2, String t3) throws SQLException, FileNotFoundException, ClassNotFoundException {
        t2 = DataBase.getEmployee_byNAME2(t2);
        t3 = DataBase.getStructure_byNAME(t3);
        statmt.execute("UPDATE work " +
                "set employee_id = "  + t2 + "," +
                " structure_id = "+ t3 +
                " where work_id = " + t1+";");
    }

    public static void UpdateStructure(String id, String t1, String t2, String t3, String t4, String t5, String t6) throws SQLException {
        //System.out.println(t1+"|"+t2+"|"+ t3+"|" + t4);
        statmt.execute("UPDATE structure " +
                "set structure_name = '"  + t1 + "'," +
                " address = '"+ t2 + "'," +
                " specialty_id = '" + t3 + "'," +
                " post_index = '" + t4 + "'," +
                " tel_number = '" + t5 + "'," +
                " num_employees = " + t6 +
                " where structure_id = " + id+";");
    }
    public static void UpdateEmployee(String id, String t1, String t2, String t3, String t4,
                                      String t5, String t6, String t7, String t8) throws SQLException {
        //System.out.println(t1+"|"+t2+"|"+ t3+"|" + t4);
        statmt.execute("UPDATE employee " +
                "set employee_name = '"  + t1 + "'," +
                " post_id = "+ t2 + "," +
                " employee_exp = " + t3 + "," +
                " employee_inf = '" + t5 + "'," +
                " employee_age = " + t6 + "," +
                " employee_num = " + t8 +
                " where employee_id = " + id+";");
    }

    public static void UpdateVisit(String visit_id, String client_id, String str, String ser, String date,
                                   String emp,String summa, String points) throws SQLException, FileNotFoundException, ClassNotFoundException {
        str = DataBase.getStructure_byNAME(str);
        ser = DataBase.getService_byNAME(ser);
       // System.out.println(emp);
        emp = DataBase.getEmployee_byNAME2(emp);
        //System.out.println(str+"|"+ ser+"|"+"|"+date+"|"+emp);
        statmt.execute("UPDATE visit " +
                "set client_id = "  + client_id + "," +
                " structure_id = "+ str + "," +
                " service_id = " + ser + "," +
                " data = '" + date + "'," +
                " employee_id = " + emp + "," +
                " summa = " + summa + "," +
                " points = " + points +
                " where visit_id = " + visit_id+";");
    }

    public static void UpdateUser(String id, String t1, String t2, String t3) throws SQLException {
        //System.out.println(t1+"|"+t2+"|"+ t3+"|" + t4);
        statmt.execute("UPDATE \"user\" " +
                "set login = '"  + t1 + "'," +
                " password = '"+ t2 + "'," +
                " level_id = " + t3  +
                " where employee_id = " + id+";");
    }
    public static void delClient(String t1) throws SQLException {
        statmt.execute("DELETE FROM visit "+
                " where client_id = " + t1+";");
        statmt.execute("DELETE FROM client "+
                " where client_id = " + t1+";");
    }
    public static void delVisit(String t1) throws SQLException {
        statmt.execute("DELETE FROM visit "+
                " where visit_id = " + t1+";");
    }

    public static void delWork(String t1) throws SQLException {
        statmt.execute("DELETE FROM work "+
                " where work_id = " + t1+";");
    }

    public static void delEmployee(String t1) throws SQLException, FileNotFoundException, ClassNotFoundException {
        statmt.execute("DELETE FROM \"user\" "+
                " where employee_id = " + t1+";");
        statmt.execute("DELETE FROM work "+
                " where employee_id = " + t1+";");
        statmt.execute("DELETE FROM employee "+
                " where employee_id = " + t1+";");
        t1 = DataBase.getEmployee_byID2(t1);
        statmt.execute("DROP ROLE \"" + t1 + "\";");
    }

    public static Structure_Local[] getStructure() throws SQLException, ClassNotFoundException, FileNotFoundException {
        statmt = connection.createStatement();
        String r = getLastStructure();
        int kol = Integer.parseInt(r);
        Structure_Local[] mas = new Structure_Local[kol];
        resSet = statmt.executeQuery("SELECT structure_id, structure_name, address, specialty_name, post_index, tel_number, " +
                "num_employees \n" +
                "FROM structure JOIN specialty using(specialty_id) ORDER by structure_id asc;");
        int i=0;
        while (resSet.next()) {
            String t1 = resSet.getString("structure_id");
            String t2 = resSet.getString("structure_name");
            String t3 = resSet.getString("address");
            String t4 = resSet.getString("specialty_name");
            String t5 = resSet.getString("post_index");
            String t6 = resSet.getString("tel_number");
            String t7 = resSet.getString("num_employees");
            //System.out.println(t1 + " | " + t2 + " | " + t3 + " | " + t4);
            mas[i] = new Structure_Local(t1,t2,t3,t4,t5,t6,t7);
            i++;
        }
        return mas;
        //  System.out.println("-");
    }
    public static Structure_Local[] getStructure2() throws SQLException, ClassNotFoundException, FileNotFoundException {
        statmt = connection.createStatement();
        String r = getLastStructure2();
        int kol = Integer.parseInt(r);
        Structure_Local[] mas = new Structure_Local[kol];
        resSet = statmt.executeQuery("SELECT structure_id, structure_name, address, specialty_name, post_index, tel_number, " +
                "num_employees \n" +
                "FROM structure JOIN specialty using(specialty_id) ORDER by structure_id asc;");
        int i=0;
        while (resSet.next()) {
            String t1 = resSet.getString("structure_id");
            String t2 = resSet.getString("structure_name");
            String t3 = resSet.getString("address");
            String t4 = resSet.getString("specialty_name");
            String t5 = resSet.getString("post_index");
            String t6 = resSet.getString("tel_number");
            String t7 = resSet.getString("num_employees");
            //System.out.println(t1 + " | " + t2 + " | " + t3 + " | " + t4);
            mas[i] = new Structure_Local(t1,t2,t3,t4,t5,t6,t7);
            i++;
        }
        return mas;
        //  System.out.println("-");
    }

    public static Service_Analyst[] getService() throws SQLException, ClassNotFoundException, FileNotFoundException {
        statmt = connection.createStatement();
        String r = getLastService();
        int kol = Integer.parseInt(r);
        Service_Analyst[] mas = new Service_Analyst[kol];
        resSet = statmt.executeQuery("SELECT * FROM service ORDER by service_id asc;");
        int i=0;
        while (resSet.next()) {
            String t1 = resSet.getString("service_id");
            String t2 = resSet.getString("service_name");
            String t3 = resSet.getString("service_price");
            mas[i] = new Service_Analyst(t1,t2,t3);
            i++;
        }
        return mas;
        //  System.out.println("-");
    }

    public static String[] getService_name() throws SQLException, ClassNotFoundException, FileNotFoundException {
        statmt = connection.createStatement();
        String r = getLastService();
        int kol = Integer.parseInt(r);
        String[] mas = new String[kol];
        resSet = statmt.executeQuery("SELECT * FROM service ORDER by service_id asc;");
        int i=0;
        while (resSet.next()) {
            String t2 = resSet.getString("service_name");
            mas[i] = t2;
            i++;
        }
        return mas;
        //  System.out.println("-");
    }

    public static String getPrice(String name) throws SQLException, ClassNotFoundException, FileNotFoundException {
        statmt = connection.createStatement();
        resSet = statmt.executeQuery("SELECT service_price FROM service WHERE service_name = '"+ name + "';");
        String t ="";
        while (resSet.next()) {
            t = resSet.getString("service_price");
        }
        return t;
        //  System.out.println("-");
    }

    public static String getCurrent_User() throws SQLException, ClassNotFoundException, FileNotFoundException {
        statmt = connection.createStatement();
        resSet = statmt.executeQuery("SELECT current_user;");
        String t ="";
        while (resSet.next()) {
            t = resSet.getString("current_user");
        }
        return t;
        //  System.out.println("-");
    }
    public static User getUser(String id) throws SQLException, ClassNotFoundException, FileNotFoundException {
        statmt = connection.createStatement();
        resSet = statmt.executeQuery("SELECT * FROM \"user\" WHERE employee_id = " + id + " ;");
        User mas = null;
        while (resSet.next()) {
            String t1 = resSet.getString("login");
            String t2 = resSet.getString("password");
            String t3 = resSet.getString("level_id");
            mas = new User(id,t1,t2,t3);
        }
        return mas;
        //  System.out.println("-");
    }

    public static Structure_Local getStructure_byID(String id) throws SQLException, ClassNotFoundException, FileNotFoundException {
        statmt = connection.createStatement();
        resSet = statmt.executeQuery("SELECT structure_id, structure_name, address, specialty_name, post_index, tel_number, " +
                "num_employees \n" +
                "FROM structure JOIN specialty using(specialty_id) WHERE structure_id = "+ id + ";");
        Structure_Local mas = null;
        while (resSet.next()) {
            String t1 = resSet.getString("structure_id");
            String t2 = resSet.getString("structure_name");
            String t3 = resSet.getString("address");
            String t4 = resSet.getString("specialty_name");
            String t5 = resSet.getString("post_index");
            String t6 = resSet.getString("tel_number");
            String t7 = resSet.getString("num_employees");
            //System.out.println(t1 + " | " + t2 + " | " + t3 + " | " + t4);
            mas = new Structure_Local(t1,t2,t3,t4,t5,t6,t7);
        }
        return mas;
        //  System.out.println("-");
    }

    public static String[] getWork_byID(String id) throws SQLException, ClassNotFoundException, FileNotFoundException {
        statmt = connection.createStatement();
        resSet = statmt.executeQuery("SELECT * FROM work JOIN structure using(structure_id) JOIN employee using(employee_id)" +
                " WHERE work_id = "+ id + ";");
        String[] mas = new String[3];
        while (resSet.next()) {
            mas[0] = resSet.getString("work_id");
            mas[1] = resSet.getString("structure_name");
            mas[2] = resSet.getString("employee_name");
        }
        return mas;
        //  System.out.println("-");
    }

    public static Employee_Local getEmployee_byID(String id) throws SQLException, ClassNotFoundException, FileNotFoundException {
        statmt = admin_connection.createStatement();
        resSet = statmt.executeQuery("SELECT * \n" +
                "FROM employee join post using(post_id) WHERE employee_id = "+ id + ";");
        Employee_Local mas = null;
        while (resSet.next()) {
            String t1 = resSet.getString("employee_id");
            String t2 = resSet.getString("employee_name");
            String t3 = resSet.getString("post_name");
            String t4 = resSet.getString("employee_exp");
            String t5 = resSet.getString("employee_sal");
            String t6 = resSet.getString("employee_inf");
            String t7 = resSet.getString("employee_age");
            String t8 = resSet.getString("employee_score");
            String t9 = resSet.getString("employee_num");
            //System.out.println(t1 + " | " + t2 + " | " + t3 + " | " + t4 + " | " + t5 + " | " + t6 + " | " + t7 + " | " + t8 + " | " + t9);
            mas = new Employee_Local(t1,t2,t3,t4,t5,t6,t7,t8,t9);
        }
        return mas;
    }



    public static Client_Staff[] getAllClient_Staff() throws SQLException, ClassNotFoundException, FileNotFoundException {
        statmt = connection.createStatement();
        String r = getLastClient();
        System.out.println("kol: " + r);
        int kol = Integer.parseInt(r);
        Client_Staff[] mas = new Client_Staff[kol];
        resSet = statmt.executeQuery("SELECT * \n" +
                "FROM client ORDER by client_id asc;");
        int i=0;
        while (resSet.next()) {
            String t1 = resSet.getString("client_id");
            String t2 = resSet.getString("client_name");
            String t3 = resSet.getString("client_status");
            String t4 = resSet.getString("client_bonus");
            System.out.println(t1 + " | " + t2 + " | " + t3 + " | " + t4);
            mas[i] = new Client_Staff(t1,t2,t3,t4);
            i++;
        }
        System.out.println("-");
        return mas;
    }

    public static Visit_Staff[] getAllVisit_Staff() throws SQLException, ClassNotFoundException, FileNotFoundException {
        statmt = connection.createStatement();
        String r = getLastVisit();
        System.out.println("kol: " + r);
        int kol = Integer.parseInt(r);
        Visit_Staff[] mas = new Visit_Staff[kol];
        resSet = admin_statmt.executeQuery("SELECT visit_id, client_name, structure_name, service_name, \"data\", employee_name, summa, points FROM visit\n" +
                "JOIN client using(client_id)\n" +
                "JOIN structure using(structure_id)\n" +
                "JOIN service using(service_id)\n" +
                "JOIN employee using(employee_id)" +
                " ORDER BY visit_id asc;");
        int i=0;
        while (resSet.next()) {
            System.out.println("i:"+i);
            String t1 = resSet.getString("visit_id");
            String t2 = resSet.getString("client_name");
            String t3 = resSet.getString("structure_name");
            String t4 = resSet.getString("service_name");
            String t5 = resSet.getString("data");
            String t6 = resSet.getString("employee_name");
            String t7 = resSet.getString("summa");
            String t8 = resSet.getString("points");
            //System.out.println(t1 + " | " + t2 + " | " + t3 + " | " + t4);
            mas[i] = new Visit_Staff(t1,t2,t3,t4,t5,t6,t7,t8);
            i++;
        }
        System.out.println("-");
        return mas;
    }

    public static String[] getStructure_name() throws SQLException, ClassNotFoundException, FileNotFoundException {
        statmt = connection.createStatement();
        String r = getLastStructure();
        //System.out.println("kol: " + r);
        int kol = Integer.parseInt(r);
        String[] mas = new String[kol];
        resSet = statmt.executeQuery("SELECT structure_name FROM structure;");
        int i=0;
        while (resSet.next()) {
            //System.out.println("i:"+i);
            String t1 = resSet.getString("structure_name");
            mas[i] = t1;
            i++;
        }
        System.out.println("-");
        return mas;
    }

    public static String[] getEmployee_name() throws SQLException, ClassNotFoundException, FileNotFoundException {
        statmt = connection.createStatement();
        String r = getLastEmployee();
        //System.out.println("kol: " + r);
        int kol = Integer.parseInt(r);
        String[] mas = new String[kol];
        resSet = statmt.executeQuery("SELECT employee_name FROM employee;");
        int i=0;
        while (resSet.next()) {
            //System.out.println("i:"+i);
            String t1 = resSet.getString("employee_name");
            mas[i] = t1;
            i++;
        }
        System.out.println("-");
        return mas;
    }

    public static String getStructure_byNAME(String name) throws SQLException, ClassNotFoundException, FileNotFoundException {
        statmt = admin_connection.createStatement();
        String t="";
        resSet = statmt.executeQuery("SELECT structure_id FROM structure WHERE structure_name = '"+ name+"';");
        while (resSet.next()) {
            //System.out.println("i:"+i);
            String t1 = resSet.getString("structure_id");
            t = t1;
        }
        System.out.println("-");
        return t;
    }

    public static String getService_byNAME(String name) throws SQLException, ClassNotFoundException, FileNotFoundException {
        statmt = connection.createStatement();
        String t="";
        resSet = statmt.executeQuery("SELECT service_id FROM service WHERE service_name = '"+ name+"';");
        while (resSet.next()) {
            //System.out.println("i:"+i);
            String t1 = resSet.getString("service_id");
            t = t1;
        }
        System.out.println("-");
        return t;
    }
    public static String getEmployee_byNAME(String name) throws SQLException, ClassNotFoundException, FileNotFoundException {
        statmt = connection.createStatement();
        String t="";
        resSet = statmt.executeQuery("SELECT employee_id FROM \"user\" WHERE login = '"+ name+"';");
        while (resSet.next()) {
            //System.out.println("i:"+i);
            String t1 = resSet.getString("employee_id");
            t = t1;
        }
        System.out.println("-");
        return t;
    }
    public static String getEmployee_byID2(String id) throws SQLException, ClassNotFoundException, FileNotFoundException {
        statmt = connection.createStatement();
        String t="";
        resSet = statmt.executeQuery("SELECT login FROM \"user\" WHERE employee_id = "+ id+";");
        while (resSet.next()) {
            //System.out.println("i:"+i);
            String t1 = resSet.getString("login");
            t = t1;
        }
        System.out.println("-");
        return t;
    }

    public static Message[] getMessage(String id) throws SQLException, ClassNotFoundException, FileNotFoundException {
        statmt = connection.createStatement();
        String t="";
        resSet = statmt.executeQuery("SELECT * FROM application WHERE employee_from = "+ id+" or employee_to = "
                + id+" ORDER by date desc;");
        Message[] mas = new Message[100];
        int i=0;
        while (resSet.next()) {
            //System.out.println("i:"+i);
            String t1 = resSet.getString("application_id");
            String t2 = resSet.getString("application_title");
            String t3 = resSet.getString("application_desc");
            String t4 = resSet.getString("application_status");
            String t5 = resSet.getString("employee_from");
            String t6 = resSet.getString("date");
            String t7 = resSet.getString("employee_to");
            mas[i] = new Message(t1,t2,t3,t4,t5,t6,t7);
            i++;
        }
        System.out.println("-");
        return mas;
    }

    public static Message getMessage_byID(String id) throws SQLException, ClassNotFoundException, FileNotFoundException {
        statmt = connection.createStatement();
        String t="";
        resSet = statmt.executeQuery("SELECT * FROM application WHERE application_id = " + id+";");
        Message mas = null;
        int i=0;
        while (resSet.next()) {
            //System.out.println("i:"+i);
            String t1 = resSet.getString("application_id");
            String t2 = resSet.getString("application_title");
            String t3 = resSet.getString("application_desc");
            String t4 = resSet.getString("application_status");
            String t5 = resSet.getString("employee_from");
            String t6 = resSet.getString("date");
            String t7 = resSet.getString("employee_to");
            mas = new Message(t1,t2,t3,t4,t5,t6,t7);
        }
        System.out.println("-");
        return mas;
    }
    public static String getEmployee_byNAME2(String name) throws SQLException, ClassNotFoundException, FileNotFoundException {
        statmt = admin_connection.createStatement();
        String t="";
        resSet = statmt.executeQuery("SELECT employee_id FROM employee WHERE employee_name = '"+ name+"';");
        while (resSet.next()) {
            //System.out.println("i:"+i);
            String t1 = resSet.getString("employee_id");
            t = t1;
        }
        System.out.println("-");
        return t;
    }

    public static Employee_Local[] getAllEmployee() throws SQLException, ClassNotFoundException, FileNotFoundException {
        statmt = connection.createStatement();
        String id = DataBase.getLastEmployee();
        int kol = Integer.parseInt(id);
        Employee_Local[] mas = new Employee_Local[kol];
        resSet = statmt.executeQuery("SELECT * \n" +
                "FROM employee join post using(post_id) ORDER BY employee_id asc;");
        int i=0;
        while (resSet.next()) {
            String t1 = resSet.getString("employee_id");
            String t2 = resSet.getString("employee_name");
            String t3 = resSet.getString("post_name");
            String t4 = resSet.getString("employee_exp");
            String t5 = resSet.getString("employee_sal");

            String t6 = resSet.getString("employee_inf");
            String t7 = resSet.getString("employee_age");
            String t8 = resSet.getString("employee_score");
            String t9 = resSet.getString("employee_num");
            //System.out.println(t1 + " | " + t2 + " | " + t3 + " | " + t4 + " | " + t5 + " | " + t6 + " | " + t7 + " | " + t8 + " | " + t9);
            mas[i] = new Employee_Local(t1,t2,t3,t4,t5,t6,t7,t8,t9);
            i++;
        }
        System.out.println("-");
        return mas;
    }
    public static Employee_Local[] getAllEmployee2() throws SQLException, ClassNotFoundException, FileNotFoundException {
        statmt = admin_connection.createStatement();
        String id = DataBase.getLastEmployee2();
        int kol = Integer.parseInt(id);
        Employee_Local[] mas = new Employee_Local[kol];
        resSet = statmt.executeQuery("SELECT * \n" +
                "FROM employee join post using(post_id) ORDER BY employee_id asc;");
        int i=0;
        while (resSet.next()) {
            String t1 = resSet.getString("employee_id");
            String t2 = resSet.getString("employee_name");
            String t3 = resSet.getString("post_name");
            String t4 = resSet.getString("employee_exp");
            String t5 = resSet.getString("employee_sal");

            String t6 = resSet.getString("employee_inf");
            String t7 = resSet.getString("employee_age");
            String t8 = resSet.getString("employee_score");
            String t9 = resSet.getString("employee_num");
            //System.out.println(t1 + " | " + t2 + " | " + t3 + " | " + t4 + " | " + t5 + " | " + t6 + " | " + t7 + " | " + t8 + " | " + t9);
            mas[i] = new Employee_Local(t1,t2,t3,t4,t5,t6,t7,t8,t9);
            i++;
        }
        System.out.println("-");
        return mas;
    }

    public static Work_Admin[] getAllWork() throws SQLException, ClassNotFoundException, FileNotFoundException {
        statmt = connection.createStatement();
        String id = DataBase.getLastWork();
        int kol = Integer.parseInt(id);
        Work_Admin[] mas = new Work_Admin[kol];
        resSet = statmt.executeQuery("SELECT * \n" +
                "FROM work join employee using(employee_id) JOIN structure using(structure_id) ORDER BY work_id asc;");
        int i=0;
        while (resSet.next()) {
            String t1 = resSet.getString("work_id");
            String t2 = resSet.getString("employee_name");
            String t3 = resSet.getString("structure_name");
            mas[i] = new Work_Admin(t1,t2,t3);
            i++;
        }
        System.out.println("-");
        return mas;
    }

    public  static void CreateUserAcc(String login, String pass, String lvl) throws SQLException {
        statmt = connection.createStatement();
        statmt.execute("CREATE ROLE \""+ login  +"\""+
                "\tLOGIN\n" +
                "\tNOSUPERUSER\n" +
                "\tNOCREATEDB\n" +
                "\tNOCREATEROLE\n" +
                "\tINHERIT\n" +
                "\tNOREPLICATION\n" +
                "\tCONNECTION LIMIT -1\n" +
                "\tPASSWORD '" + pass + "';");
        statmt.execute("GRANT \""+lvl+"\" TO \""+ login+"\" WITH ADMIN OPTION;");
    }

    public  static void addClient(String id, String name, String status, String bonus) throws SQLException {
        statmt = connection.createStatement();
        statmt.execute("INSERT INTO public.client(\n" +
                "\tclient_id, client_name, client_status, client_bonus)\n" +
                "\tVALUES (" + id + ", '" + name + "', '" + status + "', " +  bonus + ");");
    }

    public  static void addMessage(String id, String name, String desc, String status,
                                   String from, String date, String to ) throws SQLException, FileNotFoundException, ClassNotFoundException {
        statmt = connection.createStatement();
        System.out.println(to);
        to = DataBase.getEmployee_byNAME2(to);
        System.out.println(to);
        statmt.execute("INSERT INTO public.application(\n" +
                "\tapplication_id, application_title, application_desc, " +
                "application_status, employee_from, date, employee_to)\n" +
                "\tVALUES( " + id + ", '" + name + "', '" + desc + "', '" +  status + "', " +
                from + ", '" + date + "', "+ to + ");");
    }

    public  static void addWork(String id, String name, String str) throws SQLException, FileNotFoundException, ClassNotFoundException {
        statmt = connection.createStatement();
        str = DataBase.getStructure_byNAME(str);
        name = DataBase.getEmployee_byNAME2(name);
        statmt.execute("INSERT INTO public.work(\n" +
                "\twork_id, structure_id, employee_id)\n" +
                "\tVALUES (" + id + ", " + str + ", " + name + " );");
    }

    public  static void addUser(String user_id, String employee_id, String log, String pass, String lvl) throws SQLException, FileNotFoundException, ClassNotFoundException {
        statmt = connection.createStatement();
        statmt.execute("INSERT INTO public.\"user\"(\n" +
                "\tuser_id, employee_id, login, password, level_id)\n" +
                "\tVALUES (" + user_id + ", " + employee_id + ", '" + log + "', '" + pass + "', "+ lvl+");");
    }

    public  static void addEmployee(String id, String name, String post, String exp, String sal,
                                    String inf,String age, String num) throws SQLException {
        statmt = connection.createStatement();
        System.out.println(id+"|"+name+"|"+post+"|"+exp+"|"+sal+"|"+inf+"|"+age+"|"+num);
        statmt.execute("INSERT INTO public.employee(\n" +
                "\temployee_id, employee_name, post_id, employee_exp, employee_sal, employee_inf, employee_age, employee_num)\n" +
                "\tVALUES (" + id +", '" + name + "', " + post + ", '" + exp + "', " + sal + ", '" + inf + "', " + age + ", " + num +");");
    }

    public  static void addVisit(String visit_id, String client_id, String str, String ser, String date,
                                    String emp,String summa, String points) throws SQLException, FileNotFoundException, ClassNotFoundException {
        statmt = connection.createStatement();
        str = DataBase.getStructure_byNAME(str);
        ser = DataBase.getService_byNAME(ser);
        emp = DataBase.getEmployee_byNAME(emp);
        System.out.println(points);
        statmt.execute("INSERT INTO public.visit(\n" +
                "\tvisit_id, client_id, structure_id, service_id, data, employee_id, summa, points)\n" +
                "\tVALUES (" + visit_id +", " + client_id + ", " + str + ", " + ser + ", '" + date + "', " + emp + ", " +
                summa + ", " + points +");");
    }

    public static void task1() throws SQLException {
        statmt = admin_connection.createStatement();
        statmt.execute("COPY (SELECT sum(summa) as \"Доход от услуги\", service_name as \"Название услуги\"\n" +
                "\t  FROM visit\n" +
                "\t  JOIN service using(service_id)\n" +
                "\t  GROUP BY \"Название услуги\"\n" +
                "\t  ORDER BY \"Доход от услуги\" desc\n" +
                "\t  LIMIT 3\n" +
                "\t  )\n" +
                "\t TO 'D:\\postgreSQL\\max_service.csv' CSV HEADER\n" +
                "\t DELIMITER '|' ENCODING 'WIN1251';");
    }

    public static void task2() throws SQLException {
        statmt = admin_connection.createStatement();
        statmt.execute("COPY (SELECT sum(summa) as \"Доход от услуги\", service_name as \"Название услуги\"\n" +
                "\t  FROM visit\n" +
                "\t  JOIN service using(service_id)\n" +
                "\t  GROUP BY \"Название услуги\"\n" +
                "\t  ORDER BY \"Доход от услуги\" asc\n" +
                "\t  LIMIT 3\n" +
                "\t  )\n" +
                "\t TO 'D:\\postgreSQL\\min_service.csv' CSV HEADER\n" +
                "\t DELIMITER '|' ENCODING 'WIN1251';");
    }
    public static void task3() throws SQLException {
        statmt = admin_connection.createStatement();
        statmt.execute("COPY (SELECT sum(summa) as \"Общий доход\"\n" +
                "    FROM visit\n" +
                "\t  )\n" +
                "    TO 'D:\\postgreSQL\\all_capital.csv' CSV HEADER\n" +
                "    DELIMITER '|' ENCODING 'WIN1251';");
    }

    public static void task4() throws SQLException {
        statmt = admin_connection.createStatement();
        statmt.execute("COPY (SELECT structure_name as \"Название заведения\", +\n" +
                "                CASE sum(summa)" +
                "                WHEN 0 THEN 0 \n" +
                "                ELSE sum(summa) end as \"Прибыль\" " +
                "                FROM visit" +
                "                right join structure using(structure_id)" +
                "                GROUP BY structure_name" +
                "                ORDER BY \"Прибыль\" asc)" +
                "                TO 'D:\\postgreSQL\\max_structure.csv' CSV HEADER" +
                "                DELIMITER '|' ENCODING 'WIN1251';");
    }

    public static void task5() throws SQLException {
        statmt = admin_connection.createStatement();
        statmt.execute("COPY (SELECT employee_name, employee_score\n" +
                "\t  FROM employee\n" +
                "\t  ORDER BY employee_score DESC\n" +
                "\t  LIMIT 3\n" +
                "\t )\n" +
                "\t TO 'D:\\postgreSQL\\avg_employee.csv' CSV HEADER\n" +
                "\t DELIMITER '|' ENCODING 'WIN1251';");
    }
}