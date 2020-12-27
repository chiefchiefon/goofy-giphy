package com.github.chiefchiefon.goofigiffy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.github.chiefchiefon.goofigiffy.model.network.GiphyApiServiceImpl
import com.github.chiefchiefon.goofigiffy.model.network.GiphyResponse
import com.github.chiefchiefon.goofigiffy.view.GifsAdapter
import com.github.chiefchiefon.goofigiffy.view.statuses
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var gifAdapter: GifsAdapter
    private lateinit var gifsListView: RecyclerView
    private lateinit var progressBar: ProgressBar

    private lateinit var statusTV: TextView
    /**
     * Basically we should separate logic from View.
     * I don't know if my implementation meets this IKARON.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        progressBar = findViewById(R.id.progressBar)
        gifsListView = findViewById(R.id.gifsList)
        statusTV = findViewById<TextView>(R.id.statusTV)
        with(statusTV) {
            text = statuses.initializing.name
            setBackgroundColor(currentHintTextColor)
        }

        gifAdapter = GifsAdapter()
        gifsListView.adapter = gifAdapter

        loadGifs()
        if(statusTV.text == statuses.loading_giphs.name) {
            statusTV.text = statuses.giphs_loaded.name
            //status = statuses.giphs_loaded
            statusTV.visibility = View.INVISIBLE
        }
    }

    private fun setProgressVisibility(show: Boolean) {
        progressBar.isVisible = show
    }

    private fun loadGifs() {
        val call = GiphyApiServiceImpl
            .service
            .fetchTrendingGifs("jOxMrxfI7M6NlGQBgDyvWVRPaEI4VtI6"/*""EEOBXcKu1hdwwYapZHU9QBcXMb5dNhEg"*/)

        call.enqueue(GifCallback())

        setProgressVisibility(true)
    }

    inner class GifCallback: Callback<GiphyResponse> {
        override fun onResponse(call: Call<GiphyResponse>, response: Response<GiphyResponse>) {
            //status = statuses.loading_giphs
            with(statusTV) {
                text = statuses.loading_giphs.name//status.name
                setBackgroundColor(shadowColor)
            }

            if (response.isSuccessful) {
                response.body()?.data?.let {
                    gifAdapter.submitList(it)
                }
                with(statusTV) {
                    text = statuses.giphs_loaded.name
                    visibility = View.INVISIBLE
                }
            }
            else {
                //status = statuses.error_in_response
                with(statusTV) {
                    text = statuses.error_in_response.name//status.name
                    setBackgroundColor(highlightColor)
                }
            }
            setProgressVisibility(false)
        }

        override fun onFailure(call: Call<GiphyResponse>, t: Throwable) {
            setProgressVisibility(false)

            //status = statuses.error_in_connection
            with(statusTV) {
                text = statuses.error_in_connection.name//status.name
                setBackgroundColor(highlightColor)
            }

            Log.e(MainActivity::javaClass.name, "Api call failed", t)
        }
    }
}