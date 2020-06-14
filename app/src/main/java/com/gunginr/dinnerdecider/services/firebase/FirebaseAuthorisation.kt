package com.gunginr.dinnerdecider.services.firebase

import android.app.Activity
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.gunginr.dinnerdecider.util.goToAndCloseOther
import com.gunginr.dinnerdecider.util.storagedata.readFromSharedPref
import com.gunginr.dinnerdecider.view.activity.DecideActivity

object FirebaseAuthorisation {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    fun getUID(): String {
        return auth.currentUser?.uid!!
    }

    fun singUp(activity: Activity, email: String, password: String, repeatPassword: String) {
        if (password.trim() != repeatPassword.trim()) return
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (!it.isSuccessful) return@addOnCompleteListener
                Log.d("FirebaseAuth", "User added uid: ${it.result?.user?.uid}")
                FirebaseFirestore.initCollectionUser(
                    it.result?.user?.uid,
                    readFromSharedPref(activity)
                )

                activity.goToAndCloseOther(DecideActivity::class.java)
            }
            .addOnFailureListener {

            }
    }

    fun singIn(activity: Activity, email: String, password: String) {
        if (email == "" || password == "") return
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (!it.isSuccessful) return@addOnCompleteListener
                Log.d("FirebaseAuth", "User login uid: ${it.result?.user?.uid}")
                activity.goToAndCloseOther(DecideActivity::class.java)
            }
    }

    fun isAuth(): Boolean {
        return auth.currentUser != null
    }

    fun logout() {
        auth.signOut()
    }
}
