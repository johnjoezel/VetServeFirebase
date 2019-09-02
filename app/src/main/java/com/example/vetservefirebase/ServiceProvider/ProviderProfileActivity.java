package com.example.vetservefirebase.ServiceProvider;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.vetservefirebase.MainActivity;
import com.example.vetservefirebase.Model.ServiceProvider;
import com.example.vetservefirebase.Model.Services;
import com.example.vetservefirebase.Others.ShowAlert;
import com.example.vetservefirebase.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @BindView(R.id.clinicServices)
    ExpandableListView clinicServices;
    ServiceProvider provider;
    String providerKey;
    ArrayList<String> servicename = new ArrayList<>();
    List<String> servicesHeader;
    Map<String, ArrayList<String>> servicesDataChild;
    ExpandableListAdapter listAdapter;
    private DatabaseReference dRef;
    private String uId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider_profile);
        ButterKnife.bind(this);
        loadheaderList();
        Toolbar mToolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        TextView tv=new TextView(this);
        Typeface regular = ResourcesCompat.getFont(this, R.font.champagne);
        tv.setText("Add as Provider");
        tv.setTextSize(20);
        tv.setTypeface(regular, Typeface.NORMAL);
        tv.setTextColor(ContextCompat.getColor(this, R.color.material_white_1000));
        Toolbar.LayoutParams l3=new Toolbar.LayoutParams(Toolbar.LayoutParams.WRAP_CONTENT, Toolbar.LayoutParams.WRAP_CONTENT);
        l3.rightMargin = 16;
        l3.gravity=Gravity.END;
        tv.setLayoutParams(l3);
        mToolbar.addView(tv);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    onBackPressed();
            }
        });
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirm();
            }
        });
        uId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        provider = getIntent().getParcelableExtra("provider");
        providerKey = getIntent().getStringExtra("providerKey");
        dRef = FirebaseDatabase.getInstance().getReference("services").child(providerKey);
        dRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds: dataSnapshot.getChildren()){
                    Log.d("asdf", "onDataChange: " + ds.getKey());
                    Services services = ds.getValue(Services.class);
                    servicename.add(services.getServicename());

                }
                loadServicesChild();
                loadinformation();
                listAdapter = new ExpandableListAdapter(getApplicationContext(), servicesHeader, servicesDataChild);
                clinicServices.setAdapter(listAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void confirm(){
        ShowAlert.alertAddProvider(this, provider.getClinicname(), providerKey, uId);
    }

    private void loadheaderList() {
        servicesHeader = new ArrayList<>();
        servicesHeader.add("Services");
    }

    private void loadServicesChild() {
        servicesDataChild = new HashMap<String, ArrayList<String>>();
        servicesDataChild.put(servicesHeader.get(0), servicename);
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
