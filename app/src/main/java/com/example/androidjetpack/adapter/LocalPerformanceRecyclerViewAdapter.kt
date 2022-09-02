package com.example.androidjetpack.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidjetpack.data.source.local.entities.LocalPerformance
import com.example.androidjetpack.databinding.LocalPerformanceRecyclerviewItemBinding
import com.example.androidjetpack.ui.scorefragment.ScoreFragmentViewModel
import com.example.androidjetpack.util.DateFormatter.Companion.millisToDateAndTime

class LocalPerformanceRecyclerViewAdapter(
    private val scoreFragmentViewModel: ScoreFragmentViewModel,
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
            itemBinding.tvPoints.text = localPerformance.score.toString()
            itemBinding.tvTimePlayed.text = localPerformance.timePlayed
            itemBinding.tvDate.text = localPerformance.timeStamp.millisToDateAndTime()
        }
    }
}