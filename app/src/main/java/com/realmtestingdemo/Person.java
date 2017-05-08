package com.realmtestingdemo;

/**
 * Created by on 08/05/17.
 * Purpose of this class is to
 */

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.Ignore;

// Your model just have to extend RealmObject.
// This will inherit an annotation which produces proxy getters and setters for all fields.
public class Person extends RealmObject {

    // All fields are by default persisted.
    private String name;
    private int age;

    // Other objects in a one-to-one relation must also subclass RealmObject
    private Dog dog;

    // You can instruct Realm to ignore a field and not persist it.
    @Ignore
    private int tempReference;

    private long id;

    // The standard getters and setters your IDE generates are fine.
    // Realm will overload them and code inside them is ignored.
    // So if you prefer you can also just have empty abstract methods.

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Dog getDog() {
        return dog;
    }

    public void setDog(Dog dog) {
        this.dog = dog;
    }

    public int getTempReference() {
        return tempReference;
    }

    public void setTempReference(int tempReference) {
        this.tempReference = tempReference;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}