package com.example.salesapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_registration.*

class Registration : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        regBtnSignUp.setOnClickListener{
if(regTxtPassword.text.toString().equals(txtConfirm.text.toString())){
        var url="http://192.168.1.3/eCommerce/PHPFiles/add_user.php?Email="+txtEmail.text.toString()+"&Name="+
                txtName.text.toString()+"&Password="+ regTxtPassword.text.toString()+"&Phone="+ regTxtMobile.text.toString()
    var req:RequestQueue=Volley.newRequestQueue(this)
    var src=StringRequest(Request.Method.GET,url,Response.Listener { response ->
if(response.equals("0"))
    Toast.makeText(this,"Mail already used",Toast.LENGTH_LONG).show()
        else{
    Toast.makeText(this,"User added",Toast.LENGTH_LONG).show()
    var intent=Intent(this,Home::class.java)
    startActivity(intent)
}
    },Response.ErrorListener { error ->
        Toast.makeText(this,error.message,Toast.LENGTH_LONG).show()
    })

    req.add(src)
}
            else{
    Toast.makeText(this,"Password not matched",Toast.LENGTH_LONG).show()
            }
            }
    }
}
