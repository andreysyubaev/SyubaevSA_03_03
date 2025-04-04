package com.example.syubaevsa_01_03

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class result : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        // Получаем данные из Intent
        val squareMeters = intent.getDoubleExtra("SQUARE_METERS", 0.0)
        val totalCost = intent.getDoubleExtra("TOTAL_COST", 0.0)
        val apartmentType = intent.getStringExtra("APARTMENT_TYPE") ?: ""

        // Находим View
        val tvSquareMeters = findViewById<TextView>(R.id.tvSquareMeters)
        val tvTotalCost = findViewById<TextView>(R.id.tvTotalCost)
        val btnToMain = findViewById<Button>(R.id.btnToMain)

        // Устанавливаем данные
        tvSquareMeters.text = "Метры: ${squareMeters} м²"
        tvTotalCost.text = "Итого: ${"%.2f".format(totalCost)} тыс. руб."

        // Кнопка "На главную"
        btnToMain.setOnClickListener {
            val intent = Intent(this, flat_bank::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP // Закрыть все предыдущие экраны
            startActivity(intent)
        }
    }
}