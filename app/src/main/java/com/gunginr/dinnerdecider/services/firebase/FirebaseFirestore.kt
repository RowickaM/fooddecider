package com.gunginr.dinnerdecider.services.firebase

import android.app.Activity
import android.util.Log
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.gunginr.dinnerdecider.model.FoodCategory
import com.gunginr.dinnerdecider.util.storagedata.readFromSharedPref
import com.gunginr.dinnerdecider.util.variables.*


object FirebaseFirestore {
    private const val TAG = "FIREBASE_FIRESTORE"
    private val db = Firebase.firestore

    private val dishesCollection = db.collection(DISHES_COLLECTION)

    private var dishesCollectionListener: ListenerRegistration? = null
    private var templatesCollectionListener: ListenerRegistration? = null

    fun removeListeners() {
        templatesCollectionListener?.remove()
        dishesCollectionListener?.remove()
        Log.d(TAG, "all listeners has been removed")
    }

    fun dishesCollectionListener(activity: Activity, doSomething: (ArrayList<String>) -> Unit) {
        dishesCollectionListener =
            dishesCollection.document(FirebaseAuthorisation.getUID())
                .addSnapshotListener { snapshot, error ->
                    if (error != null) {
                        Log.e(TAG, "${error.message}")
                        doSomething(readFromSharedPref(activity))
                        return@addSnapshotListener
                    }

                    doSomething(getListDishes(snapshot!!))

                }
    }

    fun getTemplates(doSomething: (ArrayList<FoodCategory>) -> Unit) {
        templatesCollectionListener = db.collection(TEMPLATES_COLLECTION)
            .addSnapshotListener { snapshot, exception ->
                if (exception != null)
                    return@addSnapshotListener

                val list = arrayListOf<FoodCategory>()
                for (doc in snapshot!!.documents) {
                    val category = doc[CATEGORY_KEY] as Map<String, Any>
                    val tags = category[TAGS_KEY] as ArrayList<String>
                    val dishes = doc[DISHES_KEY] as ArrayList<String>

                    list.add(
                        FoodCategory(
                            category[NAME_KEY].toString(),
                            category[IMAGE_KEY].toString(),
                            tags,
                            dishes
                        )
                    )
                }
                doSomething(list)
            }
    }

    private fun getListDishes(snapshot: DocumentSnapshot): ArrayList<String> {
        return snapshot.get(DISHES_KEY) as ArrayList<String>
    }

    fun initCollectionUser(UID: String?, list: ArrayList<String>) {
        if (UID != null) {
            if (list.count() == 0) {
                list.add("Burger")
                list.add("Chili con Carne")
                list.add("Macaroni and cheese")
            }
            saveList(list)
        }
    }

    fun saveList(list: ArrayList<String>) {
        dishesCollection.document(FirebaseAuthorisation.getUID()).set(
            hashMapOf(
                DISHES_KEY to list
            )
        ).addOnCompleteListener {
            if (it.isSuccessful) {
                Log.d(TAG, "success")
            } else {
                Log.d(TAG, "not success")
            }
        }
            .addOnFailureListener {
                Log.d(TAG, it.message)
            }

    }
}


