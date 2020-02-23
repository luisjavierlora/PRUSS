package com.example.pruss

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.DatePicker
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_register.*
import java.util.*

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)


        val c = Calendar.getInstance()
        val ano = c.get(Calendar.YEAR)
        val mes =c.get(Calendar.MONTH)
        val dia = c.get(Calendar.DAY_OF_MONTH)

        tvFecha.setOnClickListener{
            val dpd= DatePickerDialog(this,
                DatePickerDialog.OnDateSetListener { view: DatePicker, mYear:Int, mMonth:Int, mDay: Int ->
                    tvFecha.setText(""+mDay+"/"+(mMonth+1)+"/"+mYear)
                },ano,mes,dia)
            dpd.show()
        }




        btRegistrar.setOnClickListener {

            val nombre :String = et_Nombre.text.toString()
            val correo: String = et_Correo.text.toString()
            val contra:String=et_Contrasena.text.toString()
            val rcontra:String=et_Rcontrasena.text.toString()
            var genero:String= rb_Maculino.text.toString()
            val fecha :String = tvFecha.text.toString()


            if(rb_Femenino.isChecked){
                genero=rb_Femenino.text.toString()
            }

            if(nombre.isEmpty() || correo.isEmpty()  || contra.isEmpty() || rcontra.isEmpty() || fecha.isEmpty()){
                Toast.makeText(this,"Por favor llenar todos los campos", Toast.LENGTH_SHORT).show()}
            else{
                if(contra.length>5){
                    if(contra != rcontra)
                        Toast.makeText(this,"Contraseñas no coinciden", Toast.LENGTH_SHORT).show()
                    else {
                        var intent = Intent()
                        intent.putExtra("correo", correo)
                        intent.putExtra("contra", contra)
                        setResult(Activity.RESULT_OK, intent)
                        finish()
                    }
                }
                else
                    Toast.makeText(this,"La contraseña debe ser de almenos 6 digitos", Toast.LENGTH_SHORT).show()

            }




        }
    }


    override fun onBackPressed() {
        super.onBackPressed()
        setResult(Activity.RESULT_CANCELED)
        finish()
    }


}
