package com.nikunj.kotlindemo.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nikunj.kotlindemo.databinding.GridViewItemBinding
import com.nikunj.kotlindemo.network.PersonProperty

class PhotoGridAdapter( private val onClickListener: OnClickListener) :
        ListAdapter<PersonProperty,
                PhotoGridAdapter.PersonPropertyViewHolder>(
            DiffCallback
        ) {

    class PersonPropertyViewHolder(private var binding: GridViewItemBinding):
            RecyclerView.ViewHolder(binding.root) {
        fun bind(personProperty: PersonProperty) {
            binding.property = personProperty
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<PersonProperty>() {
        override fun areItemsTheSame(oldItem: PersonProperty, newItem: PersonProperty): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: PersonProperty, newItem: PersonProperty): Boolean {
            return oldItem.id == newItem.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): PersonPropertyViewHolder {
        return PersonPropertyViewHolder(
            GridViewItemBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    override fun onBindViewHolder(holder: PersonPropertyViewHolder, position: Int) {
        val personProperty = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(personProperty)
        }
        holder.bind(personProperty)
    }

    class OnClickListener(val clickListener: (personProperty: PersonProperty) -> Unit) {
        fun onClick(personProperty: PersonProperty) = clickListener(personProperty)
    }
}

