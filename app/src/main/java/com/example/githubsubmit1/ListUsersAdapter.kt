package com.example.githubsubmit1


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

@Suppress("DEPRECATION")
class ListUsersAdapter(private val listUsers: ArrayList<Users>): RecyclerView.Adapter<ListUsersAdapter.CardViewViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_user_row, parent,false)
        return CardViewViewHolder(view)
    }

    override fun onBindViewHolder(holder: CardViewViewHolder, position: Int) {
        val user = listUsers[position]

        Glide.with(holder.itemView.context)
                .load(user.profilePic)
                .apply(RequestOptions().override(250, 250))
                .into(holder.imgUserProfilePic)

        holder.tvUsername.text = user.Username
        holder.tvUserFullname.text = user.Name
        holder.tvCompany.text = user.Company
        holder.itemView.setOnClickListener{ onItemClickCallback.onItemClicked(listUsers[holder.adapterPosition])}
    }

    inner class CardViewViewHolder (itemView: View):RecyclerView.ViewHolder(itemView) {

        var tvUsername: TextView = itemView.findViewById(R.id.user_name)
        var tvUserFullname : TextView = itemView.findViewById(R.id.user_full_name)
        var tvCompany : TextView = itemView.findViewById(R.id.user_company)
        var imgUserProfilePic: ImageView = itemView.findViewById(R.id.user_profile_picture)

    }

    override fun getItemCount(): Int {
        return listUsers.size
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Users)
    }

}