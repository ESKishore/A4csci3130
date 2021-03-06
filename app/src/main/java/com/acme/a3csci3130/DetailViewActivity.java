package com.acme.a3csci3130;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class DetailViewActivity extends Activity {

    private EditText businessNoField, nameField, addressField;
    private Spinner spinnerPrimaryBus, spinnerProvince;
    private TextView tvErr;
    Contact receivedBusinessInfo;
    private MyApplicationData appState;
    MiscTasks miscTasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);

        appState = ((MyApplicationData) getApplicationContext());
        receivedBusinessInfo = (Contact)getIntent().getSerializableExtra("Contact");
        miscTasks = new MiscTasks();
        nameField = findViewById(R.id.name);
        businessNoField = findViewById(R.id.businessNum);
        addressField = findViewById(R.id.etAddress);
        spinnerPrimaryBus = findViewById(R.id.spinnerPrimaryBus);
        spinnerProvince = findViewById(R.id.spinnerProv);
        tvErr = findViewById(R.id.tvErr);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.primary_business, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPrimaryBus.setAdapter(adapter);
        ArrayAdapter<CharSequence> adapterProv = ArrayAdapter.createFromResource(this,
                R.array.provinces, android.R.layout.simple_spinner_item);
        adapterProv.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerProvince.setAdapter(adapterProv);

        if(receivedBusinessInfo != null){
            nameField.setText(receivedBusinessInfo.name);
            businessNoField.setText(receivedBusinessInfo.businessNum);
            addressField.setText(receivedBusinessInfo.address);
            if(receivedBusinessInfo.primaryBusiness != null){
               int primaryBusPos = adapter.getPosition(receivedBusinessInfo.primaryBusiness);
               spinnerPrimaryBus.setSelection(primaryBusPos);
            }
            if(receivedBusinessInfo.province != null) {
                int provincePos = adapterProv.getPosition(receivedBusinessInfo.province);
                spinnerProvince.setSelection(provincePos);
            }
        }
    }

    /*
    * This method pushes the updated business information
    * to the firebase database
    *
    * @param    v       the view in the current activity
    */
    public void updateContact(View v){
        //TODO: Update contact funcionality
        ArrayList<String> chkListArray = new ArrayList<>();

        //each entry needs a unique ID
        String ID = receivedBusinessInfo.uid;
        String name = nameField.getText().toString();
        String businessNum = businessNoField.getText().toString();
        String primaryBusiness = spinnerPrimaryBus.getSelectedItem().toString();
        String address = addressField.getText().toString();
        String province = spinnerProvince.getSelectedItem().toString();

        chkListArray = miscTasks.performChecks(name, businessNum, primaryBusiness, address, province);

        if (chkListArray.get(0).equals("All checks passed!")) {
            Contact business = new Contact(ID, businessNum, name, primaryBusiness, address, province);
            appState.firebaseReference.child(ID).setValue(business);
            finish();
        }
        else{
            tvErr.setText(chkListArray.toString());
        }

    }

    /*
    * This method deletes the information of a particular
    * business from the Firebase database
    *
    * @param    v       the view in the current activity
    */

    public void eraseContact(View v)
    {
        //TODO: Erase contact functionality
        String ID = receivedBusinessInfo.uid;
        appState.firebaseReference.child(ID).removeValue();
        finish();
    }
}
