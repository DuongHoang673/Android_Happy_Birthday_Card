package com.example.listview

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var inputNumber: EditText
    private lateinit var radioGroup: RadioGroup
    private lateinit var showButton: Button
    private lateinit var listView: ListView
    private lateinit var errorText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inputNumber = findViewById(R.id.inputNumber)
        radioGroup = findViewById(R.id.radioGroup)
        showButton = findViewById(R.id.showButton)
        listView = findViewById(R.id.listView)
        errorText = findViewById(R.id.errorText)

        showButton.setOnClickListener {
            displayNumbers()
        }
    }

    private fun displayNumbers() {
        val n = inputNumber.text.toString().toIntOrNull()

        if (n == null || n < 0) {
            errorText.visibility = View.VISIBLE
            errorText.text = "Please enter a valid positive integer."
            listView.adapter = null
            return
        } else {
            errorText.visibility = View.GONE
        }

        val numbers = when (radioGroup.checkedRadioButtonId) {
            R.id.radioEven -> generateEvenNumbers(n)
            R.id.radioOdd -> generateOddNumbers(n)
            R.id.radioSquare -> generatePerfectSquares(n)
            else -> emptyList()
        }

        listView.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, numbers)
    }

    private fun generateEvenNumbers(n: Int): List<Int> {
        return (0..n).filter { it % 2 == 0 }
    }

    private fun generateOddNumbers(n: Int): List<Int> {
        return (1..n).filter { it % 2 != 0 }
    }

    private fun generatePerfectSquares(n: Int): List<Int> {
        val squares = mutableListOf<Int>()
        var i = 0
        while (i * i <= n) {
            squares.add(i * i)
            i++
        }
        return squares
    }
}