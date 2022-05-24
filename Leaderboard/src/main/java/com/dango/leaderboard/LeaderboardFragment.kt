package com.dango.leaderboard

import adapters.LeaderboardAdapter
import adapters.SeasonAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dango.leaderboard.databinding.FragmentLeaderboardBinding
import models.LeaderboardModel


class LeaderboardFragment : Fragment() {

    private lateinit var songListViewModel: LeaderboardViewModel
    private var _binding: FragmentLeaderboardBinding? = null
    private var startColorArray:ArrayList<String> = ArrayList()
    private var endColorArray: ArrayList<String> = ArrayList()
    private var rankList: ArrayList<LeaderboardModel> = ArrayList()
    private var leaderboardAdapter: LeaderboardAdapter? = null
    private var seasonsAdapter: SeasonAdapter? = null
    private var seasonsList: ArrayList<String> = ArrayList()
    companion object{

        var isSeasonsExpanded = false
    }

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        songListViewModel =
            ViewModelProvider(this).get(LeaderboardViewModel::class.java)

        _binding = FragmentLeaderboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        val textView: TextView = binding.
//        songListViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })x
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        startColorArray = arrayListOf("#7abfbc", "#4fa3a9", "#8baddc", "#7697c4","#478bca", "#5871b6", "#6353a0")
        endColorArray = arrayListOf("#4fa3a9", "#8baddc", "#7697c4", "#478bca", "#5871b6", "#6353a0", "#413669")
        rankList.add(LeaderboardModel("RALIU31","52,462", 4))
        rankList.add(LeaderboardModel("LOSTMYPOCKET","50,500", 5))
        rankList.add(LeaderboardModel("SPACEFIT","50,050", 6))
        rankList.add(LeaderboardModel("RESTLESSSOUL","47,921", 7))
        rankList.add(LeaderboardModel("THORMODO","46,500", 8))
        rankList.add(LeaderboardModel("GENERALABE","42,350", 9))
        rankList.add(LeaderboardModel("KIRIMIHELLGREA","41,001", 10))

        seasonsList.add("May 27, 2022")
        seasonsList.add("Feb 27, 2022")
        seasonsList.add("Nov 27, 2021")


        binding.selectedSeasonTextView.text = seasonsList[0]

        onClickListeners()

        renderDataToSeasonsRecyclerView(seasonsList, binding.selectedSeasonTextView, binding.seasonRecyclerView, binding.collapseIcon, binding.expandIcon)
        renderDataToLeaderboardRecyclerView(startColorArray, endColorArray, rankList)

    }

    private fun onClickListeners() {
        binding.seasonDropdown.setOnClickListener {
            if(!isSeasonsExpanded){
                binding.seasonRecyclerView.visibility = View.VISIBLE
                binding.expandIcon.visibility = View.GONE
                binding.collapseIcon.visibility = View.VISIBLE
            } else {
                binding.seasonRecyclerView.visibility = View.GONE
                binding.expandIcon.visibility = View.VISIBLE
                binding.collapseIcon.visibility = View.GONE
            }
            isSeasonsExpanded = !isSeasonsExpanded
        }
    }

    private fun renderDataToLeaderboardRecyclerView(
        startColorArray: ArrayList<String>,
        endColorArray: ArrayList<String>,
        rankList: ArrayList<LeaderboardModel>
    ) {
        leaderboardAdapter = LeaderboardAdapter(requireActivity(), startColorArray, endColorArray, rankList)
        leaderboardAdapter?.let {
            binding.leaderboardRecyclerView.layoutManager = LinearLayoutManager(context)
            binding.leaderboardRecyclerView.adapter = it
        }
    }

    private fun renderDataToSeasonsRecyclerView(
        seasonsList: ArrayList<String>,
        selectedSeasonTextView: TextView,
        seasonRecyclerView: RecyclerView,
        collapseIcon: ImageView,
        expandIcon: ImageView
    ) {
        seasonsAdapter = SeasonAdapter(requireActivity(), seasonsList, selectedSeasonTextView, seasonRecyclerView, collapseIcon, expandIcon)
        seasonsAdapter?.let {
            binding.seasonRecyclerView.layoutManager = LinearLayoutManager(context)
            binding.seasonRecyclerView.adapter = it
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}