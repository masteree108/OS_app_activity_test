package com.example.activity_test

import android.app.Activity
import android.content.Intent
import android.net.wifi.WifiManager
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val send_msg = findViewById<Button>(R.id.send_btn)
        val read_msg = findViewById<EditText>(R.id.msg)
        val tip = findViewById<TextView>(R.id.msg)
        val receive_msg = findViewById<TextView>(R.id.receive)
        var mac = getLocalMacAddress();    //it doesn't work ,only get 02:00...
        tip.text = mac


        send_msg.setOnClickListener {
            if(read_msg.length()<1) {
                tip.text = "請輸入message"
            } else {
                receive_msg.text = ""
                send_msg_by_intent(read_msg)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val receive_msg = findViewById<TextView>(R.id.receive)
        data?.extras?.let{
            if(requestCode==1 && resultCode == Activity.RESULT_OK) {
                val data = it.getString("key")
                receive_msg.text = data.toString()
            } else {
                receive_msg.text = "ng"
            }
        }
    }

    private fun send_msg_by_intent(read_msg: EditText) {
        val bundle = Bundle()
        val msg = read_msg.text.toString()
        bundle.putString("key1", msg)
        val intent = Intent(this, MainActivity2::class.java).apply {
            putExtras(bundle)
            startActivityForResult(this, 1)
        }
    }



    private fun getLocalMacAddress():String{
        var Context = this.applicationContext;
        var wifi= getApplicationContext().getSystemService(WIFI_SERVICE) as WifiManager;
        var info=wifi.connectionInfo;
            return info.macAddress;
     }

}


