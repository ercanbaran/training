package e2m2.net.serviceexample.utils

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context
import e2m2.net.serviceexample.services.TestService

class ServiceUtils
{
    companion object {

        fun startTestService(context: Context)
        {
            val serviceComponent = ComponentName(context, TestService::class.java)
            val builder = JobInfo.Builder(0, serviceComponent)
            builder.setMinimumLatency((1 * 1000).toLong()) // wait at least
            builder.setOverrideDeadline((3 * 1000).toLong()) // maximum delay
            builder.setPersisted(true)
            //builder.setPeriodic(10000)//min 15 minutes
            //builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY); // require unmetered network
            //builder.setRequiresDeviceIdle(true); // device should be idle
            //builder.setRequiresCharging(false); // we don't care if the device is charging or not
            val jobScheduler = context.getSystemService(JobScheduler::class.java)
            jobScheduler.schedule(builder.build())
        }
    }
}