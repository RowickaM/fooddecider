package com.gunginr.dinnerdecider.view.activity.authorization

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.gunginr.dinnerdecider.R
import com.gunginr.dinnerdecider.services.firebase.FirebaseAuthorisation
import com.gunginr.dinnerdecider.util.goTo
import com.gunginr.dinnerdecider.view.fragment.LoadingFragment
import kotlinx.android.synthetic.main.activity_registration.*

class RegistrationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        signup.setOnClickListener {
            showPopup(true)
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
}
