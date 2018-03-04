package com.acme.a3csci3130;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class DetailViewActivity extends Activity {

    private EditText businessNoField, nameField, addressField;
    private Spinner spinnerPrimaryBus, spinnerProvince;
    Contact receivedBusinessInfo;
    private MyApplicationData appState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);

        appState = ((MyApplicationData) getApplicationContext());
        receivedBusinessInfo = (Contact)getIntent().getSerializableExtra("Contact");

        nameField = findViewById(R.id.name);
        businessNoField = findViewById(R.id.businessNum);
        addressField = findViewById(R.id.etAddress);
        spinnerPrimaryBus = findViewById(R.id.spinnerPrimaryBus);
        spinnerProvince = findViewById(R.id.spinnerProv);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.primary_business, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPrimaryBus.setAdapter(adapter);
        ArrayAdapter<CharSequence> adapterProv = ArrayAdapter.createFromResource(this,
                R.array.provinces, android.R.layout.simple_spinner_item);
        adapterProv.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerProvince.setAdapter(adapterProv);

//        System.out.print(receivedBusinessInfo.toString());
        Log.e("receivedBusInfo", receivedBusinessInfo.toString());
        if(receivedBusinessInfo != null){
            nameField.setText(receivedBusinessInfo.name);
            businessNoField.setText(receivedBusinessInfo.businessNum);
            addressField.setText(receivedBusinessInfo.address);
            if(receivedBusinessInfo.primaryBusiness != null){
               int primaryBusPos = adapter.getPosition(receivedBusinessInfo.primaryBusiness);
               Log.e("primaryBusPos", primaryBusPos+"");
               spinnerPrimaryBus.setSelection(primaryBusPos);
            }
            if(receivedBusinessInfo.province != null) {
                int provincePos = adapterProv.getPosition(receivedBusinessInfo.province);
                Log.e("provincePos", provincePos+"");
                spinnerProvince.setSelection(provincePos);
            }
        }
    }

    public void updateContact(View v){
        //TODO: Update contact funcionality
        //each entry needs a unique ID
        String ID = receivedBusinessInfo.uid;
        String name = nameField.getText().toString();
        String businessNum = businessNoField.getText().toString();
        String primaryBusiness = spinnerPrimaryBus.getSelectedItem().toString();
        String address = addressField.getText().toString();
        String province = spinnerProvince.getSelectedItem().toString();
        Contact business = new Contact(ID, businessNum, name, primaryBusiness, address, province);
        if((name.length() >= 2 ) && (name.length() <= 48 )){
            if(businessNum.length() == 9){
                if(!primaryBusiness.isEmpty()){
                    if(address.length() < 50){
                        appState.firebaseReference.child(ID).setValue(business);
                        finish();
                    }
                    else{
                        Toast.makeText(this, "Address should be less than 50 characters!", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    //primary business
                    Toast.makeText(this, "Primary business value must be selected!", Toast.LENGTH_SHORT).show();
                }
            }
            else{
                Toast.makeText(this, "Business Number should be equal to 9 characters!", Toast.LENGTH_SHORT).show();
            }
        }
        else{
            Toast.makeText(this, "Name must be between 2 and 4 characters!", Toast.LENGTH_SHORT).show();
        }

    }

    public void eraseContact(View v)
    {
        //TODO: Erase contact functionality
        String ID = receivedBusinessInfo.uid;
        appState.firebaseReference.child(ID).removeValue();
        finish();
    }
}
