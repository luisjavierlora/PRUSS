package com.example.pruss

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    var correo:String?=""
    var contra:String?=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


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

            if (correo!!.isEmpty()||contra!!.isEmpty())
                Toast.makeText(this,"Sus datos no coinciden",Toast.LENGTH_SHORT).show()

            else{
                if(et_Correo.text.toString()==correo && et_Contra.text.toString()==contra){
                    Toast.makeText(this,"Valido ",Toast.LENGTH_SHORT).show()
                    goToMainActivity()

                }

                else
                    Toast.makeText(this,"Sus datos no coinciden ",Toast.LENGTH_SHORT).show()
            }


        }



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



    }


    private fun goToMainActivity(){
        var intent =Intent(this,ActivityNAV::class.java)
        intent.putExtra("correo",correo)
        intent.putExtra("contra",contra)


        startActivity(intent)
        finish()
    }





}
