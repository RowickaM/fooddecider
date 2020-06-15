package com.gunginr.dinnerdecider.view.activity.authorization

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gunginr.dinnerdecider.R
import com.gunginr.dinnerdecider.services.firebase.FirebaseAuthorisation
import com.gunginr.dinnerdecider.util.goTo
import com.gunginr.dinnerdecider.util.goToAndCloseOther
import com.gunginr.dinnerdecider.view.activity.DecideActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        if (FirebaseAuthorisation.isAuth()) {
            this.goToAndCloseOther(DecideActivity::class.java)
        }

        login.setOnClickListener {
            FirebaseAuthorisation.singIn(
                this,
                emailInput.text.toString(),
                passwordInput.text.toString()
            )
        }
        toRegistration.setOnClickListener {
            this.goTo(RegistrationActivity::class.java)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity()
    }
}
