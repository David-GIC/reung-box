package com.daviddev.reungbox.ui.screen.auth

import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.daviddev.reungbox.utils.ErrorStatus
import com.daviddev.reungbox.utils.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class AuthViewModel : ViewModel() {
    val auth = FirebaseAuth.getInstance()
    val firestore = FirebaseFirestore.getInstance()
    val userCollection = "users"

    var isLoggedIn by mutableStateOf(false)

    var emailInput by mutableStateOf("")
    var passwordVisible by mutableStateOf(false)
    var isLoading by mutableStateOf(false)

    var usernameInput by mutableStateOf("")
    var passwordInput by mutableStateOf("")
    var confirmPasswordInput by mutableStateOf("")
    var confirmPasswordVisible by mutableStateOf(false)

    init {
        if(auth.currentUser != null){
            isLoggedIn = true
        }
    }

    fun onLogout(){
        auth.signOut()
        isLoggedIn = false
    }

    fun login(context: android.content.Context, onResult: (ErrorStatus) -> Unit)  {
        val validateEmailStatus = validateEmail(emailInput.trim())
        if(validateEmailStatus.isError){
            context.showToast(validateEmailStatus.message.toString())
            return
        }

        val validatePasswordStatus = validatePassword(passwordInput.trim())
        if(validatePasswordStatus.isError){
            context.showToast(validatePasswordStatus.message.toString())
            return
        }

        isLoading = true
        auth.signInWithEmailAndPassword(emailInput.trim(), passwordInput.trim())
            .addOnCompleteListener { task ->
                isLoading = false
                if (task.isSuccessful) {
                    onResult(ErrorStatus(false, "Login Successfully"))
                } else {
                    onResult(ErrorStatus(true, "Login failed | ${task.exception}"))
                }
            }
    }

    fun register(context: android.content.Context, onResult: (ErrorStatus) -> Unit)  {
        val validateEmailStatus = validateEmail(emailInput.trim());
        if(validateEmailStatus.isError){
            context.showToast(validateEmailStatus.message.toString());
            return
        }

        val validatePasswordStatus = validatePassword(passwordInput.trim());
        if(validatePasswordStatus.isError){
            context.showToast(validatePasswordStatus.message.toString());
            return
        }

        val validateConfirmPasswordStatus = validateConfirmPassword(confirmPasswordInput.trim());
        if(validateConfirmPasswordStatus.isError){
            context.showToast(validatePasswordStatus.message.toString());
            return
        }

        val validateMatchPasswordStatus = validateMatchPassword(passwordInput.trim(), confirmPasswordInput.trim());
        if(validateMatchPasswordStatus.isError){
            context.showToast(validatePasswordStatus.message.toString());
            return
        }

        isLoading = true
        auth.createUserWithEmailAndPassword(emailInput.trim(), passwordInput.trim())
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val userMap = hashMapOf(
                        "email" to emailInput.trim(),
                        "username" to usernameInput.trim(),
                        "gender" to ""
                    )
                    val userId = auth.currentUser?.uid ?: ""
                    firestore.collection(userCollection).document(userId)
                        .set(userMap)
                        .addOnSuccessListener {
                            isLoading = false
                            onResult(ErrorStatus(false, "Register Successfully"))

                        }
                        .addOnFailureListener { e ->
                            isLoading = false
                            auth.signOut()
                            onResult(ErrorStatus(true, "Register failed | ${e.message}"))
                        }

                } else {
                    isLoading = false
                    onResult(ErrorStatus(true, "Register failed | ${task.exception}"))
                }
            }
    }
}