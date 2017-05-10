package com.realmtestingdemo;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.core.classloader.annotations.SuppressStaticInitializationFor;
import org.powermock.modules.junit4.rule.PowerMockRule;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import io.realm.Realm;
import io.realm.internal.log.RealmLog;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 19)
@PowerMockIgnore({"org.mockito.*", "org.robolectric.*", "android.*"})
@SuppressStaticInitializationFor("io.realm.internal.Util")
@PrepareForTest({Realm.class, RealmLog.class})
public class RealmHelperTest {

    @Rule
    public PowerMockRule mRule = new PowerMockRule();
    Realm mRealm;

    @Before
    public void setUp() throws Exception {
        mockStatic(RealmLog.class);
        mockStatic(Realm.class);
        this.mRealm = PowerMockito.mock(Realm.class);
        when(Realm.getDefaultInstance()).thenReturn(mRealm);
    }

    @Test
    public void shouldBeAbleToGetDefaultInstance() {
        assertThat(Realm.getDefaultInstance(), is(mRealm));
    }

    @Test
    public void shouldBeAbleToMockRealmMethods() {
        when(mRealm.isAutoRefresh()).thenReturn(true);
        assertThat(mRealm.isAutoRefresh(), is(true));

        when(mRealm.isAutoRefresh()).thenReturn(false);
        assertThat(mRealm.isAutoRefresh(), is(false));
    }

    //Testing if the mock realm object is capable of
    @Test
    public void shouldBeAbleToCreateRealmObject() throws Exception {
        //Create an object
        final Person expectedPerson = new Person();
        //Stub createObject func to return above object
        when(mRealm.createObject(Person.class)).thenReturn(expectedPerson);
        //Make the createObject call
        final Person actualPerson = mRealm.createObject(Person.class);
        //assert on expected and actual object
        assertThat(actualPerson, is(expectedPerson));
    }

    /*@Test
    public void shouldVerifyThatObjectWasCreated() throws Exception {
        doCallRealMethod().when(mRealm).executeTransaction(any(Realm.Transaction.class));
        Person expectedperson =


    }*/
}