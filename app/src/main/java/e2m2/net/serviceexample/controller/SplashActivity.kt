package e2m2.net.serviceexample.controller

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import e2m2.net.serviceexample.R

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        var runnable = Runnable { disappear() }
        val handler = Handler()
        handler.postDelayed(runnable,2000)
    }


    fun disappear()
    {
        var intent = Intent(this, UserListActivity::class.java)
        startActivity(intent)
        finish()
    }
}
