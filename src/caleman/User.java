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
 * @author xjankov2, xbalent
 */
public class User implements Comparable<User> {

    private Integer id;
    private String name;
    private Set<Record> records = new HashSet<Record>();


    /*public User(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
     *
     */

    /*public User(String name) {
        this.name = name;
    }
     *
     */

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

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

    public int compareTo(User o) {
        return this.id - o.getId();
    }
}
