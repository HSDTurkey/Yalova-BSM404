package com.myapps.yalovauniversity_week6

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import com.myapps.yalovauniversity_week6.databinding.ActivityMainBinding
import android.content.ActivityNotFoundException
import android.net.Uri


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var _rating: Float? = null

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        // RatingBar Change Listener
        binding.ratingBar.setOnRatingBarChangeListener { _, rating, _ ->
            binding.ratingTextView.text = "Your Rate: $rating"
            _rating = rating
        }

        // Send Rating Value
        binding.sendRatingButton.setOnClickListener {

            if (_rating != null) {
                // Set Visible ProgressBar
                binding.progressBar.visibility = VISIBLE

                Handler(Looper.getMainLooper()).postDelayed({
                    // After 3 seconds
                    binding.progressBar.visibility = GONE

                    Toast.makeText(
                        this,
                        "You have successfully submitted your rating.",
                        Toast.LENGTH_LONG
                    ).show()

                }, 3000)

            } else {
                Toast.makeText(this, "Please select your rate.", Toast.LENGTH_SHORT).show()
            }

        }

        // Navigate to WebView using Instagram Card View
        binding.instagramCardView.setOnClickListener {
            val intent = Intent(this, WebViewActivity::class.java)
            intent.putExtra("Instagram", "https://www.instagram.com/huaweimobiletr/")
            startActivity(intent)
        }

        // Navigate to WebView using Twitter Card View
        binding.twitterCardView.setOnClickListener {
            val intent = Intent(this, WebViewActivity::class.java)
            intent.putExtra("Twitter", "https://twitter.com/HuaweiMobileTR/")
            startActivity(intent)
        }

        // Navigate to WebView using LinkedIn Card View
        binding.linkedInCardView.setOnClickListener {
            val intent = Intent(this, WebViewActivity::class.java)
            intent.putExtra(
                "LinkedIn",
                "https://www.linkedin.com/company/huawei-enterprise-turkiye/"
            )
            startActivity(intent)
        }

        // Navigate to Intent using AppGallery Card View
        binding.appGalleryImageView.setOnClickListener {
            try {
                val intent =
                    Intent(Intent.ACTION_VIEW, Uri.parse("https://appgallery.cloud.huawei.com/ag/n/app/C10132067"))
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
            } catch (exception: ActivityNotFoundException) {
                Toast.makeText(this, "Huawei AppGallery not found!", Toast.LENGTH_SHORT).show()
            }
        }

        setContentView(binding.root)
    }
}