/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package caleman;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author xjankov2
 */
public class User {

    private Integer id;
    private String name;
    private Set<Record> records = new HashSet<Record>();


    public User(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public User(String name) {
        this.name = name;
    }

    public User(){};

    public boolean addRecord(Record record) {
        return records.add(record);
    }
    public boolean removeRecord(Record record) {
        return records.remove(record);
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<Record> getRecords() {
            return Collections.unmodifiableSet(records);
    }
}
