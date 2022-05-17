package com.dango.leaderboard

import adapters.LeaderboardAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.dango.leaderboard.databinding.FragmentLeaderboardBinding
import models.LeaderboardModel


class LeaderboardFragment : Fragment() {

    private lateinit var songListViewModel: LeaderboardViewModel
    private var _binding: FragmentLeaderboardBinding? = null
    private var startColorArray:ArrayList<String> = ArrayList()
    private var endColorArray: ArrayList<String> = ArrayList()
    private var rankList: ArrayList<LeaderboardModel> = ArrayList()
    private var leaderboardAdapter: LeaderboardAdapter? = null

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
//        })
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


        renderDataToRecyclerView(startColorArray, endColorArray, rankList)

    }

    private fun renderDataToRecyclerView(
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}