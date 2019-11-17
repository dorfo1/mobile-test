package br.com.mobile_test.ui.detalhes


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager

import br.com.mobile_test.R
import br.com.mobile_test.databinding.FragmentDetalhesBinding
import kotlinx.android.synthetic.main.fragment_detalhes.*

class DetalhesFragment : Fragment() {

    private lateinit var binding : FragmentDetalhesBinding
    private lateinit var detalhesViewModel : DetalhesViewModel

    private lateinit var companyAdapter : ProducaoAdapter
    private lateinit var castAdapter: CastAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        detalhesViewModel = ViewModelProviders.of(this).get(DetalhesViewModel::class.java)
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_detalhes,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args = arguments?.let { DetalhesFragmentArgs.fromBundle(it) }


        args?.movie?.let {
            detalhesViewModel.fetchMovieDetails(it)
        }

        binding.lifecycleOwner = this
        binding.viewmodel = detalhesViewModel

        companyAdapter = ProducaoAdapter(ArrayList())
        detalhesrvCompanies.adapter = companyAdapter
        detalhesrvCompanies.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)

        castAdapter = CastAdapter(ArrayList())
        detalhesrvCast.adapter = castAdapter
        detalhesrvCast.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)

        setObservers()


    }

    private fun setObservers() {
        detalhesViewModel.movieDetails.observe(viewLifecycleOwner, Observer {
           it?.let {movieDetail ->
               companyAdapter.addCompanies(movieDetail.companies)
               movieDetail.elenco?.let { elenco ->
                   castAdapter.addElenco(elenco)
               }
           }
        })
    }

}
