package com.example.demo2;

import java.sql.*;
import java.time.LocalDate;

public class DataBaseHU extends Configs {
    Connection dbConnection;

    public Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;
        Class.forName("com.mysql.cj.jdbc.Driver");
        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);
        return dbConnection;
    }

    public void signUpCustomer(String title, String fio, String address, String otv_l,
                               String otv_l_im, String plan, String dogovor) throws SQLException, ClassNotFoundException {
        String insert = "INSERT INTO " + Const.CUSTOMER_TABLE + "(" + Const.CUSTOMER_TITLE + "," + Const.CUSTOMER_FIO +
                "," + Const.CUSTOMER_ADDRESS + "," + Const.CUSTOMER_OTV_L + "," + Const.CUSTOMER_OTV_L_IM + "," +
                Const.CUSTOMER_PLAN + "," + Const.CUSTOMER_DOGOVOR + ")" + "VALUES(?,?,?,?,?,?,?)";

        PreparedStatement prSt = getDbConnection().prepareStatement(insert);
        prSt.setString(1, title);
        prSt.setString(2, fio);
        prSt.setString(3, address);
        prSt.setString(4, otv_l);
        prSt.setString(5, otv_l_im);
        prSt.setString(6, plan);
        prSt.setString(7, dogovor);
        prSt.executeUpdate();
    }

    public void signUpAct(LocalDate data, String zakaz, String pact) throws SQLException, ClassNotFoundException {
        String insert = "INSERT INTO " + Const.ACT_TABLE + "(" + Const.ACT_DATA + "," +
                Const.ACT_ZAKAZ + "," + Const.ACT_PACT + ")" + "VALUES(?,?,?)";

        PreparedStatement prSt = getDbConnection().prepareStatement(insert);
        prSt.setDate(1, Date.valueOf(data));
        prSt.setString(2, zakaz);
        prSt.setString(3, pact);
        prSt.executeUpdate();

    }

    public ResultSet getUser(String email, String password) {
        ResultSet resSet;

        String select = "SELECT * FROM " + Const.USER_TABLE + " WHERE " + Const.USERS_EMAIL + "=? AND "
                + Const.USERS_PASSWORD + "=?";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1, email);
            prSt.setString(2, password);;

            resSet = prSt.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return resSet;
    }

    public ResultSet getCustomer(String title) throws SQLException, ClassNotFoundException {
        ResultSet resSet;
        String select = "SELECT * FROM " + Const.CUSTOMER_TABLE + " WHERE " + Const.CUSTOMER_TITLE + "=?";
        PreparedStatement prSt = getDbConnection().prepareStatement(select);
        prSt.setString(1, title);

        resSet = prSt.executeQuery();
        return resSet;
    }

    public ResultSet getCustomer1(String title) throws SQLException, ClassNotFoundException {
        ResultSet resSet;
        String select = "SELECT * FROM " + Const.CUSTOMER_TABLE + " WHERE " + Const.CUSTOMER_TITLE + " LIKE?";
        PreparedStatement prSt = getDbConnection().prepareStatement(select);
        prSt.setString(1, title + "%");

        resSet = prSt.executeQuery();
        return resSet;
    }

    public ResultSet getCustomer2(String fio) throws SQLException, ClassNotFoundException {
        ResultSet resSet;
        String select = "SELECT * FROM " + Const.CUSTOMER_TABLE + " WHERE " + Const.CUSTOMER_FIO + "=?";
        PreparedStatement prSt = getDbConnection().prepareStatement(select);
        prSt.setString(1, fio);

        resSet = prSt.executeQuery();
        return resSet;
    }

    public ResultSet getNAct() throws SQLException, ClassNotFoundException {
        ResultSet resSet;
        String select = "SELECT * FROM " + Const.ACT_TABLE;
        PreparedStatement prSt = getDbConnection().prepareStatement(select);
        resSet = prSt.executeQuery();
        return resSet;
    }

    public ResultSet getAllCustomers() throws SQLException, ClassNotFoundException {
        ResultSet resSet;
        String select = "SELECT * FROM " + Const.CUSTOMER_TABLE;
        PreparedStatement prSt = getDbConnection().prepareStatement(select);
        resSet = prSt.executeQuery();
        return resSet;
    }

    public void DeleteCustomer(String title) throws SQLException, ClassNotFoundException {
        String delete = "DELETE FROM " + Const.CUSTOMER_TABLE + " WHERE " + Const.CUSTOMER_TITLE + "=?";
        PreparedStatement prSt = getDbConnection().prepareStatement(delete);
        prSt.setString(1, title);
        prSt.executeUpdate();
    }

}
