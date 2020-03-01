package com.vmoskvyak.bowlingleague.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.RecyclerView.ItemAnimator
import com.vmoskvyak.bowlingleague.R
import com.vmoskvyak.bowlingleague.databinding.FragmentPlayersBinding
import com.vmoskvyak.bowlingleague.model.Player
import com.vmoskvyak.bowlingleague.resource.Resource
import com.vmoskvyak.bowlingleague.resource.Status
import com.vmoskvyak.bowlingleague.ui.adapter.PlayersAdapter
import com.vmoskvyak.bowlingleague.viewmodel.PlayersFragmentBindingModel
import com.vmoskvyak.bowlingleague.viewmodel.PlayersViewModel

class PlayersFragment : Fragment() {

    private lateinit var binding: FragmentPlayersBinding
    private lateinit var playersViewModel: PlayersViewModel

    private val playersAdapter =
        PlayersAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_players,
            container,
            false
        )

        binding.lifecycleOwner = viewLifecycleOwner
        binding.data = PlayersFragmentBindingModel()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        initViews()
    }

    private fun initViewModel() {
        playersViewModel = ViewModelProvider(this).get(PlayersViewModel::class.java)
        playersViewModel.status.observe(this, Observer<Resource<Player>> { value ->
            when(value.status) {
                Status.NO_PLAYERS -> binding.btnAddPlayer.isEnabled = false
                Status.MAX_NUMBER_OF_PLAYERS_REACHED -> {
                    Toast.makeText(context, "Max number of players reached", Toast.LENGTH_LONG).show()
                }
                else -> {
                    value.data?.let {
                        playersAdapter.addPlayer(it)
                    }
                    binding.btnStartGame.isEnabled = true
                }
            }
        })
    }

    private fun initViews() {
        val itemAnimator: ItemAnimator = DefaultItemAnimator()
        itemAnimator.apply {
            addDuration = 1000
            removeDuration = 1000
        }

        binding.rvPlayers.apply {
            adapter = playersAdapter
            setItemAnimator(itemAnimator)
        }

        binding.btnAddPlayer.setOnClickListener {
            binding.data?.name?.get().let { name ->
                name?.let { thisName ->
                    playersViewModel.addPlayer(Player(thisName))
                    binding.data?.name?.set("")
                }
            }
        }

        binding.btnStartGame.setOnClickListener {
            (activity as MainActivity).showFragment(GameFragment.newInstance(playersViewModel.playersList))
        }
    }
}