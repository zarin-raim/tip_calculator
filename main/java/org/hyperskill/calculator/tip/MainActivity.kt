package org.hyperskill.calculator.tip

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.math.BigDecimal
import java.math.RoundingMode

import kotlin.math.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edit_text.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
                showTip()
            }
        })

        slider.addOnChangeListener { _,_,_ -> showTip() }
    }

    fun showTip() {
        if (edit_text.editableText.isEmpty()) {
            text_view.visibility = View.INVISIBLE
            return
        }

        val billValue = edit_text.editableText.toString().toDouble()
        val sliderValue = slider.value.toInt()

        val tipAmount = BigDecimal(billValue * sliderValue / 100).setScale(2, RoundingMode.HALF_EVEN)

        val result = "Tip amount: $tipAmount"

        text_view.text = result
        text_view.visibility = View.VISIBLE
    }
}