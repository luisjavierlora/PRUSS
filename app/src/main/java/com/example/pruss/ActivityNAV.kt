package com.example.pruss

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.pruss.ui.home.HomeFragment
import kotlinx.android.synthetic.main.nav_header_activity_nav.*

class ActivityNAV : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    var correo:String?=""
    var contra:String?=""
    var id_user:String?=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nav)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)


        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        navView.bringToFront()


        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_equipos, R.id.nav_finanzas,
                R.id.nav_inbox, R.id.nav_archivos, R.id.nav_sett, R.id.nav_sign_off
            ), drawerLayout
        )

        if(navView.getMenu().getItem(0).isChecked){
            Toast.makeText(this,"Sesion cerrada",Toast.LENGTH_SHORT).show()
        }

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        if(navView.getMenu().getItem(5).isChecked){
            goToLoginActivity( )
            Toast.makeText(this,"Sesion cerrada",Toast.LENGTH_SHORT).show()
        }
        /*navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_sign_off -> {
                    Toast.makeText(this,"Sesion cerrada",Toast.LENGTH_SHORT).show()
                    goToLoginActivity( )
                    true
                }
                else -> false
            } }*/

    }


    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }


    private fun goToLoginActivity( ){
        val intent = Intent(this,LoginActivity::class.java)
        intent.putExtra("correo",correo)
        intent.putExtra("contra",contra)
        startActivity(intent)
        finish()
    }

}
