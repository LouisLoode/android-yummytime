package me.yummyti.yummytime.models;

/**
 * Created by louisloode on 14/12/2016.
 */

public class Users {
    private Integer id, week_id;
    private String name, image, mail;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getWeek_id() {
        return week_id;
    }

    public void setWeek_id(Integer week_id) {
        this.week_id = week_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

}
