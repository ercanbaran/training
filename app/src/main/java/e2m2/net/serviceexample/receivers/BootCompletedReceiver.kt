package e2m2.net.serviceexample.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import e2m2.net.serviceexample.utils.ServiceUtils

class BootCompletedReceiver:BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        println("----> Boot completed event received")
        if(context != null)
        {
            ServiceUtils.startTestService(context!!);
        }
    }
}