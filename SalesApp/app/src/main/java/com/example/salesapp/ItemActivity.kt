package com.example.salesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_item.*

class ItemActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item)

        val cat:String=intent.getStringExtra("cat")
        val url= "http://192.168.1.3/eCommerce/PHPFiles/get_items.php?Category="+cat
        val list=ArrayList<Item>()
        val req: RequestQueue = Volley.newRequestQueue(this) // kreiran zahtjev
        val jar= JsonArrayRequest(Request.Method.GET,url,null, Response.Listener{ response ->
            for(x in 0 .. response.length()-1)
                list.add(Item(response.getJSONObject(x).getInt("ID"),response.getJSONObject(x).getString("Name"),
                    response.getJSONObject(x).getDouble("Price"),response.getJSONObject(x).getString("Photo")))
            val adp=ItemAdapter(this,list)
            rvItem.layoutManager=LinearLayoutManager(this)
            rvItem.adapter=adp
        }, Response.ErrorListener { error ->
            Toast.makeText(this,error.message, Toast.LENGTH_LONG).show()
        })
        req.add(jar)

    }
}
