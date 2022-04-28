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
import com.example.tasklistapplication.model.UserData
import com.example.tasklistapplication.view.EventAdapter
import com.example.tasklistapplication.view.TaskAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

class Events_List : AppCompatActivity() {
    private lateinit var addsBtn:FloatingActionButton
    private lateinit var backBtn:FloatingActionButton
    private lateinit var recv:RecyclerView
    private lateinit var userList:ArrayList<UserData>
    private lateinit var taskAdapter:TaskAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_events_list)
        userList= ArrayList()
        addsBtn=findViewById(R.id.fab)
        backBtn=findViewById(R.id.fab2)
        recv = findViewById(R.id.tasksRecycler)
        taskAdapter= EventAdapter(this,userList)
        recv.layoutManager=LinearLayoutManager(this)
        recv.adapter=taskAdapter
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
        val eventDesc=v.findViewById<EditText>(R.id.eventDesc)

        val addDialog=AlertDialog.Builder(this)
        addDialog.setView(v)
        addDialog.setPositiveButton("Ok"){

                dialog, _->
            val names=taskName.text.toString()
            val description=taskDesc.text.toString()
            userList.add(UserData("Task: $names","Task Description: $description"))
            taskAdapter.notifyDataSetChanged()
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