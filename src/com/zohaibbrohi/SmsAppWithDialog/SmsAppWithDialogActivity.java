package com.zohaibbrohi.SmsAppWithDialog;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class SmsAppWithDialogActivity extends Activity {
	TextView txt_data = null;
	Button btn_cancel = null;
	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maindialog);
        
        Bundle extras = getIntent().getExtras(); 
        String num = extras.getString("num");
        String msg = extras.getString("msg");
    
        txt_data = (TextView) findViewById(R.id.TextView01);
        btn_cancel = (Button) findViewById(R.id.Button01);
        txt_data.setText("Number " + num + "\nMsg " + msg);
        btn_cancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
    }
}