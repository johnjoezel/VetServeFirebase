package com.example.vetservefirebase.Others;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

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
    public static String showAlertwithreturn(LayoutInflater inflater, Context context, String msg, String string){
        String returnvalue = null;
        try{
            final AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
            final View dialogView = inflater.inflate(R.layout.custom_alert_dialog, null);
            alertDialog.setView(dialogView);
            final EditText edt = dialogView.findViewById(R.id.edit);
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
                    dialog.dismiss();

                }
            });

            alertDialog.show();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
