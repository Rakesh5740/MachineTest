package com.tejendramachinetest.authentication

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.tejendramachinetest.R
import com.tejendramachinetest.navigationdrawermodule.HomeActivity
import com.tejendramachinetest.utils.GoogleClientBuilder
import com.tejendramachinetest.utils.Helper
import com.tejendramachinetest.utils.PreferenceConstants
import com.tejendramachinetest.utils.PreferenceStore

class AuthenticationActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mGoogleSignInClient: GoogleSignInClient
    private lateinit var dialog :Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authentication)

        mGoogleSignInClient = GoogleClientBuilder.getInstance().googleSignInClient(this)
        findViewById<View>(R.id.sign_in_button).setOnClickListener(this)
    }

    private fun signIn() {
        dialog = Helper.showLoader(this)
        val signInIntent = mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) { // The Task returned from this call is always completed, no need to attach
// a listener.
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)
            dialog.dismiss()

            // Signed in successfully, show authenticated UI.
            updateUI(account)
        } catch (e: ApiException) { // The ApiException status code indicates the detailed failure reason.
// Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w(TAG, "signInResult:failed code=" + e.statusCode)
            dialog.dismiss()
        }
    }

    private fun updateUI(o: GoogleSignInAccount?) {
        if (o == null) return
        PreferenceStore.getInstance(this).logIn()
        if(o.photoUrl !=null) {
            val uri = o.photoUrl!!
            PreferenceStore.getInstance(this).saveString(PreferenceConstants.IMAGE_URI, uri.toString())
        }
        PreferenceStore.getInstance(this).saveString(PreferenceConstants.NAME, o.displayName)
        PreferenceStore.getInstance(this).saveString(PreferenceConstants.EMAIL, o.email)
        Toast.makeText(this@AuthenticationActivity,getString(R.string.loggedin_successful),Toast.LENGTH_SHORT).show()
        startActivity(Intent(this, HomeActivity::class.java))
        finish()
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.sign_in_button -> signIn()
        }
    }

    companion object {
        private const val RC_SIGN_IN = 1
        private const val TAG = "MAINACTIVITY"
    }
}