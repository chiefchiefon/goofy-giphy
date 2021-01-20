package com.github.chiefchiefon.goofigiffy

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

class FullScreenImageActivity: AppCompatActivity() {
    private val imageView: ImageView by lazy { findViewById(R.id.fullImage) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_full_screen_image)

        val url = intent.getStringExtra("url")
        url?.let {
            loadImage(it)
        }
    }

    private fun loadImage(url: String) {

        Glide.with(this)
                .load(url)
                .placeholder(R.drawable.loading)
                .thumbnail(Glide.with(this).load(R.drawable.loading))
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageView)
    }
}