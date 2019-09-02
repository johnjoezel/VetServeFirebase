package com.example.vetservefirebase.Others;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.vetservefirebase.PetOwnerProfile.ProfileFragment;
import com.example.vetservefirebase.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class ShowAlert {
    public static void showAlert(Context context, String msg){
        try{
            final AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
            alertDialog.setTitle("Alert");
            alertDialog.setCancelable(false);
            alertDialog.setMessage(msg);
            alertDialog.setNegativeButton("Close", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });

            alertDialog.show();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public static void showAlertwithreturn(LayoutInflater inflater, Context context, String msg, String string, TextView textView){

        try{
            final AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
            final View dialogView = inflater.inflate(R.layout.custom_alert_dialog, null);
            final EditText edt = dialogView.findViewById(R.id.edit);
            alertDialog.setView(dialogView);
            edt.setText(string);
            alertDialog.setMessage(msg);
            alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            alertDialog.setPositiveButton("Confirm",  new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if(!edt.getText().toString().trim().isEmpty())
                        textView.setText(edt.getText().toString().trim());
                    else
                        textView.setText(string);
                    dialog.dismiss();
                }
            });

            alertDialog.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void alertRemovePet(Context context, String msg, String petKey, String uId){
        try{
            final AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
            alertDialog.setTitle("Alert");
            alertDialog.setCancelable(false);
            alertDialog.setMessage(msg);
            alertDialog.setNegativeButton("Confirm", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    DatabaseReference mDatabase  = FirebaseDatabase.getInstance().getReference();
                    DatabaseReference dRef = mDatabase.child("pets").child(uId).child(petKey);
                    dRef.child("status").setValue("removed").addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(context, "Pet Removed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    dialog.dismiss();
                }
            });
            alertDialog.setPositiveButton("Cancel",  new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });

            alertDialog.show();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public static void alertAddProvider(Context context, String msg, String providerKey, String uId){
        try{
            final AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
            alertDialog.setTitle("Alert");
            alertDialog.setCancelable(false);
            alertDialog.setMessage("Confirm adding " + msg + " as provider?");
            alertDialog.setNegativeButton("Confirm", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    DatabaseReference mDatabase  = FirebaseDatabase.getInstance().getReference();
                    DatabaseReference dRef = mDatabase.child("user_provider").child(uId);
                    Map<String, Object> map = new HashMap();
                    map.put("providerID", providerKey);
                    String id = dRef.push().getKey();
                    dRef.child(id).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                map.clear();
                                Toast.makeText(context, "You added " + msg + "as provider", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    dialog.dismiss();
                }
            });
            alertDialog.setPositiveButton("Cancel",  new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });

            alertDialog.show();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
