package com.nuron.flickz;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import com.nuron.flickz.Data;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class Homepage extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        /**
         * Set up Android CardView/RecycleView
         */
        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        final CardAdapter mCardAdapter = new CardAdapter();
        mRecyclerView.setAdapter(mCardAdapter);

        /**
         * START: button set up
         */
        Button bClear = (Button) findViewById(R.id.button_clear);
        Button bFetch = (Button) findViewById(R.id.button_fetch);
        bClear.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mCardAdapter.clear();
            }
        });

        bFetch.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                GithubService service = ServiceFactory.createRetrofitService(GithubService.class, GithubService.SERVICE_ENDPOINT);
                for(String login : Data.githubList) {
                    service.getUser(login)
                            .subscribeOn(Schedulers.newThread())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Subscriber<Github>() {
                                @Override
                                public final void onCompleted() {
                                    // do nothing
                                }

                                @Override
                                public final void onError(Throwable e) {
                                    Log.e("GithubDemo", e.getMessage());
                                }

                                @Override
                                public final void onNext(Github response) {
                                    mCardAdapter.addData(response);
                                }
                            });
                }
            }
        });
        /**
         * END: button set up
         */
    }

}
