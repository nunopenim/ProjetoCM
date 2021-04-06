package pt.ulusofona.deisi.a2020.cm.g3

import android.os.Bundle
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.view.ScaleGestureDetector.SimpleOnScaleGestureListener
import android.widget.ImageView
import android.widget.ScrollView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.github.chrisbanes.photoview.PhotoView


class PhotoViewActivity : AppCompatActivity() {
    lateinit var toolbar: Toolbar
    lateinit var photo: PhotoView
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
    }
}