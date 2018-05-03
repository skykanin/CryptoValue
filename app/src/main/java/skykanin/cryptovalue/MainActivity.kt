package skykanin.cryptovalue

import android.app.Activity
import android.os.Bundle
import org.jetbrains.anko.setContentView

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        MainActivityUI().setContentView(this)
    }
}
