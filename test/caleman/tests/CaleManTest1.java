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
import java.util.GregorianCalendar;
import java.util.Set;
import javax.sql.DataSource;
import junit.framework.TestCase;
import org.junit.Test;

/**
 *
 * @author xbalent
 */
public class CaleManTest1 extends TestCase {

    @Test
    public void testMain() throws SQLException {

        /*Date startTime = new GregorianCalendar(2010, 3, 14, 16, 00).getTime();
        Date endTime = new GregorianCalendar(2010, 3, 14, 16, 20).getTime();
        Date notifyTime = new GregorianCalendar(2010, 3, 14, 15, 59).getTime();


        Record record1 = new Record("pripomienka1", "si uplny kokot, nezabudni :)",
                RecordType.BIRTHDAY, startTime,endTime,notifyTime);

        Record record2 = new Record("pripomienka2", "si uplny kokot, nezabudni :)",
                RecordType.BIRTHDAY, startTime,endTime,notifyTime);

        Record record3 = new Record("pripomienka3", "si uplny kokot, nezabudni :)",
                RecordType.BIRTHDAY, startTime,endTime,notifyTime);

        User user1 = new User("jaro");
        User user2 = new User("robo");
        User user3 = new User("Du hast nichts");

        RecordManager manager = new RecordManager();

        DataSource dataSource = new DataSource() {

            public Connection getConnection() throws SQLException {
                return DriverManager.getConnection("jdbc:derby://localhost:1527/Calendar", "app", "app");
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

        manager.setDataSource(dataSource);

        manager.deleteRecords();
        manager.deleteUsers();
        manager.insertUser(user1);
        manager.insertUser(user2);
        manager.setCurrentUser(user1);
        manager.insertRecord(record1);

        manager.setCurrentUser(user2);

        manager.insertRecord(record2);
        manager.setCurrentUser(user1);
        manager.insertRecord(record3);
        manager.setCurrentUser(user2);

        Set<Record> records = manager.getAllRecords();
        int i = 1;
        for (Record r : records) {
            assertEquals(r.toString(), "pripomienka" + i + " BIRTHDAY 2010-04-14 2010-04-14");
            assertEquals(r.getText(), "si uplny kokot, nezabudni :)");
            System.out.println(r);
            i++;
        }

         *
         */
         }


}
