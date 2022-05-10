package adapters

import android.app.ActionBar
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
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
    private var dName: TextView? = null
    private var dType: TextView? = null
    private var dCooldown: TextView? = null
    private var dContent: TextView? = null
    private var dDone: Button? = null


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
        holder.itemView.setOnClickListener {
            callPopupDialog(context, row)
        }
    }

    override fun getItemCount(): Int = commandsList.size

    private fun callPopupDialog(context: FragmentActivity, row: CommandsModel) {
        val dialog = Dialog(context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_layout)
        initDialogViews(dialog)
        setDataToViews(dialog, row)
        dialog.window?.setLayout(ActionBar.LayoutParams.MATCH_PARENT,ActionBar.LayoutParams.WRAP_CONTENT)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setCanceledOnTouchOutside(true)
        dialog.show()
    }

    private fun initDialogViews(dialog: Dialog) {
        dName = dialog.findViewById(R.id.commandNameTextView)
        dType = dialog.findViewById(R.id.commandTypeTextView)
        dCooldown = dialog.findViewById(R.id.coolDownTextView)
        dContent = dialog.findViewById(R.id.contentTextView)
        dDone = dialog.findViewById(R.id.doneButton)
    }

    private fun setDataToViews(dialog: Dialog, row: CommandsModel) {
        dName?.text = row.commandName
        dType?.text = row.commandType
        dCooldown?.text = if(row.hasCooldown){
            "Yes"
        } else {
            "No"
        }
        dContent?.text = row.content
        dDone?.setOnClickListener {
            dialog.dismiss()
        }
    }


    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        internal var commandTextView: TextView = itemView.findViewById(R.id.commandTextView)
        internal var countTextView: TextView = itemView.findViewById(R.id.countTextView)
        internal var permissionsTextView: TextView = itemView.findViewById(R.id.permissionsTextView)
    }


}