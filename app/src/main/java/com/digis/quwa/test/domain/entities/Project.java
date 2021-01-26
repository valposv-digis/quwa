package com.digis.quwa.test.domain.entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Project implements Parcelable {

    @SerializedName("id") private final long id;
    @SerializedName("name") private final String name;
    @SerializedName("logo_url") private final String logoUrl;
    @SerializedName("position") private final int position;
    @SerializedName("is_active") private final boolean isActive;
    @SerializedName("is_owner_watcher") private final boolean isOwnerWatcher;
    @SerializedName("users") private final List<User> users;

    public Project(long id, String name, String logoUrl, int position, boolean isActive, boolean isOwnerWatcher, Date dtaUserSince, List<User> users) {
        this.id = id;
        this.name = name;
        this.logoUrl = logoUrl;
        this.position = position;
        this.isActive = isActive;
        this.isOwnerWatcher = isOwnerWatcher;
        this.users = users;
    }

    protected Project(Parcel in) {
        id = in.readLong();
        name = in.readString();
        logoUrl = in.readString();
        position = in.readInt();
        isActive = in.readByte() != 0;
        isOwnerWatcher = in.readByte() != 0;
        users = in.createTypedArrayList(User.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(name);
        dest.writeString(logoUrl);
        dest.writeInt(position);
        dest.writeByte((byte) (isActive ? 1 : 0));
        dest.writeByte((byte) (isOwnerWatcher ? 1 : 0));
        dest.writeTypedList(users);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Project> CREATOR = new Creator<Project>() {
        @Override
        public Project createFromParcel(Parcel in) {
            return new Project(in);
        }

        @Override
        public Project[] newArray(int size) {
            return new Project[size];
        }
    };

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public int getPosition() {
        return position;
    }

    public boolean isActive() {
        return isActive;
    }

    public boolean isOwnerWatcher() {
        return isOwnerWatcher;
    }

    public List<User> getUsers() {
        return users;
    }
}