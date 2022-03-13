package com.srn.satellitelist.ui.base

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.srn.satellitelist.R;

/**
 * Created by SoheilR .
 */

abstract class BaseFragment<T : ViewDataBinding> : Fragment() {

    lateinit var binding: T
    @LayoutRes
    protected abstract fun initLayout(): Int
    protected abstract fun init()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, initLayout(), container, false)
        binding.root.fitsSystemWindows = true
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    // Show long toast
    protected fun showLongToast(message: String?) {
        if (message != null) {
            Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
        }
    }

    // Show short toast
    protected fun showShortToast(message: String?) {
        if (message != null) {
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        }
    }

    // get color resource
    protected fun getColorRes(colorRes: Int): Int {
        return if (activity != null) ContextCompat.getColor(requireActivity(), colorRes) else 0
    }

    // get drawable resource
    protected fun getDrawableRes(drawableRes: Int): Drawable? {
        return ContextCompat.getDrawable(requireActivity(), drawableRes)
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