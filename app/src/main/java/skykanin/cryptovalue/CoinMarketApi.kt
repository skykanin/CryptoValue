package skykanin.cryptovalue

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface CoinMarketApi {
    @GET("listings/")
    fun getListings(): Call<Listing>

    @GET("ticker/{id}")
    fun getTicker(@Path("id") id: String): Call<TickerData>
}