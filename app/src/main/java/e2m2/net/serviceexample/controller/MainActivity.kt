package e2m2.net.serviceexample.controller

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import e2m2.net.serviceexample.R
import android.content.*
import android.util.Log
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.*
import com.karumi.dexter.listener.single.PermissionListener
import e2m2.net.serviceexample.utils.ServiceUtils




class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //ServiceUtils.startTestService(context = this)


//        var intent = Intent(this, UserListActivity::class.java)
//        startActivity(intent)
//        finish()

//        inputText.addTextChangedListener(object: TextWatcher{
//            override fun afterTextChanged(s: Editable?) {
//            }
//
//            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
//            }
//
//            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//                clearError()
//                resultText = s.toString()
//            }
//        })
//        return

        getLocationPermission()

    }

    override fun onResume() {
        val intentFilter = IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED)
        registerReceiver(airplaneModeReceiver,intentFilter);
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }


    var airplaneModeReceiver = object : BroadcastReceiver()
    {
        override fun onReceive(context: Context?, intent: Intent?) {
            val state = intent?.getBooleanExtra("state", false)
            println("---> Airplane mode changed = " +  state)
        }

    }


    fun getLocationPermission()
    {
        Dexter
            .withActivity(this)
            .withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
            .withListener(object : PermissionListener {
                override fun onPermissionGranted(response: PermissionGrantedResponse?) {
                    Log.d("-------->", "permission granted")
                    ServiceUtils.startTestService(context = applicationContext)
                }

                override fun onPermissionRationaleShouldBeShown(
                    permission: PermissionRequest?,
                    token: PermissionToken?
                ) {
                }

                override fun onPermissionDenied(response: PermissionDeniedResponse?) {
                    Log.d("-------->", "permission denied")
                }

            }).withErrorListener(object : PermissionRequestErrorListener {
                override fun onError(error: DexterError?) {
                    Log.d("-------->", "permission error ")
                }

            })
            .check()
    }


    fun getPhonePermission()
    {
        Dexter
            .withActivity(this)
            .withPermission(Manifest.permission.CALL_PHONE)
            .withListener(object : PermissionListener {
                override fun onPermissionGranted(response: PermissionGrantedResponse?) {
                    Log.d("-------->", "permission granted")
                }

                override fun onPermissionRationaleShouldBeShown(
                    permission: PermissionRequest?,
                    token: PermissionToken?
                ) {
                }

                override fun onPermissionDenied(response: PermissionDeniedResponse?) {
                    Log.d("-------->", "permission denied")
                }

            }).withErrorListener(object : PermissionRequestErrorListener {
                override fun onError(error: DexterError?) {
                    Log.d("-------->", "permission error ")
                }

            })
            .check()
    }
}
