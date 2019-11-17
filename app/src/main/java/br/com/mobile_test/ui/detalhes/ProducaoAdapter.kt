package br.com.mobile_test.ui.detalhes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import br.com.mobile_test.R
import br.com.mobile_test.databinding.RowProducaoItemBinding
import br.com.mobile_test.model.Company

class ProducaoAdapter(private var companies: MutableList<Company>) :
    RecyclerView.Adapter<ProducaoAdapter.ProducaoHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProducaoHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<RowProducaoItemBinding>(inflater, R.layout.row_producao_item,parent,false)
        return ProducaoHolder(binding)
    }

    override fun getItemCount(): Int {
        return companies.size
    }

    override fun onBindViewHolder(holder: ProducaoHolder, position: Int) {
        holder.binding.company = companies[position]
    }

    fun addCompanies(companies: List<Company>){
        this.companies.clear()
        this.companies.addAll(companies)
        notifyDataSetChanged()
    }

    fun clearLista() {
        companies.clear()
    }


    class ProducaoHolder(val binding: RowProducaoItemBinding) : RecyclerView.ViewHolder(binding.root)

}