/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package caleman;

import java.util.Date;

/**
 *
 * @author xjankov2
 */
public class Record implements Comparable<Record> {

    private Integer id;
    private String name;
    private String text;
    private RecordType recordType;
    private Date startTime;
    private Date endTime;
    private Date notifyTime;

    public Record(Integer id, String name, String text, RecordType recordType,
                  Date startTime, Date endTime, Date notifyTime) {
        this.id = id;
        this.name = name;
        this.text = text;
        this.recordType = recordType;
        this.startTime = startTime;
        this.endTime = endTime;
        this.notifyTime = notifyTime;
    }

    public Record(String name, String text, RecordType recordType,
                  Date startTime, Date endTime, Date notifyTime) {
        this.name = name;
        this.text = text;
        this.recordType = recordType;
        this.startTime = startTime;
        this.endTime = endTime;
        this.notifyTime = notifyTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public RecordType getRecordType() {
        return recordType;
    }

    public void setRecordType(RecordType recordType) {
        this.recordType = recordType;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
    
    public Date getNotifyTime() {
        return notifyTime;
    }

    public void setNotifyTime(Date notifyTime) {
        this.notifyTime = notifyTime;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Record other = (Record) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        Integer inte = new Integer(3);
        inte.compareTo(4);

        return name + " " + recordType + " " + startTime + " " + endTime;
    }

    public int compareTo(Record o) {
        return this.id - o.getId();
    }

}
