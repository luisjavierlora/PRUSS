package com.example.pruss.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.pruss.R
import com.google.android.material.navigation.NavigationView

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        //val titulo :TextView = root.findViewById(R.id.tv_header_title)
        /*
        val textView: TextView = root.findViewById(R.id.text_home)
        homeViewModel.text.observe(this, Observer {
            textView.text = it
        })*/

        val navigationView : NavigationView  = activity!!.findViewById(R.id.nav_view)
        val headerView : View = navigationView.getHeaderView(0)
        headerView.findViewById<TextView>(R.id.tv_header_title).text=getString(R.string.Home)
        //val navUsername : TextView = headerView.findViewById(R.id.txtUserName)
        //val navUserEmail : TextView = headerView.findViewById(R.id.txtEmail)



        return root
    }
}