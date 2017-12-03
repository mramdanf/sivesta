package com.sivesta.androidfarmer.Helpers;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.sivesta.androidfarmer.Models.Farmer;
import com.sivesta.androidfarmer.R;

/**
 * Created by Ramdan Firdaus on 3/12/2017.
 */

public class Utility {

    public static void displayAlert(Context c, String msg){
        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(c);

        LayoutInflater layoutInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogView = layoutInflater.inflate(R.layout.custom_alert_dialog, null);
        TextView tvCustomAlert = (TextView) dialogView.findViewById(R.id.tv_custom_alert);
        tvCustomAlert.setText(msg);

        builder
                .setView(dialogView)
                .setTitle(c.getString(R.string.app_name))
                .setCancelable(false)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int id) {

                                dialog.cancel();
                            }
                        }).show();

    }

    public static void setFarmerPrefs(Context c, Farmer f) {
        SharedPreferences prfs = c.getSharedPreferences(AppConst.PRF_FARMER, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prfs.edit();
        editor.putString(AppConst.PRF_TAG_UNAME, f.getUsername());
        editor.putString(AppConst.PRF_TAG_PASS, f.getPassword());
        editor.apply();
    }

    public static Farmer getFarmerPrefs(Context c) {
        SharedPreferences prfs = c.getSharedPreferences(AppConst.PRF_FARMER, Context.MODE_PRIVATE);
        Farmer f = new Farmer();
        f.setUsername(prfs.getString(AppConst.PRF_TAG_UNAME, ""));
        f.setPassword(prfs.getString(AppConst.PRF_TAG_PASS, ""));
        return f;
    }
}
