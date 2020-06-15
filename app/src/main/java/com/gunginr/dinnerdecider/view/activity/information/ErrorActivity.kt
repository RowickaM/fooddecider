package com.gunginr.dinnerdecider.view.activity.information

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gunginr.dinnerdecider.R
import com.gunginr.dinnerdecider.util.goToAndCloseOther
import com.gunginr.dinnerdecider.view.activity.DecideActivity
import kotlinx.android.synthetic.main.activity_error.*

class ErrorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_error)

        backToMainScreen.setOnClickListener {
            goToAndCloseOther(DecideActivity::class.java)
        }
    }

    override fun onBackPressed() {
//        super.onBackPressed()
    }
}
