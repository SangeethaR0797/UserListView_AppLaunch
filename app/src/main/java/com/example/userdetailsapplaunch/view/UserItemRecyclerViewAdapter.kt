package com.example.userdetailsapplaunch.view

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.userdetailsapplaunch.R
import com.example.userdetailsapplaunch.model.UserModel

import com.example.userdetailsapplaunch.view.dummy.DummyContent.DummyItem


class UserItemRecyclerViewAdapter(
    private val values: List<UserModel>
) : RecyclerView.Adapter<UserItemRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_user_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        val name=item.firstName +" "+item.lastName
        holder.idView.text = name
        holder.contentView.text = item.emailId
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val idView: TextView = view.findViewById(R.id.item_number)
        val contentView: TextView = view.findViewById(R.id.content)

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }
}