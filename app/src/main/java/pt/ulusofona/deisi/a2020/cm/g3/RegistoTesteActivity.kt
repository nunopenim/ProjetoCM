package pt.ulusofona.deisi.a2020.cm.g3

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.FileProvider
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.button.MaterialButton
import com.google.android.material.navigation.NavigationView
import pt.ulusofona.deisi.a2020.cm.g3.blocs.InfoSingleton
import pt.ulusofona.deisi.a2020.cm.g3.blocs.Teste
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class RegistoTesteActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    lateinit var drawerLayout: DrawerLayout
    lateinit var navigationView: NavigationView
    lateinit var toolbar: Toolbar
    lateinit var local: TextView
    lateinit var dataTeste: TextView
    lateinit var dropdown: Spinner
    lateinit var saveButton: MaterialButton
    lateinit var photoButton: MaterialButton
    lateinit var takenPhotoMessage: TextView

    private val REQUEST_CODE = 51 // Arbitrário, so Area51 code it is :)
    private lateinit var photoFile: File
    private val FILE_NAME = "buffer.jpg"

    var photo: Bitmap? = null
    lateinit var options: Array<String>

    val mcurrentTime = Calendar.getInstance()
    val year = mcurrentTime.get(Calendar.YEAR)
    val month = mcurrentTime.get(Calendar.MONTH)
    val day = mcurrentTime.get(Calendar.DAY_OF_MONTH)
    var y = 0
    var m = 0
    var d = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registo_teste)
        drawerLayout = findViewById(R.id.drawer_layout_rt)
        navigationView = findViewById(R.id.nav_view_rt)
        toolbar = findViewById(R.id.toolbar_rt)
        local = findViewById(R.id.local)
        dataTeste = findViewById(R.id.data)
        dropdown = findViewById(R.id.result_spinner)
        saveButton = findViewById(R.id.regist_test)
        photoButton = findViewById(R.id.take_foto)
        takenPhotoMessage = findViewById(R.id.foto_tirada)
        setSupportActionBar(toolbar)
        navigationView.bringToFront()
        val toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.close_burger,
            R.string.open_burger
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        navigationView.setNavigationItemSelectedListener(this)
        val datePicker = DatePickerDialog(this, DatePickerDialog.OnDateSetListener
        { view, year, month, dayOfMonth ->
            dataTeste.text = String.format("%02d / %02d / %04d", dayOfMonth, month + 1, year)
            y = year
            m = month + 1
            d = dayOfMonth
        }, year, month, day
        )
        dataTeste.setOnClickListener { datePicker.show() }
        options = arrayOf(getString(R.string.neg), getString(R.string.pos))
        val adapter: ArrayAdapter<String> =
            ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, options)
        dropdown.adapter = adapter
        photoButton.setOnClickListener {
            val photoIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            photoFile = getPhotoFile(FILE_NAME)
            val fileprovider = FileProvider.getUriForFile(this, "pt.ulusofona.deisi.a2020.cm.g3.fileprovider", photoFile)
            photoIntent.putExtra(MediaStore.EXTRA_OUTPUT, fileprovider)

            if (photoIntent.resolveActivity(this.packageManager) != null) {
                startActivityForResult(photoIntent, REQUEST_CODE)
            }
            else {
                Toast.makeText(this, "AAAAA", Toast.LENGTH_LONG).show() // Aqui falhou a abrir a camera seja lá porque motivo, mudar o texto
            }

            takenPhotoMessage.visibility = View.VISIBLE
        }
        saveButton.setOnClickListener {
            var erro = false
            if (local.text.toString() == "") {
                local.error = getString(R.string.invalid_local)
                erro = true
            }
            if (dataTeste.text.toString() == "") {
                dataTeste.error = getString(R.string.invalid_data)
                erro = true
            }
            if (!erro) {
                var resultado = false
                if (dropdown.selectedItemPosition == 0) {
                    resultado = false
                }
                else if (dropdown.selectedItemPosition == 1) {
                    resultado = true
                }
                val timeStr = String.format("%04d-%02d-%02d", y, m, d)
                val formatter = SimpleDateFormat("yyyy-MM-dd")
                val date = formatter.parse(timeStr).time
                var teste = Teste(local.text.toString(), resultado, Date(date), photo)
                InfoSingleton.addTestToList(teste)
                startActivity(Intent(this, ListaTestesActivity::class.java))
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val takenpic = BitmapFactory.decodeFile(photoFile.absolutePath)
            photo = takenpic
        }
        else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        }
        else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.dashboard -> {
                startActivity(Intent(this, MainActivity::class.java))
            }
            R.id.contactos -> {
                startActivity(Intent(this, ContactosActivity::class.java))
            }
            R.id.vacinas -> {
                startActivity(Intent(this, VacinacaoActivity::class.java))
            }
            R.id.lista_testes -> {
                startActivity(Intent(this, ListaTestesActivity::class.java))
            }
            R.id.add_teste -> {
                drawerLayout.closeDrawer(GravityCompat.START)
                item.isChecked = false
            }
        }
        return true
    }

    private fun getPhotoFile(fileName: String): File {
        val storagetemp = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return createTempFile(fileName, ".jpg", storagetemp)
    }
}

