package com.gunginr.dinnerdecider.model

import java.io.Serializable


class FoodCategory(
    var name: String,
    var image: String,
    var tags: ArrayList<String>,
    var dishes: ArrayList<String>
) : Serializable