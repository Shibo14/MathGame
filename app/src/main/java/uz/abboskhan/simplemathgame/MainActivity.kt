package uz.abboskhan.simplemathgame

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import uz.abboskhan.simplemathgame.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.Timer
import java.util.TimerTask
import kotlin.math.log
import kotlin.math.log2
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    private var result = 0
    private var count = 0
    private var countFalse = 0
    private var firstRandomNumber = 0
    private var secondRandomNumber = 0
    private var progr = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getRandomNumbers()

        binding.progressBar.visibility = android.view.View.VISIBLE // Progres bar ko'rsatiladi

        val startTime = System.currentTimeMillis()
        val endTime = startTime + 20000 // 10 sekunddan so'ng ikkinchi Activityga o'tish
        val handler = Handler()


        binding.click.setOnClickListener {
            val a = binding.answer.text.toString()
            if (a.isEmpty()) {
                Toast.makeText(this, "raqamlarni kiriting!!!", Toast.LENGTH_SHORT).show()
            } else {


                if (a.toInt() == result) {
                    count++
                    binding.countTv.text = count.toString()
                }
                getRandomNumbers()
                binding.countTv.text = count.toString()
                binding.answer.text.clear()
            }


        }
        handler.postDelayed({
            binding.progressBar.visibility = android.view.View.INVISIBLE // Progres bar yashiriladi
            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("count", count)
            startActivity(intent)
            finish()


        }, 20000) // 10 sekunddan so'ng ikkinchi Activityga o'tish

        val timer = Timer()
        timer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                val currentTime = System.currentTimeMillis()
                val remainingTime = (endTime - currentTime) / 1000

                runOnUiThread {
                    val formattedTime = SimpleDateFormat("ss", Locale.getDefault()).format(
                        Date(remainingTime * 1000)
                    )

                    binding.textViewRemainingTime.text = "$formattedTime"
                    progr = formattedTime.toInt()
                    binding.progressBar.max = 20
                    binding.progressBar.progress = progr

                }
            }
        }, 0, 1000) // Her 1 sekunda ichida vaqtni yangilash

    }

    @SuppressLint("SetTextI18n")
    private fun getRandomNumbers() {
        firstRandomNumber = Random.nextInt(2, 10)
        secondRandomNumber = Random.nextInt(2, 10)

        binding.number1.text = "$firstRandomNumber + $secondRandomNumber"

        when (Random.nextInt(0, 4)) {
            0 -> {
                binding.number1.text = "$firstRandomNumber + $secondRandomNumber"
                result = firstRandomNumber + secondRandomNumber
            }

            1 -> {
                binding.number1.text = "$firstRandomNumber - $secondRandomNumber"
                result = firstRandomNumber - secondRandomNumber
            }

            2 -> {
                binding.number1.text = "$firstRandomNumber * $secondRandomNumber"
                result = firstRandomNumber * secondRandomNumber
            }

            3 -> {
                binding.number1.text = "$firstRandomNumber / $secondRandomNumber"
                result = firstRandomNumber / secondRandomNumber
            }


        }


    }


}