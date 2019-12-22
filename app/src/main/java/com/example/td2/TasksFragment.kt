package com.example.td2

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.td2.network.Api
import com.example.td2.network.TasksRepository
import kotlinx.android.synthetic.main.tasks_fragment.*
import kotlinx.android.synthetic.main.tasks_fragment.view.*
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class TasksFragment :Fragment()
{



    private val tasks = mutableListOf(
        Task(id = "id_1", title = "Task 1", description = "description 1"),
        Task(id = "id_2", title = "Task 2", description = "description 2"),
        Task(id = "id_3", title = "Task 3", description = "description 3")
    )

    private val coroutineScope = MainScope()
    private val tasksRepository = TasksRepository()
    //private val tasks = mutableListOf<Task>()
    private val taskAdapter = TasksAdapter(tasks){onDeleteClick(it)}

    private fun onDeleteClick(task : Task) {
        coroutineScope.launch {

            Api.taskService.deleteTask(task.id)
            tasks.remove(task)
            taskAdapter.notifyDataSetChanged()
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.tasks_fragment, container, false)
        view.tasks_recycler_view.adapter = taskAdapter
        view.tasks_recycler_view.layoutManager = LinearLayoutManager(context)
        return view
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        // subscribe the fragment to task update
        tasksRepository.getTasks().observe(this, Observer {
            if( it != null){
                tasks.clear()
                tasks.addAll(it)
                Log.e("task ", it.toString())
                taskAdapter.notifyDataSetChanged()
            }
        })
        super.onCreate(savedInstanceState)
    }
    override fun onResume() {
        coroutineScope.launch {
            Api.userService.getInfo()
        }
        super.onResume()
    }

    override fun onDestroy() {
        coroutineScope.cancel()
        super.onDestroy()
    }


}