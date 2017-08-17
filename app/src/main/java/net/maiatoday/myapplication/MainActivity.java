package net.maiatoday.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.sharedpreferences.Pref;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @ViewById(R.id.textView)
    TextView textView;

    @ViewById(R.id.star1)
    View star1;
    @ViewById(R.id.star2)
    View star2;

    @Pref
    Prefs_ prefs;

    @Click
    void logTokenButton() {
        String token = FirebaseInstanceId.getInstance().getToken();

        // Log and toast
        String msg = getString(R.string.msg_token_fmt, token);
//                String msg = "Strength of the pack!";
        Log.d(TAG, msg);
        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    @Click
    void loginButton() {
        prefs.loggedIn().put(true);
        gotoHomeScreen();
    }

    @AfterViews
    void init() {
        if (getIntent().getExtras() != null) {
            Log.d(TAG, "init: we started from a push notification");
            for (String key : getIntent().getExtras().keySet()) {
                String value = getIntent().getExtras().getString(key);
                Log.d(TAG, "Key: " + key + " Value: " + value);
            }
        }
        textView.setText("Hello World " + BuildConfig.FLAVOR + " " + BuildConfig.BUILD_TYPE);
        if (BuildConfig.FLAVOR_quality == "cheap") {
            star1.setVisibility(View.GONE);
            star2.setVisibility(View.GONE);
        } else {
            star1.setVisibility(View.VISIBLE);
            star2.setVisibility(View.VISIBLE);
        }
        boolean loggedIn = prefs.loggedIn().get();
        if (loggedIn) {
            gotoHomeScreen();
        }
    }

    private void gotoHomeScreen() {
        Intent i = new Intent(this, HomeActivity_.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        Bundle launchIntentBundle = getIntent().getExtras();
        if (launchIntentBundle != null && launchIntentBundle.containsKey("sale_id")) {
            i.putExtra("sale_id", launchIntentBundle.getString("sale_id"));
        }
        startActivity(i);
        finish();
    }

}
