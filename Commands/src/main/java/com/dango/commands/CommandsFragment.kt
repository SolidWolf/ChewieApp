package com.dango.commands

import adapters.CommandsAdapter
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
import com.dango.commands.databinding.FragmentCommandsBinding
import models.CommandsModel

class CommandsFragment : Fragment() {

    private lateinit var commandsViewModel: CommandsViewModel
    private var _binding: FragmentCommandsBinding? = null
    private var commandsAdapter: CommandsAdapter? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        commandsViewModel =
            ViewModelProvider(this).get(CommandsViewModel::class.java)

        _binding = FragmentCommandsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textSlideshow
        commandsViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val commandsList: ArrayList<CommandsModel> = arrayListOf(
            CommandsModel("chewiebot", "test1", 3, false, "Text", "Viewer"),
            CommandsModel("chewieCoin", "test1", 40, false, "Text", "Moderator"),
            CommandsModel("cry", "test1", 600, false, "Alias", "Viewer"),
            CommandsModel("dango", "test1", null, false, "Alias", "Viewer"),
            CommandsModel("explainchews", "test1", 18, true, "Text", "Moderator"),
            CommandsModel("crying", "test1", 36518929, false, "Text", "Viewer")
        )
        renderDatatoRecyclerView(commandsList)

        onClickListeners()

    }

    private fun onClickListeners(){

    }

    private fun renderDatatoRecyclerView(commandsList: ArrayList<CommandsModel>){
        commandsAdapter = CommandsAdapter(requireActivity(), commandsList, commandsViewModel)
        commandsAdapter?.let{
            binding.commandsRecyclerView.layoutManager = LinearLayoutManager(context)
            binding.commandsRecyclerView.adapter = it
            if(binding.commandsRecyclerView.itemDecorationCount == 0){
                val rvDivider = activity?.let{ it1 ->
                    DividerItemDecoration(it1, DividerItemDecoration.VERTICAL)
                }
                if(rvDivider != null){
                    binding.commandsRecyclerView.addItemDecoration(rvDivider)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}