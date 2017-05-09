package com.realmtestingdemo;

/**
 * Created by on 09/05/17.
 * Purpose of this class is to
 */

interface PersonRealmChangeListener {
    void onPersonAdded(Person person);

    void onCleanUp();

    void onRemoveFirstPerson();
}
