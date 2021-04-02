package pt.ulusofona.deisi.a2020.cm.g3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class TesteDetailActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener  {
    lateinit var drawerLayout: DrawerLayout
    lateinit var navigationView: NavigationView
    lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teste_detail)
        drawerLayout = findViewById(R.id.drawer_layout_detail)
        navigationView = findViewById(R.id.nav_view_detail)
        toolbar = findViewById(R.id.toolbar_detail)
        setSupportActionBar(toolbar)
        navigationView.bringToFront()
        val toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.close_burger, R.string.open_burger)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        navigationView.setNavigationItemSelectedListener(this)
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