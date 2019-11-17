package com.medpixels.vkpress


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.product_bought.view.*



/**
 * A simple [Fragment] subclass.
 */
class ProductFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.product_bought, container, false)
        val bdl = arguments
        val product = bdl?.get("product") as Product
        Glide.with(this).load(product.image).centerCrop().into(view.image)
        view.price.text = product.currency + " "+  product.price
        view.desc.text = product.desc
        view.back.setOnClickListener { activity?.onBackPressed() }
        view.buy_now.setOnClickListener {
            val bundle = Bundle()
            bundle.putSerializable("product", product)
            val fragmentt = PaymentFragment()
            fragmentt.arguments = bundle
            activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.fragment , fragmentt , "payment")?.commit()
            activity?.supportFragmentManager?.beginTransaction()?.detach(activity?.supportFragmentManager?.findFragmentByTag("product")!!)?.commit()
           }
        return view
    }


}
