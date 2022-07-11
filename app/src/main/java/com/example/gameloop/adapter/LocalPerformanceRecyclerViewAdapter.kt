package com.example.gameloop.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gameloop.OnRecyclerViewItemClickListener
import com.example.gameloop.data.source.local.entities.LocalPerformance
import com.example.gameloop.databinding.LocalPerformanceRecyclerviewItemBinding
import com.example.gameloop.ui.scorefragment.ScoreFragmentViewModel
import com.example.gameloop.util.DateFormatter.Companion.millisToDate
import com.example.gameloop.util.DateFormatter.Companion.millisToDateAndTime

class LocalPerformanceRecyclerViewAdapter(
    private val scoreFragmentViewModel: ScoreFragmentViewModel,
    //private val context: Context,
    //private val clickListener: OnRecyclerViewItemClickListener
): RecyclerView.Adapter<LocalPerformanceRecyclerViewAdapter.ViewHolder>() {

    private var dataList = emptyList<LocalPerformance>()

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LocalPerformanceRecyclerviewItemBinding.
        inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val localPerformance: LocalPerformance = dataList[position]
        holder.bind(localPerformance)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun setData(localPerformanceList: List<LocalPerformance>){
        this.dataList = localPerformanceList
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val itemBinding: LocalPerformanceRecyclerviewItemBinding): RecyclerView.ViewHolder(itemBinding.root){
        fun bind(localPerformance: LocalPerformance){
            itemBinding.tvPoints.text = "Points: ${localPerformance.score.toString()}"
            itemBinding.tvTimePlayed.text = "Time played: ${localPerformance.timePlayed}"
            itemBinding.tvDate.text = localPerformance.timeStamp.millisToDateAndTime()
        }
    }
}