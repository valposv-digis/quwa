package com.digis.quwa.test.domain.entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class User implements Parcelable {

    @SerializedName("id") private final long id;
    @SerializedName("name") private final String name;
    @SerializedName("avatar_url") private final String avatarUrl;
    @SerializedName("is_online") private final boolean isOnline;

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

    public User(long id, String name, String avatarUrl, boolean isOnline) {
        this.id = id;
        this.name = name;
        this.avatarUrl = avatarUrl;
        this.isOnline = isOnline;
    }

    protected User(Parcel in) {
        id = in.readLong();
        name = in.readString();
        avatarUrl = in.readString();
        isOnline = in.readByte() != 0;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public boolean isOnline() {
        return isOnline;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(name);
        dest.writeString(avatarUrl);
        dest.writeByte((byte) (isOnline ? 1 : 0));
    }
}
