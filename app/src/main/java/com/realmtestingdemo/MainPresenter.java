package com.realmtestingdemo;

import android.content.ContentValues;

import java.util.List;

public class MainPresenter implements PersonRealmChangeListener {
    private final MainView mMainView;
    private final RealmHelper mRealmHelper;
    private int mRealmId;
    private final PersonViewModel mPersonViewModel;

    public MainPresenter(final MainView mainView) {
        this.mMainView = mainView;
        mPersonViewModel = new PersonViewModel();
        mRealmHelper = new RealmHelper(this);
    }

    public void createNewEntry() {
        mRealmHelper.create(getNewPerson());
    }

    private Person getNewPerson() {
        final Person person = new Person();
        person.setId(mRealmId);
        person.setName("Person" + mRealmId);
        person.setAge(mRealmId * 10);
        mRealmId++;
        return person;
    }

    public void deleteLastEntry() {
        mRealmHelper.delete(mRealmHelper.getFirstPerson());
    }

    public void updateFirstEntry() {

    }

    public void closeRealm() {
        mRealmHelper.close();
    }

    @Override
    public void onPersonAdded(final Person person) {
        mMainView.showToast("New person added with name " + person.getName());
        presentData();
    }

    private void presentData() {
        mMainView.setData(mPersonViewModel.getAsString(mRealmHelper.getAllData()));
    }

    @Override
    public void onCleanUp() {
        mMainView.showToast("Cleared DB");
    }

    @Override
    public void onRemoveFirstPerson() {
        presentData();
    }
}
