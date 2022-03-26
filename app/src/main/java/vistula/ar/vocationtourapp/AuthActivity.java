package vistula.ar.vocationtourapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import vistula.ar.vocationtourapp.fragments.SignInFragment;


public class AuthActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        getSupportFragmentManager().beginTransaction().replace(R.id.frameAuthContainer, new SignInFragment()).commit();
    }
}