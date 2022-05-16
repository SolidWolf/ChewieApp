package adapters

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.dango.leaderboard.R
import models.LeaderboardModel

class LeaderboardAdapter(
    private var requireActivity: FragmentActivity,
    private var startColorArray: ArrayList<String>,
    private var endColorArray: ArrayList<String>,
    private var rankList: ArrayList<LeaderboardModel>
): RecyclerView.Adapter<LeaderboardAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder( LayoutInflater.from(parent.context).inflate(R.layout.rank_layout, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val row = rankList[position]
        holder.nameTextView.text = row.name
        holder.rankTextView.text = row.rank.toString()
        holder.chewsTextView.text = row.chews
        val rankBackgroundDrawable = holder.rankCircle.background as GradientDrawable
        rankBackgroundDrawable.run {
            mutate()
            colors = intArrayOf(Color.parseColor(startColorArray[position]), Color.parseColor(endColorArray[position]))
        }
        val userBorderDrawable = holder.userLayout.background as GradientDrawable
        userBorderDrawable.run {
            mutate()
            colors = intArrayOf(Color.parseColor(startColorArray[position]), Color.parseColor(endColorArray[position]))
        }

    }

    override fun getItemCount(): Int = rankList.size

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        internal var rankTextView: TextView = itemView.findViewById(R.id.rankTextView)
        internal var nameTextView: TextView = itemView.findViewById(R.id.rankNameTextView)
        internal var chewsTextView: TextView = itemView.findViewById(R.id.rankChewsTextView)
        internal var rankCircle: RelativeLayout = itemView.findViewById(R.id.rankCircle)
        internal var userLayout: RelativeLayout = itemView.findViewById(R.id.userBorderRelativeLayout)
    }
}