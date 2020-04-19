package com.example.pruss.ui.home

import android.app.AlertDialog
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pruss.R
import com.example.pruss.SesionRoom
import com.example.pruss.model.LocalTask.Task
import com.example.pruss.model.LocalTask.TaskDAO
import com.example.pruss.model.ProyectoRoom
import com.example.pruss.ui.adapters.Rv_AdapterProyects
import com.example.pruss.model.proyecto
import com.example.pruss.ui.adapters.Rv_AdapterTask
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.dialog_new_task.view.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import java.sql.Types.NULL
import kotlin.collections.ArrayList

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    var  Proyectos:MutableList<proyecto> = mutableListOf()
    lateinit var adaptador: Rv_AdapterProyects
    lateinit var  adapterTask: Rv_AdapterTask
    var allTask : MutableList<Task> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_home, container, false)


        val navigationView : NavigationView  = activity!!.findViewById(R.id.nav_view)
        val headerView : View = navigationView.getHeaderView(0)
        headerView.findViewById<TextView>(R.id.tv_header_title).text=getString(R.string.Home)


/////////////////////////
        root.rv_task.layoutManager = LinearLayoutManager(
            activity!!.applicationContext,
            RecyclerView.VERTICAL,
            false
        )
        //root.rv_task.setHasFixedSize(true)

        var taskDAO: TaskDAO = SesionRoom.databaseTask.TaskDAO()
         allTask = taskDAO.getTasks().toMutableList()

        adapterTask = Rv_AdapterTask(
            activity!!.applicationContext,
            allTask as ArrayList<Task>
        )

        root.rv_task.adapter = adapterTask

        val params = root.rv_task.layoutParams


        if (allTask.size > 5){
            params.height = 300
            root.rv_task.layoutParams = params
        }



        //////////////////////////////////
        adaptador = Rv_AdapterProyects(activity!!,Proyectos as ArrayList<proyecto>)
        root.rv_proyectos.layoutManager = LinearLayoutManager(activity!!.applicationContext,LinearLayoutManager.HORIZONTAL,false)

        root.rv_proyectos.adapter=adaptador
        root.rv_proyectos.setHasFixedSize(true)

        root.tv_add.setOnClickListener {
            //Mostrar dialog Task
            dialog_add(activity!!)
        }

        return root
    }


    override fun onResume() {
        super.onResume()
        if (isNetworkConnected()==true)
            ChargeFirebaseProjects()
        else
            ChargeProjectsRooms()

    }

    private fun isNetworkConnected(): Boolean {
        val connectivityManager = context!!.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
        val isConnected: Boolean = activeNetwork?.isConnectedOrConnecting == true
        return  isConnected
    }


    private fun ChargeFirebaseProjects(){

        val id_user: String = FirebaseAuth.getInstance().currentUser!!.uid
        val database = FirebaseDatabase.getInstance()
        Proyectos.add(
            proyecto(
                "0",
                "+Add",
                "#2BF188",
                "#000000"
            )
        )

        getsubscribers_project2user(id_user,database)



    }


    fun getsubscribers_project2user(id_user:String,database:FirebaseDatabase){

        val myRef = database.getReference("subscribe_project2user")

        myRef.child(id_user).addValueEventListener(object: ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(p0: DataSnapshot)
            {
                if (p0.exists())
                {
                    Log.e("Count","" + p0.childrenCount)
                    val list:List<String> = p0.children.map{ it.key!!}

                    get_projects(database,list)

                 }
            }

        })

    }

    fun get_projects(database: FirebaseDatabase, list: List<String>){
        Log.e("ListaE","" + list)

        val myRefP = database.getReference("proyectos")


            Proyectos.clear()
            Proyectos.add(
                proyecto(
                    "0",
                    "+Add",
                    "#2BF188",
                    "#000000"
                )
            )
            myRefP.addValueEventListener(object : ValueEventListener {
                override fun onCancelled(p1: DatabaseError) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onDataChange(p1: DataSnapshot) {
                    if(p1.exists()) {
                        for (key in list){
                            val pro: proyecto=p1.child(key).getValue(proyecto::class.java)!!
                            Proyectos.add((pro))

                        }
                        save_projects_room(Proyectos)
                        adaptador.notifyDataSetChanged()

                    }

                }


            })

    }


    fun dialog_add(context: Context){

        val mDialogView = LayoutInflater.from(context).inflate(R.layout.dialog_new_task, null)
        //AlertDialogBuilder
        val mBuilder = AlertDialog.Builder(context)
            .setView(mDialogView)

        mDialogView.calendarTask.visibility=View.GONE
        mDialogView.invalidate()
        //show dialog
        val  mAlertDialog = mBuilder.show()
        //login button click of custom layout
        //mAlertDialog.window!!.setBackgroundDrawableResource(android.R.color.transparent)
        mAlertDialog.window!!.getDecorView().getBackground().setAlpha(0)
        mAlertDialog.window!!.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        mDialogView.bt_time.setOnClickListener{
            mDialogView.calendarTask.visibility=View.GONE
            if(mDialogView.tp_hora.visibility == View.VISIBLE){
                mDialogView.tp_hora.visibility =View.GONE
            }
            else{
                mDialogView.tp_hora.visibility =View.VISIBLE
            OnClickTime(mDialogView)
            }

        }

        mDialogView.btt_calendar.setOnClickListener {
            mDialogView.tp_hora.visibility =View.GONE
            Oncalender(mDialogView)

            if(mDialogView.calendarTask.visibility==View.VISIBLE){
                mDialogView.calendarTask.visibility=View.GONE
                mDialogView.invalidate()

            }
            else {
                mDialogView.calendarTask.visibility = View.VISIBLE
                mDialogView.invalidate()

            }


        }


        mDialogView.bt_agg.setOnClickListener {
            //dismiss dialog
            val title :String = mDialogView.tv_task.text.toString()
            val fecha: String = mDialogView.tv_date.text.toString()
            val hora:String =mDialogView.tv_hora.text.toString()
            val task:Task = Task(NULL,title,fecha,hora,0)
            saveTaskRoom(task)

            mAlertDialog.dismiss()


        }
        //cancel button click of custom layout
        mDialogView.bt_cancel.setOnClickListener {
            //dismiss dialog
            mAlertDialog.dismiss()
        }
    }


    private fun OnClickTime(mDialog:View) {
        val timePicker = mDialog.tp_hora
        timePicker.setOnTimeChangedListener { _, hour, minute -> var hour = hour
            var am_pm = ""
            // AM_PM decider logic
            when {hour == 0 -> { hour += 12
                am_pm = "am"
            }
                hour == 12 -> am_pm = "pm"
                hour > 12 -> { hour -= 12
                    am_pm = "pm"
                }
                else -> am_pm = "am"
            }
            if (mDialog.tv_hora != null) {
                val hour = if (hour < 10) "0" + hour else hour
                val min = if (minute < 10) "0" + minute else minute
                // display format of time
                val msg = "$hour:$min $am_pm"
                mDialog.tv_hora.text = msg
                mDialog.tv_hora.visibility = ViewGroup.VISIBLE
            }
        }
    }

    private  fun Oncalender(mDialog: View){

        val calender = mDialog.calendarTask

        // Add Listener in calendar
        calender?.setOnDateChangeListener { _, year, month, dayOfMonth ->
            // Note that months are indexed from 0. So, 0 means January, 1 means february, 2 means march etc.
            val msg =  dayOfMonth.toString() + "/" + (month + 1) + "/" + year
            mDialog.tv_date.text =msg
        }

    }

    private  fun saveTaskRoom(task: Task){

        val room= SesionRoom.databaseTask.TaskDAO()

        room.insertTask(task)
        allTask.add(task)

        //adapterTask.notifyItemInserted(allTask.size)
        Log.d("datospas",Proyectos.size.toString())
        //rv_proyectos.layoutManager =LinearLayoutManager(activity!!.applicationContext,LinearLayoutManager.HORIZONTAL,false)
        //val adaptador2 =Rv_AdapterProyects(activity!!.applicationContext,Proyectos as ArrayList<proyecto>)
        //rv_proyectos.adapter=adaptador2

        adapterTask.notifyDataSetChanged()

    }


    private fun save_projects_room(proyectos: MutableList<proyecto>){
        for (pro in proyectos) {

            val proyecto: ProyectoRoom = ProyectoRoom(pro.id, pro.nombre, pro.color_p, pro.color_s)
            val room = SesionRoom.databaseR.proyectoDAO()
            val proyectoR= room.searchProyecto(pro.id)
            if(proyectoR==null)
                room.insertProyecto(proyecto)
        }

    }

    fun ChargeProjectsRooms(){
        val room = SesionRoom.databaseR.proyectoDAO()
        val proyectos=room.getProyectos()
        for (pro in proyectos) {

            val proyecto: proyecto = proyecto(pro.id, pro.nombre, pro.color_p, pro.color_s)
            Proyectos.add(proyecto)
        }
        adaptador.notifyDataSetChanged()

    }

}