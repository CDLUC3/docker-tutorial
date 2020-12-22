package org.cdluc3;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.Scanner;

public class IngestCLI {
    private String connstr = "jdbc:mysql://mydb:3306/userdb";
    private String user = "root";
    private String password = "password";

    public IngestCLI() {
    }

    public void runQuery(String sql) throws SQLException {
        try(Connection con = DriverManager.getConnection(connstr, user, password)){
            Statement stmt = con.createStatement();  
            ResultSet rs=stmt.executeQuery(sql);  
            while(rs.next()) {
                System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));  
            }     
        }
    }

    public void processInput(String fname) {
        try (BufferedReader br = new BufferedReader(new FileReader(fname))) {
            for(String line = br.readLine(); line != null; line = br.readLine()) {
                System.out.println(line);
            }
        } catch(Exception e) { 
            System.out.println(e);
        }  
    }

    public void processInput1() {
        try {  
            runQuery("select 1, now(), user()");
        } catch(Exception e) { 
            System.out.println(e);
        }  
    }

    public static final void main(String[] argv) {
        System.out.println("Hello..");
        IngestCLI cli = new IngestCLI();
        cli.processInput("test.csv");

        System.out.println("...");
    }

}