package com.example.vetservefirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.vetservefirebase.Dashboard.PetOwnerDashboardFragment;
import com.example.vetservefirebase.Model.User;
import com.example.vetservefirebase.Others.CircleTransform;
import com.example.vetservefirebase.PetOwnerProfile.ChangePasswordActivity;
import com.example.vetservefirebase.PetOwnerProfile.ProfileFragment;
import com.example.vetservefirebase.SignIn.SignInActivity;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Main Activity";
    public NavigationView navigationView;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference dRef;
    private FirebaseUser user;
    private DrawerLayout drawer;
    public static int navItemIndex = 0;
    private static final String TAG_HOME = "Home";
    private static final String TAG_PROFILE = "User Profile";
    public static String CURRENT_TAG = TAG_HOME;
    private Handler mHandler;
    TextView headerDisplayname;
    ImageView headerBackground, headerDisplaypic;
    private View navHeader;
    private String[] activityTitles;
    private Toolbar toolbar;
    private String email;
    private int state;
    private int fragmentid = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        state = 0;
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mHandler = new Handler();
        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        navHeader = navigationView.getHeaderView(0);
        headerDisplaypic = navHeader.findViewById(R.id.headerDisplaypic);
        headerDisplayname = navHeader.findViewById(R.id.headerDisplayname);
        headerBackground = navHeader.findViewById(R.id.img_header_bg);
        activityTitles = getResources().getStringArray(R.array.nav_item_activity_titles);
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        //Set up an AuthStateListener that responds to changes in the user's sign-in state
        if (user != null) {
            email = user.getEmail();
            dRef = FirebaseDatabase.getInstance().getReference("users").child(user.getUid());
            dRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    User user = dataSnapshot.getValue(User.class);
                    String photoUrl = user.getPhotoUrl();
                    loadNavHeader(photoUrl, email);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
            setUpNavigationView();
            if (savedInstanceState == null) {
                navItemIndex = 0;
                CURRENT_TAG = TAG_HOME;
                loadHomeFragment();
            }
        } else {
                startActivity(new Intent(MainActivity.this, SignInActivity.class));
        }

    }

    private void loadNavHeader(String photoUrl, String email) {
        headerDisplayname.setText(email);
        Glide.with(this).load(R.drawable.main_background)
                .transition(withCrossFade())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(headerBackground);
        Glide.with(this).load(photoUrl)
                .transition(withCrossFade())
                .thumbnail(0.5f)
                .transform(new CircleTransform())
                .circleCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(headerDisplaypic);
    }

    private void loadHomeFragment() {
        // selecting appropriate nav menu item
        selectNavMenu();

        // set toolbar title
        setToolbarTitle();

        // if user select the current navigation menu again, don't do anything
        // just close the navigation drawer
        if (getSupportFragmentManager().findFragmentByTag(CURRENT_TAG) != null) {
            drawer.closeDrawers();
            // show or hide the fab button
            return;
        }

        // Sometimes, when fragment has huge data, screen seems hanging
        // when switching between navigation menus
        // So using runnable, the fragment is loaded with cross fade effect
        // This effect can be seen in GMail app
        Runnable mPendingRunnable = new Runnable() {
            @Override
            public void run() {
                // update the main content by replacing fragments
                Fragment fragment = getHomeFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                        android.R.anim.fade_out);
                fragmentTransaction.replace(R.id.fragment_container, fragment, CURRENT_TAG);
                fragmentTransaction.commitAllowingStateLoss();
            }
        };

        // If mPendingRunnable is not null, then add to the message queue
        if (mPendingRunnable != null) {
            mHandler.post(mPendingRunnable);
        }

        //Closing drawer on item click
        drawer.closeDrawers();

        // refresh toolbar menu
        invalidateOptionsMenu();
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if(state==1){
                startActivity(new Intent(this,MainActivity.class));
            }else{
                Intent a = new Intent(Intent.ACTION_MAIN);
                a.addCategory(Intent.CATEGORY_HOME);
                a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(a);
            }
        }
    }


    private Fragment getHomeFragment() {
        switch (navItemIndex) {
            case 0:
                // home
                PetOwnerDashboardFragment petOwnerDashboardFragment = new PetOwnerDashboardFragment();
                return petOwnerDashboardFragment;
            case 1:
                ProfileFragment profileFragment = new ProfileFragment();
                return profileFragment;
            default:
                return new PetOwnerDashboardFragment();
        }
    }

    private void setToolbarTitle() {
        getSupportActionBar().setTitle(activityTitles[navItemIndex]);
    }

    private void selectNavMenu() {
        navigationView.getMenu().getItem(navItemIndex).setChecked(true);
    }
    private void setUpNavigationView() {
        //Setting Navigation View Item Selected Listener to handle the item click of the navigation menu
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    //Replacing the main content with ContentFragment Which is our Inbox View;
                    case R.id.nav_home:
                        navItemIndex = 0;
                        CURRENT_TAG = TAG_HOME;
                        break;
                    case R.id.nav_profile:
                        navItemIndex = 1;
                        CURRENT_TAG = TAG_PROFILE;
                        break;
                    case R.id.nav_password:
                        startActivity(new Intent(getApplicationContext(), ChangePasswordActivity.class));
                        break;
                    case R.id.nav_logout:
                        // launch new intent instead of loading fragment
                        mAuth.signOut();
                        startActivity(new Intent(getApplicationContext(), SignInActivity.class));
                        break;
                    default:
                        navItemIndex = 0;
                }

                if (menuItem.isChecked()) {
                    menuItem.setChecked(false);
                } else {
                    menuItem.setChecked(true);
                }
                menuItem.setChecked(true);

                loadHomeFragment();

                return true;
            }
        });

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.openDrawer, R.string.closeDrawer) {

            @Override
            public void onDrawerClosed(View drawerView) {
                // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                super.onDrawerClosed(drawerView);
            }
            @Override
            public void onDrawerOpened(View drawerView) {
                // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank
                super.onDrawerOpened(drawerView);
            }
        };
        //Setting the actionbarToggle to drawer layout
        drawer.setDrawerListener(actionBarDrawerToggle);
        //calling sync state is necessary or else your hamburger icon wont show up
        actionBarDrawerToggle.syncState();
    }

}
