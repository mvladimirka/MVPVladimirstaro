package hr.tvz.android.listavladimir;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AlertDialog.Builder;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class BatteryActivity extends AppCompatActivity {
    public static final String ACTION_SHARE = "hr.tvz.android.listavladimir.SHARE";
    private final BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            int level = intent.getIntExtra("level", 0);

            ProgressBar progBar = (ProgressBar) findViewById(R.id.pb);
            progBar.setProgress(level);

            TextView phone = (TextView) findViewById(R.id.phone);
            phone.setText("Battery Level: " + Integer.toString(level) + "%");

            if (level < 55) {
                Toast.makeText(BatteryActivity.this, "Low battery", Toast.LENGTH_SHORT).show();

                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(BatteryActivity.this);
                alertBuilder.setTitle("Low battery!");
                alertBuilder.setMessage("You have low battery,please connect with a power source!");

                alertBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }

                    ;
                });
                AlertDialog ad = alertBuilder.create();
                ad.show();
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battery);

        registerReceiver(receiver, new IntentFilter(
        Intent.ACTION_BATTERY_CHANGED));

        //String.valueOf(new Intent(ACTION_SHARE))));
    }


}