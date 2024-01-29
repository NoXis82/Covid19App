package com.example.covid19app.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.covid19app.R
import com.example.covid19app.databinding.ItemDataBinding
import com.example.covid19app.models.ItemInfo

class ItemAdapter() : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    var data: List<ItemInfo> = emptyList()
        set(newValue) {
            field = newValue
            notifyDataSetChanged()
        }

    class ItemViewHolder(val binding: ItemDataBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ItemViewHolder(ItemDataBinding.inflate(inflater, parent, false))
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val dataInfo = data[position]
        val context = holder.itemView.context
        with(holder.binding) {
            cases.text = context.getString(R.string.cases, dataInfo.cases)
            country.text = context.getString(R.string.country, dataInfo.country)
            lastUpdate.text = context.getString(R.string.lastUpdate, dataInfo.lastUpdate)
            newCases.text = context.getString(R.string.newCases, dataInfo.newCases)
            newDeaths.text = context.getString(R.string.newDeaths, dataInfo.newDeaths)
            totalCases.text = context.getString(R.string.totalCases, dataInfo.totalCases)
            totalDeaths.text = context.getString(R.string.totalDeaths, dataInfo.totalDeaths)
            totalRecovered.text = context.getString(R.string.totalRecovered, dataInfo.totalRecovered)
        }
    }
}