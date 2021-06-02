package pt.ulusofona.deisi.a2020.cm.g3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    lateinit var drawerLayout: DrawerLayout
    lateinit var navigationView: NavigationView
    lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        NavigationManager.goToDashboard(supportFragmentManager)
        drawerLayout = findViewById(R.id.drawer_layout)
        navigationView = findViewById(R.id.nav_view)
        toolbar = findViewById(R.id.toolbar)
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
                drawerLayout.closeDrawer(GravityCompat.START)
                item.isChecked = false
                NavigationManager.goToDashboard(supportFragmentManager)
            }
            R.id.contactos -> {
                drawerLayout.closeDrawer(GravityCompat.START)
                item.isChecked = false
            }
            R.id.vacinas -> {
                drawerLayout.closeDrawer(GravityCompat.START)
                item.isChecked = false
            }
            R.id.lista_testes -> {
                drawerLayout.closeDrawer(GravityCompat.START)
                item.isChecked = false
            }
            R.id.add_teste -> {
                drawerLayout.closeDrawer(GravityCompat.START)
                item.isChecked = false
            }
        }
        return true
    }

}
