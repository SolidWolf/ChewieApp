package com.dango.chewieapp.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.dango.chewieapp.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null
    private val GLORIOUSLEADER = "chewiemelodies"
    private val TWITCHURL = "https://www.twitch.tv/"
    private val YOUTUBEURL = "https://www.youtube.com/"
    private val SPOTIFYURL = "https://open.spotify.com/artist/2dbjQX4XbIMrb5kayolqSZ?si=KsrM6Hn_SpiWj44_vPZ1kw"
    private val PATREONURL = "https://www.patreon.com/"
    private val DISCORDURL = "https://discordapp.com/invite/"

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this)[HomeViewModel::class.java]

        _binding = FragmentHomeBinding.inflate(inflater, container, false)

//        val textView: TextView = binding.textHome
//        homeViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onClickListeners()
    }

    private fun onClickListeners(){
        binding.twitchButton.setOnClickListener {
            openApp(TWITCHURL+GLORIOUSLEADER)
        }
        binding.youtubeButton.setOnClickListener {
            openApp(YOUTUBEURL+GLORIOUSLEADER)
        }
        binding.spotifyButton.setOnClickListener {
            openApp(SPOTIFYURL)
        }
        binding.patreonButton.setOnClickListener {
            openApp(PATREONURL+GLORIOUSLEADER)
        }
        binding.discordButton.setOnClickListener{
            openApp(DISCORDURL+GLORIOUSLEADER)
        }
    }

    private fun openApp(url: String){
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}