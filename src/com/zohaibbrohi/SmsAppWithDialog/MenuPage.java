package com.zohaibbrohi.SmsAppWithDialog;


import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.KeyEvent;

public class MenuPage extends Activity {




    private static MenuPage m_instance = null;

    /**
     * Get the singleton instance of this class
     *
     * @return MenuPage
     */
    public static MenuPage getInstance() {
        if (m_instance == null) {
            m_instance = new MenuPage();
        }
        return m_instance;
    }

    /**
     * Constructor, default
     */
    public MenuPage() {
        m_instance = this;

    }


    /**
     * Called when the activity is first created.
     */
    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        //getWindow().setWindowAnimations(android.R.style.Animation_Toast);
        registerBroadcastReceiver();

        setContentView(R.layout.main);
        
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            onTerminate();
        }
        return super.onKeyDown(keyCode, event);
    }

    // Register BroadcastReceiver
    private void registerBroadcastReceiver() {
        PackageManager pm = getApplicationContext().getPackageManager();
        pm.setComponentEnabledSetting(
                new ComponentName(this, SmsReceiver.class),
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP);

    }

    /**
     * unRegister BroadcastReceiver
     */
    private void unregisterBroadcastReceiver() {

        PackageManager pm = getApplicationContext().getPackageManager();
        pm.setComponentEnabledSetting(
                new ComponentName(this, SmsReceiver.class),
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP);
    }


    /**
     * clean up application global
     */
    public void onTerminate() {
        unregisterBroadcastReceiver();

        onStop();
        onDestroy();
        finish();          // /// close the application
        System.runFinalizersOnExit(true);
        System.exit(0);
    }


    public void showReceivedMessage(String message, final String phone) {
    	Intent in = new Intent(this,SmsAppWithDialogActivity.class);
        in.putExtra("num", phone);
        in.putExtra("msg", message);
    	startActivity(in);
    	
    }

}
