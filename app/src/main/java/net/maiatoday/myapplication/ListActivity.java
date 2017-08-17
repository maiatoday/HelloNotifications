package net.maiatoday.myapplication;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.google.firebase.iid.FirebaseInstanceId;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_list)
public class ListActivity extends AppCompatActivity {
    private static final String TAG = "ListActivity";

    @ViewById(R.id.my_recycler_view)
    RecyclerView recyclerView;

    private LinearLayoutManager layoutManager;
    private MyAdapter adapter;
    private String[] data = {
            "FLopsy",
            "Mopsy",
            "CottonTail",
            "Peter"
    };

    @Click
    void fab(View clickedView) {

        String token = FirebaseInstanceId.getInstance().getToken();

        // Log and toast
        String msg = getString(R.string.msg_token_fmt, token);
        Log.d(TAG, msg);
        Snackbar.make(clickedView, msg, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    @AfterViews
    protected void init() {
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
        adapter = new MyAdapter(data);
        recyclerView.setAdapter(adapter);

    }

}
