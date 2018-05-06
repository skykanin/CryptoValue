package skykanin.cryptovalue

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.support.v4.content.res.ResourcesCompat
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import org.jetbrains.anko.*
import org.jetbrains.anko.db.TEXT
import org.jetbrains.anko.design.longSnackbar
import org.jetbrains.anko.sdk25.coroutines.onClick

class MainActivityUI : AnkoComponent<MainActivity> {

    override fun createView(ui: AnkoContext<MainActivity>): View = with(ui) {
        scrollView {
            verticalLayout {
                linearLayout {
                    imageView(R.drawable.btc).lparams(dip(75), dip(75)) {
                        rightMargin = dip(20)
                    }

                    textView {
                        text = "BTC"
                        textSize = 24f
                        textColor = Color.BLACK
                    }.lparams {
                        gravity = Gravity.CENTER_VERTICAL
                    }

                    textView {
                        text = "$ 7320"
                        textSize = 24f
                    }.lparams {
                        gravity = Gravity.CENTER_VERTICAL
                        leftMargin = dip(100)
                    }

                    background = ResourcesCompat.getDrawable(resources, R.drawable.border_new, null)
                }.lparams(width = matchParent, height = dip(100)) {
                    gravity = Gravity.START
                }
            }.lparams(width= matchParent, height = matchParent)
        }
    }
}