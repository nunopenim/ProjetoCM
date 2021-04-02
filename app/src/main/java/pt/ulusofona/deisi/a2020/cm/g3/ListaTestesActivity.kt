package pt.ulusofona.deisi.a2020.cm.g3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationView
import pt.ulusofona.deisi.a2020.cm.g3.blocs.API.FakeAPI
import pt.ulusofona.deisi.a2020.cm.g3.blocs.GUI.TesteAdapter

class ListaTestesActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    lateinit var drawerLayout: DrawerLayout
    lateinit var navigationView: NavigationView
    lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_testes)
        drawerLayout = findViewById(R.id.drawer_layout_lt)
        navigationView = findViewById(R.id.nav_view_lt)
        toolbar = findViewById(R.id.toolbar_lt)
        setSupportActionBar(toolbar)
        navigationView.bringToFront()
        val toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.close_burger, R.string.open_burger)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        navigationView.setNavigationItemSelectedListener(this)

        //RecyclerView is comming, boys and girls
        val testeAdapter  = TesteAdapter(FakeAPI.fakeTests())
        val rv : RecyclerView = findViewById(R.id.recycler_testes)
        rv.adapter = testeAdapter

        // https://stackoverflow.com/questions/3913592/start-an-activity-with-a-parameter :)
        testeAdapter.onItemClick = {teste ->
            var intent = Intent(this, TesteDetailActivity::class.java)
            var b = Bundle()
            b.putString("local", teste.local)
            b.putString("data", teste.data.toString())
            b.putBoolean("resultado", teste.positivo)
            intent.putExtras(b)
            startActivity(intent)
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
                drawerLayout.closeDrawer(GravityCompat.START)
                item.isChecked = false
            }
            R.id.add_teste -> {
                startActivity(Intent(this, RegistoTesteActivity::class.java))
            }
        }
        return true
    }
}