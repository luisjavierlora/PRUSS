package com.example.pruss.ui.archivos


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast

import com.example.pruss.R
import com.google.android.material.navigation.NavigationView

/**
 * A simple [Fragment] subclass.
 */
class ArchivosFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val navigationView : NavigationView = activity!!.findViewById(R.id.nav_view)
        val headerView : View = navigationView.getHeaderView(0)
        headerView.findViewById<TextView>(R.id.tv_header_title).text=getString(R.string.archivos)



        return inflater.inflate(R.layout.fragment_archivos, container, false)
    }



}
