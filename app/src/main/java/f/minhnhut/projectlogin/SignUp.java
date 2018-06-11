package f.minhnhut.projectlogin;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import f.minhnhut.projectlogin.Verify.Verify;
import f.minhnhut.projectlogin.Verify.resultLogin;

public class SignUp extends AppCompatActivity implements resultLogin {
    Button btnSignUp;
    TextInputLayout layoutemailSU, layoutpasswordSU, layoutconfirmpasswordSU;
    TextInputEditText editemailSU, editpasswordSU, editconfirmpasswordSU;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        btnSignUp = (Button) findViewById(R.id.btnSignUp);
        layoutemailSU = (TextInputLayout) findViewById(R.id.txtinlayoutSignUpEmail);
        layoutpasswordSU = (TextInputLayout) findViewById(R.id.txtinlayoutSignUpPassword);
        layoutconfirmpasswordSU = (TextInputLayout) findViewById(R.id.txtinlayoutSignUpConfirmPassword);
        editemailSU = (TextInputEditText) findViewById(R.id.editSignUpEmail);
        editpasswordSU = (TextInputEditText) findViewById(R.id.editSignUpPassword);
        editconfirmpasswordSU = (TextInputEditText) findViewById(R.id.editSignUpConfirmPassword);


        final Verify verifySU = new Verify();
        layoutpasswordSU.setPasswordVisibilityToggleEnabled(true);
        layoutconfirmpasswordSU.setPasswordVisibilityToggleEnabled(true);
        editemailSU.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                verifySU.checkEmail(editable, layoutemailSU);
            }
        });

        editpasswordSU.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
            verifySU.checkPassword(editable, layoutpasswordSU, layoutconfirmpasswordSU);
            }
        });
        editconfirmpasswordSU.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
            verifySU.checkConfirmPassword(editable, layoutpasswordSU, layoutconfirmpasswordSU);
            }
        });
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (verifySU.Verify(layoutemailSU, layoutpasswordSU, layoutconfirmpasswordSU))
                {
                    successLogin();
                }
                else
                {
                    failedLogin();
                }
                if (!verifySU.validateEmail(layoutemailSU)) {
                    layoutemailSU.setError("Please enter a valid email address.");
                }
                else if (!verifySU.validatePassword(layoutpasswordSU)) {
                    layoutpasswordSU.setError("Use from 6 to 25 characters for your password.");
                }
                else if (!verifySU.validateConfirmPassword(layoutconfirmpasswordSU, layoutpasswordSU)) {
                    layoutconfirmpasswordSU.setError("Those password didn't match. Try again.");
                }
            }
        });
    }

    @Override
    public void successLogin() {
        Toast.makeText(getApplicationContext(), "Correct standard ", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void failedLogin() {
        Toast.makeText(getApplicationContext(), "Incorrect standard", Toast.LENGTH_SHORT).show();
    }
}
