package pt.ulusofona.deisi.a2020.cm.g3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.github.chrisbanes.photoview.PhotoView
import pt.ulusofona.deisi.a2020.cm.g3.viewmodel.TestDetailViewModel

class PhotoViewFragment(uuid: String) : Fragment() {

    val uud = uuid
    private lateinit var viewModel: TestDetailViewModel
    lateinit var photo: PhotoView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_photo_view, container, false)
        viewModel = ViewModelProviders.of(this).get(TestDetailViewModel::class.java)
        return view
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        photo = activity!!.findViewById(R.id.teste_foto_view)
        val teste = viewModel.loadTeste(uud)
        photo.setImageBitmap(teste.photo)
    }

}