package pt.ulusofona.deisi.a2020.cm.g3.extra

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import pt.ulusofona.deisi.a2020.cm.g3.R

class TesteAdapter (var testList: List<Teste>) : RecyclerView.Adapter<TesteAdapter.TesteViewHolder>() {

    var onItemClick: ((Teste) -> Unit)? = null

    inner class TesteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var icon = itemView.findViewById<ImageView>(R.id.image_view)

        var number = itemView.findViewById<TextView>(R.id.test_number)
        var date = itemView.findViewById<TextView>(R.id.test_date)
        var result = itemView.findViewById<TextView>(R.id.test_result)

        fun bind(teste: Teste) {
            if(teste.hasPhoto()) {
                icon.setBackgroundResource(R.drawable.has_test)
            }
            else {
                icon.setBackgroundResource(R.drawable.has_no_test)
            }
            number.setText(teste.local)
            date.setText(teste.stringMyDate())
            if(teste.positivo) {
                result.setText(R.string.result_pos)
            }
            else {
                result.setText(R.string.result_neg)
            }

            itemView.setOnClickListener {
                onItemClick?.invoke(testList.get(adapterPosition))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TesteViewHolder {
        //val inflater = LayoutInflater.from(parent!!.getContext())
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.teste_item, parent, false)
        return TesteViewHolder(view)

        //.listen {position, type ->
        //            val item = testList.get(position)
        //            //Meter aqui a página dos detalhes do teste!
        //        }
    }

    override fun getItemCount(): Int {
        return testList.size
    }

    override fun onBindViewHolder(holder: TesteViewHolder, position: Int) {
        val teste = testList.get(position)
        holder.bind(teste)
    }

}