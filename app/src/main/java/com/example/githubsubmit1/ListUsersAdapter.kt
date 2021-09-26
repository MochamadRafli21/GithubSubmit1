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
class ListUsersAdapter(private val listUsers: ArrayList<ItemsItem>): RecyclerView.Adapter<ListUsersAdapter.CardViewViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CardViewViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_user_row, parent,false))

    override fun onBindViewHolder(holder: CardViewViewHolder, position: Int) {
        val user = listUsers[position]

        Glide.with(holder.itemView.context)
                .load(user.avatarUrl)
                .apply(RequestOptions().override(250, 250))
                .into(holder.imgUserProfilePic)

        holder.tvUsername.text = user.login
        holder.tvUserUrl.text = user.htmlUrl
        holder.tvCompany.text = user.organizationsUrl
        holder.itemView.setOnClickListener{ onItemClickCallback.onItemClicked(listUsers[holder.adapterPosition])}
    }

    inner class CardViewViewHolder (itemView: View):RecyclerView.ViewHolder(itemView) {
        var tvUsername: TextView = itemView.findViewById(R.id.user_name)
        var tvUserUrl : TextView = itemView.findViewById(R.id.user_html)
        var tvCompany : TextView = itemView.findViewById(R.id.user_company)
        var imgUserProfilePic: ImageView = itemView.findViewById(R.id.user_profile_picture)
    }

    override fun getItemCount() = listUsers.size

    interface OnItemClickCallback {
        fun onItemClicked(data: ItemsItem)
    }

    fun setUserData(data: List<ItemsItem>){
        listUsers.clear()
        listUsers.addAll(data)
        notifyDataSetChanged()
    }

}