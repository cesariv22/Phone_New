package com.example.phone_new.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.example.phone_new.R
import com.example.phone_new.databinding.FragmentSecondBinding
import com.example.phone_new.viewModel.PhoneViewModel

class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!

    private val mViewModel: PhoneViewModel by activityViewModels()
    private var phoneId: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let { bundle ->
            phoneId = bundle.getString("phoneId")
            Log.d("seleccion", phoneId.toString())
        }

        //Obtener detalle de un teléfono por el ID.
        phoneId?.let { id ->
            mViewModel.getPhoneDetailByIdFromInternet(id)
        }

        //Obtener el detalle de un teléfono desde el viewModel.
        mViewModel.getPhoneDetail().observe(viewLifecycleOwner) {
            Log.d("seleccion3", phoneId.toString())

            //Cargamos los datos seleccionados desde el primer fragmento
            Glide.with(binding.imagePhoneSelected).load(it.image).into(binding.imagePhoneSelected)
            binding.tvPhoneName.text = it.name
            binding.tvPhonePrice.text = it.price.toString()
            binding.tvPhoneDescription.text = it.description
            binding.tvPhoneLastPrice.text = it.lastPrice.toString()
            binding.tvPhoneCredit.text = it.credit.toString()
            if (it.credit) {
                binding.tvPhoneCredit.text = getString(R.string.true_credit)
            } else {
                binding.tvPhoneCredit.text = getString(R.string.false_credit)
            }

            //Botón para enviar correo.
            binding.btMail.setOnClickListener {
                val productName = binding.tvPhoneName.text.toString()
                val productId = phoneId ?: ""

                //Intent para enviar
                val intent = Intent(Intent.ACTION_SEND).apply {
                    //Se parsea la dirección del correo.
                    data = Uri.parse("mailto:info@novaera.cl")
                    //Texto para el asunto
                    putExtra(
                        Intent.EXTRA_SUBJECT,
                        "Consulta por celular ${productName} codigo id: $productId"
                    )

                    //Texto que se enviara en el cuerpo del correo.
                    val message =
                        "Hola,\n\nVi el celular <b>$productName</b> de código id <b>$productId</b> y me gustaría que me contactaran a este correo o al siguiente número _________.\n\nQuedo atento."
                    putExtra(Intent.EXTRA_TEXT, message)
                }
                //con esto se elije que app de correo se desea utilizar. intent, se ejecuta, y "Enviar correo" es el mensaje del cuadro de dialogo.
                startActivity(Intent.createChooser(intent, "Enviar correo"))
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}