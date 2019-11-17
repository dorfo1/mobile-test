package br.com.mobile_test.ui.detalhes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import br.com.mobile_test.R
import br.com.mobile_test.databinding.RowAtorItemBinding
import br.com.mobile_test.model.Ator

class CastAdapter(private var elenco : MutableList<Ator>) : RecyclerView.Adapter<CastAdapter.CastHolder>(){



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<RowAtorItemBinding>(inflater, R.layout.row_ator_item,parent,false)
        return CastHolder(binding)
    }

    override fun getItemCount(): Int {
      return elenco.size
    }

    override fun onBindViewHolder(holder: CastHolder, position: Int) {
        holder.binding.ator = elenco[position]
    }

    fun addElenco(elenco: List<Ator>){
        this.elenco.clear()
        this.elenco.addAll(elenco)
        notifyDataSetChanged()
    }

    fun clearLista() {
        elenco.clear()
    }


    class CastHolder(val binding: RowAtorItemBinding) : RecyclerView.ViewHolder(binding.root)
}