package pt.ulusofona.deisi.a2020.cm.g3.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.chrisbanes.photoview.PhotoView
import pt.ulusofona.deisi.a2020.cm.g3.R
import pt.ulusofona.deisi.a2020.cm.g3.viewmodel.TestDetailViewModel

class PhotoViewFragment(viewModel: TestDetailViewModel) : Fragment() {

    val vm = viewModel
    lateinit var photo: PhotoView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_photo_view, container, false)
        return view
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        photo = activity!!.findViewById(R.id.teste_foto_view)
        val teste = vm.getTeste()
        photo.setImageBitmap(teste.photo)
    }

}