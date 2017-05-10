package com.realmtestingdemo;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

class RealmHelper {
    private final Realm mRealm;
    private final PersonRealmChangeListener mRealmChangeListener;

    public RealmHelper(final PersonRealmChangeListener realmChangeListener) {
        mRealm = Realm.getDefaultInstance();
        this.mRealmChangeListener = realmChangeListener;
        cleanUp();
    }

    private void cleanUp() {
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(final Realm realm) {
                realm.clear(Person.class);
                mRealmChangeListener.onCleanUp();
            }
        });
    }

    public void create(final Person person) {
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(final Realm realm) {
                // Add a person
                final Person realmPerson = realm.createObject(Person.class);
                realmPerson.setId(System.currentTimeMillis());
                realmPerson.setName(person.getName());
                realmPerson.setAge(person.getAge());
                mRealmChangeListener.onPersonAdded(realmPerson);
            }
        });
    }

    public void close() {
        mRealm.close();
    }

    public List<Person> getAllData() {
        final List<Person> persons = new ArrayList<>();
        final RealmResults<Person> people = mRealm.where(Person.class).findAll();
        for (final Person person : people) {
            persons.add(person);
        }
        return persons;
    }

    public void delete(final Person person) {
        if (person == null) {
            return;
        }
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(final Realm realm) {
                person.removeFromRealm();
                mRealmChangeListener.onRemoveFirstPerson();
            }
        });
    }

    public Person getFirstPerson() {
        return mRealm.where(Person.class).findFirst();
    }

    public void update() {
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(final Realm realm) {
                final Person person = getFirstPerson();
                if (person != null) {
                    person.setName("Update");
                    realm.copyToRealmOrUpdate(person);
                    mRealmChangeListener.onUpdateFirstPerson();
                }
            }
        });
    }
}
