package com.example.pruss.ui.ajustes


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.example.pruss.R
import com.google.android.material.navigation.NavigationView

/**
 * A simple [Fragment] subclass.
 */
class AjustesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


        val navigationView : NavigationView = activity!!.findViewById(R.id.nav_view)
        val headerView : View = navigationView.getHeaderView(0)
        headerView.findViewById<TextView>(R.id.tv_header_title).text=getString(R.string.settings)


        return inflater.inflate(R.layout.fragment_ajustes, container, false)
    }


}
