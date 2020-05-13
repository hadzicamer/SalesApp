package com.example.salesapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_order.*

class OrderActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)

        var url="http://192.168.1.3/eCommerce/PHPFiles/get_temp_order.php?Email="+UserInfo.Email
        var list=ArrayList<String>()
        var req: RequestQueue = Volley.newRequestQueue(this)
        var jar= JsonArrayRequest(Request.Method.GET,url,null, Response.Listener{ response ->
            for(x in 0.. response.length()-1)
                list.add("Name: "+response.getJSONObject(x).getString("Name")+"\n"+"Price: "+response.getJSONObject(x).getString("Price")
                +"\n"+"Quantity: "+response.getJSONObject(x).getString("Qty"))
            var adp= ArrayAdapter(this,android.R.layout.simple_list_item_1,list)
            lvOrder.adapter=adp

        }, Response.ErrorListener { error ->
            Toast.makeText(this,error.message, Toast.LENGTH_LONG).show()
        })
        req.add(jar)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu) // show menu on the screen
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(item.itemId==R.id.itemBack) {
            var i = Intent(this, Home::class.java)
            startActivity(i)
        }
        if(item.itemId==R.id.itemCancel) {
            var url="http://192.168.1.3/eCommerce/PHPFiles/cancel_order.php?Email="+UserInfo.Email
            var rq:RequestQueue= Volley.newRequestQueue(this)
            var sr= StringRequest(Request.Method.GET,url,Response.Listener { response ->
                var i= Intent(this,Home::class.java)
                startActivity(i)
                Toast.makeText(this,"Orders have been cancelled",Toast.LENGTH_LONG).show()
            },
                Response.ErrorListener { error ->
                    Toast.makeText(this,error.message,Toast.LENGTH_LONG).show()
                })
rq.add(sr)
        }

        if(item.itemId==R.id.itemConfirm){
            var url="http://192.168.1.3/eCommerce/PHPFiles/confirm_order.php?Email="+UserInfo.Email
            var rq:RequestQueue= Volley.newRequestQueue(this)
            var sr= StringRequest(Request.Method.GET,url,Response.Listener { response ->
                var i= Intent(this,TotalActivity::class.java)
                i.putExtra("bno",response)
                startActivity(i)
                Toast.makeText(this,"Your bill",Toast.LENGTH_LONG).show()
            },
                Response.ErrorListener { error ->
                    Toast.makeText(this,error.message,Toast.LENGTH_LONG).show()
                })
            rq.add(sr)
        }
        
        return super.onOptionsItemSelected(item)
    }
}
