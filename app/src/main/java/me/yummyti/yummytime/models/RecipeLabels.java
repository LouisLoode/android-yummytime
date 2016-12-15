package me.yummyti.yummytime.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by louisloode on 14/12/2016.
 */

public class RecipeLabels implements Parcelable {

    private String icon;
    private String medium;
    private String large;

    public RecipeLabels() {

    }

    protected RecipeLabels(Parcel in) {
        icon = in.readString();
        medium = in.readString();
        large = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(icon);
        dest.writeString(medium);
        dest.writeString(large);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<RecipeLabels> CREATOR = new Creator<RecipeLabels>() {
        @Override
        public RecipeLabels createFromParcel(Parcel in) {
            return new RecipeLabels(in);
        }

        @Override
        public RecipeLabels[] newArray(int size) {
            return new RecipeLabels[size];
        }
    };

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public String getLarge() {
        return large;
    }

    public void setLarge(String large) {
        this.large = large;
    }
}
