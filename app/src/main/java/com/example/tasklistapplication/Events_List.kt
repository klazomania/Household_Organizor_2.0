package com.example.tasklistapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tasklistapplication.model.EventData
import com.example.tasklistapplication.model.UserData
import com.example.tasklistapplication.view.EventAdapter
import com.example.tasklistapplication.view.TaskAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

class Events_List : AppCompatActivity() {
    private lateinit var addsBtn:FloatingActionButton
    private lateinit var backBtn:FloatingActionButton
    private lateinit var recv:RecyclerView
    private lateinit var eventList:ArrayList<EventData>
    private lateinit var eventAdapter:EventAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_events_list)
        eventList= ArrayList()
        addsBtn=findViewById(R.id.fab)
        backBtn=findViewById(R.id.fab2)
        recv = findViewById(R.id.tasksRecycler)
        eventAdapter= EventAdapter(this,eventList)
        recv.layoutManager=LinearLayoutManager(this)
        recv.adapter=eventAdapter
        addsBtn.setOnClickListener{addInfo()}
        backBtn.setOnClickListener{goBack()}

    }

    fun goBack() {
        val intent = Intent(this, Home_Screen::class.java)
        startActivity(intent)
    }

    private fun addInfo() {
        val infilter=LayoutInflater.from(this)
        val v=infilter.inflate(R.layout.add_event_item,null)
        val eventName=v.findViewById<EditText>(R.id.eventName)
        val eventDesc=v.findViewById<EditText>(R.id.EventDesc)

        val addDialog=AlertDialog.Builder(this)
        addDialog.setView(v)
        addDialog.setPositiveButton("Ok"){

                dialog, _->
            val names=eventName.text.toString()
            val description=eventDesc.text.toString()
            eventList.add(EventData("Task: $names","Task Description: $description"))
            eventAdapter.notifyDataSetChanged()
            Toast.makeText(this,"Adding Task Information Success",Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }
        addDialog.setNegativeButton("cancel"){
                dialog,_->
            dialog.dismiss()
            Toast.makeText(this,"Cancel",Toast.LENGTH_SHORT).show()
        }
        addDialog.create()
        addDialog.show()
    }
}