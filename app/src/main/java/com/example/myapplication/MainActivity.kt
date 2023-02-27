package com.example.myapplication

import android.app.Activity
import android.app.Instrumentation.ActivityResult
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.constraintlayout.widget.ConstraintLayout

class MainActivity : AppCompatActivity() {
    companion object{
        const val COLOR_RESULT_1:String = "com.example.myapplication.COLOR_RESULT_1"
        const val COLOR_RESULT_2:String = "com.example.myapplication.COLOR_RESULT_2"
        const val COLOR_RESULT_3:String = "com.example.myapplication.COLOR_RESULT_3"
    }
    private lateinit var messageTextView: TextView
    private lateinit var color1: TextView
    private lateinit var color2: TextView
    private lateinit var color3: TextView

private val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        result: androidx.activity.result.ActivityResult ->
    if (result.resultCode == Activity.RESULT_OK) {
        val intent = result.data
        color1.text = intent?.getStringExtra(COLOR_RESULT_1)
        color2.text = intent?.getStringExtra(COLOR_RESULT_2)
        color3.text = intent?.getStringExtra(COLOR_RESULT_3)
    }
}


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        color1 = EditText(this).apply {
            var box1 = View.generateViewId()
            hint = "Enter Box1 Hex Color without #"
        }
         color2 = EditText(this).apply {
            hint = "Enter Box2 Hex Color without #"
        }
         color3 = EditText(this).apply {
            hint = "Enter Box3 Hex Color without #"
        }


        val submitButton = Button(this).apply {
            text = "Submit"
            setOnClickListener {
                val passableData = Intent(applicationContext, ColorActivity::class.java).apply {
                    putExtra(ColorActivity.BOX1_COLOR_REQUESTED, "#"+color1.text.toString())
                    putExtra(ColorActivity.BOX2_COLOR_REQUESTED, "#"+color2.text.toString())
                    putExtra(ColorActivity.BOX3_COLOR_REQUESTED, "#"+color3.text.toString())
                }
                startForResult.launch(passableData)
            }
        }

        val linearLayout = LinearLayoutCompat(this).apply {
            layoutParams = LinearLayoutCompat.LayoutParams(
                LinearLayoutCompat.LayoutParams.MATCH_PARENT,
                LinearLayoutCompat.LayoutParams.MATCH_PARENT,
            )
            orientation = LinearLayoutCompat.VERTICAL
            addView(color1)
            addView(color2)
            addView(color3)
            addView(submitButton)

        }

        //look up the main layout by the id we just gave it
        findViewById<ConstraintLayout>(R.id.main_layout).apply {
        setBackgroundColor (Color.GREEN)
        addView (linearLayout)
        }
    }
}