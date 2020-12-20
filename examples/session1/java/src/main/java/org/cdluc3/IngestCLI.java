package org.cdluc3;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

public class IngestCLI {
    public static final void main(String[] argv) {
        System.out.println("Hello..");

        try {  
            Connection con = DriverManager.getConnection(  
                "jdbc:mysql://mydb:3306/userdb",
                "root",
                "password"
            );  
            String sql = "select 1, now(), user()";
            System.out.println(sql);
            Statement stmt = con.createStatement();  
            ResultSet rs=stmt.executeQuery(sql);  
            while(rs.next()) {
                System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));  
            } 
            con.close();  
        } catch(Exception e) { 
            System.out.println(e);
        }  
        System.out.println("...");
    }

}