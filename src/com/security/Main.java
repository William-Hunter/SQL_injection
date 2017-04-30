package com.security;

import java.sql.*;
import java.util.Scanner;

public class Main {
    static Connection con;
    static Statement state;
    static ResultSet rs;
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        init();
        Scanner sc = new Scanner(System.in);
        boolean retu = false;
        while (!retu) {
            System.out.print("1:create\t2:read\t3:delete\t4:update\t5:showall\t6:退出\n");
            String[] piece = null;
            retu = false;
            switch (sc.nextLine()) {
                case "1":
                    System.out.print("请输入ID,NAME：");
                    piece = sc.nextLine().split(",");
                    create(Integer.valueOf(piece[0]), piece[1]);
                    System.out.println("----------------------------------------");
                    break;
                case "2":
                    System.out.print("请输入ID：");
                    read(Integer.valueOf(sc.nextLine()));
                    System.out.println("----------------------------------------");
                    break;
                case "3":
                    System.out.print("请输入ID：");
                    delete(Integer.valueOf(sc.nextLine()));
                    System.out.println("----------------------------------------");
                    break;
                case "4":
                    System.out.print("请输入ID,NAME：");
                    piece=sc.nextLine().split(",");
                    update(Integer.valueOf(piece[0]), piece[1]);
                    System.out.println("----------------------------------------");
                    break;
                case "5":
                    display();
                    break;
                case "6":
                    retu = true;
                    break;
                default:
                    System.out.println("不识别的内容");
                    break;
            }
        }
        System.out.println("Bye Bye");
    }
    static void display() throws SQLException {
        String sql = "SELECT * FROM main_test;";
        state = con.createStatement();
        rs = state.executeQuery(sql);
        ResultSetMetaData rsmd = rs.getMetaData();
        for (rs.first(); !rs.isAfterLast(); rs.next()) {
            for (int index = 1; index <=rsmd.getColumnCount(); index++) {
                System.out.print(rs.getObject(index)+"\t");
            }
            System.out.println();
        }
    }
    static boolean update(int ID,String name) throws SQLException {
        String sql = "UPDATE main_test SET name='"+name+"' WHERE ID=" + ID + ";";
        System.out.println("sql:"+sql);
        state = con.createStatement();
        return state.execute(sql);
    }
    static boolean delete(int ID) throws SQLException {
        String sql = "DELETE FROM main_test WHERE ID=" + ID + ";";
        System.out.println("sql:"+sql);
        state = con.createStatement();
        return state.execute(sql);
    }
    static void read(int ID) throws SQLException {
        String sql = "SELECT * FROM main_test WHERE ID=" + ID + ";";
        System.out.println("sql:"+sql);
        state = con.createStatement();
        rs = state.executeQuery(sql);
        ResultSetMetaData rsmd = rs.getMetaData();
        for (rs.first(); !rs.isAfterLast(); rs.next()) {
            for (int index = 1; index <= rsmd.getColumnCount(); index++) {
                System.out.print(rs.getObject(index)+"\t");
            }
            System.out.println();
        }
    }
    static boolean create(int ID, String name) throws SQLException {
        String sql = "INSERT INTO main_test VALUES(" + ID + ",'" + name + "');";
        System.out.println("sql:"+sql);
        state = con.createStatement();
        return state.execute(sql);
    }

    static void init() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/injection", "root", "IWantFuckTheWorld");
    }
}
