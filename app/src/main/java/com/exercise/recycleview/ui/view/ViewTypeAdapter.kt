package com.exercise.recycleview.ui.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.exercise.recycleview.BR

class ViewTypeAdapter<E : ViewTypeAdapter.ViewType<*>>(
    private val list: MutableList<E> = mutableListOf(),
    private val onItemActionListener: OnItemActionListener? = null
) : RecyclerView.Adapter<ViewTypeAdapter.ViewTypeHolder>() {

    // region ADAPTER BASEMENT

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewTypeHolder {
        val binding: ViewDataBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            viewType,
            parent,
            false
        )
        return ViewTypeHolder(binding, onItemActionListener)
    }

    override fun onBindViewHolder(holder: ViewTypeHolder, position: Int) {
        holder.bindItem(list[position])
    }

    override fun getItemViewType(position: Int): Int = list[position].layoutId()

    override fun getItemCount(): Int = list.size

    // endregion

    // region CRUD

    fun setList(data: List<E>) = with(list) {
        clear()
        addAll(data)
        notifyDataSetChanged()
    }

    // endregion

    // region AUX

    class ViewTypeHolder(
        private val binding: ViewDataBinding,
        private val onItemActionListener: OnItemActionListener?
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bindItem(item: ViewType<*>) {
            binding.setVariable(BR.model, item.data())
            if (item.isUserInteractionEnabled()) {
                binding.setVariable(BR.position, adapterPosition)
                binding.setVariable(BR.actionItemListener, onItemActionListener)
            }
            binding.executePendingBindings()
        }
    }

    interface ViewType<out T> {
        @LayoutRes
        fun layoutId(): Int
        fun data(): T
        fun isUserInteractionEnabled() = true
    }

    interface OnItemActionListener {
        fun onItemClicked(position: Int)
    }

    interface OnItemClickListener<T> : OnItemActionListener {
        fun onItemClicked(position: Int, item: T)
    }

    // endregion
}
