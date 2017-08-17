package net.maiatoday.myapplication;

import org.androidannotations.annotations.sharedpreferences.DefaultBoolean;
import org.androidannotations.annotations.sharedpreferences.SharedPref;

/**
 * Created by maia on 2017/08/17.
 */
@SharedPref(value=SharedPref.Scope.UNIQUE)
public interface Prefs {

    @DefaultBoolean(false)
    boolean loggedIn();

}
