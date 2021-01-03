package org.cdluc3;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.File;
import java.util.Scanner;

public class IngestCLI {
    private String connstr = "jdbc:mysql://mydb:3306/userdb?characterEncoding=UTF-8&characterSetResults=UTF-8";
    //private String connstr = "jdbc:mysql://mydb:3306/userdb";
    private String user = "root";
    private String password = "password";

    public IngestCLI() {
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(connstr, user, password);
    }

    public void printRecord(int id, String fname, String lname, String email, String phone) {
        System.out.println(
            String.format(
                "%5d\t%10s\t%10s\t%30s\t%10s",
                id,
                fname, 
                lname, 
                email,
                phone
            )
        );

    }

    public void addRecord(String fname, String lname, String email, String phone) throws SQLException {
        int id = getUserId(fname, lname);
        if (id == 0) {
            id = addUser(fname, lname);
        }
        printRecord(id, fname, lname, email, phone);
        addPhone(id, phone);
        addEmail(id, email);
    }

    public int getUserId(String fname, String lname) throws SQLException {
        try(Connection con = getConnection()){
            String sql = "select id from users where first_name = ? and last_name = ?";
            try (PreparedStatement stmt = con.prepareStatement(sql)){
                stmt.setString(1, fname);
                stmt.setString(2, lname);
                ResultSet rs=stmt.executeQuery();
                while(rs.next()) {
                    return rs.getInt(1);  
                }  
            }
        }
        return 0;
    }

    public int addUser(String fname, String lname) throws SQLException {
        try(Connection con = getConnection()){
            String sql = "insert into users(first_name, last_name) select ?, ?";
            try (PreparedStatement stmt = con.prepareStatement(sql)){
                stmt.setString(1, fname);
                stmt.setString(2, lname);
                if (stmt.executeUpdate() > 0) {
                    return getUserId(fname, lname);
                }
            }
        }
        return 0;
    }

    public void addPhone(int id, String phone) throws SQLException {
        if (id == 0 || phone.isEmpty()) {
            return;
        }
        try(Connection con = getConnection()){
            String sql = "insert into phone(user_id, phone) select ?, ?";
            try (PreparedStatement stmt = con.prepareStatement(sql)){
                stmt.setInt(1, id);
                stmt.setString(2, phone);
                stmt.executeUpdate();
            } catch(SQLIntegrityConstraintViolationException ex) {
                System.out.println(String.format("\t\tPhone [%s] already exists for user id %d", phone, id));
            }
        }
    }

    public void addEmail(int id, String email) throws SQLException {
        if (id == 0 || email.isEmpty()) {
            return;
        }
        try(Connection con = getConnection()){
            String sql = "insert into email(user_id, email) select ?, ?";
            try (PreparedStatement stmt = con.prepareStatement(sql)){
                stmt.setInt(1, id);
                stmt.setString(2, email);
                stmt.executeUpdate();
            } catch(SQLIntegrityConstraintViolationException ex) {
                System.out.println(String.format("\t\tEmail [%s] already exists for user id %d", email, id));
            } 
        }
    }

    public void processInput(String filename) {
        try(Scanner scanner = new Scanner(new File(filename))) {
            scanner.useDelimiter("[,\\n]");
            while(scanner.hasNext()) {
                String fname = scanner.next();
                if (!fname.startsWith("#")){
                    String lname = scanner.next();
                    String email = scanner.next();
                    String phone = scanner.next();
                    addRecord(fname, lname, email, phone);
                }
                if (scanner.hasNextLine()) {
                    scanner.nextLine();
                }
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
        String file = argv.length > 0 ? argv[0] : "test.csv";
        System.out.println(String.format("Import the contents of [%s]", file));
        IngestCLI cli = new IngestCLI();
        cli.processInput(file);
    }

}