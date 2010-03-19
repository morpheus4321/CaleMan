/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package caleman.tests;

import caleman.Record;
import caleman.RecordManager;
import caleman.User;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;
import javax.sql.DataSource;
import junit.framework.TestCase;

/**
 *
 * @author xjankov2
 */
public class CalemanTest extends TestCase{



    RecordManager recordManager = new RecordManager();

    @Override
    public void setUp() throws SQLException {
        DataSource ds = new DataSource() {

            public Connection getConnection() throws SQLException {
                return DriverManager.getConnection("jdbc:derby://localhost:1527/TestCalendar","app","app");
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
    public void testGetAllProducts() throws SQLException {
        SortedSet<Record> products = new TreeSet<Record>();
        products = recordManager.getAllRecords();
        assertTrue(products.isEmpty());
    }
    
    public void testGetAllUsers() throws SQLException {
        SortedSet<User> users = new TreeSet<User>();
        users = recordManager.getAllUsers();
        assertTrue(users.isEmpty());
    }

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
        SortedSet<User> users = recordManager.getAllUsers();
     
        assertEquals(2, recordManager.getAllUsers().size());
    }
}
