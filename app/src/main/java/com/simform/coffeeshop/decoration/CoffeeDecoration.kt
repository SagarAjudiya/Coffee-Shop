package com.simform.coffeeshop.decoration

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class CoffeeDecoration(private var vertical: Int = 16, private var horizontal: Int = 20) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        val position = parent.getChildAdapterPosition(view)
        outRect.apply {
            left = horizontal
            right = horizontal
            top = vertical
            bottom = vertical
        }
    }

}