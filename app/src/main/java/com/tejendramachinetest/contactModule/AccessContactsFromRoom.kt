package com.tejendramachinetest.contactModule

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tejendramachinetest.R
import kotlinx.android.synthetic.main.activity_access_contacts_from_room.*
import java.util.*
import java.util.concurrent.Executor
import java.util.concurrent.Executors


class AccessContactsFromRoom : AppCompatActivity() {

    private var mAdapter: ContactsAdapter? = null
    private lateinit var alContactsModel: List<User>
    private  var mDb: AppDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_access_contacts_from_room)


        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true);
        supportActionBar?.setHomeButtonEnabled(true);

        mDb = AppDatabase.getInstance(this)
        alContactsModel = ArrayList()

        mAdapter = ContactsAdapter( alContactsModel)
        recycler.adapter = mAdapter

        val executor: Executor = Executors.newSingleThreadExecutor()
        executor.execute {
            val myOrders = mDb?.userDao()!!.all
            val sortedList = myOrders.sortedBy {  it.name?.toString() }
            (alContactsModel as ArrayList<User>).addAll(sortedList)
            mAdapter!!.notifyDataSetChanged()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}