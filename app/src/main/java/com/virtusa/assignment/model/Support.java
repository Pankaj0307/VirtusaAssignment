package com.virtusa.assignment.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Support implements Parcelable {

	@SerializedName("text")
	private String text;

	@SerializedName("url")
	private String url;

	protected Support(Parcel in) {
		text = in.readString();
		url = in.readString();
	}

	public static final Creator<Support> CREATOR = new Creator<Support>() {
		@Override
		public Support createFromParcel(Parcel in) {
			return new Support(in);
		}

		@Override
		public Support[] newArray(int size) {
			return new Support[size];
		}
	};

	public void setText(String text){
		this.text = text;
	}

	public String getText(){
		return text;
	}

	public void setUrl(String url){
		this.url = url;
	}

	public String getUrl(){
		return url;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(text);
		dest.writeString(url);
	}
}