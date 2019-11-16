package com.medpixels.vkpress

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import androidx.recyclerview.widget.LinearLayoutManager
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        list_posts.layoutManager = LinearLayoutManager(this)
        val allPosts = ArrayList<Post>()
        allPosts.add(Post(fullName = "Abdallah Herzallah" ,createdAt =  Date() , profilePic = "https://images.freeimages.com/images/large-previews/5ae/grape-vine-leaf-1327453.jpg" , image = "https://www.irishcapsusa.com/wp-content/uploads/2015/04/Kerry-Cap-335-1-1001_675x675.jpg" , btnName = "Hocine Khen" , nbVu = 433 , nbComment = 6 ,nbLike =  26))
        allPosts.add(Post(fullName = "Mehdi Karech" ,createdAt =  Date() , profilePic = "https://images.askmen.com/1080x540/2016/01/25-021526-facebook_profile_picture_affects_chances_of_getting_hired.jpg" , image = "http://beecleanhome.com/site/wp-content/uploads/2016/01/Fashion-Big-Luxury-Handbags-Women-Bags-Ladies-Hand-Bags-Leather-Snake-Skin-Shopper-Bag-Brand-Large.jpg" , btnName = "Mekki Mohamed" , nbVu = 311 , nbComment = 4 ,nbLike =  29))
        allPosts.add(Post(fullName = "Cristian Dallas" ,createdAt =  Date() , profilePic = "https://twistedsifter.files.wordpress.com/2012/09/trippy-profile-pic-portrait-head-on-and-from-side-angle.jpg?w=800" , image = "https://images.freeimages.com/images/large-previews/5ae/grape-vine-leaf-1327453.jpg" , btnName = "Laura Slush" , nbVu = 99 , nbComment = 4 ,nbLike =  10))
        allPosts.add(Post(fullName = "Big Smoke" ,createdAt =  Date() , profilePic = "https://i.pinimg.com/originals/7d/1a/3f/7d1a3f77eee9f34782c6f88e97a6c888.jpg" , image = "https://cnet3.cbsistatic.com/img/3qibrOD7OE6nULbrteFMysErlYI=/2018/10/13/92cde876-c7d5-4762-a297-94214173e5f2/106-google-pixel-3.jpg" , btnName = "Ilyes Boudjeltia" , nbVu = 57 , nbComment = 0 ,nbLike =  2))
        allPosts.add(Post(fullName = "Daniel Bioah" ,createdAt =  Date() , profilePic = "https://www.bigredcloud.com/wp-content/uploads/4-tips-for-taking-professional-profile-pictures.jpg" , image = "https://www.felicita.ma/wp-content/uploads/Montre-homme-Swatch-Night-Flight-1.jpg" , btnName = "Ilyes Boudjeltia" , nbVu = 1994 , nbComment = 59 ,nbLike =  220))
        allPosts.add(Post(fullName = "Hacker Boal" ,createdAt =  Date() , profilePic = "https://m.economictimes.com/thumb/msid-69381991,width-1200,height-900,resizemode-4,imgsize-594328/hacker-1.jpg" , image = "https://ufpro.com/storage/app/media/Product%20Images/Shirts-Sweaters/Urban%20T-Shirt/Black/thumb/1920x1920.crop/Urban-Tshirt-black-hero-2019-493.jpg" , btnName = "Bouteflika Abdelaziz" , nbVu = 333 , nbComment = 4 ,nbLike =  22))
        val adapter = PostAdapter(allPosts, this)
        list_posts.adapter = adapter
        adapter.notifyDataSetChanged()
    }




}
