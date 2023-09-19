package uz.abboskhan.simplemathgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import uz.abboskhan.simplemathgame.databinding.ActivitySecondActivityBinding

class SecondActivity : AppCompatActivity() {
    lateinit var binding: ActivitySecondActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val getCount = intent.getIntExtra("count", 0)
        binding.textView2.text = getCount.toString()

        binding.quit.setOnClickListener {
            finish()
        }
        binding.res.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

    }
}