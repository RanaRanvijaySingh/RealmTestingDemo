package com.realmtestingdemo;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity implements MainView {

    public static final String TAG = MainActivity.class.getName();

    @BindView(R.id.textViewData)
    TextView mTextViewData;

    //    private Realm realm;
    private MainPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mPresenter = new MainPresenter(this);

        // Open the default Realm for the UI thread.
//        realm = Realm.getDefaultInstance();

        // Clean up from previous run
//        cleanUp();

        // Small operation that is ok to run on the main thread
//        basicCRUD(realm);

        // More complex operations can be executed on another thread.
        AsyncTask<Void, Void, String> foo = new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... voids) {
                String info = "";
                info += complexQuery();
                return info;
            }

            @Override
            protected void onPostExecute(String result) {
            }
        };

//        foo.execute();
    }

    @OnClick(R.id.buttonCreate)
    public void onClickCreateButton(View view) {
        mPresenter.createNewEntry();
    }

    @OnClick(R.id.buttonDelete)
    public void onClickDeleteButton(View view) {
        mPresenter.deleteLastEntry();
    }

    @OnClick(R.id.buttonUpdate)
    public void onClickUpdateButton(View view) {
        mPresenter.updateFirstEntry();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.closeRealm();
    }

    private void basicCRUD(Realm realm) {

        // Find the first person (no query conditions) and read a field
        final Person person = realm.where(Person.class).findFirst();

        // Update person in a transaction
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                person.setName("John Senior");
                person.setAge(89);
            }
        });


        // Add two more people
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Person jane = realm.createObject(Person.class);
                jane.setName("Jane");
                jane.setAge(27);

                Person doug = realm.createObject(Person.class);
                doug.setName("Robert");
                doug.setAge(42);
            }
        });
    }

    private String complexQuery() {
        String status = "\n\nPerforming complex Query operation...";

        Realm realm = Realm.getDefaultInstance();
        status += "\nNumber of people in the DB: " + realm.where(Person.class).count();

        // Find all persons where age between 1 and 99 and name begins with "J".
        RealmResults<Person> results = realm.where(Person.class)
                .between("age", 1, 99)       // Notice implicit "and" operation
                .beginsWith("name", "J").findAll();
        status += "\nNumber of people aged between 1 and 99 who's name start with 'J': " + results.size();

        realm.close();
        return status;
    }

    @Override
    public void showToast(final String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setData(final String data) {
        mTextViewData.setText(data);
    }
}