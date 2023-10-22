package com.yusif.catchthekennyballkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.yusif.catchthekennyballkotlin.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList

private lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {

    var score = 0
    var imageArray = ArrayList<ImageView>()
    var handler = Handler()
    var runnable = Runnable { }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //Image Array
        imageArray.add(imageView1)
        imageArray.add(imageView2)
        imageArray.add(imageView3)
        imageArray.add(imageView4)
        imageArray.add(imageView5)
        imageArray.add(imageView6)
        imageArray.add(imageView7)
        imageArray.add(imageView8)
        imageArray.add(imageView9)
        imageArray.add(imageView10)
        imageArray.add(imageView11)
        imageArray.add(imageView12)
        imageArray.add(imageView13)
        imageArray.add(imageView14)
        imageArray.add(imageView15)
        imageArray.add(imageView16)
        hideImages()

        //CountDownTimer

        object : CountDownTimer(25500, 900) {
            //her saniye ne olacag
            override fun onTick(p0: Long) {
                timeText.text = "Time: " + p0 / 1000
            }

            //vaxt birince ne olacag
            override fun onFinish() {
                timeText.text = "Time: 0"
                handler.removeCallbacks(runnable)//stop game after passing time

                //All image are invisible after stop game
                for (image in imageArray) {
                    image.visibility = View.INVISIBLE
                }

                //Alert
                val alert = AlertDialog.Builder(this@MainActivity)
                alert.setTitle("Game Over")
                alert.setMessage("Restart The Game?")
                alert.setPositiveButton("YES") { dialog, which ->
                    //Restart
                    val intent = intent
                    finish()//close app
                    startActivity(intent)

                }
                alert.setNegativeButton("No") { dialog, which ->
                    Toast.makeText(this@MainActivity, "Game Over", Toast.LENGTH_LONG).show()
                    finish()//close app
                }
                alert.show()
            }

        }.start()

    }

    fun hideImages() {

        runnable = object : Runnable {
            override fun run() {
                for (image in imageArray) {
                    image.visibility = View.INVISIBLE
                }
                val random = Random()
                val randomIndex = random.nextInt(16)
                imageArray[randomIndex].visibility = View.VISIBLE

                //game speed
                handler.postDelayed(runnable, 500)
            }
        }
        handler.post(runnable)
    }

    fun increaseScore(view: View) {
        score++
        scoreText.text = "Score: ${score}"
    }
}