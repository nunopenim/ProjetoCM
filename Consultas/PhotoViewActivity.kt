package pt.ulusofona.deisi.a2020.cm.g3

import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.github.chrisbanes.photoview.PhotoView


class PhotoViewActivity : AppCompatActivity() {
    lateinit var toolbar: Toolbar
    lateinit var photo: PhotoView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_view)
        val b = intent.extras
        val foto_id = b?.getByteArray("photo")

        toolbar = findViewById(R.id.toolbar_photoview)
        photo = findViewById(R.id.teste_foto_view)
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener {onBackPressed()}
        val foto_pic = BitmapFactory.decodeByteArray(foto_id, 0, foto_id!!.size)
        photo.setImageBitmap(foto_pic)
    }
}