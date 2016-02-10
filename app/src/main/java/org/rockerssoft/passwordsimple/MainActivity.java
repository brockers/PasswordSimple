package org.rockerssoft.passwordsimple;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

public class MainActivity extends AppCompatActivity {

  Button btSubmit;
  EditText etPassword;
  EditText etUsername;
  int failCounter = 3;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    btSubmit = (Button)findViewById(R.id.btSubmit);
    etPassword = (EditText)findViewById(R.id.etPassword);
    etUsername = (EditText)findViewById(R.id.etUsername);

	  btSubmit.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
	      // Pull our string for readability
	      String uname = etUsername.getText().toString();
	      String paswd = etPassword.getText().toString();
        if(uname.equals("bobby") && paswd.equals("isawesome")){
	        Toast.makeText(getApplicationContext(),
		        "Success... forwarding",
            Toast.LENGTH_LONG).show();
	        failCounter=3;
	        etUsername.setText("");
	        etPassword.setText("");
        } else {
          Toast.makeText(getApplicationContext(),
	          "Bad username/password",
	          Toast.LENGTH_LONG).show();
          failCounter--;

	        etUsername.setTextColor(Color.RED);
	        etPassword.setTextColor(Color.RED);

	        if(failCounter <= 0){
		        btSubmit.setEnabled(false);
	        }
        }
      }
    });
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
      // Inflate the menu; this adds items to the action bar if it is present.
      getMenuInflater().inflate(R.menu.menu_main, menu);
      return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
      // Handle action bar item clicks here. The action bar will
      // automatically handle clicks on the Home/Up button, so long
      // as you specify a parent activity in AndroidManifest.xml.
      int id = item.getItemId();

      //noinspection SimplifiableIfStatement
      if (id == R.id.action_settings) {
          return true;
      }

      return super.onOptionsItemSelected(item);
  }
}
