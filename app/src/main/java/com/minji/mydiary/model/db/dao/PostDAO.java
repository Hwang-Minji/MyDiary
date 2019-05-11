package com.minji.mydiary.model.db.dao;

import android.os.Parcel;

import org.parceler.ParcelConstructor;

import io.realm.PostDAORealmProxy;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

@org.parceler.Parcel(implementations = {PostDAORealmProxy.class},
                    value = org.parceler.Parcel.Serialization.FIELD,
                    analyze = {PostDAO.class})
public class PostDAO extends RealmObject {
    private String text;
    private String imagePath;
    @PrimaryKey
    private String date;

    public PostDAO() {}

    @ParcelConstructor
    public PostDAO(String date, String text, String imagePath) {
        this.date = date;
        this.imagePath = imagePath;
        this.text = text;
    }

    protected PostDAO(Parcel in) {
        date = in.readString();
        imagePath = in.readString();
        text = in.readString();

    }

    public void setDate(String date) { this.date = date; }
    public void setText(String text) { this.text = text; }
    public void setImagePath(String imagePath) { this.imagePath = imagePath; }

    public String getDate() { return date; }
    public String getText() { return text; }
    public String getImagePath() { return imagePath; }

    public static class Builder {
        private String date;
        private String text;
        private String imagePath;

        public Builder setDate(String date) {
            this.date = date;
            return this;
        }

        public Builder setText(String text) {
            this.text = text;
            return this;
        }

        public Builder setImagePath(String imagePath) {
            this.imagePath = imagePath;
            return this;
        }

        public PostDAO build() {
            return new PostDAO(date, text, imagePath);
        }

    }
}
