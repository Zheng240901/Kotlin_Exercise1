package my.com.exercise2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import my.com.exercise2.databinding.ActivitySubBinding

class SubActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySubBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySubBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnClose.setOnClickListener { finish() }

        // Output
        val fruits = intent.getStringExtra("fruits") ?: ""
        val quantity = intent.getIntExtra("quantity", 0)
        val subtotal = intent.getDoubleExtra("subtotal", 0.0)
        val total = intent.getDoubleExtra("total", 0.0)

        with (binding) {
            lblFruits.text = fruits
            lblQuantity.text = quantity.toString()
            lblSubtotal.text = "%.2f".format(subtotal)
            lblTotal.text = "%.2f".format(total)
        }
    }
}