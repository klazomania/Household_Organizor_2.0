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
import com.example.tasklistapplication.model.ChoreData
import com.example.tasklistapplication.view.ChoreAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

class Chore_List : AppCompatActivity() {
    private lateinit var addsBtn:FloatingActionButton
    private lateinit var backBtn:FloatingActionButton
    private lateinit var recv:RecyclerView
    private lateinit var choreList:ArrayList<ChoreData>
    private lateinit var choreAdapter: ChoreAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chore_list)
        choreList= ArrayList()
        addsBtn=findViewById(R.id.fab)
        recv = findViewById(R.id.choresRecycler)
        choreAdapter= ChoreAdapter(this,choreList)
        recv.layoutManager=LinearLayoutManager(this)
        recv.adapter=choreAdapter
        addsBtn.setOnClickListener{addInfo()}
        backBtn.setOnClickListener{goBack()}


    }

    fun goBack() {
        val intent = Intent(this, Home_Screen::class.java)
        startActivity(intent)
    }



    private fun addInfo() {
        val infilter=LayoutInflater.from(this)
        val v=infilter.inflate(R.layout.chores_add_item,null)
        val choreName=v.findViewById<EditText>(R.id.choreName)
        val choreDesc=v.findViewById<EditText>(R.id.choreDesc)
        val choreDate=v.findViewById<EditText>(R.id.choreDate)

        val addDialog=AlertDialog.Builder(this)
        addDialog.setView(v)
        addDialog.setPositiveButton("Ok"){

                dialog, _->
            val names=choreName.text.toString()
            val description=choreDesc.text.toString()
            val date=choreDate.text.toString()
            choreList.add(ChoreData("Chore: $names","Chore Description: $description", "Complete by Date: $date" ))
            choreAdapter.notifyDataSetChanged()
            Toast.makeText(this,"Adding Chore Information Success",Toast.LENGTH_SHORT).show()
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