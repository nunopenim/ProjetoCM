package pt.ulusofona.deisi.a2020.cm.g3

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.google.android.material.navigation.NavigationView
import pt.ulusofona.deisi.a2020.cm.g3.blocs.API.FakeAPI
import pt.ulusofona.deisi.a2020.cm.g3.blocs.InfoSingleton

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    lateinit var drawerLayout: DrawerLayout
    lateinit var navigationView: NavigationView
    lateinit var toolbar: Toolbar
    lateinit var chart: BarChart

    var data = FakeAPI.fakeData()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        drawerLayout = findViewById(R.id.drawer_layout)
        navigationView = findViewById(R.id.nav_view)
        toolbar = findViewById(R.id.toolbar)

        //cards
        val confirmados = findViewById<TextView>(R.id.confirmados)
        val recuperados = findViewById<TextView>(R.id.recuperados)
        val internados = findViewById<TextView>(R.id.internados)
        val obitos = findViewById<TextView>(R.id.obitos)
        val confirmadosstr = getString(R.string.confirmados) + data.confirmados
        val recuperadosstr = getString(R.string.recuperados) + data.recuperados
        val internadosstr = getString(R.string.internados) + data.internados
        val obitosstr = getString(R.string.obitos) + data.obitos
        confirmados.text = confirmadosstr
        recuperados.text = recuperadosstr
        internados.text = internadosstr
        obitos.text = obitosstr

        //chart stuff
        chart = findViewById(R.id.chart1)
        val barData0to9 = BarDataSet(arrayListOf(BarEntry(0f, data.confirmados_general()[0].toFloat())), "0-9")
        val barData10to19 = BarDataSet(arrayListOf(BarEntry(1.25f, data.confirmados_general()[1].toFloat())), "10-19")
        val barData20to29 = BarDataSet(arrayListOf(BarEntry(2.5f, data.confirmados_general()[2].toFloat())), "20-29")
        val barData30to39 = BarDataSet(arrayListOf(BarEntry(3.75f, data.confirmados_general()[3].toFloat())), "30-39")
        val barData40to49 = BarDataSet(arrayListOf(BarEntry(5f, data.confirmados_general()[4].toFloat())), "40-49")
        val barData50to59 = BarDataSet(arrayListOf(BarEntry(6.25f, data.confirmados_general()[5].toFloat())), "50-59")
        val barData60to69 = BarDataSet(arrayListOf(BarEntry(7.5f, data.confirmados_general()[6].toFloat())), "60-69")
        val barData70to79 = BarDataSet(arrayListOf(BarEntry(8.75f, data.confirmados_general()[7].toFloat())), "70-79")
        val barData80 = BarDataSet(arrayListOf(BarEntry(10f, data.confirmados_general()[8].toFloat())), "80+")
        val barData = BarData(barData0to9, barData10to19, barData20to29, barData30to39, barData40to49, barData50to59, barData60to69, barData70to79, barData80)
        barData0to9.valueTextSize=12f
        barData10to19.valueTextSize=12f
        barData10to19.color=Color.BLACK
        barData20to29.valueTextSize=12f
        barData20to29.color=Color.RED
        barData30to39.valueTextSize=12f
        barData30to39.color=Color.GREEN
        barData40to49.valueTextSize=12f
        barData40to49.color=Color.YELLOW
        barData50to59.valueTextSize=12f
        barData50to59.color=Color.GRAY
        barData60to69.valueTextSize=12f
        barData60to69.color=Color.MAGENTA
        barData70to79.valueTextSize=12f
        barData70to79.color=Color.DKGRAY
        barData80.valueTextSize=12f
        barData80.color=Color.BLUE
        chart.data = barData
        chart.xAxis.setDrawLabels(false)
        chart.description.text = ""

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
