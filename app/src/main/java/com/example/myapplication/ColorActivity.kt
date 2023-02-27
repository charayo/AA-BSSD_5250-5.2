package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout.LayoutParams
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.constraintlayout.widget.ConstraintLayout

class ColorActivity : AppCompatActivity() {
    companion object{
        const val BOX1_COLOR_REQUESTED:String = "package com.example.myapplication.BOX1_COLOR_REQUESTED"
        const val BOX2_COLOR_REQUESTED:String = "package com.example.myapplication.BOX2_COLOR_REQUESTED"
        const val BOX3_COLOR_REQUESTED:String = "package com.example.myapplication.BOX3_COLOR_REQUESTED"
    }
    private var col1:Int = 0
    private var col2:Int = 0
    private var col3:Int = 0
    private var newcolor1:String = ""
     private var newcolor2: String = ""
     private var newcolor3: String =""

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_color)

        val getIntentDataButton = Button(this).apply {
            text = "Save"
            setOnClickListener {
                val passableData = Intent().apply {
                    putExtra(MainActivity.COLOR_RESULT_1, newcolor1)
                    putExtra(MainActivity.COLOR_RESULT_2, newcolor2)
                    putExtra(MainActivity.COLOR_RESULT_3, newcolor3)
                }
                setResult(RESULT_OK, passableData)
                finish()
            }
        }
        fun toHex(col: Int): String {
            var hexVal = "%X".format(col)
            hexVal = hexVal.subSequence(2, hexVal.length).toString()
            return hexVal
        }
        val color1 = View(this).apply {
            var box1 = View.generateViewId()
            layoutParams = LinearLayoutCompat.LayoutParams(
                LinearLayoutCompat.LayoutParams.MATCH_PARENT,
                LinearLayoutCompat.LayoutParams.MATCH_PARENT,
                1f

            )
            try {
                col1 = Color.parseColor(intent.getStringExtra(BOX1_COLOR_REQUESTED))
                setBackgroundColor(col1)
            }catch (e:NumberFormatException){
                setBackgroundColor(Color.WHITE)
                col1 = 0
            }
            newcolor1 = toHex(col1)
            setOnClickListener{
                col1 += 10
                it.setBackgroundColor(col1)
                newcolor1 = toHex(col1)

            }
        }

        val color2 = View(this).apply {
            var box2 = View.generateViewId()
            layoutParams = LinearLayoutCompat.LayoutParams(
                LinearLayoutCompat.LayoutParams.MATCH_PARENT,
                LinearLayoutCompat.LayoutParams.MATCH_PARENT,
                1f

            )
            try {
                col2 = Color.parseColor(intent.getStringExtra(BOX2_COLOR_REQUESTED))
                setBackgroundColor(col2)
            }catch (e:NumberFormatException){
                setBackgroundColor(Color.WHITE)
                col2 = 0
            }
            newcolor2 = toHex(col2)
            setOnClickListener{
                col2 += 10
                it.setBackgroundColor(col2)
                newcolor2 = toHex(col2)
            }
        }

        val color3 = View(this).apply {
            var box3 = View.generateViewId()
            layoutParams = LinearLayoutCompat.LayoutParams(
                LinearLayoutCompat.LayoutParams.MATCH_PARENT,
                LinearLayoutCompat.LayoutParams.MATCH_PARENT,
                1f

            )

            try {
                col3 = Color.parseColor(intent.getStringExtra(BOX3_COLOR_REQUESTED))
                setBackgroundColor(col3)
            }catch (e:NumberFormatException){
                setBackgroundColor(Color.WHITE)
                col3 = 0
            }
            newcolor3 = toHex(col3)
            setOnClickListener{
                col3 += 20
                it.setBackgroundColor(col3)
                newcolor3 = toHex(col3)
            }
        }
        val linearLayout = LinearLayoutCompat(this).apply {
            layoutParams = LinearLayoutCompat.LayoutParams(
                LinearLayoutCompat.LayoutParams.MATCH_PARENT,
                LinearLayoutCompat.LayoutParams.MATCH_PARENT,
            )
            weightSum= 3f
            orientation = LinearLayoutCompat.VERTICAL
            addView(color1)
            addView(color2)
            addView(color3)

        }
        //look up the main layout by the id we just gave it
        findViewById<ConstraintLayout>(R.id.color_layout).apply {
           addView(linearLayout)
            addView (getIntentDataButton)
        }
    }
}