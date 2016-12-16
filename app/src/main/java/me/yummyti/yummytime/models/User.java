package me.yummyti.yummytime.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * Created by louisloode on 14/12/2016.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class User implements Parcelable {

    private int id;
    private int cookbook_count;
    private int recipes_count;

    private String name;
    private String image_file_name;
    private String mail;

    public User() {
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    protected User(Parcel in) {
        id = in.readInt();
        cookbook_count = in.readInt();
        recipes_count = in.readInt();
        name = in.readString();
        image_file_name = in.readString();
        mail = in.readString();
    }


    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeInt(cookbook_count);
        parcel.writeInt(recipes_count);
        parcel.writeString(name);
        parcel.writeString(image_file_name);
        parcel.writeString(mail);

    }


    @Override
    public int describeContents() {
        return 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCookbook_count() {
        return cookbook_count;
    }

    public void setCookbook_count(int cookbook_count) {
        this.cookbook_count = cookbook_count;
    }

    public int getRecipes_count() {
        return recipes_count;
    }

    public void setRecipes_count(int recipes_count) {
        this.recipes_count = recipes_count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getImage_file_name() {
        return image_file_name;
    }

    public void setImage_file_name(String image_file_name) {
        this.image_file_name = image_file_name;
    }
}
