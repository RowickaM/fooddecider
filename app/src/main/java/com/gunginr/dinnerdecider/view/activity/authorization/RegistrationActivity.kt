package com.gunginr.dinnerdecider.view.activity.authorization

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.gunginr.dinnerdecider.R
import com.gunginr.dinnerdecider.services.firebase.FirebaseAuthorisation
import com.gunginr.dinnerdecider.util.goTo
import com.gunginr.dinnerdecider.util.isEmailValid
import com.gunginr.dinnerdecider.util.isPasswordValid
import com.gunginr.dinnerdecider.view.fragment.LoadingFragment
import kotlinx.android.synthetic.main.activity_registration.*

class RegistrationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        showPopup(false)

        signup.setOnClickListener {
            if (validation()) {
                showPopup(true)
                FirebaseAuthorisation.singUp(
                    this,
                    emailInput.text.toString(),
                    passwordInput.text.toString(),
                    repeatPasswordInput.text.toString()
                )
            }
        }
        toLogin.setOnClickListener {
            this.goTo(LoginActivity::class.java)
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
        if (password != repeatPasswordInput.text.toString()) {
            repeatPasswordInput.error = getString(R.string.passwords_match_error)
        }

        return false
    }

    override fun onBackPressed() {
        if (popup.visibility == View.GONE) {
            finishAffinity()
            super.onBackPressed()
        }
    }
}
