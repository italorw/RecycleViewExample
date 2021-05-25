package com.exercise.recycleview.main

import com.exercise.recycleview.R
import com.exercise.recycleview.ui.view.ViewTypeAdapter

data class Model1(
    val content: String,
)

data class Model1View(
    private val model: Model1
) : ViewTypeAdapter.ViewType<Model1> {
    override fun layoutId(): Int = R.layout.card_01
    override fun data(): Model1 = model
    override fun isUserInteractionEnabled(): Boolean = false
}

data class Model2(
    val content: String,
    val subtitle: String,
)

data class Model2View(
    private val model: Model2
) : ViewTypeAdapter.ViewType<Model2> {
    override fun layoutId(): Int = R.layout.card_02
    override fun data(): Model2 = model
}
