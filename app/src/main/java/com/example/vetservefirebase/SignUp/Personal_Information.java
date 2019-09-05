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
    @BindView(R.id.txtaddress)
    EditText txtaddress;
    @BindView(R.id.dateofbirth)
    TextView dateofbirth;
    DatePickerDialog dialog;
    Validation validation = new Validation();
    private int dayofmonth, birthmonth, birthyear;
    private Calendar calendar;
    private DatePickerDialog datePickerDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_information);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.dateofbirth) public void getDOB() {
        calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        birthyear = year;
                        birthmonth = month;
                        dayofmonth = day;
                        dateofbirth.setText(day + "/" + (month + 1) + "/" + year);
                    }
                }, year, month, dayOfMonth);
        datePickerDialog.getDatePicker();
        datePickerDialog.show();
    }
    @OnClick (R.id.btncontinue)
    public void toContinue() {
        String firstname= txtfirstname.getText().toString().trim();
        String middlename= txtmname.getText().toString().trim();
        String lastname= txtlastname.getText().toString().trim();
        String birthday= dateofbirth.getText().toString().trim();
        String contact = txtcontact.getText().toString().trim();
        String address = txtaddress.getText().toString().trim();
        Boolean testFirst=validation.validateNormalInput(txtfirstname);
        Boolean testMiddle=validation.validateNormalInput(txtmname);
        Boolean testLast=validation.validateNormalInput(txtlastname);
        Boolean testContact= validation.validateNumber(txtcontact);
        Boolean testAddress= validation.validateNormalInput(txtaddress);
        Boolean testBdayAge=getAge(birthyear,birthmonth,dayofmonth);
        if(!testBdayAge){
            dateofbirth.setError("Should be 18 years old or above");
        }else if(birthday.isEmpty()){
            dateofbirth.setError("Field can't be empty");
        }
        if (testFirst) {
            if(testMiddle){
                if(testLast){
                    if(testContact) {
                        if(testAddress) {
                            if (!birthday.isEmpty()) {
                                if (testBdayAge) {
                                    ArrayList<String> data = new ArrayList<String>();
                                    data.add(firstname);
                                    data.add(middlename);
                                    data.add(lastname);
                                    data.add(birthday);
                                    data.add(contact);
                                    data.add(address);
                                    Intent intent = new Intent(this, SignUpActivity.class);
                                    intent.putStringArrayListExtra("data", data);
                                    startActivity(intent);
                                } else {
                                    dateofbirth.setFocusableInTouchMode(true);
                                    dateofbirth.requestFocus();
                                }
                            } else {
                                dateofbirth.setFocusableInTouchMode(true);
                                dateofbirth.requestFocus();
                            }
                        }else{
                            txtaddress.requestFocus();
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
