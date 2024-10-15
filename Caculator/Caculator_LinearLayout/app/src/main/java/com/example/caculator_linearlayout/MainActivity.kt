package com.example.caculator_linearlayout

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var display: TextView
    private var currentInput = ""
    private var operator = ""
    private var firstValue = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Tham chiếu tới các View từ XML
        display = findViewById(R.id.display)

        // Tham chiếu các nút
        val button0 = findViewById<Button>(R.id.button_0)
        val button1 = findViewById<Button>(R.id.button_1)
        val button2 = findViewById<Button>(R.id.button_2)
        val button3 = findViewById<Button>(R.id.button_3)
        val button4 = findViewById<Button>(R.id.button_4)
        val button5 = findViewById<Button>(R.id.button_5)
        val button6 = findViewById<Button>(R.id.button_6)
        val button7 = findViewById<Button>(R.id.button_7)
        val button8 = findViewById<Button>(R.id.button_8)
        val button9 = findViewById<Button>(R.id.button_9)
        val buttonPlus = findViewById<Button>(R.id.button_plus)
        val buttonMinus = findViewById<Button>(R.id.button_minus)
        val buttonMultiply = findViewById<Button>(R.id.button_multiply)
        val buttonDivide = findViewById<Button>(R.id.button_divide)
        val buttonEqual = findViewById<Button>(R.id.button_equal)
        val buttonC = findViewById<Button>(R.id.button_c)
        val buttonCE = findViewById<Button>(R.id.button_ce)
        val buttonBS = findViewById<Button>(R.id.button_bs)
        val buttonDot = findViewById<Button>(R.id.button_dot)
        val buttonPlusMinus = findViewById<Button>(R.id.button_plus_minus)
        // Set sự kiện cho các nút số
        val numberButtons = listOf(button0, button1, button2, button3, button4, button5, button6, button7, button8, button9)
        for ((index, button) in numberButtons.withIndex()) {
            button.setOnClickListener {
                currentInput += index.toString()
                display.text = currentInput
            }
        }

        // Set sự kiện cho các nút chức năng
        buttonPlus.setOnClickListener { onOperatorClicked("+") }
        buttonMinus.setOnClickListener { onOperatorClicked("-") }
        buttonMultiply.setOnClickListener { onOperatorClicked("*") }
        buttonDivide.setOnClickListener { onOperatorClicked("/") }

        buttonEqual.setOnClickListener {
            if (firstValue.isNotEmpty() && currentInput.isNotEmpty()) {
                val result = calculateResult(firstValue, currentInput, operator)
                display.text = result
                currentInput = result
                firstValue = ""
                operator = ""
            }
        }

        buttonC.setOnClickListener {
            currentInput = ""
            display.text = "0"
        }

        buttonCE.setOnClickListener {
            currentInput = ""
            firstValue = ""
            operator = ""
            display.text = "0"
        }

        buttonBS.setOnClickListener {
            if (currentInput.isNotEmpty()) {
                currentInput = currentInput.dropLast(1)
                display.text = if (currentInput.isEmpty()) "0" else currentInput
            }
        }

        buttonDot.setOnClickListener {
            if (!currentInput.contains(".")) {
                currentInput += "."
                display.text = currentInput
            }
        }
        buttonPlusMinus.setOnClickListener {
            if (currentInput.isNotEmpty()) {

                currentInput = if (currentInput.startsWith("-")) {
                    currentInput.substring(1)
                } else {
                    "-$currentInput"
                }
                display.text = currentInput
            }
        }
    }

    private fun onOperatorClicked(selectedOperator: String) {
        if (currentInput.isNotEmpty()) {
            firstValue = currentInput
            operator = selectedOperator
            currentInput = ""
        }
    }

    private fun calculateResult(value1: String, value2: String, op: String): String {
        return try {
            val result = when (op) {
                "+" -> value1.toDouble() + value2.toDouble()
                "-" -> value1.toDouble() - value2.toDouble()
                "*" -> value1.toDouble() * value2.toDouble()
                "/" -> value1.toDouble() / value2.toDouble()
                else -> 0.0
            }
            result.toString()
        } catch (e: Exception) {
            "Error"
        }
    }
}