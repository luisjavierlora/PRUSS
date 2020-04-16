package com.example.pruss


import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.firebase.ui.auth.AuthUI
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInApi
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.common.api.internal.OnConnectionFailedListener
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth

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

        startActivity(intent)
        activity!!.finish()
        FirebaseAuth.getInstance().signOut()
        AuthUI.getInstance().signOut(activity!!)
    }



}
