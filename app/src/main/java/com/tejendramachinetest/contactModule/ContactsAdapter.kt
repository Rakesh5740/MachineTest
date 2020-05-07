package com.tejendramachinetest.contactModule

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.tejendramachinetest.R
import com.tejendramachinetest.contactModule.ContactsAdapter.MyHolder

class ContactsAdapter( private var alContactsModel: List<User>?) : RecyclerView.Adapter<MyHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.contacts_adapter, parent, false)
        return MyHolder(view)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        if (alContactsModel != null && alContactsModel!!.isNotEmpty()) {
            val contactsModel = alContactsModel!![position]
            holder.contactsName.text = contactsModel.getName()
            holder.contactsNumber.text = contactsModel.getNumber()
        }
    }

    override fun getItemCount(): Int {
        return alContactsModel!!.size
    }

    inner class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var contactsName: AppCompatTextView = itemView.findViewById(R.id.contactsName)
        var contactsNumber: AppCompatTextView = itemView.findViewById(R.id.contactsNumber)

    }

}