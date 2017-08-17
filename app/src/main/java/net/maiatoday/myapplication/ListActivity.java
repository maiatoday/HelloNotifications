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

    @ViewById(R.id.toolbar)
    Toolbar toolbar;

    private LinearLayoutManager layoutManager;
    private MyAdapter adapter;

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

        setSupportActionBar(toolbar);
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
        String[] data = getResources().getStringArray(R.array.snack_list);
        adapter = new MyAdapter(data);
        recyclerView.setAdapter(adapter);

    }

}
