package com.example.tasklistapplication.view

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.tasklistapplication.R
import com.example.tasklistapplication.model.ChoreData

class ChoreAdapter (val c:Context, val userList:ArrayList<ChoreData>):RecyclerView.Adapter<ChoreAdapter.TaskViewHolder>(){

    inner class TaskViewHolder(val v: View): RecyclerView.ViewHolder(v){
        var name:TextView
        var description:TextView
        var dueDate:TextView
        var mMenus:ImageView



        init{
            name=v.findViewById<TextView>(R.id.taskTitle)
            description=v.findViewById<TextView>(R.id.taskSubTitle)
            dueDate=v.findViewById<TextView>(R.id.taskDueDate)
            mMenus=v.findViewById(R.id.mMenus)
            mMenus.setOnClickListener{popupMenus(it)}
        }

        private fun popupMenus(v:View) {
            val position=userList[absoluteAdapterPosition]
            val popupMenus=PopupMenu(c,v)
            popupMenus.inflate(R.menu.show_menu)
            popupMenus.setOnMenuItemClickListener {
                when(it.itemId){
                    R.id.editText->{
                        val v=LayoutInflater.from(c).inflate(R.layout.add_item,null)
                        val name=v.findViewById<EditText>(R.id.choreName)
                        val desc=v.findViewById<EditText>(R.id.choreDesc)
                        val date=v.findViewById<EditText>(R.id.choreDate)

                        AlertDialog.Builder(c)
                            .setView(v)
                            .setPositiveButton("Ok"){
                                    dialog,_->
                                position.choreName=name.text.toString()
                                position.choreSub=desc.text.toString()
                                position.choreDate=date.text.toString()
                                notifyDataSetChanged()
                                Toast.makeText(c,"Chore information has been edited", Toast.LENGTH_SHORT).show()
                                dialog.dismiss()
                            }
                            .setNegativeButton("Cancel"){
                                    dialog,_->
                                dialog.dismiss()
                            }

                            .create()
                            .show()

                        true
                    }
                    R.id.deleteText->{
                        AlertDialog.Builder(c)
                            .setTitle("Delete")
                            .setIcon(R.drawable.ic_warning)
                            .setMessage("Are you sure you want to delete this chore?")
                            .setPositiveButton("Yes"){
                                    dialog,_->
                                userList.removeAt(absoluteAdapterPosition)
                                notifyDataSetChanged()
                                Toast.makeText(c,"Chore has been deleted", Toast.LENGTH_SHORT).show()
                                dialog.dismiss()
                            }
                            .setNegativeButton("No"){
                                    dialog,_->
                                dialog.dismiss()
                            }
                            .create()
                            .show()

                        true
                    }
                    else-> true
                }

            }
            popupMenus.show()
            val popup = PopupMenu::class.java.getDeclaredField("mPopup")
            popup.isAccessible=true
            val menu=popup.get(popupMenus)
            menu.javaClass.getDeclaredMethod("setForceShowIcon",Boolean::class.java)
                .invoke(menu,true)



        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val inflater=LayoutInflater.from(parent.context)
        val v=inflater.inflate(R.layout.list_item,parent,false)
        return TaskViewHolder(v)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val newList=userList[position]
        holder.name.text=newList.choreName
        holder.description.text=newList.choreSub
        holder.dueDate.text=newList.choreDate
    }

    override fun getItemCount(): Int {
        return userList.size
    }
}