package com.example.hipoteam

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import androidx.appcompat.widget.SearchView
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    val arrayList = ArrayList<Member>()
    val displayList = ArrayList<Member>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        arrayList.add(Member(31, "artizco", Hipo("Android Developer", 3), "Istanbul", "Yasin Cetiner", R.drawable.yasincetiner))
        arrayList.add(Member(26, "tolgacanunal", Hipo("Android Developer", 3), "Istanbul", "Tolga Can Unal",R.drawable.tolgacanunal))
        arrayList.add(Member(27, "mitsinsar", Hipo("Android Developer", 2), "Tekirdağ", "Mithat Sinan Sari",R.drawable.mithatsari))
        arrayList.add(Member(40, "Not available", Hipo("Assistant to the Regional Manager", 0), "Scranton", "Dwight Schrute",R.drawable.dwight))
        arrayList.add(Member(40, "Not available", Hipo("Regional Manager", 0), "Scranton", "Michael Scott",R.drawable.michael))
        arrayList.add(Member(27, "Not available", Hipo("Intern (Hopefully)", 0), "Istanbul", "Dilara Tekinoglu",R.drawable.dilara))

        displayList.addAll(arrayList)
        val myAdapter = MyAdapter(displayList, this)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = myAdapter

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.menu, menu)
        val menuItem = menu!!.findItem(R.id.search)
        if (menuItem != null) {
            val searchView = menuItem.actionView as SearchView
            val editText = searchView.findViewById<EditText>(androidx.appcompat.R.id.search_src_text)
            editText.hint = "Search by name or position"
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    if (newText!!.isNotEmpty()) {
                        displayList.clear()
                        val search = newText.toLowerCase(Locale.getDefault())
                        arrayList.forEach {
                            if (it.name.toLowerCase(Locale.getDefault()).contains(search)) {
                                displayList.add(it)
                            }
                            else {
                                if (it.hipo.position.toLowerCase(Locale.getDefault()).contains(search)) {
                                    displayList.add(it)
                                }
                            }
                        }
                        recyclerView.adapter!!.notifyDataSetChanged()

                    }
                    else {
                        displayList.clear()
                        displayList.addAll(arrayList)
                        recyclerView.adapter!!.notifyDataSetChanged()
                    }
                    return true
                }
            })
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }
}