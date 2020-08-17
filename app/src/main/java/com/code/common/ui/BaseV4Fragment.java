package com.code.common.ui;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.code.designpatternapp.utils.Constants;


public abstract class BaseV4Fragment extends Fragment {

    protected Typeface latoRegular, latoBold, latoLight, latoMedium, latoSemiBold, latoBlack, neutra_text_bold,
            neutra_text_book, latoHeavy, montserrat_regular;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupActivityComponent();

        latoBold = Typeface.createFromAsset(getActivity().getAssets(), Constants.LATO_BOLD);
        latoBlack = Typeface.createFromAsset(getActivity().getAssets(), Constants.LATO_BLACK);
    }

    protected abstract void setupActivityComponent();

}
