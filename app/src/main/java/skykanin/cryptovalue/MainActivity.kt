package skykanin.cryptovalue

import android.app.Activity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView_main.layoutManager = LinearLayoutManager(this)
        launch(UI) {
            // apiCall()
            getTickerData()
        }
    }

    private fun apiCall() {
        val builder = Retrofit.Builder()
                .baseUrl("https://api.coinmarketcap.com/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        val client = builder.create(CoinMarketApi::class.java)
        val call = client.getListings()
        val response = call.execute()
        when {
            response.isSuccessful -> { println(response.body()) }
            !response.isSuccessful -> throw IOException("Unexpected code $response")
        }
    }

    private fun getTickerData() {
        val builder = Retrofit.Builder()
                .baseUrl("https://api.coinmarketcap.com/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        val client = builder.create(CoinMarketApi::class.java)
        val call = client.getTicker("1")
        /*val response = call.execute()
        when {
            response.isSuccessful -> {
                val list = arrayListOf(response.body())
                recyclerView_main.adapter = MainAdapter(list)
            }
            !response.isSuccessful -> throw IOException("Unexpected code $response")
        }*/
        call.enqueue(object : Callback<TickerData> {
            override fun onResponse(call: Call<TickerData>?, response: Response<TickerData>?) {
                println(response?.body())
                val list = arrayListOf(response?.body())
                recyclerView_main.adapter = MainAdapter(list)
            }

            override fun onFailure(call: Call<TickerData>?, t: Throwable?) {
                val list = arrayListOf<TickerData?>()
                recyclerView_main.adapter = MainAdapter(list)
                println("Error http request failed")
            }
        })
    }
}
