package com.example.vetservefirebase.Dashboard;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.vetservefirebase.Model.User;
import com.example.vetservefirebase.Others.CircleTransform;
import com.example.vetservefirebase.PetDashboard.PetDashboardActivity;
import com.example.vetservefirebase.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;
import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;


public class PetOwnerDashboardFragment extends Fragment implements DashboardView{


    @BindView(R.id.userProfpic)
    ImageView userProfpic;
    @BindView(R.id.displayusername)
    TextView txtUsername;
    FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseStorage storage = FirebaseStorage.getInstance();
    private int IMG_REQUEST= 1;
    private Bitmap bitmap;
    private String photoPath;
    private String uId;
    private DatabaseReference dRef;
    private DashboardPresenterImpl dashboardpresenter;
    private final String IMAGE_URL = "ProfileImage/";

    public PetOwnerDashboardFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragmentx
        View view = inflater.inflate(R.layout.petownerdashboardfragment, container, false);
        ButterKnife.bind(this, view);
        dashboardpresenter = new DashboardPresenterImpl();
        uId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        Log.d("batanggiduay", uId);
        dRef = FirebaseDatabase.getInstance().getReference("users").child(uId);
        dRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(isAdded()) {
                    User user = dataSnapshot.getValue(User.class);
                    Glide.with(getActivity()).load(user.getPhotoUrl())
                            .transition(withCrossFade())
                            .thumbnail(0.5f)
                            .transform(new CircleTransform())
                            .circleCrop()
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .into(userProfpic);
                    txtUsername.setText(user.getDisplayname());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        Log.d("waysoftdrinks", dRef.toString());
        return view;

    }
    @OnClick(R.id.userProfpic) void changePP(){
        selectimage();
        userProfpic.setDrawingCacheEnabled(true);
        userProfpic.buildDrawingCache();
        Bitmap bitmap = ((BitmapDrawable) userProfpic.getDrawable()).getBitmap();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] byteArray = baos.toByteArray();

        

    }
    public void selectimage(){
        Intent intent= new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, IMG_REQUEST);
    }
    @OnClick(R.id.forpets) void next(){
        Intent intent = new Intent(getContext(), PetDashboardActivity.class);
        startActivity(intent);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==IMG_REQUEST && resultCode==RESULT_OK && data!=null){
            Uri photoPath = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), photoPath);
                userProfpic.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void loadprofile() {

    }

    @Override
    public void changeprofilepic() {

    }
}
