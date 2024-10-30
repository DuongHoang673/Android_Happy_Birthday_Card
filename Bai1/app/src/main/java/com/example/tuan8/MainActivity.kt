package com.example.tuan8


// MainActivity.kt
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editNumber = findViewById<EditText>(R.id.editNumber)
        val radioEven = findViewById<RadioButton>(R.id.radioEven)
        val radioOdd = findViewById<RadioButton>(R.id.radioOdd)
        val radioPerfectSquare = findViewById<RadioButton>(R.id.radioPerfectSquare)
        val btnShow = findViewById<Button>(R.id.btnShow)
        val listView = findViewById<ListView>(R.id.listView)
        val tvError = findViewById<TextView>(R.id.tvError)

        btnShow.setOnClickListener {
            tvError.visibility = TextView.GONE
            val input = editNumber.text.toString()
            if (input.isEmpty()) {
                tvError.text = "Please enter a number"
                tvError.visibility = TextView.VISIBLE
                return@setOnClickListener
            }

            val n = input.toIntOrNull()
            if (n == null || n <= 0) {
                tvError.text = "Please enter a positive integer"
                tvError.visibility = TextView.VISIBLE
                return@setOnClickListener
            }

            val numbers = when {
                radioEven.isChecked -> generateEvenNumbers(n)
                radioOdd.isChecked -> generateOddNumbers(n)
                radioPerfectSquare.isChecked -> generatePerfectSquares(n)
                else -> emptyList()
            }

            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, numbers)
            listView.adapter = adapter
        }
    }

    private fun generateEvenNumbers(n: Int): List<Int> {
        return (0..n).filter { it % 2 == 0 }
    }

    private fun generateOddNumbers(n: Int): List<Int> {
        return (0..n).filter { it % 2 != 0 }
    }

    private fun generatePerfectSquares(n: Int): List<Int> {
        val result = mutableListOf<Int>()
        var i = 0
        while (i * i <= n) {
            result.add(i * i)
            i++
        }
        return result
    }
}
