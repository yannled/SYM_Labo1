/*
 * File     : MainActivity.java
 * Project  : TemplateActivity
 * Author   : Markus Jaton 2 juillet 2014
 * 			  Fabien Dutoit 28 août 2018
 *            IICT / HEIG-VD
 *
 * mailto:fabien.dutoit@heig-vd.ch
 *
 * This piece of code reads a [email_account / password ] combination.
 * It is used as a template project for the SYM module element given at HEIG-VD
 * Target audience : students IL, TS, IE [generally semester 1, third bachelor year]
 *
 * THIS SOFTWARE IS PROVIDED "AS IS" AND ANY EXPRESSED OR IMPLIED WARRANTIES,
 * INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY
 * AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL
 * THE REGENTS OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED
 * TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 */
package ch.heigvd.sym.template;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;


public class MainActivity extends AppCompatActivity {

    // For logging purposes
    private static final String TAG = MainActivity.class.getSimpleName();

    HashMap<String, String> hashMap = new HashMap<>();

    // GUI elements
    private EditText email = null;
    private Button signIn = null;
    private EditText password = null;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onStart() {
        Log.w(TAG, "ONSTART !!!!!!!!!!!!!!");
        super.onStart();
    }

    @Override
    protected void onPause() {
        Log.w(TAG, "ONPAUSE !!!!!!!!!!!!!!");
        super.onPause();
    }

    @Override
    protected void onResume() {
        Log.w(TAG, "ONRESUME !!!!!!!!!!!!!!");
        super.onResume();
    }

    @Override
    protected void onStop() {
        Log.w(TAG, "ONSTOP !!!!!!!!!!!!!!");
        super.onStop();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        hashMap.put("toto@tutu.com", "tata");
        hashMap.put("yannlederrey@gmail.com", "1234");
        hashMap.put("miguel@assiste.com", "1432");

        Log.w(TAG, "ONCREATE !!!!!!!!!!!!!!");

        super.onCreate(savedInstanceState);
        // Show the welcome screen / login authentication dialog
        setContentView(R.layout.authent);

        // Link to GUI elements
        this.email = findViewById(R.id.email);
        this.signIn = findViewById(R.id.buttOk);
        this.password = findViewById(R.id.pass);

        // Then program action associated to "Ok" button
        signIn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                /*
                 * There you have to check out if the email/password
                 * combination given is valid or not
                 */
                String mail = email.getText().toString();
                String passwd = password.getText().toString();

                if (isValid(mail, passwd)) {
                    // start new activity
                    Intent intent = new Intent(MainActivity.this, ch.heigvd.sym.template.newActivity.class);
                    intent.putExtra("emailEntered", mail);
                    intent.putExtra("passwordGiven", passwd);
                    int requestCode = 1;
                    startActivityForResult(intent, requestCode);

                    Toast.makeText(MainActivity.this, getResources().getString(R.string.good), Toast.LENGTH_LONG).show();
                    //finish();
                } else {
                    // Wrong combination, display pop-up dialog and stay on login screen
                    //re-init Text Fields
                    email.setText("");
                    password.setText("");
                    showErrorDialog(mail, passwd);
                }
            }

        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            String returnValue = data.getStringExtra("myResult");
            if (!returnValue.isEmpty()) {
                Toast.makeText(
                        this,
                        "Your result is :  " + getResources().getString(R.string.back),
                        Toast.LENGTH_SHORT).show();

            }
        }
    }

    private boolean isValid(String mail, String passwd) {
        if (mail == null || passwd == null) {
            Log.w(TAG, "isValid(mail, passwd) - mail and passwd cannot be null !");
            return false;
        } else if (passwd.contains("@")) {
            Log.w(TAG, "isValid(passwd) - password does not contain @ character !");
            return false;
        }
        // Return true if combination valid, false otherwise
        return (hashMap.get(mail).equals(passwd));
    }

    protected void showErrorDialog(String mail, String passwd) {
        /*
         * Pop-up dialog to show error
         */
        AlertDialog.Builder alertbd = new AlertDialog.Builder(this);
        //OLD Line
        // alertbd.setIcon(android.R.drawable.ic_dialog_alert);
        alertbd.setIcon(R.drawable.emergency);
        alertbd.setTitle(R.string.wronglogin);
        alertbd.setMessage(R.string.wrong);
        alertbd.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // we do nothing...
                // dialog close automatically
            }
        });
        alertbd.create().show();
    }
}
