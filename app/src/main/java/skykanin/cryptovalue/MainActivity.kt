package skykanin.cryptovalue

import android.app.Activity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

class MainActivity : Activity() {

    private lateinit var mainAdapter : MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView_main.layoutManager = LinearLayoutManager(this)
        mainAdapter = MainAdapter()
        recyclerView_main.adapter = mainAdapter
        apiStreamCall()
    }

    private fun apiStreamCall() {
        val builder = Retrofit.Builder()
                .baseUrl("https://api.coinmarketcap.com/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

        val client = builder.create(CoinMarketApi::class.java)
        client.getListings()
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    getTickerData(
                            it.data
                            .filter { it.symbol in CryptoCurrencies.values().map { it.ticker } }
                            .map { it.id.toString() }
                    )})
    }

    private fun getTickerData(ids: List<String>?) {
        val builder = Retrofit.Builder()
                .baseUrl("https://api.coinmarketcap.com/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

        val observables: List<Observable<TickerData>>? = ids?.map { builder
                .create(CoinMarketApi::class.java)
                .getTicker(it)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())}

        val combined = Observable.merge(observables)

        /*val combined = Observable.zip(observables, object : (Array<Any>) -> List<TickerData> {
            override fun invoke(p1: Array<Any>): List<TickerData> {
                return p1.toList().filterIsInstance<TickerData>()
            }
        })
        combined.subscribe(
                        { mainAdapter.addTickerDataList(it) },
                        { Toast.makeText(applicationContext,
                                it.message,
                                Toast.LENGTH_SHORT).show() })*/

        combined.subscribe({ mainAdapter.addTickerData(it) },
                { Toast.makeText(applicationContext,
                        it.message,
                        Toast.LENGTH_SHORT).show() })
    }
}
