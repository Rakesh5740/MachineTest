package com.tejendramachinetest

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.tejendramachinetest.authentication.AuthenticationActivity
import com.tejendramachinetest.navigationdrawermodule.HomeActivity
import com.tejendramachinetest.utils.PreferenceStore

class SplashActivity : AppCompatActivity() {
    lateinit var handler: Handler
    lateinit var runnable: Runnable
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        runnable = Runnable {
            if (isLoggedIn) {
                startActivity(Intent(this@SplashActivity, HomeActivity::class.java))
                finish()
            } else {
                startActivity(Intent(this@SplashActivity, AuthenticationActivity::class.java))
                finish()
            }
        }
    }

    val isLoggedIn: Boolean
        get() = PreferenceStore.getInstance(this).isLoggedIn

    override fun onResume() {
        super.onResume()
        handler = Handler()
        handler.postDelayed(runnable, 1000)
    }

    override fun onPause() {
        super.onPause()
        handler.removeCallbacks(runnable)
    }
}