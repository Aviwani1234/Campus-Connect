package com.example.campus_connect_app

import DatabaseHelper
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.campus_connect_app.model.Complaint
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class ComplaintActivity : AppCompatActivity() {
    var mList: ArrayList<Complaint> = ArrayList()
    var rv: RecyclerView? = null
    var adapter: ShowAllComplaintAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        enableEdgeToEdge()
        setContentView(R.layout.activity_complaint)
        mList = ArrayList();
        adapter = ShowAllComplaintAdapter(this,mList);
        rv = findViewById(R.id.rv_showAllFood)

        showAllComplaints()
       /* ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }*/

        val addComplaint = findViewById<FloatingActionButton>(R.id.addComplaint)
        addComplaint.setOnClickListener {
            val intent = Intent(this, AddComplaintActivity::class.java)
            startActivity(intent)
        }

    }

    fun showAllComplaints(){
        /* val firebaseUser = FirebaseAuth.getInstance().currentUser
         if (firebaseUser!!.uid != null) {
             val reference =
                 FirebaseDatabase.getInstance().getReference().child(RegisterActivity.COMPLAINT)
                     .child(model.getUserName())
             reference.addListenerForSingleValueEvent(object : ValueEventListener {
                 override fun onDataChange(snapshot: DataSnapshot) {
                     mList.clear()
                     for (dataSnapshot in snapshot.getChildren()) {
                         val model = dataSnapshot.getValue(complaint::class.java)
                         mList.add(model)
                     }
                     adapter = ShowAllComplaintAdapter(this@DepartmentDashboard, mList)
                     rv.setAdapter(adapter)
                 }

                 override fun onCancelled(error: DatabaseError) {}
             })
         }*/


//        val mItem1 = Complaint("1", "Ramesh", "Garbage", "Open", "Work on progress", R.drawable.dirtyriver, "This place needs to be clean")
//        val mItem2 = Complaint("2", "Pravin", "Water", "In progress", "The water will be cleaned within one week", R.drawable.garbage, "The water is dirty")
//
//        mList.add(mItem1)
//        mList.add(mItem2)

        GlobalScope.launch(Dispatchers.IO) {
            val dbHelper = DatabaseHelper(applicationContext)
            val complaintsFromDb = dbHelper.getAllComplaints()

            launch(Dispatchers.Main){
                mList.clear()

                val complaint1 = Complaint(null, "Rajesh", "Garbage Problem", "", "Pending", "",
//                    BitmapFactory.decodeResource(resources, R.drawable.garbage),
                    R.drawable.garbage,
                    "Here is too much garbage in our area. Its making the are unhealthy for the people living here. We have requested earlier but still there is no action taken. So its my humber request to please take appropriate action again this garbage issue."
                )
                mList.add(complaint1)
                mList.addAll(complaintsFromDb)

                adapter = ShowAllComplaintAdapter(applicationContext, mList)
                val lManager = LinearLayoutManager(applicationContext)

                rv?.layoutManager = lManager
                rv?.setAdapter(adapter)

                adapter?.notifyDataSetChanged()
            }
        }




//        val complaint1 = Complaint(null, "Rajesh", "Garbage Problem", "","Pending","",
//            BitmapFactory.decodeResource(resources, R.drawable.garbage),"Here is too much garbage in our area. Its making the are unhealthy for the people living here. We have requested earlier but still there is no action taken. So its my humber request to please take appropriate action again this garbage issue.",
//        )
////        mList.add(Complaint(null, "Rajesh", "Garbage Problem", "","Pending","",
////            BitmapFactory.decodeResource(resources, R.drawable.garbage),"Here is too much garbage in our area. Its making the are unhealthy for the people living here. We have requested earlier but still there is no action taken. So its my humber request to please take appropriate action again this garbage issue.",
////            ))
//        mList.add(complaint1)
//        mList.addAll(complaintsFromDb)
//
//
//        adapter = ShowAllComplaintAdapter(this, mList)
//        val lManager = LinearLayoutManager(this)
//
//        rv?.layoutManager = lManager
//        rv?.setAdapter(adapter)
    }

}