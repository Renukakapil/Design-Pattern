package com.code.common.helpers;

import android.widget.Button;
import android.widget.Toast;

import com.code.designpatternapp.R;


public class EnableDisableHelper {

    int mEnableColor,mDisableColor;

    public EnableDisableHelper(int enableColor, int disableColor){
        mEnableColor=enableColor;
        mDisableColor=disableColor;
    }

    public void enableButton(Button button){
        button.setEnabled(true);
        button.setBackgroundResource(R.drawable.button_round_corner);
        button.setText(R.string.ps_continue);
    }

    public void disableButton(Button button){
        button.setEnabled(false);
        button.setBackgroundResource(R.drawable.custom_diabled_button_background);
    }
}
