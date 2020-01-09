package com.example.td2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageButton
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val taskFormIntent = Intent(this, TaskFormActivity::class.java)

        val button = findViewById<Button>(R.id.AddButton)
        button.setOnClickListener {
            startActivity(taskFormIntent)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu) : Boolean
    {
        val inflater : MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            R.id.item1 -> {
                val preferenceActivityIntent=Intent(this, PreferenceActivity::class.java)
                startActivity(preferenceActivityIntent)
                true
            }
            R.id.help -> {
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}
