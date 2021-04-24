package com.example.oop_project.Main;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "myTable")
public class EntityClass {
    @PrimaryKey(autoGenerate = true)
    int id;
    @ColumnInfo(name = "eventname")
    String eventname;
    @ColumnInfo(name = "eventdate")
    String eventdate;
    @ColumnInfo(name = "eventtime")

    String eventtime;


    public String getEventname() {
        return eventname;
    }

    public void setEventname(String eventname) {
        this.eventname = eventname;
    }

    public String getEventdate() {
        return eventdate;
    }

    public void setEventdate(String eventdate) {
        this.eventdate = eventdate;
    }

    public String getEventtime() {
        return eventtime;
    }

    public void setEventtime(String eventtime) {
        this.eventtime = eventtime;
    }
}