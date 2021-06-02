package pt.ulusofona.deisi.a2020.cm.g3

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.FileProvider
import com.google.android.material.button.MaterialButton
import pt.ulusofona.deisi.a2020.cm.g3.blocs.InfoSingleton
import pt.ulusofona.deisi.a2020.cm.g3.blocs.Teste
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class RegistoTesteFragment : Fragment() {
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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_registo_teste, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        local = view!!.findViewById(R.id.local)
        dataTeste = view!!.findViewById(R.id.data)
        dropdown = view!!.findViewById(R.id.result_spinner)
        saveButton = view!!.findViewById(R.id.regist_test)
        photoButton = view!!.findViewById(R.id.take_foto)
        takenPhotoMessage = view!!.findViewById(R.id.foto_tirada)
        val datePicker = DatePickerDialog((activity as Context), DatePickerDialog.OnDateSetListener
        {view , year, month, dayOfMonth ->
            dataTeste.text = String.format("%02d / %02d / %04d", dayOfMonth, month + 1, year)
            y = year
            m = month + 1
            d = dayOfMonth
        }, year, month, day
        )
        dataTeste.setOnClickListener { datePicker.show() }
        options = arrayOf(getString(R.string.neg), getString(R.string.pos))
        val adapter: ArrayAdapter<String> = ArrayAdapter((activity as Context), android.R.layout.simple_spinner_dropdown_item, options)
        dropdown.adapter = adapter
        photoButton.setOnClickListener {
            val photoIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            photoFile = getPhotoFile(FILE_NAME)
            val fileprovider = FileProvider.getUriForFile((activity as Context), "pt.ulusofona.deisi.a2020.cm.g3.fileprovider", photoFile)
            photoIntent.putExtra(MediaStore.EXTRA_OUTPUT, fileprovider)

            if (photoIntent.resolveActivity(activity!!.packageManager) != null) {
                startActivityForResult(photoIntent, REQUEST_CODE)
            }
            else {
                Toast.makeText((activity), "AAAAA", Toast.LENGTH_LONG).show() // Aqui falhou a abrir a camera seja lá porque motivo, mudar o texto
            }
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
                NavigationManager.goToList(activity!!.supportFragmentManager)
            }
        }
    }
    private fun getPhotoFile(fileName: String): File {
        val storagetemp = activity!!.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return createTempFile(fileName, ".jpg", storagetemp)
    }
}