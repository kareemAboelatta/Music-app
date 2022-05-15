package com.example.mazika.commen.extension

import android.app.Activity
import android.graphics.Color
import android.os.Build
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.mazika.R


fun Activity.hideKeyboard() {
    val inputMethodManager: InputMethodManager =
        getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    // Find the currently focused view, so we can grab the correct window token from it.
    var view: View? = currentFocus
    // If no view currently has focus, create a new one, just so we can grab a window token from it.
    if (view == null) {
        view = View(this)
    }
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}

fun Activity.showKeyboard() {
    val inputMethodManager: InputMethodManager =
        getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    // Find the currently focused view, so we can grab the correct window token from it.
    var view: View? = currentFocus
    // If no view currently has focus, create a new one, just so we can grab a window token from it.
    if (view == null) {
        view = View(this)
    }
    inputMethodManager.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
}

//fun Activity.makeStatusBarTransparent() {
//    window.apply {
//        clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
//        addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            decorView.systemUiVisibility =
//                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
//        } else {
//            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//        }
//        statusBarColor = Color.TRANSPARENT
//    }
//}
//
//fun Activity.clearTransparentStatusBar() {
//    window.apply {
//        decorView.systemUiVisibility = View.STATUS_BAR_VISIBLE
//        clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
//        addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
//        statusBarColor =
//            ContextCompat.getColor(this@clearTransparentStatusBar, R.color.secondaryColor)
//    }
//}

fun Activity.showContentAboveStatusBar(color: Int = -1) {
    try {
        window.apply {
            statusBarColor = if (color != -1) Color.TRANSPARENT else color
            setFlags(
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
            )
        }
    } catch (e: Exception) {
        Log.e("ActivityExtensions", "showContentAboveStatusBar: ${e.localizedMessage}")
    }
}



fun Activity.hideStatusBar() {
    try {
        window.apply {
                setFlags(
                    WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN
                )
        }


    } catch (e: Exception) {
        Log.e("ActivityExtensions", "showContentAboveStatusBar: ${e.localizedMessage}")
    }
}

fun Activity.showContentNormallyUnderStatusBar(color: Int) {
    window.apply {
        statusBarColor =
            ContextCompat.getColor(this@showContentNormallyUnderStatusBar, color)
        clearFlags(
            WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
        )
    }
}
fun Activity.showStatusBar(color: Int = -1) {
    try {

            window.apply {
                statusBarColor = if (color != -1) Color.TRANSPARENT else color
                setFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                )
            }


    } catch (e: Exception) {
        Log.e("ActivityExtensions", "showContentAboveStatusBar: ${e.localizedMessage}")
    }
}
fun Activity.showContentNormallyUnderStatusBarWithMainColor() {
    showContentNormallyUnderStatusBar(R.color.colorAccent)
}