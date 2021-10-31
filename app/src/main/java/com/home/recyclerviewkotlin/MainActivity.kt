package com.home.recyclerviewkotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)

        val list = generateList(200)
        recyclerView.adapter = RecyclerViewAdapter(list)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
    }

    private fun generateList(size: Int): List<RecyclerViewItem> {

        val list = ArrayList<RecyclerViewItem>()

        for (i in 0 until size) {
            val drawable = when (i % 3) {
                0 -> R.drawable.ic_android
                1 -> R.drawable.ic_baseline_add_reaction
                else -> R.drawable.ic_baseline_night_shelter
            }
            val item = RecyclerViewItem(drawable, "Item $i", "Line 2")
            list += item
        }
        return list
    }

}