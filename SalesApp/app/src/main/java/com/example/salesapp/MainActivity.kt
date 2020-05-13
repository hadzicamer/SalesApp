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

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnSignUp.setOnClickListener {
            var intent = Intent(this, Registration::class.java)
            startActivity(intent)
        }

            btnLogin.setOnClickListener {
                var url="http://192.168.1.3/eCommerce/PHPFiles/login.php?Email="+lgnEmail.text.toString()+"&Password="+
                        lgnPassword.text.toString()
                var req: RequestQueue = Volley.newRequestQueue(this)
                var src= StringRequest(Request.Method.GET,url,Response.Listener { response ->
                    if(response.equals("0"))
                        Toast.makeText(this,"Login failed", Toast.LENGTH_LONG).show()
                    else{
                        UserInfo.Email=lgnEmail.text.toString()
                        var intent=Intent(this,Home::class.java)
                        startActivity(intent)
                    }
                }, Response.ErrorListener { error ->
                    Toast.makeText(this,error.message, Toast.LENGTH_LONG).show()
                })

                req.add(src)
            }
        }
    }

