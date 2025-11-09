package com.daviddev.reungbox.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import android.content.Context
import android.widget.Toast
import androidx.compose.ui.platform.LocalDensity

@Composable
fun getScreenWidth(): Dp {
    val configuration = LocalConfiguration.current
    return configuration.screenWidthDp.dp
}

@Composable
fun getScreenHeight(): Dp {
    val configuration = LocalConfiguration.current
    return configuration.screenHeightDp.dp
}

@Composable
fun dpToPx(dp: Dp): Int {
    val density = LocalDensity.current
    return with(density) { dp.toPx().toInt() } // convert Dp → Px → Int
}

fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

fun validateEmail(email: String): ErrorStatus {
    val emailPattern = Regex("[a-zA-Z\\d._-]+@[a-z]+\\.+[a-z]+")
    return when {
        email.trim().isEmpty() -> {
            ErrorStatus(true, "Email is required")
        }

        !email.trim().matches(emailPattern) -> {
            ErrorStatus(true, "Invalid Email, please try again!")
        }

        else -> {
            ErrorStatus(false)
        }
    }
}

fun validatePassword(password: String): ErrorStatus {
    return when {
        password.trim().isEmpty() -> {
            ErrorStatus(true, "Password is required")
        }

        password.trim().length < 8-> {
            ErrorStatus(true, "Password must be at least 8 character, please try again!")
        }

        else -> {
            ErrorStatus(false)
        }
    }
}

fun validateConfirmPassword(password: String): ErrorStatus {
    return when {
        password.trim().isEmpty() -> {
            ErrorStatus(true, "Confirm Password is required")
        }

        password.trim().length < 8-> {
            ErrorStatus(true, "Confirm Password must be at least 8 character, please try again!")
        }

        else -> {
            ErrorStatus(false)
        }
    }
}

fun validateMatchPassword(password: String, confirmPassword: String): ErrorStatus {
    return when {

        password != confirmPassword -> {
            ErrorStatus(true, "Password and Confirm Password not match, please try again")
        }

        else -> {
            ErrorStatus(false)
        }
    }
}