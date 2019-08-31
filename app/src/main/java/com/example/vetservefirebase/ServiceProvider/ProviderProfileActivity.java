package com.example.vetservefirebase.ServiceProvider;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.vetservefirebase.Model.ServiceProvider;
import com.example.vetservefirebase.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProviderProfileActivity extends AppCompatActivity {
    @BindView(R.id.clinicName)
    TextView clinicName;
    @BindView(R.id.clinicAddress)
    TextView clinicAddress;
    @BindView(R.id.clinicHours)
    TextView clinicHours;
    @BindView(R.id.clinicPhone)
    TextView clinicPhone;
    ServiceProvider provider;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider_profile);
        ButterKnife.bind(this);
        Toolbar mToolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        provider = getIntent().getParcelableExtra("provider");
        loadinformation();

    }

    private void loadinformation() {
        clinicName.setText(provider.getClinicname());
        clinicAddress.setText(provider.getLocation());
        clinicHours.setText("8:00 AM - 5:00 PM");
        clinicPhone.setText(provider.getPhonenumber());
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
