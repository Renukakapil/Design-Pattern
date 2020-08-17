package com.code.common.ui;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.code.designpatternapp.utils.Constants;


    public abstract class BaseAppCompactActivity extends Activity {

    protected Typeface latoBlack, latoBold;
    protected ProgressDialog mLoadingProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupActivityComponent();
        latoBold = Typeface.createFromAsset(getAssets(), Constants.LATO_BOLD);
        latoBlack = Typeface.createFromAsset(getAssets(), Constants.LATO_BLACK);

    }

    protected abstract void setupActivityComponent();

    protected void showLoadingDialog() {
        try {
            if(mLoadingProgressDialog==null){
                mLoadingProgressDialog=new ProgressDialog(BaseAppCompactActivity.this);
            }
            mLoadingProgressDialog.setMessage("Loading Photos...");
            mLoadingProgressDialog.setCanceledOnTouchOutside(false);
            mLoadingProgressDialog.setCancelable(true);
            mLoadingProgressDialog.show();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    protected void showLoadingDialog(String message) {
        try {
            if(mLoadingProgressDialog==null){
                mLoadingProgressDialog=new ProgressDialog(BaseAppCompactActivity.this);
            }
            mLoadingProgressDialog.setMessage(message);
            mLoadingProgressDialog.setCanceledOnTouchOutside(false);
            mLoadingProgressDialog.setCancelable(true);
            mLoadingProgressDialog.show();
        } catch (Exception ex) {
            ex.printStackTrace();
        }


    }

    protected void cancelLoadingDialog() {
        try {
            if (mLoadingProgressDialog != null) {
                mLoadingProgressDialog.dismiss();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }


}
