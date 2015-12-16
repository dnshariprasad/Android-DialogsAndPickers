package hari.dialogsandpickers;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String DATE_FORMAT = "dd/MM/yy";
    public static final String TIME_FORMAT = "hh:mm a";
    public static final String DATE_TIME_FORMAT = "dd/MM/yy hh:mm a";

    private Calendar mCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //set listener to buttions
        (findViewById(R.id.open_dialog)).setOnClickListener(this);
        (findViewById(R.id.time__picker_dialog)).setOnClickListener(this);
        (findViewById(R.id.date__picker_dialog)).setOnClickListener(this);

        //get calender instance
        mCalendar = Calendar.getInstance();
        //setCurrent timestamp to calendar
        mCalendar.setTimeInMillis(System.currentTimeMillis());

        ((TextView) findViewById(R.id.current_time)).setText(getFormattedDate(DATE_TIME_FORMAT, mCalendar));
    }

    public static String getFormattedDate(String format, Calendar calendar) {
        //Create date formeter
        SimpleDateFormat serverFormat = new SimpleDateFormat(format);
        //pass current current time to and get the time in based on format
        return serverFormat.format(calendar.getTime());
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.open_dialog:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("I am dialog..");
                builder.setMessage("Select out of three..");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "I am ok", Toast.LENGTH_LONG).show();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "I am not ok", Toast.LENGTH_LONG).show();

                    }
                });
                builder.setNeutralButton("Neutral", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "I am neutral", Toast.LENGTH_LONG).show();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                break;
            case R.id.time__picker_dialog:
                TimePickerDialog timePicker = new TimePickerDialog(this, timeSetListener, mCalendar.get(Calendar.HOUR_OF_DAY), mCalendar.get(Calendar.MINUTE), false);
                timePicker.show();
                break;
            case R.id.date__picker_dialog:
                DatePickerDialog datePickerDialog = new DatePickerDialog(this, dateSetListener, mCalendar.get(Calendar.YEAR), mCalendar.get(Calendar.MONTH), mCalendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
                break;
        }
    }

    TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            mCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
            mCalendar.set(Calendar.MINUTE, minute);
            //set date and time based on selected time and date
            ((Button) findViewById(R.id.time__picker_dialog)).setText(getFormattedDate(TIME_FORMAT, mCalendar));
        }
    };

    DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            mCalendar.set(Calendar.YEAR, year);
            mCalendar.set(Calendar.MONTH, monthOfYear);
            mCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            ((Button) findViewById(R.id.date__picker_dialog)).setText(getFormattedDate(DATE_FORMAT, mCalendar));

        }
    };
}
