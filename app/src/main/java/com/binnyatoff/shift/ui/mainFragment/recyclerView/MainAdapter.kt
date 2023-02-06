package com.binnyatoff.shift.ui.mainFragment.recyclerView

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.binnyatoff.shift.data.network.CardNumber

class MainAdapter : ListAdapter<CardNumber, MainViewHolder>(MainDiffUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        TODO("Not yet implemented")
    }
}

class MainViewHolder(view: View):RecyclerView.ViewHolder(view) {

}

object MainDiffUtil : DiffUtil.ItemCallback<CardNumber>(){
    override fun areItemsTheSame(oldItem: CardNumber, newItem: CardNumber): Boolean {
        TODO("Not yet implemented")
    }

    override fun areContentsTheSame(oldItem: CardNumber, newItem: CardNumber): Boolean {
        TODO("Not yet implemented")
    }

}