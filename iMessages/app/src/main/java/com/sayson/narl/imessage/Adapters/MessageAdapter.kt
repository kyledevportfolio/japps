package com.sayson.narl.imessage.Adapters


import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.sayson.narl.imessage.R
import com.sayson.narl.imessage.mods.ThisMessage
class MessageAdapter(list: ArrayList<ThisMessage>, context: Context) : RecyclerView.Adapter<MessageAdapter.MessageHolder>() {
    var data = list
    var v: View? = null
    var mContext = context
    override fun onBindViewHolder(holder: MessageHolder?, position: Int) {

        if (data[position].Sender.equals(data[position].myemail)) {

            holder!!.message.text = data[position].Messages
            holder!!.rmessage.visibility = View.INVISIBLE
            holder!!.sender.visibility = View.INVISIBLE
        } else {
            holder!!.rmessage.text = data[position].Messages
            holder!!.sender.text = data[position].SenderName
            holder!!.message.visibility = View.INVISIBLE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MessageHolder {
        var view = LayoutInflater.from(parent!!.context).inflate(R.layout.listofmessage, parent, false)
        v = view

        return MessageAdapter.MessageHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class MessageHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var sender: TextView
        var message: TextView
        var backmsg: LinearLayout
        var rmessage: TextView

        init {
            backmsg = itemView.findViewById(R.id.msgbck)
            sender = itemView.findViewById(R.id.sender)
            message = itemView.findViewById(R.id.sMessages)
            rmessage = itemView.findViewById(R.id.mMessages)
        }
    }
}