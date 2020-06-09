package id.my.okisulton.passwordhashing;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class MainActivity extends AppCompatActivity {
    TextView tv_result;
    EditText et_username, et_password;
    Button btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_result = findViewById(R.id.tv_result);
        et_username = findViewById(R.id.et_username);
        et_password = findViewById(R.id.et_password);
        btn_login = findViewById(R.id.btn_login);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = et_username.getText().toString();
                String password = et_password.getText().toString();
                if (username.isEmpty() || password.isEmpty()){
                    Toast.makeText(v.getContext(), "Data tidak boleh kosong", Toast.LENGTH_SHORT).show();
                }else {
                    String hashPassword = password;
                    String bcryptHashString = BCrypt.withDefaults().hashToString(12, hashPassword.toCharArray());
                    BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), bcryptHashString);
                    if (result.verified){
                        Toast.makeText(v.getContext(), "Password Benar" +result, Toast.LENGTH_SHORT).show();
                        Log.d("Password", result.toString());
                        tv_result.setText(bcryptHashString);
                    }else {
                        Toast.makeText(v.getContext(), "Password salah ", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}

//import java.security.NoSuchAlgorithmException;
//        import java.security.SecureRandom;
//
//        import org.springframework.security.crypto.bcrypt.BCrypt;
//
//public class BCryptTest {
//
//    public static void main(String[] ar) {
//        BCryptTest bcTest = new BCryptTest();
//        String hashed = bcTest.generateHash("test");
//        boolean verify = bcTest.verifyHash("test", hashed);
//        System.out.println(hashed);
//        System.out.println(verify);
//    }
//
//    /*
//     * generate BCrypt hash
//     */
//    public String generateHash(String plainText) {
//        try {
//
//            String salt = BCrypt.gensalt(10, SecureRandom.getInstance("SHA1PRNG"));
//            return BCrypt.hashpw(plainText, salt);
//        } catch (NoSuchAlgorithmException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//
//        return null;
//    }
//
//    /*
//     * BCrypt check
//     */
//    public boolean verifyHash(String plainText, String cipher) {
//        return BCrypt.checkpw(plainText, cipher);
//    }
//}
