package com.example.vetservefirebase.Appointments;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.vetservefirebase.Model.Pet;
import com.example.vetservefirebase.Others.CircleTransform;
import com.example.vetservefirebase.R;
import com.google.api.LogDescriptor;

import java.util.Calendar;

import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

public class AppointmentsActivity extends AppCompatActivity {
    private static final int REQUEST_CODE = 1;
    @BindView(R.id.petpicture)
    ImageView petpicture;
    @BindView(R.id.petname)
    TextView petname;
    @BindView(R.id.appointmentDate)
    TextView appointmentDate;
    @BindView(R.id.appointmentServices)
    TextView appointmentServices;
    @BindView(R.id.appointmentTime)
    TextView appointmentTime;
    @BindView(R.id.appointmentNotes)
    EditText appointmentNotes;
//    @BindView(R.id.btnRequest)
//    Button btnRequest;

    DatePickerDialog datePickerDialog;
    int year;
    int month;
    int dayOfMonth;
    Calendar calendar;
    int currentHour;
    int currentMinute;
    String amPm;
    TimePickerDialog timePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointments);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.linearLayout2, R.id.appointmentDate, R.id.appointmentServices, R.id.appointmentTime}) void onClicks(View view){
        switch (view.getId()){
            case R.id.appointmentDate:
                calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                datePickerDialog = new DatePickerDialog(this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                appointmentDate.setText(day + "/" + (month + 1) + "/" + year);
                            }
                        }, year, month, dayOfMonth);
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                datePickerDialog.show();
                break;
            case R.id.linearLayout2:
                Intent intent = new Intent(this,
                        SelectPetActivity.class);
                startActivityForResult(intent , REQUEST_CODE);
                break;
            case R.id.appointmentTime:
                calendar = Calendar.getInstance();
                currentHour = calendar.get(Calendar.HOUR_OF_DAY);
                currentMinute = calendar.get(Calendar.MINUTE);

                timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                        if (hourOfDay >= 12) {
                            amPm = "PM";
                        } else {
                            amPm = "AM";
                        }
                        appointmentTime.setText(String.format("%02d:%02d", hourOfDay, minutes) + amPm);
                    }
                }, currentHour, currentMinute, false);
                timePickerDialog.show();
                break;
            default:
                    break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        try {
            super.onActivityResult(requestCode, resultCode, data);

            if (requestCode == REQUEST_CODE  && resultCode  == RESULT_OK) {
                Pet pet = data.getParcelableExtra("pet");
                Glide.with(getApplicationContext()).load(pet.getPhotoUrl())
                        .transition(withCrossFade())
                        .thumbnail(0.5f)
                        .transform(new CircleTransform())
                        .circleCrop()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(petpicture);
                petname.setText(pet.getPet_name());
            }
        } catch (Exception ex) {
            Toast.makeText(this, ex.toString(),
                    Toast.LENGTH_SHORT).show();
        }

    }
}
