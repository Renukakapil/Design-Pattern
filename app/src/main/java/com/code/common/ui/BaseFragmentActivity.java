package com.code.common.ui;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.code.designpatternapp.utils.Constants;



public abstract class BaseFragmentActivity extends FragmentActivity{

    protected Typeface latoRegular,latoBold,latoLight,latoMedium,latoSemiBold,latoBlack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupActivityComponent();

        latoBold= Typeface.createFromAsset(getAssets(), Constants.LATO_BOLD);
        latoBlack= Typeface.createFromAsset(getAssets(),Constants.LATO_BLACK);
    }

    protected abstract void setupActivityComponent();

}
