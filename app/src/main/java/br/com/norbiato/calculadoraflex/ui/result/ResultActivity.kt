package br.com.norbiato.calculadoraflex.ui.result

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import br.com.norbiato.calculadoraflex.R
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        if (intent.extras == null) {
            Toast.makeText(this, "Não foi possível realizar a operação",
                Toast.LENGTH_SHORT).show()
        } else {
            calculate()
        }
    }

    private fun calculate() {
        val gasPrice = intent.extras?.getDouble("GAS_PRICE", 0.0) ?: 1.0
        val ethanolPrice = intent.extras?.getDouble("ETHANOL_PRICE", 0.0) ?: 0.0
        val gasAverage = intent.extras?.getDouble("GAS_AVERAGE", 0.0) ?: 1.0
        val ethanolAverage = intent.extras?.getDouble("ETHANOL_AVERAGE", 0.0) ?: 0.0
        val performanceOfMyCar = ethanolAverage?.div(gasAverage!!)
        val priceOfFuelIndice = ethanolPrice?.div(gasPrice!!)
        if (priceOfFuelIndice != null) {
            if (priceOfFuelIndice <= performanceOfMyCar!!) {
                tvSuggestion.text = getString(R.string.ethanol)
            } else {
                tvSuggestion.text = getString(R.string.gasoline)
            }
        }
        if (ethanolPrice != null) {
            tvEthanolAverageResult.text = (ethanolPrice / ethanolAverage!!).toString()
        }
        if (ethanolPrice != null) {
            tvGasAverageResult.text = (gasPrice / ethanolAverage!!).toString()
        }
    }

}
