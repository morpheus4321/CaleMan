/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package caleman;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
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

    public void setCurrentUser(User currentUser) throws SQLException {
        this.currentUser = currentUser;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void insertRecord(Record record) throws SQLException {
        if (record.getName() == null) {
            throw new NullPointerException("Record name is null");
        }

        if (record.getText() == null) {
            throw new NullPointerException("Record text is null");
        }

        if (record.getRecordType() == null) {
            throw new NullPointerException("Record type is null");
        }

        if (record.getStartTime() == null) {
            throw new NullPointerException("Record start time is null");
        }

        if (record.getEndTime() == null) {
            throw new NullPointerException("Record end time is null");
        }

        if (record.getNotifyTime() == null) {
            throw new NullPointerException("Record notify time is null");
        }

        if (record.getId() != null) {
            throw new IllegalArgumentException("Product has already assigned id");
        }
        
        Connection connection = null;
        PreparedStatement insertProduct = null;
        try {
            connection = dataSource.getConnection();
            insertProduct = connection.prepareStatement("INSERT INTO APP.RECORDS (name, text, type, start_time, "
                    + "end_time, notify_time, user_id) VALUES(?,?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
            insertProduct.setString(1, record.getName());
            insertProduct.setString(2, record.getText());
            insertProduct.setInt(3, record.getRecordType().ordinal());
            java.sql.Timestamp time = new Timestamp(record.getStartTime().getTime());
            insertProduct.setTimestamp(4, time);
            time = new Timestamp(record.getEndTime().getTime());
            insertProduct.setTimestamp(5, time);
            time = new Timestamp(record.getNotifyTime().getTime());
            insertProduct.setTimestamp(6, time);
            insertProduct.setInt(7, currentUser.getId());
            insertProduct.execute();

            ResultSet generatedKeys = insertProduct.getGeneratedKeys();
            if (generatedKeys.next()) {
                record.setId(generatedKeys.getInt(1));
            } else {
                throw new SQLException("Error when fetching generated key");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            connection.close();
            insertProduct.close();
        }
    }

    public User insertUser(User newUser) throws SQLException {
        if (newUser.getName() == null) {
            throw new NullPointerException("User name is null");
        }
        if (newUser.getId() != null) {
            throw new IllegalArgumentException("User has already assigned id");
        }
        Connection connection = null;
        PreparedStatement insertUser = null;
        try {
            connection = dataSource.getConnection();
            insertUser =
                    connection.prepareStatement("INSERT INTO APP.USERS (name) VALUES(?)",Statement.RETURN_GENERATED_KEYS);
            insertUser.setString(1, newUser.getName());
            insertUser.execute();

            ResultSet generatedKeys = insertUser.getGeneratedKeys();
            if (generatedKeys.next()) {
                newUser.setId(generatedKeys.getInt(1));
            } else {
                throw new SQLException("Error when fetching generated key");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            connection.close();
            insertUser.close();
        }
        return getUser(newUser.getName());
    }

    public User getUser(String name) throws SQLException {
        User user = new User();
        Connection connection = null;
        PreparedStatement select = null;
        try {
            connection = dataSource.getConnection();

            select = connection.prepareStatement("SELECT * FROM APP.USERS WHERE name=?");
            select.setString(1, name);
            boolean result = select.execute();
            if (result) {
                ResultSet rs = select.getResultSet();


                while (rs.next()) {
                    user.setId(rs.getInt(1));
                    user.setName(rs.getString(2));
                }
            } else {
                System.out.println("No such user with asked name.");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            connection.close();
            select.close();
            return user;
        }
    }

    public void deleteRecords() throws SQLException {
        Connection connection = null;
        PreparedStatement delete = null;
        try {
            connection = dataSource.getConnection();
            delete = connection.prepareStatement("DELETE FROM APP.RECORDS");
            delete.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            connection.close();
            delete.close();
        }
    }

    public void deleteUsers() throws SQLException {
        Connection connection = null;
        PreparedStatement delete = null;
        try {
            connection = dataSource.getConnection();
            delete = connection.prepareStatement("DELETE FROM APP.USERS");
            delete.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            connection.close();
            delete.close();
        }
    }

    public SortedSet<User> getAllUsers() throws SQLException {
        SortedSet<User> users = new TreeSet<User>();
        Connection connection = null;
        PreparedStatement select = null;
        try {
            connection = dataSource.getConnection();
            select = connection.prepareStatement("SELECT * FROM APP.USERS");
            boolean result = select.execute();
            if (result) {
                ResultSet rs = select.getResultSet();

                while (rs.next()) {
                    User newUser = new User();
                    newUser.setId(rs.getInt(1));
                    newUser.setName(rs.getString(2));
                    users.add(newUser);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            connection.close();
            select.close();
        }
        return users;


    }

    public SortedSet<Record> getAllRecords() throws SQLException {
        SortedSet<Record> records = new TreeSet<Record>();
        Connection connection = null;
        PreparedStatement select = null;
        try {
            connection = dataSource.getConnection();
            select = connection.prepareStatement("SELECT * FROM APP.RECORDS");
            boolean result = select.execute();
            if (result) {
                ResultSet rs = select.getResultSet();
                while (rs.next()) {
                    Record newRecord = new Record();
                    newRecord.setId(rs.getInt(1));
                    newRecord.setName(rs.getString(2));
                    newRecord.setText(rs.getString(3));
                    newRecord.setRecordType(RecordType.values()[rs.getInt(4)]);
                    newRecord.setStartTime(rs.getDate(5));
                    newRecord.setEndTime(rs.getDate(6));
                    newRecord.setNotifyTime(rs.getDate(7));
                    records.add(newRecord);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            connection.close();
            select.close();
            return records;
        }
    }

    public SortedSet<Record> getRecordsFromUser() throws SQLException {
        SortedSet<Record> records = new TreeSet<Record>();
        Connection connection = null;
        PreparedStatement select = null;
        try {
            connection = dataSource.getConnection();
            select = connection.prepareStatement("SELECT * FROM APP.RECORDS WHERE USER_ID = ?");
            select.setInt(1, currentUser.getId());
            select.execute();
            ResultSet rs = select.getResultSet();
            while (rs.next()) {
                Record newRecord = new Record();
                newRecord.setId(rs.getInt(1));
                newRecord.setName(rs.getString(2));
                newRecord.setText(rs.getString(3));
                newRecord.setRecordType(RecordType.values()[rs.getInt(4)]);
                newRecord.setStartTime(rs.getDate(5));
                newRecord.setEndTime(rs.getDate(6));
                newRecord.setNotifyTime(rs.getDate(7));
                records.add(newRecord);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            connection.close();
            select.close();
            return records;
        }

    }
}
