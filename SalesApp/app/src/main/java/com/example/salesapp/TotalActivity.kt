package com.example.salesapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.paypal.android.sdk.payments.PayPalConfiguration
import com.paypal.android.sdk.payments.PayPalPayment
import com.paypal.android.sdk.payments.PayPalService
import com.paypal.android.sdk.payments.PaymentActivity
import kotlinx.android.synthetic.main.activity_total.*
import java.math.BigDecimal

class TotalActivity : AppCompatActivity() {

    var config:PayPalConfiguration?=null
    var amount:Double=0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_total)

        var url="http://192.168.1.3/eCommerce/PHPFiles/get_total.php?BillNo="+intent.getStringExtra("bno")
        var rq: RequestQueue = Volley.newRequestQueue(this)
        var sr= StringRequest(
            Request.Method.GET,url, Response.Listener { response ->
        txtTotal.text=response
            },
            Response.ErrorListener { error ->
                Toast.makeText(this,error.message, Toast.LENGTH_LONG).show()
            })
        rq.add(sr)

        config=PayPalConfiguration().environment(PayPalConfiguration.ENVIRONMENT_SANDBOX).clientId(UserInfo.client_ID)
        var i=Intent(this,PayPalService::class.java)
        i.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION,config)
        startService(i)

        btnPaypal.setOnClickListener {
            amount=txtTotal.text.toString().toDouble()
            var payment=PayPalPayment(BigDecimal.valueOf(amount),"EUR","Sale App",PayPalPayment.PAYMENT_INTENT_SALE) // bigdecimal varijabla koju cita paypal
       var intent=Intent(this,PaymentActivity::class.java)
            intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION,config)
            intent.putExtra(PaymentActivity.EXTRA_PAYMENT,payment)
            startActivityForResult(intent,123)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) { // kako bi znali da je user kliknuo ok da ide dalje

        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==123){
            if(resultCode==Activity.RESULT_OK){
                var obj=Intent(this,ConfirmActivity::class.java)
                startActivity(obj)
            }
        }
    }
    override fun onDestroy() { // ako treba napustiti ovu aktivnost radi necega drugog
        stopService(Intent(this,PayPalService::class.java))
        super.onDestroy()
    }
}

