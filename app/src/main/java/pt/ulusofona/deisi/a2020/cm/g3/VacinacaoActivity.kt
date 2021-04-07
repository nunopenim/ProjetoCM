package pt.ulusofona.deisi.a2020.cm.g3

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.google.android.material.navigation.NavigationView
import pt.ulusofona.deisi.a2020.cm.g3.blocs.API.FakeAPI


class VacinacaoActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    lateinit var drawerLayout: DrawerLayout
    lateinit var navigationView: NavigationView
    lateinit var toolbar: Toolbar
    lateinit var chart: PieChart
    lateinit var card: TextView

    var vacinasData = FakeAPI.fakeVaccines()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vacinacao)
        drawerLayout = findViewById(R.id.drawer_layout_vacinacao)
        navigationView = findViewById(R.id.nav_view_vacinacao)
        toolbar = findViewById(R.id.toolbar_vacinacao)
        card = findViewById(R.id.confirmados)
        val cardstr = getString(R.string.totalvacinas) + vacinasData.doses
        card.text = cardstr
        setSupportActionBar(toolbar)
        navigationView.bringToFront()
        val toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.close_burger, R.string.open_burger)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        navigationView.setNavigationItemSelectedListener(this)
        //chart
        chart = findViewById(R.id.pieChart)
        val pieEntries: ArrayList<PieEntry> = ArrayList()
        val label = "type"
        val typeAmountMap: MutableMap<String, Int> = HashMap()
        typeAmountMap[getString(R.string.first_doses)] = vacinasData.doses1!!
        typeAmountMap[getString(R.string.second_doses)] = vacinasData.doses2!!
        val colors: ArrayList<Int> = ArrayList()
        colors.add(Color.parseColor("#66CDAA"))
        colors.add(Color.parseColor("#4682B4"))
        for (type in typeAmountMap.keys) {
            pieEntries.add(PieEntry(typeAmountMap[type]!!.toFloat(), type))
        }
        val pieDataSet = PieDataSet(pieEntries, label)
        pieDataSet.valueTextSize = 15f
        pieDataSet.valueTextColor = Color.WHITE
        pieDataSet.colors = colors
        val pieData = PieData(pieDataSet)
        pieData.setDrawValues(true)
        chart.setEntryLabelTextSize(15f)
        chart.legend.isEnabled = false
        chart.description.text = ""
        chart.data = pieData
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
                drawerLayout.closeDrawer(GravityCompat.START)
                item.isChecked = false
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