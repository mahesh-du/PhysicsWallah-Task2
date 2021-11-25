package com.example.task2;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

public class Label implements Parcelable {

    private String name;
    private boolean isSelected = false;

    public Label(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }


    public boolean isSelected() {
        return isSelected;
    }


    // Parcelling part
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Label createFromParcel(Parcel in) {
            return new Label(in);
        }

        public Label[] newArray(int size) {
            return new Label[size];
        }
    };

    public Label(Parcel in){
        this.name = in.readString();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q)
            this.isSelected =  in.readBoolean();
        else
            this.isSelected =  in.readByte() != 0;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.name);
        parcel.writeByte((byte) (this.isSelected ? 1 : 0));
    }

}
