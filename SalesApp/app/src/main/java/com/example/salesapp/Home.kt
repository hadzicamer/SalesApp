package com.example.salesapp
import android.content.ClipData
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_home.*

class Home : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        var url="http://192.168.1.3/eCommerce/PHPFiles/get_categories.php"
        var list=ArrayList<String>()
        var req:RequestQueue= Volley.newRequestQueue(this) // kreiran zahtjev
        var jar=JsonArrayRequest(Request.Method.GET,url,null,Response.Listener{ response ->
            for(x in 0.. response.length()-1)
                list.add(response.getJSONObject(x).getString("Category")) // uzeli podatke sa servera i spremili ih u arraylist

            var adp=ArrayAdapter(this,R.layout.txtview,list)
            lvCategories.adapter=adp

        }, Response.ErrorListener { error ->
            Toast.makeText(this,error.message,Toast.LENGTH_LONG).show()
        })
        req.add(jar)
        lvCategories.setOnItemClickListener { parent, view, position, id ->
            var cat:String=list[position]
            var obj= Intent(this,ItemActivity::class.java)
            obj.putExtra("cat",cat)
            startActivity(obj)
        }
    }
}
