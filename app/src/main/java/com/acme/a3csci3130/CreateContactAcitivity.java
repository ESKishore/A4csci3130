package com.acme.a3csci3130;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class CreateContactAcitivity extends Activity {

    private Button submitButton;
    private EditText nameField, businessNumField, addressField;
    private Spinner spinnerBusiness, spinnerProvince;
    private TextView tvErrors;
    private MyApplicationData appState;
    MiscTasks miscTasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_contact_acitivity);
        //Get the app wide shared variables
        appState = ((MyApplicationData) getApplicationContext());

        miscTasks = new MiscTasks();

        submitButton = findViewById(R.id.submitButton);
        nameField = findViewById(R.id.name);
        businessNumField = findViewById(R.id.etBusNum);
        addressField = findViewById(R.id.etAddress);

        spinnerBusiness = findViewById(R.id.spinnerPrimaryBusiness);
        spinnerProvince = findViewById(R.id.spinnerProvince);

        tvErrors = findViewById(R.id.tvErrors);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.primary_business, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerBusiness.setAdapter(adapter);
        ArrayAdapter<CharSequence> adapterProv = ArrayAdapter.createFromResource(this,
                R.array.provinces, android.R.layout.simple_spinner_item);
        adapterProv.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerProvince.setAdapter(adapterProv);

    }

    public void submitInfoButton(View v) {
        //each entry needs a unique ID
        String ID = appState.firebaseReference.push().getKey();
        String name = nameField.getText().toString();
//        String email = emailField.getText().toString();
        String businessNum = businessNumField.getText().toString();
        String primaryBusiness = spinnerBusiness.getSelectedItem().toString();
        String address = addressField.getText().toString();
        String province = spinnerProvince.getSelectedItem().toString();

        ArrayList<String> arrayListChk = new ArrayList<>();

        arrayListChk = miscTasks.performChecks(name, businessNum, primaryBusiness, address, province);
        if (arrayListChk.get(0).equals("All checks passed!")) {
            Contact business = new Contact(ID, businessNum, name, primaryBusiness, address, province);
            appState.firebaseReference.child(ID).setValue(business);
            finish();
        }
        else
        {
            tvErrors.setText(arrayListChk.toString());
        }

    }
}
