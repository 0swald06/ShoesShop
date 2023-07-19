package com.example.shoesshop.View

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.shoesshop.Model.ShoesAdapter
import com.example.shoesshop.R
import com.example.shoesshop.ViewModel.ShoesViewModel
import com.example.shoesshop.databinding.FragmentFirstBinding

class FirstFragment : Fragment() {

    private lateinit var mBinding: FragmentFirstBinding

    private val mViewModel:ShoesViewModel by activityViewModels()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mBinding = FragmentFirstBinding.inflate(inflater, container, false)
        return mBinding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter =ShoesAdapter()
        mBinding.recyclerview.adapter=adapter
        mBinding.recyclerview.layoutManager= GridLayoutManager(context,2)
        mViewModel.getShoesList().observe(viewLifecycleOwner,{

            it?.let {
                adapter.update(it)
            }

    })
        adapter.selectedShoes().observe(viewLifecycleOwner, Observer {


            it?.let {
                // válidar si capta la seleccion
                Log.d("Seleccion", it.id.toString())

            }
            val bundle = Bundle().apply {
                putString("shoesId", it.id)
            }
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment, bundle)

        })
    }
    override fun onDestroyView() {
        super.onDestroyView()
       // mBinding = null
    }
}