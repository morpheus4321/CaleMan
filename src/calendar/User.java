/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package calendar;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author xjankov2
 */
public class User {

    private String name;
    private int id;
    private Set<Record> records = new HashSet<Record>();


    public User(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public boolean addRecord(Record record) {
        return records.add(record);
    }
    public boolean removeRecord(Record record) {
        return records.remove(record);
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<Record> getRecords() {
            return Collections.unmodifiableSet(records);
    }
}
