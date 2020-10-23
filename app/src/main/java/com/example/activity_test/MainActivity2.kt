package com.example.activity_test

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView


class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val read_msg = findViewById<TextView>(R.id.show_msg)
        val receive_btn = findViewById<Button>(R.id.receive_btn)
        val exit_btn = findViewById<Button>(R.id.exit_btn)

        var bundle = Bundle()
        receive_btn.setOnClickListener {
            val let = intent?.extras?.let {
                val data = it.getString("key1")
                read_msg.text = data
            }
        }

        exit_btn.setOnClickListener {
            bundle.putString("key", "receiveOK")
            var intent = Intent().putExtras(bundle)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }
}





