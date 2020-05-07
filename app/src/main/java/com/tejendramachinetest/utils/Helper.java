package com.tejendramachinetest.utils;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;

import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.tejendramachinetest.R;

public class Helper {

    public static boolean validateMobileNumber(String mobileNumber) {

        if (mobileNumber.length() < 10)
            return false;

        return !mobileNumber.startsWith("0");
    }

    public static boolean checkStoragePermission(Context mContext) {
        int result = ContextCompat.checkSelfPermission(mContext, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        return result == PackageManager.PERMISSION_GRANTED;
    }

    public static boolean checkCameraPermission(Context mContext) {
        int result = ContextCompat.checkSelfPermission(mContext, Manifest.permission.CAMERA);
        return result == PackageManager.PERMISSION_GRANTED;
    }

    public static void requestPermission(Context mContext, int requestCode) {

        ActivityCompat.requestPermissions(((Activity) mContext), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, requestCode);

    }

    public static Dialog showLoader(Context context/*, boolean textStatus, int currentDoc, int totalDoc*/) {
        Dialog networkDialogLoader = new Dialog(context, R.style.AppTheme);
        String fromTime = "";
        // networkDialogLoader.requestWindowFeature(Window.FEATURE_NO_TITLE);
        networkDialogLoader.setContentView(R.layout.progress_loader);
        networkDialogLoader.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        networkDialogLoader.setCancelable(false);
        networkDialogLoader.setCanceledOnTouchOutside(false);


        networkDialogLoader.show();
        return networkDialogLoader;

    }

    public void hideLoader(Dialog dialog) {
        dialog.dismiss();
    }

    public static void logOutAlert(Context context, final iAlertListner iAlertListner){

        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        alert.setMessage("Are you sure?")
                .setPositiveButton("Logout", new DialogInterface.OnClickListener()                 {

                    public void onClick(DialogInterface dialog, int which) {

                        iAlertListner.onPressOk();
                        dialog.dismiss();

                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                iAlertListner.onPressCancel();
                dialog.dismiss();
            }
        });

        AlertDialog alert1 = alert.create();
        alert1.show();

    }

    public static void permissionAlert(Context context, final iAlertListner iAlertListner){

        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        alert.setMessage("PLease allow persmission for Contact, to see your contacts.")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener()                 {

                    public void onClick(DialogInterface dialog, int which) {

                        iAlertListner.onPressOk();
                        dialog.dismiss();

                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                iAlertListner.onPressCancel();
                dialog.dismiss();
            }
        });

        AlertDialog alert1 = alert.create();
        alert1.show();

    }

    public static void hideSoftKeyboard(Activity activity) {
        if (activity != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
            if (inputMethodManager.isAcceptingText()) {
                inputMethodManager.hideSoftInputFromWindow(activity.getWindow().getDecorView().getWindowToken(), 0);

            }
        }
    }

}
