package com.medpixels.vkpress

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.aliexpress_layout.view.*
import kotlinx.android.synthetic.main.product_bought.view.*
import java.util.*
import kotlin.collections.ArrayList
import android.R.id.message
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.widget.Button


class MainActivity : AppCompatActivity() {

    lateinit var mBottomSheetDialogSuggest : BottomSheetDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_smoke_detector)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "News"
        mBottomSheetDialogSuggest = BottomSheetDialog(this , R.style.BottomSheetDialog)

        list_posts.layoutManager = LinearLayoutManager(this)
        val allPosts = ArrayList<Post>()
        allPosts.add(Post(fullName = "Hacker Boal" ,createdAt =  Date() , profilePic = "https://m.economictimes.com/thumb/msid-69381991,width-1200,height-900,resizemode-4,imgsize-594328/hacker-1.jpg" , image = "https://brainandbodysolutions.com/wp-content/uploads/2015/06/BiyaRiver.jpg" , btnName = "Bouteflika Abdelaziz" , nbVu = 333 , nbComment = 4 ,nbLike =  22))
        allPosts.add(Post(fullName = "Abdallah Herzallah" ,createdAt =  Date() , profilePic = "https://images.freeimages.com/images/large-previews/5ae/grape-vine-leaf-1327453.jpg" , image = "https://www.irishcapsusa.com/wp-content/uploads/2015/04/Kerry-Cap-335-1-1001_675x675.jpg" , btnName = "Hocine Khen" , nbVu = 433 , nbComment = 6 ,nbLike =  26))
        allPosts.add(Post(fullName = "Mehdi Karech" ,createdAt =  Date() , profilePic = "https://images.askmen.com/1080x540/2016/01/25-021526-facebook_profile_picture_affects_chances_of_getting_hired.jpg" , image = "https://www.marlborough.govt.nz/repository/libraries/id:1w1mps0ir17q9sgxanf9/hierarchy/Standard%20Images%20Reusable/Watercourse_river_stream_GCI.jpg" , btnName = "Mekki Mohamed" , nbVu = 311 , nbComment = 4 ,nbLike =  29))
        allPosts.add(Post(fullName = "Cristian Dallas" ,createdAt =  Date() , profilePic = "https://twistedsifter.files.wordpress.com/2012/09/trippy-profile-pic-portrait-head-on-and-from-side-angle.jpg?w=800" , image = "https://daman.co.id/daman.co.id/wp-content/uploads/2016/12/Cristiano-Ronaldo-for-Sacoor-Brothers.jpg" , btnName = "Laura Slush" , nbVu = 99 , nbComment = 4 ,nbLike =  10))
        allPosts.add(Post(fullName = "Big Smoke" ,createdAt =  Date() , profilePic = "https://i.pinimg.com/originals/7d/1a/3f/7d1a3f77eee9f34782c6f88e97a6c888.jpg" , image = "https://cnet3.cbsistatic.com/img/3qibrOD7OE6nULbrteFMysErlYI=/2018/10/13/92cde876-c7d5-4762-a297-94214173e5f2/106-google-pixel-3.jpg" , btnName = "Ilyes Boudjeltia" , nbVu = 57 , nbComment = 0 ,nbLike =  2))
        allPosts.add(Post(fullName = "Daniel Bioah" ,createdAt =  Date() , profilePic = "https://www.bigredcloud.com/wp-content/uploads/4-tips-for-taking-professional-profile-pictures.jpg" , image = "https://www.felicita.ma/wp-content/uploads/Montre-homme-Swatch-Night-Flight-1.jpg" , btnName = "Ilyes Boudjeltia" , nbVu = 1994 , nbComment = 59 ,nbLike =  220))
         val adapter = PostAdapter(allPosts, this)
        list_posts.adapter = adapter
        adapter.notifyDataSetChanged()
    }


    fun openAliExpress(product : Product){
        if(mBottomSheetDialogSuggest.isShowing) mBottomSheetDialogSuggest.dismiss()
        val bundle = Bundle()
        bundle.putSerializable("product", product)
        val fragmentt = ProductFragment()
        fragmentt.arguments = bundle
        supportFragmentManager.beginTransaction().replace(R.id.fragment , fragmentt , "product").commit()
    }

    fun openSuggestions(id : Int){
        val sheetView = this.layoutInflater.inflate(R.layout.aliexpress_layout, null)
        mBottomSheetDialogSuggest.setContentView(sheetView)
        mBottomSheetDialogSuggest.show()
        mBottomSheetDialogSuggest.window.decorView
            .setBackgroundResource(android.R.color.transparent)
        //listView
        sheetView.products.layoutManager = LinearLayoutManager(this , LinearLayoutManager.HORIZONTAL , false)
        val allProducts = ArrayList<Product>()
        allProducts.add(Product("Adidas f chbab" , "https://assets.adidas.com/images/w_600,f_auto,q_auto:sensitive,fl_lossy/69721f2e7c934d909168a80e00818569_9366/Chaussure_Stan_Smith_Blanc_M20324_01_standard.jpg" , "50", "US" , 111))
        allProducts.add(Product("Adidas f chbab" , "https://assets.adidas.com/images/w_600,f_auto,q_auto:sensitive,fl_lossy/69721f2e7c934d909168a80e00818569_9366/Chaussure_Stan_Smith_Blanc_M20324_01_standard.jpg" , "50", "US" , 111))
        allProducts.add(Product("Adidas f chbab" , "https://assets.adidas.com/images/w_600,f_auto,q_auto:sensitive,fl_lossy/69721f2e7c934d909168a80e00818569_9366/Chaussure_Stan_Smith_Blanc_M20324_01_standard.jpg" , "50", "US" , 111))
        allProducts.add(Product("Adidas f chbab" , "https://assets.adidas.com/images/w_600,f_auto,q_auto:sensitive,fl_lossy/69721f2e7c934d909168a80e00818569_9366/Chaussure_Stan_Smith_Blanc_M20324_01_standard.jpg" , "50", "US" , 111))
        val adapter = ProductAdapter(allProducts, this)
        sheetView.products.adapter = adapter
        adapter.notifyDataSetChanged()
        mBottomSheetDialogSuggest.setCanceledOnTouchOutside(true)
        mBottomSheetDialogSuggest.setCancelable(true)
        mBottomSheetDialogSuggest.setOnDismissListener {
            val btn = findViewById<Button>(id)
            val params = btn.layoutParams
            params.height -= 25
            params.width -= 25
            btn.layoutParams = params
            btn.setBackgroundResource(R.drawable.clickablefirst)
        }
    }


    override fun onBackPressed() {
        if(supportFragmentManager.findFragmentByTag("payment") != null){
            supportFragmentManager.beginTransaction().detach(supportFragmentManager.findFragmentByTag("payment")!!).commit()
        } else if(supportFragmentManager.findFragmentByTag("product") != null){
            supportFragmentManager.beginTransaction().detach(supportFragmentManager.findFragmentByTag("product")!!).commit()
        } else {
            super.onBackPressed()
        }
    }

}
