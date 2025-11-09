//package com.daviddev.reungbox.utils
//
//import android.os.Bundle
//import android.os.Parcelable
//import androidx.navigation.NavType
//import kotlinx.serialization.json.Json
//
//object NavigationUtils {
//    inline fun <reified  T : Parcelable> parcelableNavType() : NavType<T> {
//       return object : NavType<T>(isNullableAllowed = true) {
//
//           override fun get(bundle: Bundle, key: String) : T? {
//               return bundle.getParcelable(key) as? T
//           }
//
//           override fun parseValue(value: String): T {
//               return Json.decodeFromString(value)
//           }
//
//           override fun put(bundle: Bundle, key: String, value: T) {
//               return bundle.putParcelable(key, value)
//           }
//       }
//    }
//}