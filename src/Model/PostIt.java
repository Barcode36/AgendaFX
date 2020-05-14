/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Samuel
 */
public class PostIt {

    protected int id;
    protected String title;
    protected String description;
    protected Date scheduledDay;
    protected boolean warned;
    protected String type;
    protected String typeColor;
    protected User user;
    protected Sound sound;
    protected File music;

    public PostIt(User user) {
        this.user = user;
        
        if (music != null) {
            this.sound = new Sound(music);
        }
    }

    public PostIt() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getScheduledDay() {
        return scheduledDay;
    }

    public void setScheduledDay(Date scheduledDay) {
        this.scheduledDay = scheduledDay;
    }

    public boolean isWarned() {
        return warned;
    }

    public void setWarned(boolean warned) {
        this.warned = warned;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public java.sql.Date getSQLScheduledDay() {

        java.sql.Date sqlDate = new java.sql.Date(scheduledDay.getTime());

        return sqlDate;
    }

    public String getScheduledDate() {

        SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");

        return date.format(scheduledDay);
    }

    public String getScheduledHour() {

        SimpleDateFormat date = new SimpleDateFormat("HH:mm");

        return date.format(scheduledDay);
    }

    public String getTypeColor() {
        return typeColor;
    }

    public void setTypeColor(String typeColor) {
        this.typeColor = typeColor;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Sound getSound() {
        return sound;
    }

    public void setSound(Sound sound) {
        this.sound = sound;
    }

    public File getMusic() {
        return music;
    }

    public void setMusic(File music) {
        this.music = music;
    }
    
    

}
