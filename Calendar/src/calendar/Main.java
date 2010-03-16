/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package calendar;

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


        Record record1 = new Record(1, "pripomienka", "si uplny kokot, nezabudni :)",
                RecordType.BIRTHDAY, startTime,endTime,notifyTime);
                
        Record record2 = new Record(2, "pripomienka", "si uplny kokot, nezabudni :)",
                RecordType.BIRTHDAY, startTime,endTime,notifyTime);
        
        Record record3 = new Record(3, "pripomienka", "si uplny kokot, nezabudni :)",
                RecordType.BIRTHDAY, startTime,endTime,notifyTime);

        User user1 = new User("jaro",1);
        User user2 = new User("robo",2);
        User user3 = new User("Nemam nic", 3);

        RecordManager manager = new RecordManager(user1);

        manager.deleteUsers();
        manager.deleteRecords();

        manager.insertRecord(record1);
        manager.insertUser();
        manager.setUser(user2);
        manager.insertUser();
        manager.insertRecord(record2);
        manager.setUser(user1);
        manager.insertRecord(record3);
        manager.setUser(user2);
        /*Set<User> users = manager.getUsers();
        for (User iter : users) {
            System.out.println("name : " + iter.getName() + "   id : " + iter.getId());
        }
         * 
         */

        Set<Record> records = manager.getRecordsFromUser();
        for (Record r : records) {
            System.out.println(r.getId() + " " + r.getName());
        }
         
         

        manager.closeConnection();
        
    }


}
