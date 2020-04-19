package com.example.pruss.ui.ajustes


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.pruss.LoginActivity

import com.example.pruss.R
import com.firebase.ui.auth.AuthUI
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_ajustes.view.*

/**
 * A simple [Fragment] subclass.
 */
class AjustesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root=inflater.inflate(R.layout.fragment_ajustes, container, false)

        val navigationView : NavigationView = activity!!.findViewById(R.id.nav_view)
        val headerView : View = navigationView.getHeaderView(0)
        headerView.findViewById<TextView>(R.id.tv_header_title).text=getString(R.string.settings)


        root.tv_singoff.setOnClickListener {
            goToLoginActivity()
        }

        return root
    }

    private fun goToLoginActivity( ){
        val intent = Intent(activity, LoginActivity::class.java)

        startActivity(intent)
        activity!!.finish()
        FirebaseAuth.getInstance().signOut()
        AuthUI.getInstance().signOut(activity!!)
    }
}
