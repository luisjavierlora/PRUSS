package com.example.pruss.ui.teams


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.pruss.R
import com.example.pruss.SesionRoom
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_nav.*
import kotlinx.android.synthetic.main.nav_header_activity_nav.*
import kotlinx.android.synthetic.main.nav_header_activity_nav.view.*

/**
 * A simple [Fragment] subclass.
 */
class TeamsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val teams =inflater.inflate(R.layout.fragment_teams, container, false)

        val navigationView : NavigationView  = activity!!.findViewById(R.id.nav_view)
        val headerView : View = navigationView.getHeaderView(0)
        headerView.findViewById<TextView>(R.id.tv_header_title).text=getString(R.string.Team)

        SesionRoom.databaseR.proyectoDAO()

        return teams
    }





}
