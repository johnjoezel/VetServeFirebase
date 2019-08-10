package com.example.vetservefirebase.AddPet;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.vetservefirebase.Base.BaseActivity;
import com.example.vetservefirebase.MainActivity;
import com.example.vetservefirebase.Others.ShowAlert;
import com.example.vetservefirebase.Others.Utils;
import com.example.vetservefirebase.PetListView.PetListViewActivity;
import com.example.vetservefirebase.R;

import java.util.Calendar;

import butterknife.BindArray;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemSelected;

public class AddPetActivity extends BaseActivity implements AddPetView{

    @BindView(R.id.spnrSpecies)
    Spinner spnrSpecies;
    @BindView(R.id.spnrGender)
    Spinner spnrGender;
    @BindView(R.id.spnrBreed)
    Spinner spnrBreed;
    @BindView(R.id.txtpetname)
    EditText txtpetname;
    @BindView(R.id.petColor)
    EditText txtpetcolor;
    @BindView(R.id.petdateofbirth)
    TextView txtpetDOB;
    @BindArray(R.array.spinner_species_items)
    String[] speciesArray;
    private ArrayAdapter breedsadapter, genderadapter, speciesadapter;
    public String petspecies, petbreed, petgender, petname, petcolor, petdob,uId;
    private DatePickerDialog.OnDateSetListener mDateListener;
    private int petbirthDay, petbirthMonth, petbirthYear;
    private DatePickerDialog dialog;
    private ProgressBar progressBar;
    private AddPetPresenterImpl addPetPresenter = new AddPetPresenterImpl();
    private Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pet);
        ButterKnife.bind(this);
        intent = getIntent();
        setTitle("Add Pet");
        uId =  intent.getStringExtra("uId");
        breedsadapter = ArrayAdapter.createFromResource(this, R.array.spinner_breed_items, R.layout.spinner_item);
        breedsadapter.setDropDownViewResource(R.layout.spinner_adapter);
        genderadapter = ArrayAdapter.createFromResource(this, R.array.spinner_gender_items, R.layout.spinner_item);
        genderadapter.setDropDownViewResource(R.layout.spinner_adapter);
        speciesadapter = new ArrayAdapter(this,R.layout.spinner_item,speciesArray);
        speciesadapter.setDropDownViewResource(R.layout.spinner_adapter);
        spnrGender.setAdapter(genderadapter);
        spnrBreed.setAdapter(breedsadapter);
        spnrSpecies.setAdapter(speciesadapter);
        addPetPresenter.attachView(this);

    }


    @OnItemSelected ({R.id.spnrSpecies, R.id.spnrGender, R.id.spnrBreed}) void onItemSelected(Spinner spinner, int position){
        if(spnrSpecies == spinner)
            petspecies = spinner.getItemAtPosition(position).toString();
        if(spnrBreed == spinner)
            petbreed = spinner.getItemAtPosition(position).toString();
        if(spnrGender == spinner)
            petgender = spinner.getItemAtPosition(position).toString();
    }
    @OnClick(R.id.petdateofbirth)
    public void getpetDOB() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        mDateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                petbirthDay = day;
                month = month + 1;
                petbirthMonth = month;
                petbirthYear = year;
                String date = month + "/" + day + "/" + year;
                txtpetDOB.setText(date);
            }
        };
        dialog = new DatePickerDialog(
                this,
                android.R.style.Theme_Holo_Light_Dialog_MinWidth, mDateListener,year,month,day);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    @OnClick(R.id.btnaddpet) void toadd(){
        petname = txtpetname.getText().toString().trim();
        petcolor = txtpetcolor.getText().toString().trim();
        petdob = txtpetDOB.getText().toString().trim();
        addPetPresenter.addpet(this, uId, petname, petspecies, petbreed, petgender, petdob, petcolor);
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void addPetSuccess() {
        Utils.showMessage(this, "Pet Added Successfully");
        Intent intent = new Intent(this, PetListViewActivity.class);
        startActivity(intent);
    }

    @Override
    public void addPetError(String errcode, String errmessage) {

    }

    @Override
    public void setProgressVisibility(boolean visibility) {
        if (visibility)
            progressBar.setVisibility(View.VISIBLE);
        else
            progressBar.setVisibility(View.GONE);
    }
}
