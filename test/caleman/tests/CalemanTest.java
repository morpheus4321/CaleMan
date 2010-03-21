/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package caleman.tests;

import caleman.Record;
import caleman.RecordManager;
import caleman.RecordType;
import caleman.User;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;
import javax.sql.DataSource;
import junit.framework.TestCase;
import org.junit.Test;

/**
 *
 * @author xjankov2
 */
public class CalemanTest extends TestCase {

    RecordManager recordManager = new RecordManager();

    @Override
    public void setUp() throws SQLException {
        DataSource ds = new DataSource() {

            public Connection getConnection() throws SQLException {
                return DriverManager.getConnection("jdbc:derby://localhost:1527/TestCalendar", "app", "app");
            }

            public Connection getConnection(String username, String password) throws SQLException {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            public PrintWriter getLogWriter() throws SQLException {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            public void setLogWriter(PrintWriter out) throws SQLException {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            public void setLoginTimeout(int seconds) throws SQLException {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            public int getLoginTimeout() throws SQLException {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            public <T> T unwrap(Class<T> iface) throws SQLException {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            public boolean isWrapperFor(Class<?> iface) throws SQLException {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        };
        recordManager.setDataSource(ds);
        recordManager.deleteRecords();
        recordManager.deleteUsers();
    }

    @Test
    public void testGetAllRecords() throws SQLException {
        SortedSet<Record> records = new TreeSet<Record>();
        records = recordManager.getAllRecords();
        assertTrue(records.isEmpty());
    }

    @Test
    public void testGetAllUsers() throws SQLException {
        SortedSet<User> users = new TreeSet<User>();
        users = recordManager.getAllUsers();
        assertTrue(users.isEmpty());
    }

    @Test
    public void testAddUser() throws SQLException {
        User newUser1 = new User();
        newUser1.setName("First User");
        assertNull(newUser1.getId());
        recordManager.insertUser(newUser1);
        assertNotNull(newUser1.getId());

        Iterator<User> iter = recordManager.getAllUsers().iterator();
        assertTrue(iter.hasNext());
        User returnedUser = iter.next();
        assertNotSame(newUser1, returnedUser);

        User newUser2 = new User();
        newUser2.setName("Second User");


        recordManager.insertUser(newUser2);
        assertFalse(newUser1.getId().equals(newUser2.getId()));

        assertEquals(2, recordManager.getAllUsers().size());
    }

    @Test
    public void testAddRecord() throws SQLException {
        User testUser = new User();
        testUser.setName("Test user");
        recordManager.insertUser(testUser);
        recordManager.setCurrentUser(testUser);

        Record newRecord1 = new Record();
        newRecord1.setName("New record 1");
        newRecord1.setText("New text 2");
        newRecord1.setRecordType(RecordType.MEETING);
        newRecord1.setStartTime(new Date());
        newRecord1.setEndTime(new Date());
        newRecord1.setNotifyTime(new Date());
        assertNull(newRecord1.getId());
        recordManager.insertRecord(newRecord1);
        assertNotNull(newRecord1.getId());

        Iterator<Record> iter = recordManager.getAllRecords().iterator();
        assertTrue(iter.hasNext());
        Record returnedRecord = iter.next();
        assertNotSame(newRecord1, returnedRecord);

        Record newRecord2 = new Record();
        newRecord2.setName("New record 2");
        newRecord2.setText("New text 2");
        newRecord2.setRecordType(RecordType.MEETING);
        newRecord2.setStartTime(new Date());
        newRecord2.setEndTime(new Date());
        newRecord2.setNotifyTime(new Date());

        recordManager.insertRecord(newRecord2);

        assertFalse(newRecord1.getId().equals(newRecord2.getId()));

        assertEquals(2, recordManager.getAllRecords().size());
    }

    @Test
    public void testDeleteUsersRecords() throws SQLException {
        User user1 = new User();
        user1.setName("First user");
        recordManager.insertUser(user1);
        recordManager.setCurrentUser(user1);

        Record newRecord1 = new Record();
        newRecord1.setName("New record 1");
        newRecord1.setText("New text 2");
        newRecord1.setRecordType(RecordType.MEETING);
        newRecord1.setStartTime(new Date());
        newRecord1.setEndTime(new Date());
        newRecord1.setNotifyTime(new Date());
        recordManager.insertRecord(newRecord1);

        User user2 = new User();
        user2.setName("Second user");
        recordManager.insertUser(user2);

        recordManager.deleteUsersRecords(user2);
        assertEquals(1, recordManager.getAllRecords().size());
        recordManager.deleteUsersRecords(user1);
        assertEquals(0, recordManager.getAllRecords().size());

    }

    @Test
    public void testAddUserWithExistingId() throws SQLException {
        try {
            User firstUser = new User();
            firstUser.setName("First User");
            recordManager.insertUser(firstUser);
            User userWithSameId = new User();
            userWithSameId.setName("User With Same Id");
            userWithSameId.setId(firstUser.getId());
            recordManager.insertUser(userWithSameId);
            fail();
        } catch (IllegalArgumentException ex) {
        }
    }

    @Test
    public void testAddUserWithNullName() throws SQLException {
        try {
            User user = new User();
            recordManager.insertUser(user);
            fail();
        } catch (NullPointerException ex) {
        }
    }

    @Test
    public void testAddRecordWithNullName() throws SQLException {
        try {
            User user = new User();
            user.setName("Test name");
            recordManager.insertUser(user);
            recordManager.setCurrentUser(user);
            Record record = new Record();
            record.setText("New text");
            record.setRecordType(RecordType.MEETING);
            record.setStartTime(new Date());
            record.setEndTime(new Date());
            record.setNotifyTime(new Date());
            recordManager.insertRecord(record);
            fail();
        } catch (NullPointerException ex) {
        }
    }

    @Test
    public void testAddRecordWithNullText() throws SQLException {
        try {
            User user = new User();
            user.setName("Test name");
            recordManager.insertUser(user);
            recordManager.setCurrentUser(user);
            Record record = new Record();
            record.setName("New record");
            record.setRecordType(RecordType.MEETING);
            record.setStartTime(new Date());
            record.setEndTime(new Date());
            record.setNotifyTime(new Date());
            recordManager.insertRecord(record);
            fail();
        } catch (NullPointerException ex) {
        }
    }

    @Test
    public void testAddRecordWithNullRecordType() throws SQLException {
        try {
            User user = new User();
            user.setName("Test name");
            recordManager.insertUser(user);
            recordManager.setCurrentUser(user);
            Record record = new Record();
            record.setName("New record");
            record.setText("New text");
            record.setStartTime(new Date());
            record.setEndTime(new Date());
            record.setNotifyTime(new Date());
            recordManager.insertRecord(record);
            fail();
        } catch (NullPointerException ex) {
        }
    }

    @Test
    public void testAddRecordWithNullStartTime() throws SQLException {
        try {
            User user = new User();
            user.setName("Test name");
            recordManager.insertUser(user);
            recordManager.setCurrentUser(user);
            Record record = new Record();
            record.setName("New record");
            record.setText("New text");
            record.setRecordType(RecordType.MEETING);
            record.setEndTime(new Date());
            record.setNotifyTime(new Date());
            recordManager.insertRecord(record);
            fail();
        } catch (NullPointerException ex) {
        }
    }

    @Test
    public void testAddRecordWithNullEndTime() throws SQLException {
        try {
            User user = new User();
            user.setName("Test name");
            recordManager.insertUser(user);
            recordManager.setCurrentUser(user);
            Record record = new Record();
            record.setName("New record");
            record.setText("New text");
            record.setRecordType(RecordType.MEETING);
            record.setStartTime(new Date());
            record.setNotifyTime(new Date());
            recordManager.insertRecord(record);
            fail();
        } catch (NullPointerException ex) {
        }
    }

    @Test
    public void testAddRecordWithNullNotifyTime() throws SQLException {
        try {
            User user = new User();
            user.setName("Test name");
            recordManager.insertUser(user);
            recordManager.setCurrentUser(user);
            Record record = new Record();
            record.setName("New record");
            record.setText("New text");
            record.setRecordType(RecordType.MEETING);
            record.setStartTime(new Date());
            record.setEndTime(new Date());
            recordManager.insertRecord(record);
            fail();
        } catch (NullPointerException ex) {
        }
    }
}
