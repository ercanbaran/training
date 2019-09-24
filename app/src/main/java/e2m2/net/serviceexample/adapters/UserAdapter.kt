package e2m2.net.serviceexample.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import e2m2.net.serviceexample.R
import e2m2.net.serviceexample.model.UserModel


interface UserListener
{
    fun onUserClicked(model: UserModel)
}

class UserAdapter(ctx: Context, set: ArrayList<UserModel>, var listener:UserListener? = null) :
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {


    private var context: Context
    private var myDataset : ArrayList<UserModel>

    class UserViewHolder(val mainContainer: View) : RecyclerView.ViewHolder(mainContainer)

    init {
        context = ctx
        myDataset = set
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UserAdapter.UserViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_user, parent, false) as View

        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val model: UserModel = myDataset.get(position)
        val lblUsername = holder.mainContainer.findViewById<TextView>(R.id.txtUser)
        lblUsername.text = model.name.firstName

        val userImage = holder.mainContainer.findViewById<ImageView>(R.id.imgUser)
        Glide.with(context).load(model.picture.thumbnail).into(userImage)

        holder.mainContainer.setOnClickListener {
            listener?.onUserClicked(model)
        }

    }

    override fun getItemCount() = myDataset.size
}