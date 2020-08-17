package com.code.designpatternapp.ui;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.code.common.contracts.AsyncResult;
import com.code.common.ui.BaseAppCompactActivity;
import com.code.designpatternapp.App;
import com.code.designpatternapp.R;
import com.code.designpatternapp.data.contracts.models.AddressModel;
import com.code.designpatternapp.data.contracts.services.IAddressService;

import javax.inject.Inject;

public class MainActivity extends BaseAppCompactActivity implements View.OnClickListener {

    @Inject
    IAddressService _AddressService;

    private AddressModel addressModel;


    Button buttonApiDemo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        setListeners();
        setFonts();
    }

    @Override
    protected void setupActivityComponent() {
        App.get(MainActivity.this).component().inject(this);
    }

    private void setFonts() {
        buttonApiDemo.setTypeface(latoBold);
    }

    private void setListeners() {
        buttonApiDemo.setOnClickListener(this);
    }

    private void initViews() {
        buttonApiDemo = (Button) findViewById(R.id.button_api_demo);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button_api_demo:
                setAddressModel();
                getAddressFromApi(addressModel);
                break;
        }
    }

    //todo hard-coding data
    private void setAddressModel() {
        addressModel = new AddressModel();
        addressModel.setCity("PalmerstonNorth");
        addressModel.setName("Renuka");
        addressModel.setEmail("kapilrenuka@gmail.com");
        addressModel.setPhone("XXXXXXXXX");
        addressModel.setLine1("Lyndhurst st");
        addressModel.setLine2("Manawatu");
    }

    private void getAddressFromApi(final AddressModel addressModel) {
        _AddressService.createAddress(addressModel,new AsyncResult<AddressModel>() {
            @Override
            public void success(final AddressModel addressInfo) {
                Log.i("AddressFetch", "Success: Total" +addressInfo.getEmail());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        addressModel.setServerId(addressInfo.getId());
                    }
                });
            }

            @Override
            public void error(final String error) {
                Log.i("AddressFetch", "failed");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), error, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}