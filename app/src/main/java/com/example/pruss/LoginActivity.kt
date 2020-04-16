package com.example.pruss

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.edwinacubillos.sesionroom.model.Usuario
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.GoogleApiClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.et_Correo
import kotlinx.android.synthetic.main.activity_register.*
import javax.security.auth.callback.Callback
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import com.firebase.ui.auth.AuthUI
import java.util.*


class LoginActivity : AppCompatActivity() {
    var correo:String?=""
    var contra:String?=""
    lateinit var providers : List<AuthUI.IdpConfig>
    var googleSignInClient : GoogleSignInClient? = null

    val RC_SIGN_IN = 1000
    var mGoogleApiClient: GoogleApiClient? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        var gso =GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(this,gso)


        providers = Arrays.asList<AuthUI.IdpConfig>(
            AuthUI.IdpConfig.EmailBuilder().build(),
            AuthUI.IdpConfig.GoogleBuilder().build()

        )

        showSignInOptions()

            // ...




        bt_google.setOnClickListener {

            var signInIntent = googleSignInClient?.signInIntent
            startActivityForResult(signInIntent,RC_SIGN_IN)
        }

        var datosRecibidos : Bundle? =intent.extras
        if(datosRecibidos != null){
            correo = datosRecibidos?.getString("correo")
            contra = datosRecibidos?.getString("contra")
            //Toast.makeText(this,"return main to login",Toast.LENGTH_SHORT).show()
        }


        tv_registrar.setOnClickListener {

            var intent =Intent(this,RegisterActivity::class.java)
            startActivityForResult(intent,1001)


        }

        bt_iniciarsesion.setOnClickListener{


            val auth: FirebaseAuth =FirebaseAuth.getInstance()

            auth.signInWithEmailAndPassword(et_Correo.text.toString(), et_Contra.text.toString())
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d("loginactivity", "signInWithEmail:success")
                        val user = auth.currentUser
                         val id = user!!.uid
                        goToMainActivity(id)
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w("loginactivity", "signInWithEmail:failure", task.exception)
                        if(task.exception!!.message!!.equals("There is no user record corresponding to this identifier. The user may have been deleted.")){
                            var intent =Intent(this,RegisterActivity::class.java)
                            startActivityForResult(intent,1001)}

                        else if(task.exception!!.message!!.equals("The password is invalid or the user does not have a password.")){
                            Toast.makeText(this,"La contraseña no es valida",Toast.LENGTH_SHORT).show()
                        }
                    }

                    // ...
                }

            /*
            if (correo!!.isEmpty()||contra!!.isEmpty())
                Toast.makeText(this,"Sus datos no coinciden",Toast.LENGTH_SHORT).show()

            else{
                if(et_Correo.text.toString()==correo && et_Contra.text.toString()==contra){
                    Toast.makeText(this,"Valido ",Toast.LENGTH_SHORT).show()
                    goToMainActivity()

                }

                else
                    Toast.makeText(this,"Sus datos no coinciden ",Toast.LENGTH_SHORT).show()
            }*/


        }



    }

    fun firebaseAuthWithGoogle(acct : GoogleSignInAccount?){
        val credential = GoogleAuthProvider.getCredential(acct?.idToken,null)
        FirebaseAuth.getInstance().signInWithCredential(credential).addOnCompleteListener {
                task ->
            if(task.isSuccessful){
                val currentUser = FirebaseAuth.getInstance().currentUser
                createUserDatabase(currentUser)
                goToMainActivity( currentUser!!.uid)

            }
        }
    }


    public override fun onStart() {
        super.onStart()
        val auth = FirebaseAuth.getInstance()
        val currentUser = auth.currentUser
        if (currentUser != null)
            goToMainActivity(currentUser.uid)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)



        if(requestCode==1001 && resultCode==Activity.RESULT_OK){
            var datosRecibidos : Bundle? =data!!.extras
            if(datosRecibidos != null){
                correo = datosRecibidos?.getString("correo")
                contra = datosRecibidos?.getString("contra")
                //Toast.makeText(this,correo,Toast.LENGTH_SHORT).show()
            }

        }

        if(requestCode == RC_SIGN_IN){
            var task = GoogleSignIn.getSignedInAccountFromIntent(data)
            var account = task.getResult(ApiException::class.java)

            firebaseAuthWithGoogle(account)
        }

    }





    private fun goToMainActivity(id:String){

        var intent =Intent(this,ActivityNAV::class.java)
        intent.putExtra("id_user",id)
        startActivity(intent)
        finish()
    }

    fun createUserDatabase(user: FirebaseUser?){

        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("usuarios")

        var usuario = Usuario(user!!.uid,user.email.toString(),user.displayName!!,"","")
        myRef.child(usuario.id).setValue(usuario)
    }


    fun showSignInOptions(){
        /*startActivityForResult(AuthUI.getInstance().createSignInIntentBuilder()
            .setAvailableProviders(providers)
            .setTheme()
        )*/
    }


}
