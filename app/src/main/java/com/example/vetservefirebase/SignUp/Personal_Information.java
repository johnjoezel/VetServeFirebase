package com.example.vetservefirebase.SignUp;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.vetservefirebase.Others.Validation;
import com.example.vetservefirebase.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;



public class Personal_Information extends AppCompatActivity{

    @BindView(R.id.txtfirstname)
    EditText txtfirstname;
    @BindView(R.id.txtmname)
    EditText txtmname;
    @BindView(R.id.txtlastname)
    EditText txtlastname;
    @BindView(R.id.txtcontact)
    EditText txtcontact;
    @BindView(R.id.dateofbirth)
    TextView dateofbirth;
    DatePickerDialog dialog;
    private DatePickerDialog.OnDateSetListener mDateListener;
    Validation validation = new Validation();
    private int birthDay, birthMonth, birthYear;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_information);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.dateofbirth)
    public void getDOB() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        Log.d("datekuno","date has been set");
        mDateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                birthDay = day;
                month = month + 1;
                birthMonth = month;
                birthYear = year;
                String date = month + "/" + day + "/" + year;
                dateofbirth.setText(date);
            }
        };
        dialog = new DatePickerDialog(
                this,
                android.R.style.Theme_Holo_Light_Dialog_MinWidth, mDateListener,year,month,day);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }
    @OnClick (R.id.btncontinue)
    public void toContinue() {
        String firstname= txtfirstname.getText().toString().trim();
        String middlename= txtmname.getText().toString().trim();
        String lastname= txtlastname.getText().toString().trim();
        String birthday= dateofbirth.getText().toString().trim();
        String contact = txtcontact.getText().toString().trim();
        Boolean testFirst=validation.validateNormalInput(txtfirstname);
        Boolean testMiddle=validation.validateNormalInput(txtmname);
        Boolean testLast=validation.validateNormalInput(txtlastname);
        Boolean testContact= validation.validateNumber(txtcontact);
        Boolean testBdayAge=getAge(birthYear,birthMonth,birthDay);
        if(!testBdayAge){
            dateofbirth.setError("Should be 18 years old or above");
        }else if(birthday.isEmpty()){
            dateofbirth.setError("Field can't be empty");
        }
        if (testFirst) {
            if(testMiddle){
                if(testLast){
                    if(testContact) {
                        if(!birthday.isEmpty()) {
                            if (testBdayAge) {
                                ArrayList<String> data = new ArrayList<String>();
                                data.add(firstname);
                                data.add(middlename);
                                data.add(lastname);
                                data.add(birthday);
                                data.add(contact);
                                Intent intent = new Intent(this, SignUpActivity.class);
                                intent.putStringArrayListExtra("data", data);
                                startActivity(intent);
                            } else {
                                dateofbirth.setFocusableInTouchMode(true);
                                dateofbirth.requestFocus();
                            }
                        }else {
                            dateofbirth.setFocusableInTouchMode(true);
                            dateofbirth.requestFocus();
                        }
                    }else{
                        txtcontact.requestFocus();
                    }
                }else{
                    txtlastname.requestFocus();
                }
            }else{
                txtmname.requestFocus();
            }
        }else{
            txtfirstname.requestFocus();
        }
    }

    private Boolean getAge(int year, int month, int day) {
        try {
            Calendar dob = Calendar.getInstance();
            Calendar today = Calendar.getInstance();
            dob.set(year, month, day);
            int monthToday = today.get(Calendar.MONTH) + 1;
            int monthDOB = dob.get(Calendar.MONTH)+1;
            int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);
//            Toast.makeText(this, age+"sds"    , Toast.LENGTH_SHORT).show();
            if (age > 18) {
                return true;
            } else if (age == 18) {
                if (monthDOB > monthToday) {
                    return true;
                } else if (monthDOB == monthToday) {
                    int todayDate = today.get(Calendar.DAY_OF_MONTH);
                    int dobDate = dob.get(Calendar.DAY_OF_MONTH);
                    if (dobDate <= todayDate) { // should be less then
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
