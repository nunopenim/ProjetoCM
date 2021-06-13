package pt.ulusofona.deisi.a2020.cm.g3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import pt.ulusofona.deisi.a2020.cm.g3.data.battery.Battery
import pt.ulusofona.deisi.a2020.cm.g3.data.battery.OnBatteryCurrentListener
import pt.ulusofona.deisi.a2020.cm.g3.data.sensors.location.FusedLocation

class MainActivity : AppCompatActivity(),  NavigationView.OnNavigationItemSelectedListener, OnBatteryCurrentListener {

    lateinit var drawerLayout: DrawerLayout
    lateinit var navigationView: NavigationView
    lateinit var toolbar: Toolbar
    var shownMessage = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Battery.start(this, this)
        FusedLocation.start(this)
        setContentView(R.layout.activity_main)
        //this.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT; // bloqueado na rotação because yes
        if(supportFragmentManager.backStackEntryCount == 0) {
            NavigationManager.goToDashboard(supportFragmentManager)
        }
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
                item.isChecked = false
                drawerLayout.closeDrawer(GravityCompat.START)
                toolbar.title = getString(R.string.Dashboard)
                NavigationManager.goToDashboard(supportFragmentManager)
            }
            R.id.contactos -> {
                item.isChecked = false
                drawerLayout.closeDrawer(GravityCompat.START)
                toolbar.title = getString(R.string.contactos)
                NavigationManager.goToContacts(supportFragmentManager)
            }
            R.id.vacinas -> {
                item.isChecked = false
                drawerLayout.closeDrawer(GravityCompat.START)
                toolbar.title = getString(R.string.vacinas)
                NavigationManager.goToVaccination(supportFragmentManager)
            }
            R.id.lista_testes -> {
                item.isChecked = false
                drawerLayout.closeDrawer(GravityCompat.START)
                toolbar.title = getString(R.string.lista_testes)
                NavigationManager.goToList(supportFragmentManager)
            }
            R.id.add_teste -> {
                item.isChecked = false
                drawerLayout.closeDrawer(GravityCompat.START)
                toolbar.title = getString(R.string.add_test)
                NavigationManager.registerTest(supportFragmentManager)
            }
        }
        return true
    }

    override fun onCurrentChanged (current: Float){
        if(current <= 20) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            if (!shownMessage) {
                val toast = Toast.makeText(this, getString(R.string.lowbat), Toast.LENGTH_LONG)
                toast.show()
                shownMessage = true
            }
        }
    }
}
