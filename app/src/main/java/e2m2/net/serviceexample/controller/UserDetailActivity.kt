package e2m2.net.serviceexample.controller

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.*
import com.karumi.dexter.listener.single.PermissionListener
import e2m2.net.serviceexample.R
import e2m2.net.serviceexample.model.UserModel
import kotlinx.android.synthetic.main.activity_user_detail.*

class UserDetailActivity : AppCompatActivity() {

    companion object {
        lateinit var userModel: UserModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_detail)

        txtName.text = userModel?.name.firstName
        txtEmail.text = userModel?.userEmail
        Glide.with(this).load(userModel.picture.large).into(imgUser)
        txtPhone.text = userModel?.phone

        txtPhone.setOnClickListener {
            val intent = Intent(Intent.ACTION_CALL, Uri.parse(("tel:" + userModel.phone ) ))
            startActivity(intent)
        }

        Dexter
            .withActivity(this)
            .withPermission(Manifest.permission.CALL_PHONE)
            .withListener(object :PermissionListener {
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

            }).withErrorListener(object : PermissionRequestErrorListener{
                override fun onError(error: DexterError?) {
                    Log.d("-------->", "permission error ")
                }

            })
            .check()

    }


    override fun onResume() {
        super.onResume()
    }

}
