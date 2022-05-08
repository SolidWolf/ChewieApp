package adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.dango.commands.CommandsViewModel
import com.dango.commands.R
import models.CommandsModel

class CommandsAdapter(
    private var context: FragmentActivity,
    private var commandsList: ArrayList<CommandsModel>,
    private var viewModel: CommandsViewModel
) : RecyclerView.Adapter<CommandsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.command_layout, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val row = commandsList[position]

        holder.countTextView.text = if(row.useCount != null){
            row.useCount.toString()
        } else{
            ""
        }

        holder.commandTextView.text = row.commandName
        holder.permissionsTextView.text = row.permissionType
    }

    override fun getItemCount(): Int = commandsList.size

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        internal var commandTextView: TextView = itemView.findViewById(R.id.commandTextView)
        internal var countTextView: TextView = itemView.findViewById(R.id.countTextView)
        internal var permissionsTextView: TextView = itemView.findViewById(R.id.permissionsTextView)
    }


}