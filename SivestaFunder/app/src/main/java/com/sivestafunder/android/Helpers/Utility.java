package com.sivestafunder.android.Helpers;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.sivestafunder.android.Models.Funder;
import com.sivestafunder.android.R;


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

    public static Funder getFunderPrefs(Context c) {
        SharedPreferences prfs = c.getSharedPreferences(AppConst.PRF_FUNDER, Context.MODE_PRIVATE);
        Funder f = new Funder(c);
        f.setUsername(prfs.getString(AppConst.PRF_TAG_EMAIL, ""));
        f.setPassword(prfs.getString(AppConst.PRF_TAG_PASS, ""));
        return f;
    }

    public static void removeFunderPrefs(Context c) {
        SharedPreferences prfs = c.getSharedPreferences(AppConst.PRF_FUNDER, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prfs.edit();
        editor.clear();
        editor.apply();
    }

    public static void setFarmerPrefs(Context c, Funder f) {
        SharedPreferences prfs = c.getSharedPreferences(AppConst.PRF_FUNDER, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prfs.edit();
        editor.putString(AppConst.PRF_TAG_EMAIL, f.getEmail());
        editor.putString(AppConst.PRF_TAG_PASS, f.getPassword());
        editor.apply();
    }

    public static void displayUnAuthorize(Context c, String msg) {
        if (msg.equals("HTTP 401 Unauthorized"))
            displayAlert(c, c.getString(R.string.login_failed_tex));
    }

    public static String getSafeSubstring(String s, int maxLength){
        if(!TextUtils.isEmpty(s)){
            if(s.length() >= maxLength){
                return s.substring(0, maxLength) + "...";
            }
        }
        return s;
    }
}
