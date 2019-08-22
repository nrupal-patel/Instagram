package com.example.instagram.Profile;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.instagram.Login.LoginActivity;
import com.example.instagram.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignOutFragment extends Fragment {

    private static final String TAG = "SignOutFragment";

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListner;

    private ProgressBar mProgressBar;
    private TextView tvSignOut,tvSigningOut;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_signout,container,false);
        tvSignOut = view.findViewById(R.id.tvConfirmSignOut);
        tvSigningOut = view.findViewById(R.id.tvSigningOut);
        mProgressBar = view.findViewById(R.id.signoutProgressBar);
        Button btnConfirmSignOut = view.findViewById(R.id.btnConfirmSignOut);

        mProgressBar.setVisibility(View.VISIBLE);
        tvSigningOut.setVisibility(View.VISIBLE);

        setupFirebaseAuth();

        btnConfirmSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: attempting to sign out");

                mProgressBar.setVisibility(View.GONE);
                tvSigningOut.setVisibility(View.GONE);

                mAuth.signOut();
                getActivity().finish();
            }
        });

        return view;
    }

    /*......setup the Firebase auth object............*/

    private void setupFirebaseAuth(){
        Log.d(TAG, "setupFirebaseAuth: setting up the firebase auth.");

        mAuth = FirebaseAuth.getInstance();

        mAuthListner = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                FirebaseUser user = firebaseAuth.getCurrentUser();

                if (user != null){
                    Log.d(TAG, "onAuthStateChanged: signed_in" + user.getUid());
                } else {
                    Log.d(TAG, "onAuthStateChanged: signed_out");

                    Log.d(TAG, "onAuthStateChanged: navigate back to  login screen..");
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
            }
        };
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListner);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListner != null){
            mAuth.removeAuthStateListener(mAuthListner);
        }
    }
}
