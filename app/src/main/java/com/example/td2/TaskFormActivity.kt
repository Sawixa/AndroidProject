package com.example.td2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.td2.network.Api
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class TaskFormActivity : AppCompatActivity() {

    private val coroutineScope = MainScope()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_form)

        val button = findViewById<Button>(R.id.back)

        button.setOnClickListener{
            val createActivityIntent : Intent = Intent(this, MainActivity::class.java)

            val title = findViewById<TextView>(R.id.title).text.toString()

            val description = findViewById<TextView>(R.id.description).text.toString()

            coroutineScope.launch {
                Api.taskService.createTask(Task("id_$title", title, description))
            }
            startActivity(createActivityIntent)
        }

    }
}
