package com.gunginr.dinnerdecider.view.activity.authorization

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gunginr.dinnerdecider.R
import com.gunginr.dinnerdecider.services.firebase.FirebaseAuthorisation
import com.gunginr.dinnerdecider.util.goTo
import kotlinx.android.synthetic.main.activity_registration.*

class RegistrationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        signup.setOnClickListener {
            FirebaseAuthorisation.singUp(
                this,
                emailInput.text.toString(),
                passwordInput.text.toString(),
                repeatPasswordInput.text.toString()
            )
        }
        toLogin.setOnClickListener {
            this.goTo(LoginActivity::class.java)
        }
    }
}