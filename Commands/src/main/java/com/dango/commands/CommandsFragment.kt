package com.dango.commands

import CommandsApiInterface
import adapters.CommandsAdapter
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.dango.commands.databinding.FragmentCommandsBinding
import models.CommandsApiModelItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CommandsFragment : Fragment() {

    private lateinit var viewModel: CommandsViewModel
    private var _binding: FragmentCommandsBinding? = null
    private var commandsAdapter: CommandsAdapter? = null
    private var commandsEditText: EditText? = null
    private var commandsList: ArrayList<CommandsApiModelItem>? = null
    private var filteredData: ArrayList<CommandsApiModelItem>? = null


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

        getCommandsList()
        setUpObserver()
        onClickListeners()
    }

    private fun getCommandsList() {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(CommandsApiInterface::class.java)
        val retrofitData = retrofitBuilder.getCommandsList()
        retrofitData.enqueue(object : Callback<List<CommandsApiModelItem>?> {
            override fun onResponse(
                call: Call<List<CommandsApiModelItem>?>,
                response: Response<List<CommandsApiModelItem>?>
            ) {
                commandsList = response.body()!! as ArrayList<CommandsApiModelItem>
                commandsList?.sortBy { it.commandName }

                viewModel.postFilteredCommandsList(commandsList)
            }

            override fun onFailure(call: Call<List<CommandsApiModelItem>?>, t: Throwable) {
                Log.d("API FAILED", t.message!!)
            }
        })
    }


    private fun setUpObserver() {
        viewModel.getFilteredData().observe(viewLifecycleOwner) {
            if (it != null && it.size > 0) {
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
                viewModel.postFilteredCommandsList(commandsList)
            }
        }

    }

    private fun renderDataToRecyclerView(commandsList: ArrayList<CommandsApiModelItem>) {
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

    companion object {
        private const val BASE_URL = "https://chewiemelodies.com/"
    }
}