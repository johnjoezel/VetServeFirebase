package com.example.vetservefirebase.Others;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.vetservefirebase.PetOwnerProfile.ProfileFragment;
import com.example.vetservefirebase.R;

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
}
