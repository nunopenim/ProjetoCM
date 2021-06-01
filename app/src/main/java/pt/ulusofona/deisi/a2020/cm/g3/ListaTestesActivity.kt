package pt.ulusofona.deisi.a2020.cm.g3

import android.R.attr.bitmap
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationView
import pt.ulusofona.deisi.a2020.cm.g3.blocs.GUI.TesteAdapter
import pt.ulusofona.deisi.a2020.cm.g3.blocs.InfoSingleton
import java.io.ByteArrayOutputStream
import java.nio.ByteBuffer


class ListaTestesActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    lateinit var drawerLayout: DrawerLayout
    lateinit var navigationView: NavigationView
    lateinit var toolbar: Toolbar
    lateinit var crescente: Button
    lateinit var decrescente: Button

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
        crescente = findViewById(R.id.crescente)
        decrescente = findViewById(R.id.decrescente)

        //RecyclerView is comming, boys and girls
        val testeAdapter  = TesteAdapter(InfoSingleton.testList)
        val rv : RecyclerView = findViewById(R.id.recycler_testes)
        val relative_rv : RelativeLayout = findViewById(R.id.relative_recycler)
        val emptyList : TextView = findViewById(R.id.emptyList)
        if (InfoSingleton.testList.size != 0) {
            relative_rv.visibility = View.VISIBLE
            emptyList.visibility = View.GONE
        }
        rv.adapter = testeAdapter

        // https://stackoverflow.com/questions/3913592/start-an-activity-with-a-parameter :)
        testeAdapter.onItemClick = {teste ->
            val intent = Intent(this, TesteDetailActivity::class.java)
            val b = Bundle()
            var id: ByteArray = byteArrayOf()
            if (teste.photo != null) {
                //val byteBuffer: ByteBuffer = ByteBuffer.allocate(teste.photo!!.getByteCount())
                //teste.photo!!.copyPixelsToBuffer(byteBuffer)
                //byteBuffer.rewind()
                //id = byteBuffer.array() //para entrar aqui será doferente de null
                val baos = ByteArrayOutputStream()
                teste.photo!!.compress(Bitmap.CompressFormat.PNG, 100, baos)
                id = baos.toByteArray()
            }
            b.putString("local", teste.local)
            b.putString("data", teste.stringMyDate())
            b.putBoolean("resultado", teste.positivo)
            b.putByteArray("photo", id)
            intent.putExtras(b)
            startActivity(intent)
        }
        crescente.setOnClickListener {
            testeAdapter.testList = InfoSingleton.testList.sortedBy { it.data.time }
            testeAdapter.notifyDataSetChanged()
            val toast = Toast.makeText(this, getString(R.string.ordenado_crescente), Toast.LENGTH_SHORT)
            toast.show()
        }
        decrescente.setOnClickListener {
            testeAdapter.testList = InfoSingleton.testList.sortedByDescending { it.data.time }
            testeAdapter.notifyDataSetChanged()
            val toast = Toast.makeText(this, getString(R.string.ordenado_decrescente), Toast.LENGTH_SHORT)
            toast.show()
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