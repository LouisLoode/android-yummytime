package me.yummyti.yummytime.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * Created by louisloode on 14/12/2016.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Week implements Parcelable {


    private int id;
    private int cookbook_id;
    private int user_id;

    private int monday_id;
    private int monday_main_id;
    private int monday_dessert_id;

    private int tuesday_id;
    private int tuesday_main_id;
    private int tuesday_dessert_id;

    private int wednesday_id;
    private int wednesday_main_id;
    private int wednesday_dessert_id;

    private int friday_id;
    private int friday_main_id;
    private int friday_dessert_id;

    private int thursday_id;
    private int thursday_main_id;
    private int thursday_dessert_id;

    private int saturday_id;
    private int saturday_main_id;
    private int saturday_dessert_id;

    private int sunday_id;
    private int sunday_main_id;
    private int sunday_dessert_id;


    public Week() {
    }

    protected Week(Parcel in) {
        id = in.readInt();
        cookbook_id = in.readInt();
        user_id = in.readInt();

        monday_id = in.readInt();
        monday_main_id = in.readInt();
        monday_dessert_id = in.readInt();

        tuesday_id = in.readInt();
        tuesday_main_id = in.readInt();
        tuesday_dessert_id = in.readInt();

        wednesday_id = in.readInt();
        wednesday_main_id = in.readInt();
        wednesday_dessert_id = in.readInt();

        friday_id = in.readInt();
        friday_main_id = in.readInt();
        friday_dessert_id = in.readInt();

        thursday_id = in.readInt();
        thursday_main_id = in.readInt();
        thursday_dessert_id = in.readInt();

        saturday_id = in.readInt();
        saturday_main_id = in.readInt();
        saturday_dessert_id = in.readInt();

        sunday_id = in.readInt();
        sunday_main_id = in.readInt();
        sunday_dessert_id = in.readInt();

    }

    public static final Creator<Week> CREATOR = new Creator<Week>() {
        @Override
        public Week createFromParcel(Parcel in) {
            return new Week(in);
        }

        @Override
        public Week[] newArray(int size) {
            return new Week[size];
        }
    };



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeInt(cookbook_id);
        parcel.writeInt(user_id);

        parcel.writeInt(monday_id);
        parcel.writeInt(monday_main_id);
        parcel.writeInt(monday_dessert_id);

        parcel.writeInt(tuesday_id);
        parcel.writeInt(tuesday_main_id);
        parcel.writeInt(tuesday_dessert_id);

        parcel.writeInt(wednesday_id);
        parcel.writeInt(wednesday_main_id);
        parcel.writeInt(wednesday_dessert_id);


        parcel.writeInt(friday_id);
        parcel.writeInt(friday_main_id);
        parcel.writeInt(friday_dessert_id);

        parcel.writeInt(thursday_id);
        parcel.writeInt(thursday_main_id);
        parcel.writeInt(thursday_dessert_id);

        parcel.writeInt(saturday_id);
        parcel.writeInt(saturday_main_id);
        parcel.writeInt(saturday_dessert_id);

        parcel.writeInt(sunday_id);
        parcel.writeInt(sunday_main_id);
        parcel.writeInt(sunday_dessert_id);

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCookbook_id() {
        return cookbook_id;
    }

    public void setCookbook_id(int cookbook_id) {
        this.cookbook_id = cookbook_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getMonday_id() {
        return monday_id;
    }

    public void setMonday_id(int monday_id) {
        this.monday_id = monday_id;
    }

    public int getMonday_main_id() {
        return monday_main_id;
    }

    public void setMonday_main_id(int monday_main_id) {
        this.monday_main_id = monday_main_id;
    }

    public int getMonday_dessert_id() {
        return monday_dessert_id;
    }

    public void setMonday_dessert_id(int monday_dessert_id) {
        this.monday_dessert_id = monday_dessert_id;
    }

    public int getTuesday_id() {
        return tuesday_id;
    }

    public void setTuesday_id(int tuesday_id) {
        this.tuesday_id = tuesday_id;
    }

    public int getTuesday_main_id() {
        return tuesday_main_id;
    }

    public void setTuesday_main_id(int tuesday_main_id) {
        this.tuesday_main_id = tuesday_main_id;
    }

    public int getTuesday_dessert_id() {
        return tuesday_dessert_id;
    }

    public void setTuesday_dessert_id(int tuesday_dessert_id) {
        this.tuesday_dessert_id = tuesday_dessert_id;
    }

    public int getWednesday_id() {
        return wednesday_id;
    }

    public void setWednesday_id(int wednesday_id) {
        this.wednesday_id = wednesday_id;
    }

    public int getWednesday_main_id() {
        return wednesday_main_id;
    }

    public void setWednesday_main_id(int wednesday_main_id) {
        this.wednesday_main_id = wednesday_main_id;
    }

    public int getWednesday_dessert_id() {
        return wednesday_dessert_id;
    }

    public void setWednesday_dessert_id(int wednesday_dessert_id) {
        this.wednesday_dessert_id = wednesday_dessert_id;
    }

    public int getFriday_id() {
        return friday_id;
    }

    public void setFriday_id(int friday_id) {
        this.friday_id = friday_id;
    }

    public int getFriday_main_id() {
        return friday_main_id;
    }

    public void setFriday_main_id(int friday_main_id) {
        this.friday_main_id = friday_main_id;
    }

    public int getFriday_dessert_id() {
        return friday_dessert_id;
    }

    public void setFriday_dessert_id(int friday_dessert_id) {
        this.friday_dessert_id = friday_dessert_id;
    }

    public int getThursday_id() {
        return thursday_id;
    }

    public void setThursday_id(int thursday_id) {
        this.thursday_id = thursday_id;
    }

    public int getThursday_main_id() {
        return thursday_main_id;
    }

    public void setThursday_main_id(int thursday_main_id) {
        this.thursday_main_id = thursday_main_id;
    }

    public int getThursday_dessert_id() {
        return thursday_dessert_id;
    }

    public void setThursday_dessert_id(int thursday_dessert_id) {
        this.thursday_dessert_id = thursday_dessert_id;
    }

    public int getSaturday_id() {
        return saturday_id;
    }

    public void setSaturday_id(int saturday_id) {
        this.saturday_id = saturday_id;
    }

    public int getSaturday_main_id() {
        return saturday_main_id;
    }

    public void setSaturday_main_id(int saturday_main_id) {
        this.saturday_main_id = saturday_main_id;
    }

    public int getSaturday_dessert_id() {
        return saturday_dessert_id;
    }

    public void setSaturday_dessert_id(int saturday_dessert_id) {
        this.saturday_dessert_id = saturday_dessert_id;
    }

    public int getSunday_id() {
        return sunday_id;
    }

    public void setSunday_id(int sunday_id) {
        this.sunday_id = sunday_id;
    }

    public int getSunday_main_id() {
        return sunday_main_id;
    }

    public void setSunday_main_id(int sunday_main_id) {
        this.sunday_main_id = sunday_main_id;
    }

    public int getSunday_dessert_id() {
        return sunday_dessert_id;
    }

    public void setSunday_dessert_id(int sunday_dessert_id) {
        this.sunday_dessert_id = sunday_dessert_id;
    }
}
