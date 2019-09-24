package e2m2.net.serviceexample.controller

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.squareup.moshi.Types
import e2m2.net.serviceexample.R
import e2m2.net.serviceexample.adapters.UserAdapter
import e2m2.net.serviceexample.adapters.UserListener
import e2m2.net.serviceexample.model.UserModel
import e2m2.net.userphonebook.utils.moshi
import e2m2.net.userphonebook.utils.sumNumbers
import kotlinx.android.synthetic.main.activity_user_list.*
import org.json.JSONObject




class UserListActivity : AppCompatActivity() {

    val BASE_URL = "https://randomuser.me/api"
    val TAG = "------->"

    var userList:ArrayList<UserModel> = ArrayList()
    lateinit var viewAdapter: UserAdapter
    lateinit var viewManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_list)

        viewAdapter = UserAdapter(this,userList)
        viewManager = LinearLayoutManager(this)

        listUser.adapter = viewAdapter
        listUser.layoutManager = viewManager
        listUser.setHasFixedSize(true)

        loadData()



    }


    fun loadData()
    {
        AndroidNetworking
            .get(BASE_URL)
            .addQueryParameter("results","50")
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject?) {
                    val type = Types.newParameterizedType(List::class.java, UserModel::class.java)
                    val adapter = moshi.adapter<ArrayList<UserModel>>(type)
                    userList = adapter.fromJson(response?.getString("results")) as ArrayList<UserModel>

                    viewAdapter = UserAdapter(this@UserListActivity,userList , object : UserListener {
                        override fun onUserClicked(model: UserModel) {
                            //Log.d(TAG,model.name.firstName)

                            val intent = Intent(this@UserListActivity,UserDetailActivity::class.java)
                            UserDetailActivity.userModel = model
                            startActivity(intent)
                        }

                    })
                    listUser.adapter = viewAdapter

                    //viewAdapter.notifyDataSetChanged()

                    //Log.d(TAG,userList.get(0).name.first)
                }

                override fun onError(anError: ANError?) {
                }

            })
    }

}
