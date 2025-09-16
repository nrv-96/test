package com.example.fetchdata

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SheetAdapter : RecyclerView.Adapter<SheetAdapter.SheetViewHolder>() {
    private var data: List<List<String>> = emptyList()

    fun setData(newData: List<List<String>>) {
        data = newData
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SheetViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_item, parent, false)
        return SheetViewHolder(view)
    }

    override fun onBindViewHolder(holder: SheetViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size

    class SheetViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val text1: TextView = itemView.findViewById(R.id.text1)
        private val text2: TextView = itemView.findViewById(R.id.text2)
        private val text3: TextView = itemView.findViewById(R.id.text3)
        private val text4: TextView = itemView.findViewById(R.id.text4)

        fun bind(row: List<String>) {
            text1.text = row.getOrNull(0) ?: ""
            text2.text = row.getOrNull(1) ?: ""
            text3.text = row.getOrNull(2) ?: ""
            text4.text = row.getOrNull(3) ?: ""
        }
    }
}
