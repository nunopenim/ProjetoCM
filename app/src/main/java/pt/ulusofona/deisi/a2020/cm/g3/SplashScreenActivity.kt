package pt.ulusofona.deisi.a2020.cm.g3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        Handler().postDelayed(this::launchMain, 1000)
    }
    fun launchMain() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}