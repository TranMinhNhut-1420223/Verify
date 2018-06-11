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

public class ForgetPassword extends AppCompatActivity implements resultLogin {
    Button btnSendEmail;
    TextInputLayout layoutemailForgetPassword;
    TextInputEditText editemailForgetPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        btnSendEmail = (Button) findViewById(R.id.btnSendEmail);
        layoutemailForgetPassword = (TextInputLayout) findViewById(R.id.txtinlayoutForgetPassword_Email);
        editemailForgetPassword = (TextInputEditText) findViewById(R.id.editForgetPassword_Email);
        final Verify verifyForgetPassword = new Verify();

        editemailForgetPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                verifyForgetPassword.checkEmail(editable, layoutemailForgetPassword);
            }
        });
        btnSendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (verifyForgetPassword.Verify(layoutemailForgetPassword))
                {
                    successLogin();
                }
                else
                {
                    failedLogin();
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
