package adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.dango.leaderboard.LeaderboardFragment
import com.dango.leaderboard.R

class SeasonAdapter(
    private var requireActivity: FragmentActivity,
    private var seasonsList: ArrayList<String>,
    private var selectedSeasonTextView: TextView,
    private var seasonMenu: LinearLayout,
    private var collapseIcon: ImageView,
    private var expandIcon: ImageView
) : RecyclerView.Adapter<SeasonAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder = ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.seasons_item_layout,parent,false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val row = seasonsList[position]
        holder.seasonTextView.text = row
        holder.seasonTextView.setOnClickListener {
            selectedSeasonTextView.text = row
            seasonMenu.visibility = View.GONE
            collapseIcon.visibility = View.GONE
            expandIcon.visibility = View.VISIBLE
            LeaderboardFragment.isSeasonsExpanded = !LeaderboardFragment.isSeasonsExpanded
        }
    }

    override fun getItemCount(): Int = seasonsList.size

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        internal var seasonTextView: TextView = itemView.findViewById(R.id.seasonTextView)
    }
}