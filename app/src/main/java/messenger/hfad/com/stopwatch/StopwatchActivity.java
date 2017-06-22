package messenger.hfad.com.stopwatch;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

public class StopwatchActivity extends Activity {

    private int second = 0;
    private boolean running;
    private boolean wasRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stopwatch);
        if (savedInstanceState != null){
            second = savedInstanceState.getInt("second");
            running = savedInstanceState.getBoolean("running");
            wasRunning = savedInstanceState.getBoolean("wasRunning");
        }
        runTimer();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (wasRunning){
            running = true;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        wasRunning = running;
        running = false;
    }

//    @Override
//    protected void onStop() {
//        super.onStop();
//        wasRunning = running;
//        running = false;
//    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        if (wasRunning){
//            running = true;
//        }
//    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt("second", second);
        savedInstanceState.putBoolean("running", running);
        savedInstanceState.putBoolean("wasRunning", wasRunning);
    }


    public void onClickStart(View view){
        running = true;
    }

    public void onClickStop(View view){
        running = false;
    }

    public void onClickReset(View view){
        running = false;
        second = 0;
    }

    private void runTimer(){
        final TextView timeView = (TextView) findViewById(R.id.time_view);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = second/3600;
                int minutes = (second%3600)/60;
                int secs = second%60;
                String time = String.format("%d:%02d:%02d", hours, minutes, secs);
                timeView.setText(time);

                if (running){
                    second++;
                }
                handler.postDelayed(this, 1000);
            }
        });
    }
}
