package com.example.td2

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.preference.Preference
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_task.view.*

class TasksAdapter(private val tasks: MutableList<Task>, private val onDeleteClickListener:(Task)->Unit) : RecyclerView.Adapter<TasksAdapter.TaskViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false))
    }

    override fun getItemCount(): Int {
        return tasks.size
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(tasks[position])
        holder.itemView.button_delete.setOnClickListener { onDeleteClickListener(tasks[position]) }
    }



    class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(task: Task) {

            val sharedpreferences= PreferenceManager.getDefaultSharedPreferences(itemView.context)
            val colortext=sharedpreferences.getString("TaskBckgColor", "")
            itemView.task_title.text = task.title
            itemView.task_id.text = task.id
            itemView.task_description.text = task.description
            itemView.setBackgroundColor(Color.parseColor(colortext))
        }
    }
}