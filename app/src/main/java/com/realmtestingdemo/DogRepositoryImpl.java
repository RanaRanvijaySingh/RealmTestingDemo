package com.realmtestingdemo;

/**
 * Created by on 08/05/17.
 * Purpose of this class is to
 */

import io.realm.Realm;


public class DogRepositoryImpl implements DogRepository {
    @Override
    public void createDog(final String name) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Dog dog = realm.createObject(Dog.class);
                dog.setName(name);
            }
        });
        realm.close();
    }
}