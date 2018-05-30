package skykanin.cryptovalue

import com.google.gson.annotations.SerializedName

enum class CryptoCurrencies(val ticker: String, val img: Int) {
    Bitcoin("BTC", R.drawable.btc),
    Ethereum("ETH", R.drawable.eth),
    Ripple("XRP", R.drawable.xrp),
    BitcoinCash("BCH", R.drawable.bch),
    EOS("EOS", R.drawable.eos),
    Litecoin("LTC", R.drawable.ltc),
    IOTA("MIOTA", R.drawable.miota),
    Cardano("ADA", R.drawable.ada),
    TRON("TRX", R.drawable.trx),
    VeChain("VEN", R.drawable.ven)

}

data class DataObject(val id: Int,
                      val name: String,
                      val symbol: String,
                      val website_slug: String) {
    override fun equals(other: Any?): Boolean {
        return when(other) {
            is DataObject -> {
                this.id == other.id &&
                        this.name == other.name &&
                        this.symbol == other.symbol &&
                        this.website_slug == other.website_slug
            }
            else -> false
        }
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + name.hashCode()
        result = 31 * result + symbol.hashCode()
        result = 31 * result + website_slug.hashCode()
        return result
    }
}

data class MetaDataObject(val timestamp: Int, val num_cryptocurrencies: Int = 1, val error: String)
data class TickerMetaDataObject(val timestamp: Int, val error: String)

data class Listing(val data: Array<DataObject>, val metadata: MetaDataObject)
data class CurrencyData(val price: Double,
                        val volume_24h: Long,
                        val market_cap: Long,
                        val percent_change_1h: Double,
                        val percent_change_24h: Double,
                        val percent_change_7d: Double)
data class Quotes(@SerializedName("USD") val currency: CurrencyData)
data class TickerInnerData(val id: Int,
                           val name: String,
                           val symbol: String,
                           val website_slug: String,
                           val rank: Int,
                           val circulating_supply: Float,
                           val total_supply: Float,
                           val max_supply: Float,
                           val quotes: Quotes,
                           val last_updated: Int)
data class TickerData(val data: TickerInnerData, val metadata: TickerMetaDataObject)