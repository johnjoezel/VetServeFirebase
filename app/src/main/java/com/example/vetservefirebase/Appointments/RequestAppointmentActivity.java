package com.example.vetservefirebase.Appointments;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.vetservefirebase.MainActivity;
import com.example.vetservefirebase.Model.Appointment;
import com.example.vetservefirebase.Model.Pet;
import com.example.vetservefirebase.Model.Services;
import com.example.vetservefirebase.Others.CircleTransform;
import com.example.vetservefirebase.Others.ShowAlert;
import com.example.vetservefirebase.Others.Utils;
import com.example.vetservefirebase.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

public class RequestAppointmentActivity extends AppCompatActivity {
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
    Intent intent;

    DatePickerDialog datePickerDialog;
    int year;
    int month;
    int dayOfMonth;
    Calendar calendar;
    int currentHour;
    int currentMinute;
    String amPm;
    TimePickerDialog timePickerDialog;
    private static final int SERVICE_REQUEST = 2;
    private String providerKey, petKey, uId;
    ArrayList<Services> selectedServices;
    private DatabaseReference dRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_appointments);
        ButterKnife.bind(this);
        if(getIntent().hasExtra("providerKey"))
            providerKey = getIntent().getStringExtra("providerKey");
        uId = FirebaseAuth.getInstance().getCurrentUser().getUid();
    }

    @OnClick({R.id.linearLayout2, R.id.appointmentDate, R.id.appointmentServices, R.id.appointmentTime, R.id.btnRequest}) void onClicks(View view){
        switch (view.getId()){
            case R.id.appointmentDate:
                calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH) + 3;
                datePickerDialog = new DatePickerDialog(this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                appointmentDate.setText(day + "/" + (month + 1) + "/" + year);
                            }
                        }, year, month, dayOfMonth);
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() + 259200000);
                datePickerDialog.show();
                break;
            case R.id.linearLayout2:
                intent = new Intent(this,
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
            case R.id.appointmentServices:
                intent = new Intent(this,
                        SelectServicesActivity.class);
                intent.putExtra("providerKey", providerKey);
                startActivityForResult(intent , SERVICE_REQUEST);
                break;
            case R.id.btnRequest:
                submitAppointmentRequest();
                break;
            default:
                    break;
        }
    }

    private void submitAppointmentRequest() {
        dRef = FirebaseDatabase.getInstance().getReference("appointments").child(uId);
        String id = dRef.push().getKey();
        String appDate = appointmentDate.getText().toString();
        String appTime = appointmentTime.getText().toString();
        String appExtraNotes = appointmentNotes.getText().toString();
        String appointmentRequestCreated = getTimestamp();
        String status = "pending";
        Appointment appointment = new Appointment(id, appDate, appTime, appExtraNotes, providerKey, petKey, selectedServices, status);
        Map<String, Object> appointmentValues = appointment.toMap();
        dRef.child(id).setValue(appointmentValues).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                appointmentValues.clear();
                requestSuccessful();
            }
        });
    }

    private void requestSuccessful() {
        String msg = "You will receive an in-app notification once your provider confirms your appointment.";
        ShowAlert.reminder(this, msg, petKey);
    }

    private String getTimestamp() {
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        return dayOfMonth + "/" + (month + 1) + "/" + year;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_CODE:
                    petKey = data.getStringExtra("petKey");
                    String photoUrl = data.getStringExtra("photoUrl");
                    String petname = data.getStringExtra("petname");
                    Glide.with(getApplicationContext()).load(photoUrl)
                            .transition(withCrossFade())
                            .thumbnail(0.5f)
                            .transform(new CircleTransform())
                            .circleCrop()
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .into(petpicture);
                    this.petname.setText(petname);
                    break;
                case SERVICE_REQUEST:
                    selectedServices = data.getParcelableArrayListExtra("selectedServices");;
                    appointmentServices.setText("");
                    for (int i = 0; i < selectedServices.size(); i++) {
                        appointmentServices.append(selectedServices.get(i).getServicename() + ", ");
                    }
                    break;
                    default:
                        break;
            }
        }
    }
}
