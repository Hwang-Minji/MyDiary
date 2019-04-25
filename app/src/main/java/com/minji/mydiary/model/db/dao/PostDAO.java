package com.minji.mydiary.model.db.dao;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class PostDAO extends RealmObject {
    @PrimaryKey
    private int index;

    public void setIndex(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }
}
