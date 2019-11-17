package com.medpixels.vkpress


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_payment.view.*

/**
 * A simple [Fragment] subclass.
 */
class PaymentFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_payment, container, false)
        val bdl = arguments
        val product = bdl?.get("product") as Product
        view.desc.text = product.desc
        view.price.text = product.currency + " " + product.price
        view.total.text =  product.currency + " " + product.price
        Glide.with(this)
            .load(product.image)
            .into(view.image)
        view.back.setOnClickListener { activity?.onBackPressed() }
        view.buy_now.setOnClickListener {
            (activity as MainActivity)?.over()
        }
        return view
    }


}
