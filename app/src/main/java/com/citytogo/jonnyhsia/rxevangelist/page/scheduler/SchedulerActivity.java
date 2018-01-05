package com.citytogo.jonnyhsia.rxevangelist.page.scheduler;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.citytogo.jonnyhsia.rxevangelist.R;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.schedulers.Schedulers;

public class SchedulerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common);
    }

    public static void threadInfo(String caller) {
        Log.d("Scheduler", caller + " => " + Thread.currentThread().getName());
    }
}
