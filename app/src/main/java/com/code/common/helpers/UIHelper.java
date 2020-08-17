package com.code.common.helpers;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.List;

public class UIHelper {

    public static ProgressDialog progressDialog;

    public static ProgressDialog showProgress(Activity activity, int messageId) {
        return showProgress(activity, activity.getResources().getString(messageId));
    }

    public static ProgressDialog showProgress(Activity activity, String message) {
        progressDialog = new ProgressDialog(activity);
        progressDialog.setMessage(message);
        progressDialog.setIndeterminate(true);
        progressDialog.show();
        return progressDialog;
    }


    public static void showToast(Activity activity, int messageId) {
        showToast(activity, activity.getResources().getString(messageId));
    }

    public static void showToast(Activity activity, String message) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
    }

    public static String getString(EditText control) {
        return control.getText().toString();
    }

    public static String getEmailAddress(Context context){
        AccountManager manager = AccountManager.get(context);
        Account[] accounts = manager.getAccountsByType("com.google");
        List<String> possibleEmails = new LinkedList<String>();
        String email="";

        for (Account account : accounts) {
            // TODO: Check possibleEmail against an email regex or treat
            // account.name as an email address only for certain account.type values.
            possibleEmails.add(account.name);
        }

        if (!possibleEmails.isEmpty() && possibleEmails.get(0) != null) {
             email = possibleEmails.get(0);
            Log.d("Email",email);
        }
        Log.d("Email",possibleEmails.size()+"");
        return email;

    }
}
