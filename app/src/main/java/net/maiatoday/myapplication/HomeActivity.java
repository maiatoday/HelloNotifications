package net.maiatoday.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.sharedpreferences.Pref;

@EActivity(R.layout.activity_home)
public class HomeActivity extends AppCompatActivity {
    private static final String TAG = "HomeActivity";
    @Pref
    Prefs_ prefs;

    @Click
    void showListButton() {
        startActivity(new Intent(this, ListActivity_.class));
    }

    @Click
    void logTokenButton() {
        String token = FirebaseInstanceId.getInstance().getToken();
        // Log and toast
        String msg = getString(R.string.msg_token_fmt, token);
        Log.d(TAG, msg);
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Click
    void logoutButton() {
        prefs.loggedIn().put(false);
        finish();
    }

    @AfterViews
    void init() {
        Bundle startBundle = getIntent().getExtras();
        if (startBundle != null && startBundle.containsKey("sale_id")) {
           showListButton();
        }
    }

}
