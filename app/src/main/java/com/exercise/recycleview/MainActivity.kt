package com.exercise.recycleview

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.exercise.recycleview.databinding.ActivityMainBinding
import com.exercise.recycleview.main.Model1
import com.exercise.recycleview.main.Model1View
import com.exercise.recycleview.main.Model2
import com.exercise.recycleview.main.Model2View
import com.exercise.recycleview.ui.view.ViewTypeAdapter

val Any.TAG: String
    get() = javaClass.simpleName

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewTypeAdapter: ViewTypeAdapter<ViewTypeAdapter.ViewType<*>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewTypeAdapter = ViewTypeAdapter(
            onItemActionListener = onItemActionListener
        )

        binding = DataBindingUtil
            .setContentView<ActivityMainBinding>(this, R.layout.activity_main)
            .apply {
                lifecycleOwner = this@MainActivity
                mainList.layoutManager = LinearLayoutManager(this@MainActivity)
                mainList.adapter = viewTypeAdapter
            }

        val list = listOf(
            Model1View(Model1("Title 1")),
            Model1View(Model1("Title 2")),
            Model2View(Model2("Title 3", "Subtitle 3")),
            Model2View(Model2("Title 4", "Subtitle 4")),
        )

        viewTypeAdapter.setList(list)
    }

    private val onItemActionListener = object : ViewTypeAdapter.OnItemActionListener {
        override fun onItemClicked(position: Int) {
            Log.d(TAG, "position: $position")
        }
    }
}
