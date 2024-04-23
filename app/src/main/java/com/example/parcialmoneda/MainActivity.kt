package com.example.parcialmoneda

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val editTextAmount = findViewById<EditText>(R.id.editTextAmount)
        val buttonToDollars = findViewById<Button>(R.id.buttonToDollars)
        val buttonToPesos = findViewById<Button>(R.id.buttonToPesos)
        val textViewResult = findViewById<TextView>(R.id.textViewResult)

        buttonToDollars.setOnClickListener {
            val amountText = editTextAmount.text.toString()
            if (amountText.isNotEmpty()) {
                val pesos = amountText.toDouble()
                val dollars = convertToDollars(pesos)
                val formattedResult = DecimalFormat("#,###.##").format(dollars)
                textViewResult.text = "$pesos pesos equivale a \$$formattedResult dólares."
            } else {
                textViewResult.text = "Ingrese una cantidad válida."
            }
        }

        buttonToPesos.setOnClickListener {
            val amountText = editTextAmount.text.toString()
            if (amountText.isNotEmpty()) {
                val dollars = amountText.toDouble()
                val pesos = convertToPesos(dollars)
                val formattedResult = DecimalFormat("#,###.##").format(pesos)
                textViewResult.text = "\$$dollars dólares equivale a $formattedResult pesos colombianos."
            } else {
                textViewResult.text = "Ingrese una cantidad válida."
            }
        }
    }

    private fun convertToDollars(pesos: Double): Double {
        // Tasa de cambio: 1 dólar = 3920.93 pesos colombianos
        return pesos / 3920.93
    }

    private fun convertToPesos(dollars: Double): Double {
        // Tasa de cambio: 1 dólar = 3920.93 pesos colombianos
        return dollars * 3920.93
    }

}
