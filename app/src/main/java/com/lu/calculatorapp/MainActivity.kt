package com.lu.calculatorapp

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    companion object {
        const val TAG = "MainActivity"

        const val NO_SELECT = "No Selection"
        const val ADD = "Addition"
        const val SUB = "Subtract"
        const val MUX = "Multiply"
        const val DIV = "Division"


        const val CHOOSE_OPTION = "Please select an option"
    }

    private val options = arrayOf(NO_SELECT, ADD, SUB, MUX, DIV)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val context = this
        val edtxt1: EditText = findViewById(R.id.ednum1)
        val edtxt2: EditText = findViewById(R.id.ednum2)

        val calBtn: Button = findViewById(R.id.calBtn)

        val resultTV: TextView = findViewById(R.id.textResult)

        val spCalculator: Spinner = findViewById(R.id.spCalculator)

        spCalculator.adapter =
            ArrayAdapter(context, android.R.layout.simple_list_item_1, options)

        var operation: String = NO_SELECT

        spCalculator.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                operation = options[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }

        calBtn.setOnClickListener {
            performCalculation(edtxt1, edtxt2, resultTV, operation)
        }
    }

    fun performCalculation(
        edtxt1: EditText, edtxt2: EditText,
        resultTV: TextView, operation: String
    ) {

        if (operation == NO_SELECT) {
            Toast.makeText(this, CHOOSE_OPTION, Toast.LENGTH_SHORT).show()
            return
        }
        try {
            val x: Float = edtxt1.text.toString().toFloat()
            val y: Float = edtxt2.text.toString().toFloat()

            when (operation) {
                "Addition" -> resultTV.text = "Result is " + addition(x, y).toString()
                "Subtract" -> resultTV.text = "Result is " + subtract(x, y).toString()
                "Multiply" -> resultTV.text = "Result is " + multiply(x, y).toString()
                "Division" -> resultTV.text = "Result is " + divide(x, y).toString()
            }
        } catch (ex: RuntimeException) {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show()
            Log.e(TAG, ex.stackTraceToString())
        }
    }

    fun addition(a: Float, b: Float): Float {
        return a + b
    }

    fun multiply(a: Float, b: Float): Float {
        return a * b
    }

    fun subtract(a: Float, b: Float): Float {
        return a - b
    }

    fun divide(a: Float, b: Float): Float {
        return a / b
    }
}