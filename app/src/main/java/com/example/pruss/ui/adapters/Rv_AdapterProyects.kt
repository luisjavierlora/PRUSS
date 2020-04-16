package com.example.pruss.ui.adapters

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.ContentValues.TAG
import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.inputmethodservice.Keyboard
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.pruss.R
import com.example.pruss.SesionRoom
import com.example.pruss.model.proyecto
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.dialog_add_project.view.*
import kotlinx.android.synthetic.main.layout_proyecto.view.*
import androidx.core.content.ContextCompat.getSystemService
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.view.inputmethod.InputMethodManager
import com.example.pruss.ui.home.HomeFragment
import com.skydoves.colorpickerview.listeners.ColorListener
import kotlinx.android.synthetic.main.dialog_add_project.*
import java.lang.String.format
import java.security.Key


class Rv_AdapterProyects(var context: Context,var items: ArrayList<proyecto>): RecyclerView.Adapter<Rv_AdapterProyects.ViewHolder>() {





    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): Rv_AdapterProyects.ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(com.example.pruss.R.layout.layout_proyecto,parent,false)

        val viewHolder= ViewHolder(itemView)
        return  viewHolder
    }

    override fun getItemCount(): Int {
        return this.items.count()
    }

    override fun onBindViewHolder(holder: Rv_AdapterProyects.ViewHolder, position: Int) {
        //val item=items.get(position)
        //holder.tv_nombre?.text=item.nombre
        //holder.tv_nombre?.setTextColor(Color.parseColor(item.color_s))
        //val background:Drawable = holder.tv_nombre!!.background
        //background.setColorFilter(Color.parseColor(item.color_p), PorterDuff.Mode.SRC_ATOP)
        val item=items.get(position)
        holder.bindDeudor(item)

        if (position==0){
            holder.bt_pluss!!.visibility=View.VISIBLE
            if(items.size > 1){
                holder.tv_nombre!!.visibility=View.GONE
            }
        }
        else{
            holder.bt_pluss!!.visibility=View.GONE
            holder.tv_nombre!!.visibility=View.VISIBLE
        }


        holder.bt_pluss!!.setOnClickListener {

            if (position==0){
                //holder.tv_nombre?.text="Perfecto"
                val contexto= holder.itemView.context

                dialog_add(context)

            }

        }

    }


    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        var bt_pluss =itemView.bt_plussProject
        var tv_nombre =itemView.tv_nombre
        fun bindDeudor(proyecto: proyecto) {
            itemView.tv_nombre.text=proyecto.nombre
            itemView.tv_nombre.setTextColor(Color.parseColor(proyecto.color_s))
            val background:Drawable = itemView.tv_nombre.background
            background.setColorFilter(Color.parseColor(proyecto.color_p), PorterDuff.Mode.SRC_ATOP)

            itemView.bt_plussProject
        }


    }


    fun dialog_add(context: Context){
        """
        val mDialogView = LayoutInflater.from(context).inflate(com.example.pruss.R.layout.dialog_add_project, null)
        //AlertDialogBuilder
        val mBuilder = AlertDialog.Builder(context)
            .setView(mDialogView)
            .setTitle("Nuevo Proyecto")        
        


        
        
        //show dialog
        val  mAlertDialog = mBuilder.show()"""
        //login button click of custom layout
        val dialog = Dialog(context)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setContentView(R.layout.dialog_add_project)

        var colorP:String ="#FF00FF"
        var colorS:String ="#00FF00"

        dialog.dialogLoginBtn.setOnClickListener {
            //dismiss dialog
            dialog.dismiss()
            //get text from EditTexts of custom layout
            //val last=items?.get((items?.size)?.minus(1)!!)

            val name = dialog.dialogNameEt.text.toString()

            if(isNetworkConnected()==true){
                it.hideKeyboard()
                ProyectToFirebase(name,colorP,colorS)
                //notifyDataSetChanged()
            }
            else
                Toast.makeText(context.applicationContext,"No es posible realizar la operacion, revise su conexion",Toast.LENGTH_SHORT).show()
            //items?.set(items?.size?.minus(1)!!,new_element)


            //val email = mDialogView.dialogEmailEt.text.toString()
            //val password = mDialogView.dialogPasswEt.text.toString()
            //set the input text in TextView
            //mainInfoTv.setText("Name:"+ name +"\nEmail: "+ email +"\nPassword: "+ password)

        }

        dialog.bt_colorP.setOnClickListener {

            if(dialog.colorpickP.visibility==View.GONE)
                dialog.colorpickP.visibility =View.VISIBLE
            else
                dialog.colorpickP.visibility =View.GONE

        }
        dialog.bt_colorS.setOnClickListener {

            if(dialog.colorpickS.visibility==View.GONE)
                dialog.colorpickS.visibility =View.VISIBLE
            else
                dialog.colorpickS.visibility =View.GONE

        }
/*
        dialog.colorpickP.setOnClickListener{
            var color = dialog.colorpickP.color
            Log.d("coloresJ",color.toString())

        }*/


        dialog.colorpickP.setColorListener(
            ColorListener { color, fromUser -> Log.d("colores",color.toString())
                dialog.tv_p.setBackgroundColor(color)
                colorP =  java.lang.Integer.toHexString(color)
            }
        )

        dialog.colorpickS.setColorListener(
            ColorListener { color, fromUser ->
                dialog.tv_s.setBackgroundColor(color)
                colorS =  java.lang.Integer.toHexString(color)
            }
        )

        //cancel button click of custom layout
        dialog.dialogCancelBtn.setOnClickListener {
            //dismiss dialog
            dialog.dismiss()
        }
        dialog.show()
    }
    fun View.hideKeyboard() {
        val inputManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(windowToken, 0)
    }
    fun ProyectToFirebase(name:String,colorP:String,colorS: String){
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("proyectos")
        val proyectId = myRef.push().key

        val proyect: proyecto =
            proyecto(proyectId!!, name, '#'+colorP,'#'+ colorS)

        myRef.child(proyect.id).setValue(proyect)

        subscribe_projec2user(proyectId,name)

        //save_projects_room(proyect)



    }

    fun subscribe_projec2user(projecid:String,name: String){
        val id_user: String = FirebaseAuth.getInstance().currentUser!!.uid
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("subscribe_project2user")



        myRef.child(id_user).child(projecid).setValue(name)

    }




    private fun isNetworkConnected(): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
        val isConnected: Boolean = activeNetwork?.isConnectedOrConnecting == true
        return  isConnected
    }

}


