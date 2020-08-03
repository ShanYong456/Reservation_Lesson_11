package sg.edu.rp.c346.reservation;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    EditText etName;
    EditText etTelephone;
    EditText etSize;
    CheckBox checkBox;
    EditText etDay;
    EditText etTime;
    Button btReserve;
    Button btReset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.editTextName);
        etTelephone = findViewById(R.id.editTextTelephone);
        etSize = findViewById(R.id.editTextSize);
        checkBox = findViewById(R.id.checkBox);
        etDay = findViewById(R.id.editTextDay);
        etTime = findViewById(R.id.editTextTime);
        btReserve = findViewById(R.id.buttonReserve);
        btReset = findViewById(R.id.buttonReset);


        etDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Create the Listener to set the date
                DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener(){
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        etDay.setText(dayOfMonth + "/" + (monthOfYear+1) + "/" + year);
                    }
                };
                Calendar current_date = Calendar.getInstance();
                int year = current_date.get(Calendar.YEAR);
                int month = current_date.get(Calendar.MONTH);
                int day = current_date.get(Calendar.DAY_OF_MONTH);

                // Create the Date Picker Dialog
                DatePickerDialog myDateDialog = new DatePickerDialog(MainActivity.this, myDateListener, year, month, day);
                myDateDialog.show();
            }
        });

        etTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Create the Listener to set the time
                TimePickerDialog.OnTimeSetListener myTimeListener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        etTime.setText(hourOfDay + ":" + minute);
                    }
                };
                Calendar current_date = Calendar.getInstance();
                int hour = current_date.get(Calendar.HOUR_OF_DAY);
                int minute = current_date.get(Calendar.MINUTE);

                //Create the Time Picker Dialog
                TimePickerDialog myTimeDialog = new TimePickerDialog(MainActivity.this, myTimeListener, hour, minute, false);
                myTimeDialog.show();
            }
        });

        btReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etName.setText("");
                etTelephone.setText("");
                etSize.setText("");
                checkBox.setChecked(false);
                etDay.setText("");
                etTime.setText("");
            }
        });

        btReserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*

                String isSmoke = "";
                if (checkBox.isChecked()) {
                    isSmoke = "smoking";
                }
                else {
                    isSmoke = "non-smoking";
                }

                Toast.makeText(MainActivity.this,
                        "Hi, " + etName.getText().toString() + ", you have booked a "
                                + etSize.getText().toString() + " person(s) "
                                + isSmoke + " table on "
                                + ". Your phone number is "
                                + etTelephone.getText().toString() + ".",
                        Toast.LENGTH_LONG).show();

                 */
                String isSmoke = "";
                if (checkBox.isChecked()) {
                    isSmoke = "smoking";
                }
                else {
                    isSmoke = "non-smoking";
                }
                // Inflate the input.xml layout file

                LayoutInflater inflater = (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View viewDialog = inflater.inflate(R.layout.display, null);

                final TextView tvReservation = viewDialog.findViewById(R.id.textViewReservation);
                final TextView tvName= viewDialog.findViewById(R.id.textViewName);
                final TextView tvSmoking= viewDialog.findViewById(R.id.textViewSmoking);
                final TextView tvSize= viewDialog.findViewById(R.id.textViewSize);
                final TextView tvDate= viewDialog.findViewById(R.id.textViewDate);
                final TextView tvTime= viewDialog.findViewById(R.id.textViewTime);


                tvReservation.setText("New Reservation");
                tvName.setText("Name: "+etName.getText().toString());
                tvSmoking.setText("Smoke: "+ isSmoke.toString());
                tvSize.setText("Size: "+etSize.getText().toString());
                tvDate.setText("Date: "+etDay.getText().toString());
                tvTime.setText("Time: "+etTime.getText().toString());

                //Create the Dialog Builder
                AlertDialog.Builder myBuilder = new AlertDialog.Builder(MainActivity.this);
                //Set the Dialog Details
                myBuilder.setView(viewDialog);
                myBuilder.setTitle("Confirm Your Order");
                myBuilder.setCancelable(false);
                myBuilder.setPositiveButton("CONFIRM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Toast.makeText(MainActivity.this,
                                "SMS have been sent out!",
                                Toast.LENGTH_LONG).show();

                    }
                });

                myBuilder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog myDialog = myBuilder.create();
                myDialog.show();

            }
        });
    }
}