package br.com.norbiato.calculadoraflex.ui.login

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import br.com.norbiato.calculadoraflex.R
import br.com.norbiato.calculadoraflex.ui.form.FormActivity
import br.com.norbiato.calculadoraflex.ui.signup.SignUpActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth
    private val newUserRequestCode = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        mAuth = FirebaseAuth.getInstance()

        if (mAuth.currentUser != null){
            goToHome()
        }

        btLogin.setOnClickListener{
            mAuth.signInWithEmailAndPassword(
                inputLoginEmail.text.toString(),
                inputLoginPassword.text.toString()
            ).addOnCompleteListener{
                if(it.isSuccessful){
                    goToHome()
                }else{
                    Toast.makeText(this@LoginActivity, it.exception?.message,
                        Toast.LENGTH_SHORT).show()
                }
            }
        }

        btSignup.setOnClickListener{
            startActivityForResult(
                Intent(this, SignUpActivity::class.java),
                newUserRequestCode)
        }
    }

    private fun goToHome(){
        val intent = Intent(this, FormActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
        finish()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?){
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == newUserRequestCode && resultCode == Activity.RESULT_OK){
            inputLoginEmail.setText(data?.getStringExtra("email"))
        }
    }

}
