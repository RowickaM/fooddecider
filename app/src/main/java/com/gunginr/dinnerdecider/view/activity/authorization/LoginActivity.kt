package com.gunginr.dinnerdecider.view.activity.authorization

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.gunginr.dinnerdecider.R
import com.gunginr.dinnerdecider.services.firebase.FirebaseAuthorisation
import com.gunginr.dinnerdecider.util.goTo
import com.gunginr.dinnerdecider.util.goToAndCloseOther
import com.gunginr.dinnerdecider.util.isEmailValid
import com.gunginr.dinnerdecider.util.isPasswordValid
import com.gunginr.dinnerdecider.view.activity.DecideActivity
import com.gunginr.dinnerdecider.view.fragment.LoadingFragment
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        showPopup(false)
        if (FirebaseAuthorisation.isAuth()) {
            this.goToAndCloseOther(DecideActivity::class.java)
        }

        login.setOnClickListener {
            if (validation()) {
                showPopup(true)
                FirebaseAuthorisation.singIn(
                    this,
                    emailInput.text.toString(),
                    passwordInput.text.toString()
                )
            }
        }
        toRegistration.setOnClickListener {
            this.goTo(RegistrationActivity::class.java)
        }
    }

    private fun showPopup(show: Boolean) {
        if (show) {
            popup.visibility = View.VISIBLE
            popup.setOnClickListener { }
            popup.bringToFront()
            supportFragmentManager.beginTransaction()
                .replace(R.id.frame_layout, LoadingFragment())
                .commit()
        } else {
            popup.visibility = View.GONE
        }
    }

    override fun onBackPressed() {
        if (popup.visibility == View.GONE) {
            finishAffinity()
            super.onBackPressed()
        }
    }


    private fun validation(): Boolean {
        val email = emailInput.text.toString()
        val password = passwordInput.text.toString()

        if (isEmailValid(email) && isPasswordValid(password))
            return true
        if (!isEmailValid(email)) {
            emailInput.error = getString(R.string.email_error)
        }
        if (!isPasswordValid(password)) {
            passwordInput.error = getString(R.string.password_error)
        }
        return false
    }
}
