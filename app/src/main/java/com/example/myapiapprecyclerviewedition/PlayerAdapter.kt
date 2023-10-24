package com.example.myapiapprecyclerviewedition

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class PlayerAdapter(private val playerList: MutableList<PlayerDetails>) : RecyclerView.Adapter<PlayerAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val playerImage: ImageView
        val playerName: TextView
        val teamName: TextView

        init {
            // Find our RecyclerView item's ImageView for future use
            playerImage = view.findViewById(R.id.player_image)
            playerName = view.findViewById(R.id.Name)
            teamName = view.findViewById(R.id.Team)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.player_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(holder.itemView)
            .load(playerList[position].photo)
            .centerCrop()
            .into(holder.playerImage)
        val nameView = holder.playerName
        nameView.text = playerList[position].name
        val teamView = holder.teamName
        teamView.text = playerList[position].team


    }

    override fun getItemCount() = playerList.size
}