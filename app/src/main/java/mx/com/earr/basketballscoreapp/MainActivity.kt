package mx.com.earr.basketballscoreapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import mx.com.earr.basketballscoreapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        binding.mainViewModel = viewModel

        viewModel.localScoreViewModel.observe(this) { localScoreValue ->
            binding.localScoreText.text = localScoreValue.toString()
        }

        viewModel.visitorScoreViewModel.observe(this) { visitorScoreValue ->
            binding.visitorScoreText.text = visitorScoreValue.toString()
        }

        setupButtons()
    }

    private fun setupButtons() {
        binding.resultsButton.setOnClickListener {
            startScoreActivity()
        }
    }

    private fun startScoreActivity() {
        val intent = Intent(this, ScoreActivity::class.java)
        intent.putExtra(ScoreActivity.LOCAL_SCORE_KEY, viewModel.localScoreViewModel.value)
        intent.putExtra(ScoreActivity.VISITOR_SCORE_KEY, viewModel.visitorScoreViewModel.value)
        startActivity(intent)
    }
}