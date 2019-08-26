package com.example.vetservefirebase.AddPet;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.vetservefirebase.Base.BaseActivity;
import com.example.vetservefirebase.MainActivity;
import com.example.vetservefirebase.Model.Pet;
import com.example.vetservefirebase.Others.CircleTransform;
import com.example.vetservefirebase.Others.ShowAlert;
import com.example.vetservefirebase.Others.Utils;
import com.example.vetservefirebase.Others.Validation;
import com.example.vetservefirebase.R;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.UUID;
import butterknife.BindArray;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemSelected;
import fr.ganfra.materialspinner.MaterialSpinner;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

public class AddPetActivity extends BaseActivity implements AddPetView {

    private static final int IMG_REQUEST = 1;
    private static final String TAG = "Add Pet";
    @BindView(R.id.spnrSpecies)
    MaterialSpinner spnrSpecies;
    @BindView(R.id.spnrGender)
    MaterialSpinner spnrGender;
    @BindView(R.id.spnrBreed)
    EditText spnrBreed;
    @BindView(R.id.txtpetname)
    EditText txtpetname;
    @BindView(R.id.petpicture)
    ImageView petpicture;
    @BindView(R.id.petColor)
    EditText txtpetcolor;
    @BindView(R.id.petdateofbirth)
    TextView txtpetDOB;
    @BindView(R.id.btnaddpet)
    Button btnUpAdd;
    @BindArray(R.array.spinner_species_items)
    String[] speciesArray;
    private int petbirthDay, petbirthMonth, petbirthYear;
    ArrayAdapter genderadapter, speciesadapter;
    public String petspecies, petbreed, petgender, petname, petcolor, petdob, uId, petKey;
    Pet pet;
    private DatePickerDialog.OnDateSetListener mDateListener;
    private DatePickerDialog dialog;
    private AddPetPresenterImpl addPetPresenter = new AddPetPresenterImpl();
    private Intent intent;
    private ProgressBar progressBar;
    Validation validation = new Validation();
    private Bitmap bitmap;
    private Uri photoPath;
    private FirebaseStorage storage = FirebaseStorage.getInstance();
    private String photoUrl = "";
    Bundle extras = new Bundle();
    private DatabaseReference dRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pet);
        ButterKnife.bind(this);
        intent = getIntent();
        extras = intent.getExtras();
        uId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        genderadapter = ArrayAdapter.createFromResource(getContext(), R.array.spinner_gender_items, R.layout.spinner_item);
        genderadapter.setDropDownViewResource(R.layout.spinner_adapter);
        speciesadapter = new ArrayAdapter(getContext(), R.layout.spinner_item, speciesArray);
        speciesadapter.setDropDownViewResource(R.layout.spinner_adapter);
        spnrGender.setAdapter(genderadapter);
        spnrSpecies.setAdapter(speciesadapter);
        addPetPresenter.attachView(this);
        if(extras != null){
            btnUpAdd.setText("Save Changes");
            setTitle("Edit Pet Information");
            pet = intent.getParcelableExtra("pet");
            petKey = intent.getStringExtra("petKey");
            setPetInformation();
        }else {
            setTitle("Add Pet");
        }
    }

    private void setPetInformation() {
        photoUrl = pet.getPhotoUrl();
        Glide.with(getContext()).load(photoUrl)
                .transition(withCrossFade())
                .thumbnail(0.5f)
                .transform(new CircleTransform())
                .circleCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(petpicture);
        txtpetname.setText(pet.getPet_name());
        int spcsPosition = speciesadapter.getPosition(pet.getSpecies());
        spnrSpecies.setSelection(spcsPosition + 1);
        spnrBreed.setText(pet.getBreed());
        int genderPosition = genderadapter.getPosition(pet.getGender());
        spnrGender.setSelection(genderPosition + 1);
        txtpetDOB.setText(pet.getDob());
        txtpetcolor.setText(pet.getColor());

    }

    @OnItemSelected ({R.id.spnrSpecies, R.id.spnrGender}) void onItemSelected(Spinner spinner, int position){
        if(spnrSpecies == spinner)
            petspecies = spinner.getItemAtPosition(position).toString();
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

    @OnClick(R.id.petpicture) void addpetpic(){
        selectimage();
    }

    private void selectimage() {
        Intent intent= new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, IMG_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==IMG_REQUEST && resultCode==RESULT_OK && data!=null){
            photoPath = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), photoPath);
                petpicture.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @OnClick(R.id.btnaddpet) void toadd(){
        petname = txtpetname.getText().toString().trim();
        petcolor = txtpetcolor.getText().toString().trim();
        petdob = txtpetDOB.getText().toString().trim();
        petbreed = spnrBreed.getText().toString().trim();
        Boolean testpetname=validation.validateNormalInput(txtpetname);
        Boolean testBreed = validation.validateNormalInput(spnrBreed);
        Boolean testpetcolor=validation.validateNormalInput(txtpetcolor);
        Boolean testSpecies = validateSpinner(spnrSpecies);
        Boolean testGender = validateSpinner(spnrGender);
        if(petdob.isEmpty()){
            txtpetDOB.setError("Field can't be empty");
        }
        if(testpetname){
            if(testSpecies){
                if(testBreed){
                    if(testGender){
                        if(!petdob.isEmpty()){
                            if(testpetcolor){
                                if(photoPath != null)
                                    getImageUrl();
                                else {
                                    if(petspecies.equals("Cat")){
                                        photoUrl = "https://firebasestorage.googleapis.com/v0/b/vetservefirebase.appspot.com/o/defaultpic%2Fcatpic.png?alt=media&token=f3b14ba3-a62b-4181-8833-eb03be6ddae2";
                                    }else if(petspecies.equals("Dog")) {
                                        photoUrl = "https://firebasestorage.googleapis.com/v0/b/vetservefirebase.appspot.com/o/defaultpic%2Fdogpic.png?alt=media&token=eeb7f504-43b1-4a6f-8712-cdf39f58df75";
                                    }
                                    Log.d(TAG, "selectimage: " + petKey);
                                    if (pet != null)
                                        addPetPresenter.updatepet(getContext(), petKey, uId, petname, petspecies, petbreed, petgender, petdob, petcolor, photoUrl);
                                    else
                                        addPetPresenter.addpet(getContext(), uId, petname, petspecies, petbreed, petgender, petdob, petcolor, photoUrl);
                                }
                            }else{
                                txtpetcolor.requestFocus();
                            }
                        }else{
                            txtpetDOB.setFocusableInTouchMode(true);
                            txtpetDOB.requestFocus();
                        }
                    }else{
                        spnrGender.performClick();
                    }
                }else{
                    spnrBreed.requestFocus();
                }
            }else{
                spnrSpecies.performClick();
            }
        }else{
            txtpetname.requestFocus();
        }
    }

    private void getImageUrl() {
        String path = "PetImage/" + UUID.randomUUID() + ".jpg";
        StorageReference profileImageRef = storage.getReference(path);
        profileImageRef.putFile(photoPath).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
            @Override
            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                if (!task.isSuccessful()) {
                    throw task.getException();
                }
                // Continue with the task to get the download URL
                return profileImageRef.getDownloadUrl();
            }
        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                if (task.isSuccessful()) {
                    String photoUrl = task.getResult().toString();
                    Log.d(TAG, "onComplete: " + photoUrl);
                    if(pet != null){
                        addPetPresenter.updatepet(getContext(), petKey, uId, petname, petspecies, petbreed, petgender, petdob, petcolor, photoUrl);
                    }else
                        addPetPresenter.addpet(getContext(), uId, petname, petspecies, petbreed, petgender, petdob, petcolor, photoUrl);
                }
            }
        });
    }

    private boolean validateSpinner(Spinner spinner){
        if(spinner == spnrSpecies) {
            if (petspecies != null){
                if (!petspecies.equals("Species"))
                    return true;
                else {
                    spnrSpecies.setError("Please select a species");
                    return false;
                }
            }else{
                return false;
            }
        }else{
            if (!petgender.equals("Gender"))
                return true;
            else {
                spnrGender.setError("Please select a pet gender");
                return false;
            }
        }
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void addPetSuccess(String msg) {
        Utils.showMessage(this, msg);
        finish();
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("petKey",  petKey);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if(pet != null) {
            getMenuInflater().inflate(R.menu.removemenu, menu);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.remove)
            ShowAlert.alertRemovePet(this, "Are you sure you want to remove this pet?", petKey, uId);
        return super.onOptionsItemSelected(item);
    }
}
