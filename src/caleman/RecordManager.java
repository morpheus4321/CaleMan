/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package caleman;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import javax.sql.DataSource;

/**
 *
 * @author xjankov2
 */
public class RecordManager {

    private User currentUser;
    private DataSource dataSource;

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = getUser(currentUser.getName());
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void insertRecord(Record record) {
        Connection connection;
        try {
            connection = dataSource.getConnection();
            PreparedStatement insert =
                    connection.prepareStatement("INSERT INTO APP.RECORDS (name, text, type, start_time, end_time, notify_time, user_id) VALUES(?,?,?,?,?,?,?)");
            insert.setString(1, record.getName());
            insert.setString(2, record.getText());
            insert.setInt(3, record.getRecordType().ordinal());
            java.sql.Timestamp time = new Timestamp(record.getStartTime().getTime());
            insert.setTimestamp(4, time);
            time = new Timestamp(record.getEndTime().getTime());
            insert.setTimestamp(5, time);
            time = new Timestamp(record.getNotifyTime().getTime());
            insert.setTimestamp(6, time);
            insert.setInt(7, currentUser.getId());
            insert.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public User insertUser(User newUser) {
        Connection connection;
        try {
            connection = dataSource.getConnection();
            PreparedStatement insertUser =
                    connection.prepareStatement("INSERT INTO APP.USERS (name) VALUES(?)");
            insertUser.setString(1, newUser.getName());
            insertUser.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return getUser(newUser.getName());
    }

    public User getUser(String name) {
        User user = new User();
        Connection connection;
        try {
            connection = dataSource.getConnection();

            PreparedStatement select =
                    connection.prepareStatement("SELECT * FROM APP.USERS WHERE name=?");
            select.setString(1, name);
            select.execute();
            ResultSet rs = select.getResultSet();

            while (rs.next()) {
                user.setId(rs.getInt(1));
                user.setName(rs.getString(2));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            return user;
        }
    }

    public void deleteRecords() {
        Connection connection;
        try {
            connection = dataSource.getConnection();
            PreparedStatement delete =
                    connection.prepareStatement("DELETE FROM APP.RECORDS");
            delete.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void deleteUsers() {
        Connection connection;
        try {
            connection = dataSource.getConnection();
            PreparedStatement delete =
                    connection.prepareStatement("DELETE FROM APP.USERS");
            delete.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public SortedSet<User> getUsers() {
        SortedSet<User> users = new TreeSet<User>();
        Connection connection;
        try {
            connection = dataSource.getConnection();
            PreparedStatement select =
                    connection.prepareStatement("SELECT * FROM APP.USERS");
            select.execute();
            ResultSet rs = select.getResultSet();
            while (rs.next()) {
                users.add(new User(rs.getInt(1), rs.getString(2)));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            return users;
        }


    }

    public SortedSet<Record> getRecords() {
        SortedSet<Record> records = new TreeSet<Record>();
        Connection connection;
        try {
            connection = dataSource.getConnection();
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

    public SortedSet<Record> getRecordsFromUser() {
        SortedSet<Record> records = new TreeSet<Record>();
        Connection connection;
        try {
            connection = dataSource.getConnection();
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
