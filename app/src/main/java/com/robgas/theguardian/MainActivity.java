package com.robgas.theguardian;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.robgas.theguardian.Fragments.FeedFragment;
import com.robgas.theguardian.Models.FeedItem;
import com.robgas.theguardian.Services.TerminateService;

public class MainActivity extends AppCompatActivity {

    public static String FeedItemId = "FeedItemId";
    private FeedItem feedFromNotification = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (getIntent().getParcelableExtra(FeedItemId) != null) {
            feedFromNotification = getIntent().getParcelableExtra(FeedItemId);
        }
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, (feedFromNotification == null) ? FeedFragment.newInstance() : FeedFragment.newInstance(feedFromNotification))
                .commitAllowingStateLoss();
    }

    public void startSoloService() {
        Intent service = new Intent(this, TerminateService.class);
        startService(service);
    }
}
