package com.example.tasklistapplication.view

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.tasklistapplication.R
import com.example.tasklistapplication.model.EventData

class EventAdapter (val c:Context, val eventList:ArrayList<EventData>):RecyclerView.Adapter<EventAdapter.EventViewHolder>(){

    inner class EventViewHolder(val v: View): RecyclerView.ViewHolder(v){
        var name:TextView
        var description:TextView
        var mMenus:ImageView



        init{
            name=v.findViewById<TextView>(R.id.eventTitle)
            description=v.findViewById<TextView>(R.id.eventSubTitle)
            mMenus=v.findViewById(R.id.mMenus)
            mMenus.setOnClickListener{popupMenus(it)}
        }

        private fun popupMenus(v:View) {
            val position=eventList[absoluteAdapterPosition]
            val popupMenus=PopupMenu(c,v)
            popupMenus.inflate(R.menu.show_menu)
            popupMenus.setOnMenuItemClickListener {
                when(it.itemId){
                    R.id.editText->{
                        val v=LayoutInflater.from(c).inflate(R.layout.add_item,null)
                        val name=v.findViewById<EditText>(R.id.eventName)
                        val desc=v.findViewById<EditText>(R.id.EventDesc)

                        AlertDialog.Builder(c)
                            .setView(v)
                            .setPositiveButton("Ok"){
                                    dialog,_->
                                position.eventName=name.text.toString()
                                position.eventSub=desc.text.toString()
                                notifyDataSetChanged()
                                Toast.makeText(c,"Event Information is Edited", Toast.LENGTH_SHORT).show()
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
                            .setMessage("Are you sure you want to delete this Event?")
                            .setPositiveButton("Yes"){
                                    dialog,_->
                                eventList.removeAt(absoluteAdapterPosition)
                                notifyDataSetChanged()
                                Toast.makeText(c,"Event has been deleted", Toast.LENGTH_SHORT).show()
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val inflater=LayoutInflater.from(parent.context)
        val v=inflater.inflate(R.layout.list_item,parent,false)
        return EventViewHolder(v)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val newList=eventList[position]
        holder.name.text=newList.eventName
        holder.description.text=newList.eventSub
    }

    override fun getItemCount(): Int {
        return eventList.size
    }
}