package pt.ulusofona.deisi.a2020.cm.g3.blocs.GUI

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import pt.ulusofona.deisi.a2020.cm.g3.R
import pt.ulusofona.deisi.a2020.cm.g3.blocs.Teste

class TesteAdapter (var testList: ArrayList<Teste>) : RecyclerView.Adapter<TesteAdapter.TesteViewHolder>() {

    class TesteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var number = itemView.findViewById<TextView>(R.id.test_number)
        var date = itemView.findViewById<TextView>(R.id.test_date)
        var result = itemView.findViewById<TextView>(R.id.test_result)

        fun bind(teste: Teste) {
            number.setText(teste.local)
            date.setText(teste.data.toString())
            if(teste.positivo) {
                result.setText(R.string.result_pos)
            }
            else {
                result.setText(R.string.result_neg)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TesteViewHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.teste_item, parent, false)
        return TesteViewHolder(view)
    }

    override fun getItemCount(): Int {
        return testList.size
    }

    override fun onBindViewHolder(holder: TesteViewHolder, position: Int) {
        val teste = testList.get(position)
        holder.bind(teste)
    }

}