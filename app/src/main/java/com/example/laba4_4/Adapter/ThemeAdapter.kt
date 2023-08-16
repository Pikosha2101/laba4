package com.example.laba4_4.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.laba4_4.Model.ThemeModel
import com.example.laba4_4.R
import com.example.laba4_4.databinding.ThemeItemRecyclerBinding


class ThemeAdapter(private val listener: com.example.laba4_4.Listener<ThemeModel>) :
    RecyclerView.Adapter<ThemeAdapter.ThemeHolder>() {
    private lateinit var themeList : List<ThemeModel>

    class ThemeHolder(item: View) : RecyclerView.ViewHolder(item){
        private val binding = ThemeItemRecyclerBinding.bind(item)

        fun bind(themeModel: ThemeModel, listener: com.example.laba4_4.Listener<ThemeModel>) = with(binding){
            theme.text = themeModel.theme
            Button.setOnClickListener {
                listener.onClick(themeModel)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ThemeHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ThemeHolder(inflater.inflate(R.layout.theme_item_recycler, parent, false))
    }

    override fun getItemCount() = themeList.size

    override fun onBindViewHolder(holder: ThemeHolder, position: Int) {
        holder.bind(themeList[position], listener)
    }

    fun setList(theme: List<ThemeModel>) {
        themeList = theme
    }
}