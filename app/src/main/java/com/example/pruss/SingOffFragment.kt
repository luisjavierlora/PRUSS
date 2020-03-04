package com.example.pruss


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.navigation.NavigationView

/**
 * A simple [Fragment] subclass.
 */
class SingOffFragment : Fragment() {



    override fun onStart() {
        super.onStart()
        goToLoginActivity()
    }

    private fun goToLoginActivity( ){
        val intent = Intent(activity,LoginActivity::class.java)
        //intent.putExtra("correo",correo)
        //intent.putExtra("contra",contra)
        startActivity(intent)
        activity!!.finish()
    }



}
