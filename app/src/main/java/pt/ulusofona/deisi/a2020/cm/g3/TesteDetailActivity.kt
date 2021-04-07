package pt.ulusofona.deisi.a2020.cm.g3

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class TesteDetailActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener  {
    lateinit var drawerLayout: DrawerLayout
    lateinit var navigationView: NavigationView
    lateinit var toolbar: Toolbar
    lateinit var foto: ImageView
    var hasPhoto = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teste_detail)
        val b = intent.extras
        val local_intent = b?.getString("local")
        val data_intent = b?.getString("data")
        val resultado_intent = b?.getBoolean("resultado")
        val foto_id = b?.getInt("photo")
        drawerLayout = findViewById(R.id.drawer_layout_detail)
        navigationView = findViewById(R.id.nav_view_detail)
        toolbar = findViewById(R.id.toolbar_detail)
        foto = findViewById(R.id.teste_foto)
        if (foto_id != null && foto_id != 0) {
            foto.setImageResource(foto_id)
            hasPhoto = true
        }
        setSupportActionBar(toolbar)
        navigationView.bringToFront()
        val toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.close_burger, R.string.open_burger)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        navigationView.setNavigationItemSelectedListener(this)
        val local = findViewById<TextView>(R.id.teste_local)
        val data = findViewById<TextView>(R.id.teste_data)
        val resultado = findViewById<TextView>(R.id.teste_resultado)
        val localstr = "${getString(R.string.local)} ${local_intent}"
        val datastr = "${getString(R.string.data)} ${data_intent}"
        var resultadostr = ""
        if (resultado_intent!!) {
            resultadostr = getString(R.string.result_pos)
        }
        else {
            resultadostr = getString(R.string.result_neg)
        }
        local.text = localstr
        data.text = datastr
        resultado.text = resultadostr
        if (hasPhoto) {
            foto.setOnClickListener {
                val intent = Intent(this, PhotoViewActivity::class.java)
                val b = Bundle()
                b.putInt("photo", foto_id!!)
                intent.putExtras(b)
                startActivity(intent)
            }
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
                startActivity(Intent(this, RegistoTesteActivity::class.java))
            }
        }
        return true
    }
}