/*
* To change this template, choose Tools | Templates
* and open the template in the editor.
*/
package caleman;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Set;

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
        User user3 = new User("Nemam nic");

        RecordManager manager = new RecordManager(user1);

        manager.deleteRecords();
        manager.deleteUsers();
        manager.insertUser(user1);
        manager.insertUser(user2);
        manager.setUser(user1);
        manager.insertRecord(record1);

        manager.setUser(user2);

        manager.insertRecord(record2);
        manager.setUser(user1);
        manager.insertRecord(record3);
        manager.setUser(user2);
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



        manager.closeConnection();

    }


}