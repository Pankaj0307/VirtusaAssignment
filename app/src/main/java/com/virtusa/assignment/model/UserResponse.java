package com.virtusa.assignment.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class UserResponse implements Parcelable {

    @SerializedName("data")
    private Data data;

    @SerializedName("support")
    private Support support;


    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public Support getSupport() {
        return support;
    }

    public void setSupport(Support support) {
        this.support = support;
    }

    public static Creator<UserResponse> getCREATOR() {
        return CREATOR;
    }

    protected UserResponse(Parcel in) {
        data = in.readParcelable(Data.class.getClassLoader());
        support = in.readParcelable(Support.class.getClassLoader());
    }

    public static final Creator<UserResponse> CREATOR = new Creator<UserResponse>() {
        @Override
        public UserResponse createFromParcel(Parcel in) {
            return new UserResponse(in);
        }

        @Override
        public UserResponse[] newArray(int size) {
            return new UserResponse[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(data, flags);
        dest.writeParcelable(support, flags);
    }
}