package com.example.shoesshop.Model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.toColorInt
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.shoesshop.Model.Local.Database.ShoesDetailEntity
import com.example.shoesshop.Model.Local.Database.ShoesEntity
import com.example.shoesshop.databinding.ItemShoesBinding

class ShoesAdapter: RecyclerView.Adapter<ShoesAdapter.ShoesVH>() {


    private var listShoes= listOf<ShoesEntity>()
    private val selectedShoes= MutableLiveData<ShoesEntity>()

    fun update(list:List<ShoesEntity>){

        listShoes=list
        notifyDataSetChanged()
    }

    fun selectedShoes():LiveData<ShoesEntity> = selectedShoes

    inner class  ShoesVH(private val mBinding: ItemShoesBinding):
        RecyclerView.ViewHolder(mBinding.root), View.OnClickListener{

        fun bind(shoes:ShoesEntity){
            Glide.with(mBinding.itemImagen).load(shoes.image).centerCrop()
                .into(mBinding.itemImagen)
            mBinding.itemNombre.text=shoes.nombre.toUpperCase()
            mBinding.itemOrigen.text="El producto es de "+shoes.origen+" y pertenece a la marca "+shoes.marca
          //  mBinding.itemMarca.text="De la marca: "+shoes.marca
           mBinding.itemNumero.text="Numero disponible: "+shoes.numero
            itemView.setOnClickListener(this)

        }

        override fun onClick(p0: View?) {
            selectedShoes.value=listShoes[adapterPosition]
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoesVH {
        return  ShoesVH(ItemShoesBinding.inflate(LayoutInflater.from(parent.context)))
    }
    override fun onBindViewHolder(holder: ShoesVH, position: Int) {
        val phone = listShoes[position]
        holder.bind(phone)
    }
    override fun getItemCount()= listShoes.size
}