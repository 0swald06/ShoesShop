package com.example.shoesshop.View

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.example.shoesshop.ViewModel.ShoesViewModel
import com.example.shoesshop.databinding.FragmentSecondBinding


class SecondFragment : Fragment() {

    private lateinit  var  mBinding: FragmentSecondBinding

    private val  mViewModel : ShoesViewModel  by activityViewModels()

    private var shoesId : String? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mBinding = FragmentSecondBinding.inflate(inflater, container, false)
        return mBinding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let { bundle ->

            shoesId = bundle.getString("shoesId")
            Log.d("prueba2",shoesId.toString())

        }
        shoesId?.let { id ->
            mViewModel.getShoesDetailByIdFromInternet(id)
        }

        mViewModel.getShoesDetail().observe(viewLifecycleOwner,{

            Log.d("prueba3",shoesId.toString())
            var id= it.id
            var name = it.nombre
            val aux:String
            Glide.with(mBinding.itemImagen).load(it.image).into(mBinding.itemImagen)
            mBinding.itemNombre.text=it.nombre.toUpperCase()
            if (it.entrega=="false"){
               name="Sin despacho"
            }else{
                name="Cuenta con despacho"
            }
            mBinding.itemMarca.text="El producto es de "+it.origen+" y pertenece a la marca "+it.marca

            mBinding.itemPrecio.text="Precio oferta: "+it.precio
            mBinding.itemNumero.text="Numero disponible: "+it.numero

            mBinding.itemEntrega.text=name
            mBinding.itemEntrega.text=name

            mBinding.btMail.setOnClickListener{


                val mintent= Intent(Intent.ACTION_SEND)
                mintent.data= Uri.parse("mailto")
                mintent.type="text/plain"

                mintent.putExtra(Intent.EXTRA_EMAIL, arrayOf("Zapato.ventas@unica.cl"))
                mintent.putExtra(
                    Intent.EXTRA_SUBJECT,
                    "Consulta por Producto "+name+" "+ id
                )


                mintent.putExtra(

                    Intent.EXTRA_TEXT,"Hola\n"+

                            "Vi el producto " + name + "\n" +"de código "+id+" y me gustaría que me contactaran a este"+
                            "correo o al siguiente número_________  \n" +

                            "Quedo atento."

                )
                try {
                    startActivity(mintent)
                } catch (e: Exception) {

                    Toast.makeText(context,e.message, Toast.LENGTH_LONG).show()
                }
            }



        })



    }

    override fun onDestroyView() {
        super.onDestroyView()
        //mBinding = null
    }
}