package com.example.syubaevsa_01_03

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class cost_calculation : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cost_calculation)

        val btnBack = findViewById<ImageButton>(R.id.btnBack)
        val spinner = findViewById<Spinner>(R.id.spinnerApartmentType)
        val etSquareMeters = findViewById<EditText>(R.id.etSquareMeters)
        val btnCalculate = findViewById<Button>(R.id.btnCalculate)

        // Настройка Spinner
        val apartmentTypes = arrayOf("1-комнатная", "2-комнатная", "3-комнатная", "Студия")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, apartmentTypes)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        // Кнопка "Назад"
        btnBack.setOnClickListener {
            finish() // Возврат на предыдущий экран
        }

        // Кнопка "Рассчитать"
        btnCalculate.setOnClickListener {
            val squareMeters = etSquareMeters.text.toString().toDoubleOrNull() ?: 0.0
            if (squareMeters <= 0) {
                Toast.makeText(this, "Введите корректное значение", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val selectedType = spinner.selectedItemPosition
            val apartmentTypes = resources.getStringArray(R.array.apartment_types) // Массив из strings.xml
            val costPerMeter = 100.0 // Пример: 100 тыс. руб./м²

            val totalCost = when (selectedType) {
                0 -> costPerMeter * squareMeters * 1.4  // 1-комнатная
                1 -> costPerMeter * squareMeters         // 2-комнатная
                2 -> costPerMeter * squareMeters * 0.8   // 3-комнатная
                3 -> costPerMeter * squareMeters * 1.1   // Студия
                else -> 0.0
            }

            // Переход на ResultActivity с данными
            val intent = Intent(this, result::class.java).apply {
                putExtra("SQUARE_METERS", squareMeters)
                putExtra("TOTAL_COST", totalCost)
                putExtra("APARTMENT_TYPE", apartmentTypes[selectedType])
            }
            startActivity(intent)
        }
    }
}