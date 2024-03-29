package com.zohaibbrohi.SmsAppWithDialog;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.telephony.SmsMessage;
import android.widget.Toast;


public class SmsReceiver extends BroadcastReceiver {

    public void onReceive(Context context, Intent intent) {
        //---get the SMS message passed in---
        Bundle bundle = intent.getExtras();
        SmsMessage[] msgs = null;
        String str = "";
        if (bundle != null) {
            //---retrieve the SMS message received---
            Object[] pdus = (Object[]) bundle.get("pdus");
            msgs = new SmsMessage[pdus.length];
            for (int i = 0; i < msgs.length; i++) {
                msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                str += "SMS from " + msgs[i].getOriginatingAddress();
                str += " :";
                str += msgs[i].getMessageBody().toString();
                str += "\n";
            }
            String replyPhone = msgs[0].getOriginatingAddress();
            String request = msgs[0].getMessageBody().toString();
            //---display the new SMS message---
            //Toast.makeText(context, str + "    " + replyPhone + "\n" + request, Toast.LENGTH_LONG).show();

            Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
            vibrator.vibrate(1000);

            MenuPage.getInstance().showReceivedMessage(request,replyPhone);
        }
    }
}