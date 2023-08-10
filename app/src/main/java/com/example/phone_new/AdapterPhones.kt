package com.example.phone_new

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.phone_new.databinding.ItemPhoneCardBinding
import com.example.phone_new.modelo.local.entities.PhoneEntity

class AdapterPhones : RecyclerView.Adapter<AdapterPhones.ViewHolderPhones>() {

    private var listPhones = listOf<PhoneEntity>()
    private val selectedPhone = MutableLiveData<PhoneEntity>()

    fun update(list: List<PhoneEntity>) {
        listPhones = list
        notifyDataSetChanged()
    }

    // función para seleccionar
    fun selectedPhone(): LiveData<PhoneEntity> = selectedPhone


    inner class ViewHolderPhones(private val binding: ItemPhoneCardBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        fun bind(phone: PhoneEntity) {
            Glide.with(binding.phoneImageView).load(phone.image).into(binding.phoneImageView)
            binding.phoneNameTextView.text = phone.name
            binding.phonePriceTextView.text = phone.price.toString()
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            // referencia a la selección
            selectedPhone.value = listPhones[adapterPosition]
            Log.d("ONCLICK", adapterPosition.toString())
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderPhones {
        return ViewHolderPhones(ItemPhoneCardBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun getItemCount() = listPhones.size

    override fun onBindViewHolder(holder: ViewHolderPhones, position: Int) {
        val phone= listPhones[position]
        holder.bind(phone)
    }
}