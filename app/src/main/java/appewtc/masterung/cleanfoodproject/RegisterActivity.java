package appewtc.masterung.cleanfoodproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {

    //Explicit
    private EditText userEditText, passwordEditText, nameEditText;
    private String userString, passwordString, nameString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Bind Widget
        bindWidget();

    }   // Main Method

    public void clickSignUpRegis(View view) {

        userString = userEditText.getText().toString().trim();
        passwordString = passwordEditText.getText().toString().trim();
        nameString = nameEditText.getText().toString().trim();

        //Check Space
        if (userString.equals("") || passwordString.equals("") || nameString.equals("")) {

            //Have Space
            MyAlertDialog objMyAlertDialog = new MyAlertDialog();
            objMyAlertDialog.myDialog(RegisterActivity.this, "มีช่องว่าง", "กรุณากรอก ทุกช่อง คะ");

        } else {

            //No Space

        } // if

    }   // clickSignUpRegis

    private void bindWidget() {
        userEditText = (EditText) findViewById(R.id.edtUser);
        passwordEditText = (EditText) findViewById(R.id.edtPassword);
        nameEditText = (EditText) findViewById(R.id.edtName);
    }

}   // Main Class
