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
public class Record {

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
}
