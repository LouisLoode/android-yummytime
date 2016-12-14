package me.yummyti.yummytime.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * Created by louisloode on 14/12/2016.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Recipe implements Parcelable {

    private int id;
    private int persons_amount;
    private int count_time;
    private String name;
    private String description;
    private String image;
    private String type_menu;


    public Recipe() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPersons_amount() {
        return persons_amount;
    }

    public void setPersons_amount(int persons_amount) {
        this.persons_amount = persons_amount;
    }

    public int getCount_time() {
        return count_time;
    }

    public void setCount_time(int count_time) {
        this.count_time = count_time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getType_menu() {
        return type_menu;
    }

    public void setType_menu(String type_menu) {
        this.type_menu = type_menu;
    }

    protected Recipe(Parcel in) {
        id = in.readInt();
        persons_amount = in.readInt();
        count_time = in.readInt();
        name = in.readString();
        description = in.readString();
        image = in.readString();
        type_menu = in.readString();

    }

    public static final Creator<Recipe> CREATOR = new Creator<Recipe>() {
        @Override
        public Recipe createFromParcel(Parcel in) {
            return new Recipe(in);
        }

        @Override
        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeInt(persons_amount);
        parcel.writeInt(count_time);

        parcel.writeString(name);
        parcel.writeString(description);
        parcel.writeString(image);
        parcel.writeString(type_menu);

    }

}
