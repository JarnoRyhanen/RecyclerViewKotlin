package com.home.recyclerviewkotlin

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private val list = generateList(200)
    private val adapter = RecyclerViewAdapter(list)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
    }

    fun removeItem(view: View) {
        val index = Random.nextInt(8)
        list.removeAt(index)
        adapter.notifyItemRemoved(index)
    }

    fun insertItem(view: View) {
        val index = Random.nextInt(8)
        val newItem = RecyclerViewItem(
            R.drawable.ic_android
            , "new item at position $index",
            "line 2"
        )
        list.add(index, newItem)
        adapter.notifyItemInserted(index)
    }

    private fun generateList(size: Int): ArrayList<RecyclerViewItem> {

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