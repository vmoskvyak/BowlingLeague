package com.vmoskvyak.bowlingleague.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vmoskvyak.bowlingleague.databinding.PlayerItemViewBinding
import com.vmoskvyak.bowlingleague.model.Player

class PlayersAdapter : RecyclerView.Adapter<PlayersAdapter.PlayersViewHolder>() {

    private val items = ArrayList<Player>()

    fun addPlayer(player: Player) {
        items.add(player)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayersViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = PlayerItemViewBinding.inflate(inflater, parent, false)

        return PlayersViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: PlayersViewHolder, position: Int) = holder.bind(items[position])

    inner class PlayersViewHolder(private val binding: PlayerItemViewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Player) {
            binding.item = item
            binding.executePendingBindings()
        }
    }
}