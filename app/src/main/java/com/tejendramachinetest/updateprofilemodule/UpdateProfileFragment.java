package com.tejendramachinetest.updateprofilemodule;


import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.tejendramachinetest.R;
import com.tejendramachinetest.utils.Helper;
import com.tejendramachinetest.utils.PreferenceConstants;
import com.tejendramachinetest.utils.PreferenceStore;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */

public class UpdateProfileFragment extends Fragment {

    private static final String TAG = "UPDATEFRAGMENT";
    private CircleImageView mProfileImage;
    private EditText mEtName;
    private EditText mEtEmailId;
    private EditText mEtNumber;
    private ProgressBar mProgressBar;
    private Button btVerifyNumber;
    private Context mContext;

    private Uri fileUri; // file url to store image

    private static final int PERMISSION_REQUEST_CODE = 1;
    private final static int IMAGE_RESULT = 200;


    public UpdateProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        mContext = context;
    }

    @Override
    public void onDetach() {
        super.onDetach();

        mContext = null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_update_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mProfileImage = view.findViewById(R.id.profile_image);
        mEtName = view.findViewById(R.id.et_name);
        mEtEmailId = view.findViewById(R.id.et_email);
        mEtNumber = view.findViewById(R.id.et_mobile);
        mProgressBar = view.findViewById(R.id.progress_bar);
        btVerifyNumber = view.findViewById(R.id.bt_verify_mobile_number);

        btVerifyNumber.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                String mobileNumber = mEtNumber.getText().toString().trim();

                if (Helper.validateMobileNumber(mobileNumber)) {
                    mobileNumber = "+91" + mobileNumber;
                    verifyPhoneNumber(mobileNumber);
                } else {
                    Toast.makeText(mContext, "Invalid Mobile Number", Toast.LENGTH_SHORT).show();
                }
            }
        });

        mProfileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= 23) {
                    if (Helper.checkStoragePermission(mContext) && Helper.checkCameraPermission(mContext)) {
                        startActivityForResult(getPickImageChooserIntent(), IMAGE_RESULT);
                    } else {
                        Helper.requestPermission(mContext, PERMISSION_REQUEST_CODE); // Code for permission
                    }
                } else {
                    startActivityForResult(getPickImageChooserIntent(), IMAGE_RESULT);
                }

            }
        });

        setProfileInfo();
    }

//    private void chooseFromGallery() {
//        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//        intent.setType("image/*");
//        startActivityForResult(intent, IMAGE_RESULT);
//    }

    private void setProfileInfo() {
        String imageURL = PreferenceStore.getInstance(mContext).getStringValue(PreferenceConstants.IMAGE_URI);
        String displayName = PreferenceStore.getInstance(mContext).getStringValue(PreferenceConstants.NAME);
        String emailId = PreferenceStore.getInstance(mContext).getStringValue(PreferenceConstants.EMAIL);
        String mobileNumber = PreferenceStore.getInstance(mContext).getStringValue(PreferenceConstants.NUMBER);

        if(imageURL!=null) {
            Glide.with(mContext).load(Uri.parse(imageURL)).listener(new RequestListener<Drawable>() {
                @Override
                public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {

                    mProgressBar.setVisibility(View.GONE);
                    mProfileImage.setImageResource(R.drawable.default_profile);
                    return false;

                }

                @Override
                public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                    mProgressBar.setVisibility(View.GONE);

                    return false;
                }
            }).into(mProfileImage);
        }else {
            mProgressBar.setVisibility(View.GONE);
            mProfileImage.setImageResource(R.drawable.default_profile);
        }
        mEtName.setText(displayName);
        mEtEmailId.setText(emailId);

        if (mobileNumber != null) {
            btVerifyNumber.setVisibility(View.GONE);
            mEtNumber.setText(mobileNumber);
            mEtNumber.setFocusableInTouchMode(false);
        } else {
            btVerifyNumber.setVisibility(View.VISIBLE);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void verifyPhoneNumber(String phoneNumber) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                Objects.requireNonNull(getActivity()),               // Activity (for callback binding)
                mCallbacks);        // OnVerificationStateChangedCallbacks

    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        @Override
        public void onVerificationCompleted(PhoneAuthCredential credential) {
            // This callback will be invoked in two situations:
            // 1 - Instant verification. In some cases the phone number can be instantly
            //     verified without needing to send or enter a verification code.
            // 2 - Auto-retrieval. On some devices Google Play services can automatically
            //     detect the incoming verification SMS and perform verification without
            //     user action.
            Log.d(TAG, "onVerificationCompleted:" + credential);

            Toast.makeText(mContext, "Verification Completed", Toast.LENGTH_SHORT).show();

        }


        @Override
        public void onVerificationFailed(FirebaseException e) {
            // This callback is invoked in an invalid request for verification is made,
            // for instance if the the phone number format is not valid.
            Log.w(TAG, "onVerificationFailed", e);

            if (e instanceof FirebaseAuthInvalidCredentialsException) {
                Toast.makeText(mContext, "Invalid request", Toast.LENGTH_SHORT).show();
            } else if (e instanceof FirebaseTooManyRequestsException) {
                Toast.makeText(mContext, "The SMS quota for the project has been exceeded", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onCodeSent(@NonNull String verificationId,
                               @NonNull PhoneAuthProvider.ForceResendingToken token) {
            Toast.makeText(mContext, "Code sent successfully", Toast.LENGTH_SHORT).show();
            PreferenceStore.getInstance(mContext).saveString(PreferenceConstants.NUMBER, mEtNumber.getText().toString());
            btVerifyNumber.setVisibility(View.GONE);
            mEtNumber.setFocusableInTouchMode(false);
            mEtNumber.setFocusable(false);
        }
    };

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.e("value", "Permission Granted, Now you can use local drive .");
            } else {
                Log.e("value", "Permission Denied, You cannot use local drive .");
            }
        }
    }

    public Intent getPickImageChooserIntent() {
        Uri outputFileUri = getCaptureImageOutputUri();
        List<Intent> allIntents = new ArrayList<>();
        PackageManager packageManager = mContext.getPackageManager();

        Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        List<ResolveInfo> listCam = packageManager.queryIntentActivities(captureIntent, 0);
        for (ResolveInfo res : listCam) {
            Intent intent = new Intent(captureIntent);
            intent.setComponent(new ComponentName(res.activityInfo.packageName, res.activityInfo.name));
            intent.setPackage(res.activityInfo.packageName);
            if (outputFileUri != null) {
                intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
            }
            allIntents.add(intent);
        }

        Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        List<ResolveInfo> listGallery = packageManager.queryIntentActivities(galleryIntent, 0);
        for (ResolveInfo res : listGallery) {
            Intent intent = new Intent(galleryIntent);
            intent.setComponent(new ComponentName(res.activityInfo.packageName, res.activityInfo.name));
            intent.setPackage(res.activityInfo.packageName);
            allIntents.add(intent);
        }

        Intent mainIntent = allIntents.get(allIntents.size() - 1);
        for (Intent intent : allIntents) {
            if (intent.getComponent().getClassName().equals("com.android.documentsui.DocumentsActivity")) {
                mainIntent = intent;
                break;
            }
        }
        allIntents.remove(mainIntent);

        Intent chooserIntent = Intent.createChooser(mainIntent, "Select source");
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, allIntents.toArray(new Parcelable[allIntents.size()]));

        return chooserIntent;
    }

    private Uri getCaptureImageOutputUri() {
        Uri outputFileUri = null;
        File getImage = mContext.getExternalFilesDir("");
        if (getImage != null) {
            outputFileUri = Uri.fromFile(new File(getImage.getPath(), "profile.png"));
        }
        return outputFileUri;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == IMAGE_RESULT) {
                String filePath = getImageFilePath(data);
                try {
                    if (filePath != null) {
                        Bitmap selectedImage = BitmapFactory.decodeFile(filePath);

                        if (selectedImage != null)
                            mProfileImage.setImageBitmap(selectedImage);
                        else {
                            setGalleryImage(data);
                        }
                    }
                } catch (Exception e) {
                    Log.e("Gallery Error", e.getLocalizedMessage());
                }
            }
        }
    }

    private void setGalleryImage(Intent data) {
        try {
            if (data != null) {
                Uri contentURI = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), contentURI);
                    mProfileImage.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity(), "Failed!", Toast.LENGTH_SHORT).show();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getImageFromFilePath(Intent data) {
        boolean isCamera = data == null || data.getData() == null;
        if (isCamera) return getCaptureImageOutputUri().getPath();
        else return getPathFromURI(data.getData());

    }

    public String getImageFilePath(Intent data) {
        return getImageFromFilePath(data);
    }

    private String getPathFromURI(Uri contentUri) {
        String[] proj = {MediaStore.Audio.Media.DATA};
        Cursor cursor = mContext.getContentResolver().query(contentUri, proj, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }


}
