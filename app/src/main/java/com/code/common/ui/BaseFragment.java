package com.code.common.ui;

import android.app.Fragment;
import android.graphics.Typeface;
import android.os.Bundle;

import com.code.designpatternapp.utils.Constants;


public abstract class BaseFragment extends Fragment {

    protected Typeface latoBold;
    protected Typeface latoBlack;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupActivityComponent();

        latoBold= Typeface.createFromAsset(getActivity().getAssets(), Constants.LATO_BOLD);
        latoBlack= Typeface.createFromAsset(getActivity().getAssets(),Constants.LATO_BLACK);
    }

    protected abstract void setupActivityComponent();
}