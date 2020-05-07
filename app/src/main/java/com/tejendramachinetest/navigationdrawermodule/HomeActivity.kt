package com.tejendramachinetest.navigationdrawermodule

import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.tejendramachinetest.R
import com.tejendramachinetest.authentication.AuthenticationActivity
import com.tejendramachinetest.contactModule.GetContactActivity
import com.tejendramachinetest.imagetaskmodule.ImageListActivity
import com.tejendramachinetest.updateprofilemodule.UpdateProfileFragment
import com.tejendramachinetest.utils.*
import kotlinx.android.synthetic.main.activity_home.*
import java.util.*

class HomeActivity : AppCompatActivity() {

    private var mDrawerToggle: ActionBarDrawerToggle? = null


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        setupToolbar()
        val drawerItem = prepareDataForDrawer()
        Objects.requireNonNull(supportActionBar)?.setDisplayHomeAsUpEnabled(false)
        supportActionBar!!.setHomeButtonEnabled(true)
        val adapter = DrawerItemCustomAdapter(this, R.layout.navigation_list_row_item, drawerItem)
        left_drawer.adapter = adapter
        left_drawer.onItemClickListener = DrawerItemClickListener()

        setupDrawerToggle()

        drawer_layout.addDrawerListener(mDrawerToggle!!)
        loadChild(UpdateProfileFragment())
        setUserProfileData()
    }

    private fun prepareDataForDrawer(): Array<DataModel?> {
        val drawerItem = arrayOfNulls<DataModel>(4)
        drawerItem[0] = DataModel(R.drawable.user, "User Profile")
        drawerItem[1] = DataModel(R.drawable.contact, "Contacts")
        drawerItem[2] = DataModel(R.drawable.photo, "Image List")
        drawerItem[3] = DataModel(R.drawable.logout, "Logout")
        return drawerItem
    }

    private fun setUserProfileData() {
        val imageURL = PreferenceStore.getInstance(this).getStringValue(PreferenceConstants.IMAGE_URI)
        val displayName = PreferenceStore.getInstance(this).getStringValue(PreferenceConstants.NAME)
        if(imageURL!=null) {
            Glide.with(this).load(Uri.parse(imageURL)).listener(object : RequestListener<Drawable?> {
                override fun onLoadFailed(e: GlideException?, model: Any, target: Target<Drawable?>, isFirstResource: Boolean): Boolean {
                    tv_profile!!.setImageResource(R.drawable.default_profile)
                    return false
                }

                override fun onResourceReady(resource: Drawable?, model: Any, target: Target<Drawable?>, dataSource: DataSource, isFirstResource: Boolean): Boolean {
                    return false
                }
            }).into(tv_profile)
        }else{
            tv_profile.setImageResource(R.drawable.default_profile)

        }
        tv_name.text = displayName
    }

    private inner class DrawerItemClickListener : OnItemClickListener {
        override fun onItemClick(parent: AdapterView<*>?, view: View, position: Int, id: Long) {
            selectItem(position)
        }
    }

    private fun selectItem(position: Int) {
        when (position) {
            0 -> loadChild(UpdateProfileFragment())
            1 -> startActivity(Intent(this, GetContactActivity::class.java))
            2 -> startActivity(Intent(this, ImageListActivity::class.java))
            3 -> logOutPermission()
        }
        closeDrawer()
    }

    private fun logout() {
        GoogleClientBuilder.getInstance().googleSignInClient(this).signOut()
        PreferenceStore.getInstance(this).logOut(PreferenceConstants.IS_LOGGEDIN)
        startActivity(Intent(this, AuthenticationActivity::class.java))
        finishAffinity()
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        mDrawerToggle!!.syncState()
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    fun setupToolbar() {
        setSupportActionBar(toolbar)
        Objects.requireNonNull(supportActionBar)!!.setDisplayShowHomeEnabled(true)
    }

    fun setupDrawerToggle() {
        mDrawerToggle = object : ActionBarDrawerToggle(this, drawer_layout, toolbar, R.string.app_name, R.string.app_name){

            override fun onDrawerOpened(drawerView: View) {
                super.onDrawerOpened(drawerView)
                Helper.hideSoftKeyboard(this@HomeActivity)
            }
        }
        mDrawerToggle!!.syncState()
    }

    fun closeDrawer() {
        if (drawer_layout!!.isDrawerOpen(GravityCompat.START)) {
            drawer_layout!!.closeDrawer(GravityCompat.START)
        }
    }

    override fun onBackPressed() {
        if (drawer_layout!!.isDrawerOpen(GravityCompat.START)) {
            drawer_layout!!.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    private fun loadChild(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit()
    }

    private fun logOutPermission() {
        Helper.logOutAlert(this, object : iAlertListner {
            override fun onPressCancel() {
            }

            override fun onPressOk() {
                logout()

            }

        })
    }
}