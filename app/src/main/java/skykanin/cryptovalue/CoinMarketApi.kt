package skykanin.cryptovalue

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface CoinMarketApi {
    @GET("listings/")
    fun getListings(): Observable<Listing>

    @GET("ticker/{id}")
    fun getTicker(@Path("id") id: String): Observable<TickerData>
}