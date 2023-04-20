package com.example.todoapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.R
import com.example.todoapp.databinding.RowItemRecyclerViewBinding
import com.example.todoapp.db.ToDoList
// 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
class ToDoAdapter(
    val deleteListener: (ToDoList) -> Unit,
    val editListener: (ToDoList) -> Unit,
    val onItemClick: (ToDoList) -> Unit
) :
    RecyclerView.Adapter<ToDoAdapter.ViewHolder>() {

    var list = ArrayList<ToDoList>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RowItemRecyclerViewBinding.inflate(
            LayoutInflater.from(parent.getContext()),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.title.text = list.get(position).title
        holder.description.text = list.get(position).description
        holder.time.text = holder.itemView.context.resources.getString(
            R.string.last_updated,
            list.get(position).time
        )

        holder.btnDelete.setOnClickListener {
            deleteListener(list[position])
        }
        holder.btnEdit.setOnClickListener {
            editListener(list[position])
        }
        holder.cardView.setOnClickListener {
            onItemClick(list[position])
        }


    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(binding: RowItemRecyclerViewBinding) : RecyclerView.ViewHolder(binding.root) {
        val title = binding.tvTitle
        val description = binding.tvDescription
        val time = binding.tvTime
        val btnEdit = binding.ivEdit
        val btnDelete = binding.ivDelete
        val cardView = binding.clMain
    }

    fun addToList(todoList: List<ToDoList>) {
        list.clear()
        list.addAll(todoList)
        notifyDataSetChanged()
    }

    /*interface TodoClick{
        fun onDeleteCLick(position: Int,list: ToDoList)
        fun onEditClick(position: Int,list:ToDoList)
    }*/
}