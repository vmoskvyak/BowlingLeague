package com.vmoskvyak.bowlingleague.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.vmoskvyak.bowlingleague.R
import com.vmoskvyak.bowlingleague.databinding.FragmentGameBinding
import com.vmoskvyak.bowlingleague.logic.GameFlow
import com.vmoskvyak.bowlingleague.model.Player

class GameFragment : Fragment() {

    private lateinit var binding: FragmentGameBinding
    private var gameFlow: GameFlow? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_game,
            container,
            false
        )

        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val playersList = arguments?.getParcelableArrayList<Player>(PLAYERS_LIST_KEY)

        gameFlow = playersList?.let { GameFlow(it) }

        initViews()
    }

    private fun initViews() {
        binding.btnThrow.setOnClickListener {
            gameFlow?.addThrowResult(binding.etScore.text.toString().toInt())
            binding.tvResult.text = gameFlow?.getCurrentPlayerScore()?.toString()
        }
    }

    companion object {
        private const val PLAYERS_LIST_KEY = "players.list.key"

        fun newInstance(players: ArrayList<Player>): Fragment {
            val gameFragment = GameFragment()
            gameFragment.arguments = Bundle().apply {
                putParcelableArrayList(PLAYERS_LIST_KEY, players)
            }

            return gameFragment
        }
    }
}