package com.example.pruss.ui.inbox


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pruss.ActivityNewChat

import com.example.pruss.R
import com.example.pruss.SesionRoom
import com.example.pruss.model.Chat
import com.example.pruss.model.LocalTask.Task
import com.example.pruss.model.LocalTask.TaskDAO
import com.example.pruss.ui.adapters.Rv_AdapterChat
import com.example.pruss.ui.adapters.Rv_AdapterTask
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.android.synthetic.main.fragment_inbox.*
import kotlinx.android.synthetic.main.fragment_inbox.view.*

/**
 * A simple [Fragment] subclass.
 */
class InboxFragment : Fragment() {
    lateinit var  adapterChat: Rv_AdapterChat
    var allChat : MutableList<Chat> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root=inflater.inflate(R.layout.fragment_inbox, container, false)
        // Inflate the layout for this fragment
        val navigationView : NavigationView = activity!!.findViewById(R.id.nav_view)
        val headerView : View = navigationView.getHeaderView(0)
        headerView.findViewById<TextView>(R.id.tv_header_title).text=getString(R.string.inbox)

        root.rv_inbox.layoutManager = LinearLayoutManager(
            activity!!.applicationContext,
            RecyclerView.VERTICAL,
            false
        )

        adapterChat = Rv_AdapterChat(
            activity!!.applicationContext,
            allChat as ArrayList<Chat>
        )
        root.rv_inbox.adapter=adapterChat

        addChatTeamPRuss()

        root.bt_newchat.setOnClickListener {
            val i = Intent(activity,ActivityNewChat::class.java)
            startActivity(i)
        }


        return root
    }


    private fun addChatTeamPRuss(){

        allChat.add(Chat(R.drawable.icono_pruss,"Pruss Team",0))
        adapterChat.notifyDataSetChanged()

    }



}
