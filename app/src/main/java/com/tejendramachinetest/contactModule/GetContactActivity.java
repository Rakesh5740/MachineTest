package com.tejendramachinetest.contactModule;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.room.Room;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.tejendramachinetest.Constant;
import com.tejendramachinetest.R;
import com.tejendramachinetest.utils.Helper;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class GetContactActivity extends AppCompatActivity {

    private static final String TAG = GetContactActivity.class.getName();
    private static final int PERMISSION_REQUEST_CONTACT = 100;

    private AppDatabase database;
    private User user;
    private AppDatabase db;
    UserDao userDao;
    private ProgressBar progressBar;
    private TextView tvMessage;
    private CoordinatorLayout view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        view = findViewById(R.id.view);
        progressBar = findViewById(R.id.progress_bar);
        database = AppDatabase.getInstance(this);
        tvMessage = findViewById(R.id.tv_message);
        Toolbar toolbar = findViewById(R.id.toolbar);


        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        if (database.userDao().getAll().size() > 0) {
            showList();
            return;
        }

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, Constant.Contacts).build();


        askForContactPermission();
    }

    public void askForContactPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {

                // Should we show an explanation?
                if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.READ_CONTACTS)) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("Contacts Permission");
                    builder.setPositiveButton(android.R.string.ok, null);
                    builder.setMessage("Please confirm Contacts access");//TODO put real question
                    builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
                        @TargetApi(Build.VERSION_CODES.M)
                        @Override
                        public void onDismiss(DialogInterface dialog) {
                            requestPermissions(
                                    new String[]
                                            {Manifest.permission.READ_CONTACTS}
                                    , PERMISSION_REQUEST_CONTACT);
                        }
                    });
                    builder.show();
                    // Show an expanation to the user *asynchronously* -- don't block
                    // this thread waiting for the user's response! After the user
                    // sees the explanation, try again to request the permission.

                } else {

                    // No explanation needed, we can request the permission.

                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.READ_CONTACTS},
                            PERMISSION_REQUEST_CONTACT);


                }
            } else {
                getContactList();
            }
        } else {
            getContactList();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CONTACT: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    progressBar.setVisibility(View.VISIBLE);

                    getContactList();

                } else {
                    tvMessage.setText(getString(R.string.permission_denied_for_contact));
                    progressBar.setVisibility(View.GONE);
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    private void getContactList() {


        List<User> list = new ArrayList<>();
        ContentResolver cr = getContentResolver();
        Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI,
                null, null, null, null);
        if ((cur != null ? cur.getCount() : 0) > 0) {
            while (cur != null && cur.moveToNext()) {
                User user = new User();

                String id = cur.getString(
                        cur.getColumnIndex(ContactsContract.Contacts._ID));
                String name = cur.getString(cur.getColumnIndex(
                        ContactsContract.Contacts.DISPLAY_NAME));
                user.setName(name);

                if (cur.getInt(cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)) > 0) {
                    Cursor pCur = cr.query(
                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                            new String[]{id}, null);
                    while (pCur.moveToNext()) {
                        String phoneNo = pCur.getString(pCur.getColumnIndex(
                                ContactsContract.CommonDataKinds.Phone.NUMBER));
                        user.setNumber(phoneNo);

                    }
                    pCur.close();
                    list.add(user);

                }
            }

            cur.close();
            new InsertTask(this, list).execute();
        }
    }

    private class InsertTask extends AsyncTask<List<User>, Void, Void> {

        WeakReference<GetContactActivity> activityReference;
        List<User> user;

        // only retain a weak reference to the activity
        InsertTask(GetContactActivity context, List<User> user) {
            activityReference = new WeakReference<>(context);
            this.user = user;
            activityReference.get().userDao = activityReference.get().db.userDao();
        }

        // doInBackground methods runs on a worker thread


        @Override
        protected Void doInBackground(List<User>... lists) {
            activityReference.get().database.userDao().insertAll(user);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            showList();
        }
    }

    private void showList() {
        startActivity(new Intent(this, AccessContactsFromRoom.class));
        finish();
    }



    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
