package com.example.nhfintechsproutmarket;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignupActivity extends AppCompatActivity {

    private static final String TAG = "SignupActivity";

    @BindView(R.id.input_name) EditText _nameText;
    @BindView(R.id.input_password) EditText _passwordText;
    @BindView(R.id.input_height) EditText _heightText;
    @BindView(R.id.input_weight) EditText _weightText;
    @BindView(R.id.input_age) EditText _ageText;
    @BindView(R.id.radioMale) RadioButton _maleRadio;
    @BindView(R.id.btn_signup) Button _signupButton;
    @BindView(R.id.link_login) TextView _loginLink;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);

        _signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });

        _loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the registration screen and return to the Login activity
                finish();
            }
        });
    }

    public void signup() {
        Log.d(TAG, "Signup");

        if (!validate()) {
            onSignupFailed();
            return;
        }

        _signupButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(SignupActivity.this,
                R.style.AppTheme);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Creating Account...");
        progressDialog.show();

        // TODO: Implement your own signup logic here.

        String url = getString(R.string.server_url) + "/user/join";
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                response -> {
                    // response
                    onSignupSuccess();
                },
                error -> {
                    // error
                    onSignupFailed();
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<>();
                params.put("name", _nameText.getText().toString());
                params.put("password", _passwordText.getText().toString());
                params.put("weight", _weightText.getText().toString());
                params.put("height", _heightText.getText().toString());
                params.put("age", _ageText.getText().toString());
                params.put("gender", Boolean.toString(_maleRadio.isChecked()));
                return params;
            }
        };

        // Add the request to the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(postRequest);
    }


    public void onSignupSuccess() {
        _signupButton.setEnabled(true);
        setResult(RESULT_OK, null);
        finish();
    }

    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        _signupButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String name = _nameText.getText().toString();
        String password = _passwordText.getText().toString();

        if (name.isEmpty() || name.length() < 3) {
            _nameText.setError("at least 3 characters");
            valid = false;
        } else {
            _nameText.setError(null);
        }
        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        return valid;
    }
}
