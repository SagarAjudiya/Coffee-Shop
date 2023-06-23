package com.simform.coffeeshop.model

import android.os.Parcelable
import com.simform.coffeeshop.R
import kotlinx.parcelize.Parcelize

@Parcelize
data class Coffee(
    val rating: String,
    val ratingCount: String,
    val coffeeImage: Int,
    val coffeeName: String,
    val coffeeWith: String,
    val price: String,
    val desc: String
) : Parcelable {

    companion object {
        // All Coffee List
        private const val desc = "The seeds of the Coffea plant's fruits are separated to produce unroasted green coffee beans. The beans are roasted and then ground into fine particles that are typically steeped in hot water before being filtered out, producing a cup of coffee. It is usually served hot, although chilled or iced coffee is common. Coffee can be prepared and presented in a variety of ways (e.g., espresso, French press, caffè latte, or already-brewed canned coffee). Sugar, sugar substitutes, milk, and cream are often added to mask the bitter taste or enhance the flavor."
        val coffeeList = arrayListOf<Coffee>(
            Coffee("4.5", "320", R.drawable.coffee, "Americano", "with Chocolate", "Free", desc),
            Coffee("4.9", "150", R.drawable.imagvp, "cappuccino", "with Chips", "100", desc),
            Coffee("4.5", "320", R.drawable.coffee, "Latte", "with Chocolate", "Free", desc),
            Coffee("4.8", "320", R.drawable.coffee, "cappuccino", "with Chocolate", "8000", desc),
            Coffee("4.5", "320", R.drawable.coffee, "Machiato", "with Chocolate", "Free", desc),
            Coffee("4.5", "320", R.drawable.coffee, "cappuccino", "with Chocolate", "Free", desc),
            Coffee("4.5", "320", R.drawable.coffee, "Americano", "with Chocolate", "Free", desc),
            Coffee("4.5", "320", R.drawable.coffee, "cappuccino", "with Chocolate", "Free", desc),
            Coffee("4.5", "320", R.drawable.coffee, "Machiato", "with Chocolate", "Free", desc),
        )

        // Cart Coffee List
        var cartItemList = ArrayList<Coffee>()
    }

}