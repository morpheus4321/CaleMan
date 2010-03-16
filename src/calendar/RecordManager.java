/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package calendar;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author xjankov2
 */
public class RecordManager {

    private User currentUser;
    private Connection connection;
    private static final String URL = "jdbc:derby://localhost:1527/Calendar";
    private static final String USER = "app";
    private static final String PASSWD = "app";

    
    public RecordManager(User currentUser) {
        this.currentUser = currentUser;
        makeConnection();
    }

    private  void makeConnection() {
        try {
            connection =
                    DriverManager.getConnection(URL, USER, PASSWD );
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public void setUser(User user) {
        this.currentUser = user;
    }

    public void insertRecord(Record record) {
        try {                 
            PreparedStatement insert =
                    connection.prepareStatement("INSERT INTO APP.RECORDS VALUES(?,?,?,?,?,?,?,?)");
            insert.setInt(1, record.getId());
            insert.setString(2, record.getName());
            insert.setString(3, record.getText());
            insert.setInt(4, record.getRecordType().ordinal());
            java.sql.Timestamp time = new Timestamp(record.getStartTime().getTime());
            insert.setTimestamp(5, time);
            time = new Timestamp(record.getEndTime().getTime());
            insert.setTimestamp(6, time);
            time = new Timestamp(record.getNotifyTime().getTime());
            insert.setTimestamp(7, time);
            insert.setInt(8, currentUser.getId());
            insert.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } 
    }

    public void insertUser() {        
        try {     
            PreparedStatement insertUser =
                    connection.prepareStatement("INSERT INTO APP.USERS VALUES(?,?)");
            insertUser.setInt(1, currentUser.getId());
            insertUser.setString(2, currentUser.getName());
            insertUser.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } 
    }

    public void deleteRecords() {
        try {          
            PreparedStatement delete =
                    connection.prepareStatement("DELETE FROM APP.RECORDS");
            delete.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } 
    }

    public void deleteUsers() {        
        try {
            PreparedStatement delete =
                    connection.prepareStatement("DELETE FROM APP.USERS");
            delete.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public Set<User> getUsers() {        
        Set<User> users = new HashSet<User>();
        try {           
            PreparedStatement select =
                    connection.prepareStatement("SELECT * FROM APP.USERS");
            select.execute();
            ResultSet rs = select.getResultSet();
            while (rs.next()) {
                users.add(new User(rs.getString(2), rs.getInt(1)));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            return users;
        }


    }

    public Set<Record> getRecords() {
        Set<Record> records = new HashSet<Record>();
        try {
            PreparedStatement select =
                    connection.prepareStatement("SELECT * FROM APP.RECORDS");
            select.execute();
            ResultSet rs = select.getResultSet();
            while (rs.next()) {
                records.add(new Record(rs.getInt(1), rs.getString(2),
                        rs.getString(3), RecordType.values()[rs.getInt(4)],
                        rs.getDate(5), rs.getDate(6), rs.getDate(7)));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            return records;
        }
    }

    public Set<Record> getRecordsFromUser() {  
        Set<Record> records = new HashSet<Record>();
        try {        
            PreparedStatement select =
                    connection.prepareStatement("SELECT * FROM APP.RECORDS WHERE USER_ID = ?");
            select.setInt(1, currentUser.getId());
            select.execute();
            ResultSet rs = select.getResultSet();
            while (rs.next()) {
                records.add(new Record(rs.getInt(1), rs.getString(2),
                        rs.getString(3), RecordType.values()[rs.getInt(4)],
                        rs.getDate(5), rs.getDate(6), rs.getDate(7)));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            return records;
        }

    }
}
