package jszum.telephony;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class smsAnswer extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();

        String sender;

        SmsMessage[] messages;

        String msg = "";
        if(bundle != null){
            Object[] pdus = (Object[]) bundle.get("pdus");
            messages = new SmsMessage[pdus.length];



            for(int i = 0; i < messages.length; i++){
                messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                msg = messages[i].getMessageBody();
                sender = messages[i].getOriginatingAddress();
            }
            sender = messages[0].getOriginatingAddress();

            Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
            sendMessage(sender, "I cannot write right now. I will call you back. JSzumega");
        }
    }

    private void sendMessage(String number, String message) {
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(number, null, message, null, null);
    }
}
