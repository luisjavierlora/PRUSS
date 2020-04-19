package com.example.pruss.ui.teams


import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.pruss.R
import com.example.pruss.SesionRoom
import com.example.pruss.model.LocalTask.Task
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_nav.*
import kotlinx.android.synthetic.main.dialog_new_equipo.view.*
import kotlinx.android.synthetic.main.dialog_new_task.view.*
import kotlinx.android.synthetic.main.fragment_teams.view.*
import kotlinx.android.synthetic.main.nav_header_activity_nav.*
import kotlinx.android.synthetic.main.nav_header_activity_nav.view.*
import java.sql.Types

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


        teams.bt_newgroup.setOnClickListener {
            DialogNewTeam(activity!!)


        }



        return teams
    }



    fun DialogNewTeam(context: Context){
        val mDialogView = LayoutInflater.from(context).inflate(R.layout.dialog_new_equipo, null)
        //AlertDialogBuilder
        val mBuilder = AlertDialog.Builder(context)
            .setView(mDialogView)

        //show dialog
        val  mAlertDialog = mBuilder.show()
        //login button click of custom layout
        //mAlertDialog.window!!.setBackgroundDrawableResource(android.R.color.transparent)
        mAlertDialog.window!!.getDecorView().getBackground().setAlpha(0)
        mAlertDialog.window!!.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)


        mDialogView.bt_aceptart.setOnClickListener {

            mAlertDialog.dismiss()


        }
        //cancel button click of custom layout
        mDialogView.bt_cancelart.setOnClickListener {
            //dismiss dialog
            mAlertDialog.dismiss()
        }


    }

}
