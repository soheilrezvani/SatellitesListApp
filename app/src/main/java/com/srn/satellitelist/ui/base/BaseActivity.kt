package com.srn.satellitelist.ui.base

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.google.android.material.snackbar.Snackbar
import com.srn.satellitelist.R;

/**
 * Created by SoheilR .
 */

abstract class BaseActivity<T : ViewDataBinding> : AppCompatActivity() {

    lateinit var binding: T
    @LayoutRes
    protected abstract fun initLayout(): Int
    protected abstract fun onCreate()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, initLayout())
        binding.lifecycleOwner = this
        onCreate()
    }

    // Show long toast
    protected fun showLongToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    // Show short toast
    protected fun showShortToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    // show loading
    protected fun showLoading(loading: View) {
        loading.visibility = View.VISIBLE
    }

    // hide loading
    protected fun hideLoading(loading: View) {
        loading.visibility = View.GONE
    }

    // get color resource
    protected fun getColorRes(colorRes: Int): Int {
        return ContextCompat.getColor(this, colorRes)
    }

    // get drawable resource
    protected fun getDrawableRes(drawableRes: Int): Drawable? {
        return ContextCompat.getDrawable(this, drawableRes)
    }

    // visible a view with animation
    protected fun showViewWithAnimation(view: View) {
        view.visibility = View.VISIBLE
        view.alpha = 0.0f

        // Start the animation
        view.animate()
            .translationY(0f)
            .alpha(1.0f)
            .duration = 300
    }

    // loading handler
    protected fun initLoading(loading: View?, show: Boolean) {
        loading!!.visibility = if (show) View.VISIBLE else View.GONE
    }

    // show message in snackBar
    protected fun showSnackBar(layout: View, msg: String, onclick: (() -> Unit)?) {
        val mSnackBar = Snackbar.make(layout, msg, Snackbar.LENGTH_LONG)
            .setAction("CLOSE") {
                onclick.let { click ->
                    click?.invoke()
                }
            }
            .setActionTextColor(getColorRes(R.color.lightRed))
            .show()
    }

}