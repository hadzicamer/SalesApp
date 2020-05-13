package com.example.salesapp

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

/**
 * A simple [Fragment] subclass.
 */
class QtyFragment : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var v= inflater.inflate(R.layout.fragment_qty, container, false)
        var txt=v.findViewById<EditText>(R.id.txtQty)
        var btn=v.findViewById<Button>(R.id.btnQty)
btn.setOnClickListener {
    var url="http://192.168.1.3/eCommerce/PHPFiles/add_temp_order.php?OrderID="+UserInfo.itemID+"&Email="+UserInfo.Email+"&Qty="+txt.text.toString()
    var rq:RequestQueue= Volley.newRequestQueue(activity) // umjesto this activity kada nismo u activity dijelu
    var sr=StringRequest(Request.Method.GET,url,Response.Listener { response ->
        var i= Intent(activity,OrderActivity::class.java)
        startActivity(i)

    },
    Response.ErrorListener { error ->
Toast.makeText(activity,error.message,Toast.LENGTH_LONG).show()
    })

rq.add(sr)
}
        return v
    }

}

