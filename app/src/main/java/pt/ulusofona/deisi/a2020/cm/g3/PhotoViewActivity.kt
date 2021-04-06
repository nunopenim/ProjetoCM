package pt.ulusofona.deisi.a2020.cm.g3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.ScrollView
import android.widget.ToggleButton
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout

class PhotoViewActivity : AppCompatActivity() {
    lateinit var toolbar: Toolbar
    lateinit var photo: ImageView
    lateinit var scrollView: ScrollView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_view)
        val b = intent.extras
        val foto_id = b?.getInt("photo")
        toolbar = findViewById(R.id.toolbar_photoview)
        photo = findViewById(R.id.teste_foto_view)
        scrollView = findViewById(R.id.scrview)
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener {onBackPressed()}
        photo.setImageResource(foto_id!!)
        scrollView.bringToFront()
    }
}