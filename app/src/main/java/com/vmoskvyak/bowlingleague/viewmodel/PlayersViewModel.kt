package com.vmoskvyak.bowlingleague.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vmoskvyak.bowlingleague.model.Player
import com.vmoskvyak.bowlingleague.resource.Resource

class PlayersViewModel : ViewModel() {
    val playersList = ArrayList<Player>()
    val status = MutableLiveData<Resource<Player>>()

    init {
        status.value = Resource.noPlayers()
    }

    fun addPlayer(player: Player) {
        if (playersList.size >= MAX_NUMBER_OF_PLAYERS) {
            status.value = Resource.maxNumberOfPlayersReached(player)
            return
        }

        playersList.add(player)
        status.value = Resource.success(player)
    }

    companion object {
        private const val MAX_NUMBER_OF_PLAYERS = 6
    }
}