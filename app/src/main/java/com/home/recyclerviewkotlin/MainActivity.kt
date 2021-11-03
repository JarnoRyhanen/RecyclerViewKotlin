package com.home.recyclerviewkotlin

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.home.recyclerviewkotlin.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity(), RecyclerViewAdapter.OnItemClickListener {
    private val list = generateList(200)
    private val adapter = RecyclerViewAdapter(list, this)

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.setHasFixedSize(true)
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

    override fun onItemClick(position: Int) {
        Toast.makeText(this, "Item $position clicked", Toast.LENGTH_SHORT).show()
        val clickedItem = list[position]
        clickedItem.text1 = "clicked"
        adapter.notifyItemChanged(position)
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