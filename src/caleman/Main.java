/*
* To change this template, choose Tools | Templates
* and open the template in the editor.
*/
package caleman;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Set;
import javax.sql.DataSource;

/**
*
* @author xjankov2
*/
public class Main {

    /**
* @param args the command line arguments
*/
    public static void main(String[] args) {


        Date startTime = new GregorianCalendar(2010, 3, 14, 16, 00).getTime();
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
        User user3 = new User("Du hast nich");
        
        RecordManager manager = new RecordManager(user1);

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
        /*Set<User> users = manager.getUsers();
for (User iter : users) {
System.out.println("name : " + iter.getName() + " id : " + iter.getId());
}
*
*/

        Set<Record> records = manager.getRecords();
        for (Record r : records) {
            System.out.println(r.getId() + " " + r.getName());
        }

    }


}