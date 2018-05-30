package skykanin.cryptovalue

import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.crypto_row.view.*

class MainAdapter(private val tickerDataList: ArrayList<TickerData?>) : RecyclerView.Adapter<CustomViewHolder>() {

    override fun getItemCount(): Int {
        return tickerDataList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.crypto_row, parent, false)
        return CustomViewHolder(cellForRow)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val crypto = CryptoCurrencies.values()[position]
        holder.view.ticker.text = crypto.ticker
        holder.view.imageView.setImageResource(crypto.img)
        holder.view.setBackgroundColor(Color.WHITE)
        val price = tickerDataList[position]?.data?.quotes?.currency?.price.toString()
        holder.view.price.text = "$ $price"
    }
}

class CustomViewHolder(val view: View) : RecyclerView.ViewHolder(view)