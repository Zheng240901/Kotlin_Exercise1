package my.com.exercise2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CheckBox
import android.widget.Toast
import androidx.core.view.children
import my.com.exercise2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        reset()

        binding.btnReset.setOnClickListener { reset() }
        binding.btnSubmit.setOnClickListener { submit() }
    }

    private fun toast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }

    private fun reset() {
        with (binding) {
            // TODO: Uncheck all checkboxes
            // TODO: Method #1 - Individual
//            chkApple.isChecked = false
//            chkOrange.isChecked = false
//            chkBanana.isChecked = false

            // TODO: Method #2 - Group (for-loop)
//            for(view in grpFruits.children) {
//                (view as CheckBox).isChecked = false
//            }

            // TODO: Method #3 - Group (lambda)
            grpFruits.children
                .map { it as CheckBox }
                .forEach{ it.isChecked = false}

            edtQuantity.text.clear()
            edtQuantity.requestFocus()
        }
    }

    private fun submit() {
        // Input -----------------------------------------------------------------------------------
        val quantity = binding.edtQuantity.text.toString().toIntOrNull() ?: 0
        val fruits = getFruits() // List<String>

        // Basic input validation ------------------------------------------------------------------
        if (quantity < 1 || quantity > 99) {
            toast(getString(R.string.invalid_quantity))
            return
        }

        // TODO: At least 1 fruit is checked
        if(fruits.isEmpty()){
            toast(getString(R.string.invalid_fruits))
            return
        }

        // Calculation -----------------------------------------------------------------------------
        val subtotal = getSubtotal(fruits) // Double
        val total = subtotal * quantity

        // Navigate with data passing --------------------------------------------------------------
        val intent = Intent(this, SubActivity::class.java)
            .putExtra("fruits", fruits.joinToString()) // String
            .putExtra("quantity", quantity) // Int
            .putExtra("subtotal", subtotal) // Double
            .putExtra("total", total) // Double
        startActivity(intent)
    }

    // TODO: Return selected fruits as list of string
    private fun getFruits() : List<String> {
        // TODO: Method #1 - Individual
//        val list = mutableListOf<String>()
//
//        with (binding){
//            if(chkApple.isChecked)  list.add(chkApple.text.toString())
//            if(chkBanana.isChecked) list.add(chkBanana.text.toString())
//            if(chkOrange.isChecked) list.add(chkOrange.text.toString())
//        }
//
//        return list

        // TODO: Method #2 - Group (for-loop)
//        val list = mutableListOf<String>()
//
//        for(view in binding.grpFruits.children){
//            val chk = view as CheckBox
//            if(chk.isChecked) list.add(chk.text.toString())
//        }
//        return list

        // TODO: Method #3 - Group (lambda)
        return binding.grpFruits.children
            .map { it as CheckBox }
            .filter { it.isChecked }
            .map { it.text.toString() }
            .toList()

    }

    // TODO: Calculate subtotal - Apple 1.5, Banana 3.0, Orange 4.5
    private fun getSubtotal(fruits: List<String>): Double {
        var subtotal = 0.0

        for(fruit in fruits){
            subtotal += when(fruit){
                getString(R.string.apple) -> 1.5
                getString(R.string.banana) -> 3.0
                getString(R.string.orange) -> 4.5
                else -> 0.0
            }
        }

        return subtotal
    }

}