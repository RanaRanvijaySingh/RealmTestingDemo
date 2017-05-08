package com.realmtestingdemo;

/**
 * Created by on 08/05/17.
 * Purpose of this class is to
 */

import io.realm.RealmObject;

public class Dog extends RealmObject {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}