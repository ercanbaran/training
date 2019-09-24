package e2m2.net.serviceexample.services

import android.app.job.JobParameters
import android.app.job.JobService
import e2m2.net.serviceexample.utils.ServiceUtils
import io.nlopez.smartlocation.SmartLocation

class TestService : JobService() {
    override fun onStopJob(params: JobParameters?): Boolean {
        println("JOB Service End")
        return false
    }

    override fun onStartJob(params: JobParameters?): Boolean {
        println("JOB Service Started")

//        SmartLocation.with(applicationContext).location()
//            .oneFix()
//            .start {
//                println("----> got location = " + it.toString() )
//                jobFinished(params,true)
//
//            }
        ServiceUtils.startTestService(applicationContext)
        return true
    }
}