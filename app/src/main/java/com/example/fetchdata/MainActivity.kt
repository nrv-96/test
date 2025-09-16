package com.example.fetchdata

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var btnShowData: Button
    private lateinit var adapter: SheetAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        btnShowData = findViewById(R.id.btnShowData)
        adapter = SheetAdapter()
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        btnShowData.setOnClickListener {
            fetchData()
        }
    }

    private fun fetchData() {
        kotlinx.coroutines.CoroutineScope(kotlinx.coroutines.Dispatchers.IO).launch {
            val client = okhttp3.OkHttpClient()
            val request = okhttp3.Request.Builder()
                .url("https://symmetrical-space-guacamole-rx54xjr4j5cxj94-5000.app.github.dev/api/sheet")
                .build()
            val response = client.newCall(request).execute()
            val body = response.body?.string()
            val data = mutableListOf<List<String>>()
            if (body != null) {
                val jsonArray = org.json.JSONArray(body)
                for (i in 0 until jsonArray.length()) {
                    val row = jsonArray.getJSONArray(i)
                    val rowList = mutableListOf<String>()
                    for (j in 0 until row.length()) {
                        rowList.add(row.getString(j))
                    }
                    data.add(rowList)
                }
            }
            withContext(kotlinx.coroutines.Dispatchers.Main) {
                adapter.setData(data)
            }
        }
    }
}
