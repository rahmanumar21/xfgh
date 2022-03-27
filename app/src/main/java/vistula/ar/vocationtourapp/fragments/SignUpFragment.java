package vistula.ar.vocationtourapp.fragments;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import vistula.ar.vocationtourapp.AuthActivity;
import vistula.ar.vocationtourapp.Constant;
import vistula.ar.vocationtourapp.R;
import vistula.ar.vocationtourapp.UserInfoActivity;

public class SignUpFragment extends Fragment {

    private View view;
    private TextInputLayout layoutEmail, layoutPassword, layoutConfirm;
    private TextInputEditText txtEmail, txtPassword, txtConfirm;
    private TextView txtSignIn;
    private Button btnSignUp;
    private ProgressDialog dialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_sign_up_fragment, container, false);
        init();
        return view;
    }

    private void init() {
        layoutEmail = view.findViewById(R.id.txtLayoutEmailSignUp);
        txtEmail = view.findViewById(R.id.txtEmailSignUp);
        layoutPassword = view.findViewById(R.id.txtLayoutPasswordSignUp);
        layoutConfirm = view.findViewById(R.id.txtLayoutConfirmSignUp);
        txtPassword = view.findViewById(R.id.txtPasswordSignUp);
        txtConfirm = view.findViewById(R.id.txtPasswordConfirmSignUp);
        txtSignIn = view.findViewById(R.id.txtSignIn);
        btnSignUp = view.findViewById(R.id.btnSignUp);

        dialog = new ProgressDialog(getContext());
        dialog.setCancelable(false);

        txtSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //change fragments
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameAuthContainer, new SignInFragment()).commit();
            }
        });

        btnSignUp.setOnClickListener(v->{
            //validate fields first
            if (validate()){
                register();
            }
        });

        txtEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!txtEmail.getText().toString().isEmpty()){
                    layoutEmail.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        txtPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (txtPassword.getText().toString().length() > 7){
                    layoutPassword.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        txtConfirm.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (txtConfirm.getText().toString().equals(txtPassword.getText().toString())){
                    layoutConfirm.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }



    private boolean validate() {

        //email required
        if (txtEmail.getText().toString().isEmpty()){
            layoutEmail.setErrorEnabled(true);
            layoutEmail.setError("Email is required");
            return false;
        }

        //password required at least 8 characters
        if (txtPassword.getText().toString().isEmpty()){
            layoutPassword.setErrorEnabled(true);
            layoutPassword.setError("Required at least 8 characters");
            return false;
        }

        if (!txtConfirm.getText().toString().equals(txtPassword.getText().toString())){
            layoutConfirm.setErrorEnabled(true);
            layoutConfirm.setError("Password does not match");
        }
        return true;
    }

    private void register() {
        dialog.setMessage("Registering");
        dialog.show();
        StringRequest request = new StringRequest(Request.Method.POST, Constant.REGISTER, response -> {

            //we get response if connection success
            try {
                JSONObject object = new JSONObject(response);
                if (object.getBoolean("success")){
                    JSONObject user = object.getJSONObject("user");
                    //make shared preference user
                    SharedPreferences userPref = getActivity().getApplicationContext().getSharedPreferences("user", getContext().MODE_PRIVATE);
                    SharedPreferences.Editor editor = userPref.edit();
                    editor.putString("token", object.getString("token"));
                    editor.putString("name", user.getString("name"));
                    editor.putInt("id", user.getInt("id"));
                    editor.putString("lastname", user.getString("lastname"));
                    editor.putString("photo", user.getString("photo"));
                    editor.putBoolean("isLoggedIn", true);
                    editor.apply();
                    //if success
                    startActivity(new Intent(((AuthActivity) getContext()), UserInfoActivity.class));
                    ((AuthActivity)getContext()).finish();
                    Toast.makeText(getContext(), "Register Success", Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            dialog.dismiss();

        }, error -> {
            //error if connection not success
            error.printStackTrace();
            dialog.dismiss();
        }){
            //add parameters
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> map = new HashMap<>();
                map.put("email", txtEmail.getText().toString().trim());
                map.put("password", txtPassword.getText().toString().trim());
                return map;
            }
        };

        //add this request is request queue
        RequestQueue queue = Volley.newRequestQueue(getContext());
        queue.add(request);
    }

}