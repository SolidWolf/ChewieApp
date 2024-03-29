package com.dango.commands

import adapters.CommandsAdapter
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.dango.commands.databinding.FragmentCommandsBinding
import models.CommandsModel

class CommandsFragment : Fragment() {

    private lateinit var viewModel: CommandsViewModel
    private var _binding: FragmentCommandsBinding? = null
    private var commandsAdapter: CommandsAdapter? = null
    private var commandsEditText: EditText? = null
    private var commandsList: ArrayList<CommandsModel>? = null
    private var filteredData: ArrayList<CommandsModel>? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel =
            ViewModelProvider(this).get(CommandsViewModel::class.java)

        _binding = FragmentCommandsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        commandsEditText = binding.commandEditText
        val textView: TextView = binding.noDataFoundTextView
        viewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        commandsList = arrayListOf(
            CommandsModel(
                "chewiebot",
                "New and improved bot - www.chewiemelodies.com Convert your channel points into chews & check out our new dango cards and achievements. Bankheist, arena & dueling still exists. Ask our mods how to trade/recycle cards.",
                3,
                false,
                "Text",
                "Viewer"
            ),
            CommandsModel(
                "chewieCoin",
                "https://www.rally.io/creator/CHEWS/",
                40,
                false,
                "Text",
                "Moderator"
            ),
            CommandsModel("cry", "!crying", 600, false, "Alias", "Viewer"),
            CommandsModel(
                "!dangos",
                "Those \"angry pig noses\" or \"plug\" plushies chewie has behind are actually lovely dangos, from the anime Clannad. Want to know how chewie got them? Check this clip where a man gets pounded by 60 dango plushies! https://youtu.be/Cyn5nqSsLYo",
                null,
                false,
                "Text",
                "Viewer"
            ),
            CommandsModel(
                "explainchews",
                "Chews are in-channel currency used for trading cards, minigames, etc. You can convert chew points to chews but not the other way around. Check !gainchews for how to get more chews.",
                18,
                true,
                "Text",
                "Moderator"
            ),
            CommandsModel(
                "crying",
                "Chewie has made {count} people cry chewieFeels",
                36518929,
                false,
                "Text",
                "Viewer"
            )
        )
        renderDataToRecyclerView(commandsList!!)
        setUpObserver()
        onClickListeners()

    }

    private fun setUpObserver() {
        viewModel.getFilteredData().observe(viewLifecycleOwner) {
            if (it.size > 0) {
                binding.noDataFoundTextView.visibility = View.GONE
                binding.commandsRecyclerView.visibility = View.VISIBLE
                renderDataToRecyclerView(it)
            } else {
                binding.commandsRecyclerView.visibility = View.GONE
                binding.noDataFoundTextView.visibility = View.VISIBLE
            }
        }
    }

    private fun onClickListeners() {
        binding.clearEditText.setOnClickListener {
            commandsEditText?.text?.clear()
            binding.clearEditText.visibility = View.GONE
        }
//        binding.filtersTextView.setOnClickListener {
//            Toast.makeText(activity, "Coming Soon ™", Toast.LENGTH_SHORT).show()
//        }
        commandsEditText?.addTextChangedListener(textWatcher)
    }

    private val textWatcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun afterTextChanged(s: Editable?) {
            if (s!!.isNotEmpty()) {
                binding.clearEditText.visibility = View.VISIBLE
                commandsEditText?.setCompoundDrawablesWithIntrinsicBounds(
                    R.drawable.search_icon_white,
                    0,
                    0,
                    0
                )
                val filteredList = viewModel.filterData(s.toString(), commandsList)
                viewModel.setFilterList(filteredList, commandsList, s)
            } else {
                commandsEditText?.setCompoundDrawablesWithIntrinsicBounds(
                    R.drawable.search_icon,
                    0,
                    0,
                    0
                )
                binding.clearEditText.visibility = View.GONE
                viewModel.postFiliteredCommandsList(commandsList)
            }
        }

    }

    private fun renderDataToRecyclerView(commandsList: ArrayList<CommandsModel>) {
        commandsAdapter = CommandsAdapter(requireActivity(), commandsList, viewModel)
        commandsAdapter?.let {
            binding.commandsRecyclerView.layoutManager = LinearLayoutManager(context)
            binding.commandsRecyclerView.adapter = it
            if (binding.commandsRecyclerView.itemDecorationCount == 0) {
                val rvDivider = activity?.let { it1 ->
                    DividerItemDecoration(it1, DividerItemDecoration.VERTICAL)
                }
                if (rvDivider != null) {
                    binding.commandsRecyclerView.addItemDecoration(rvDivider)
                }
            }
        }
    }

    override fun onPause() {
        super.onPause()
        binding.commandEditText.text.clear()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}