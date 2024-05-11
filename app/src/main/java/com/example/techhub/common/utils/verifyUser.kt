package com.example.techhub.common.utils

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import com.example.techhub.common.utils.redirectToPerfilUsuario
import com.example.techhub.common.utils.showToastError
import com.example.techhub.domain.model.updateCurrentUser
import com.example.techhub.domain.model.usuario.UsuarioVerifyData
import com.example.techhub.domain.service.RetrofitService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun verifyUser(
    userData: UsuarioVerifyData,
    context: Context,
    toastErrorMessage: String,
) {
    val authService = RetrofitService.getAuthService()

    CoroutineScope(Dispatchers.Main).launch {
        try {
            val response = authService.verifyUser(userData)
            val extras = Bundle()
            extras.putInt("id", response.body()?.id!!)

            if (response.isSuccessful) {
                updateCurrentUser(
                    context = context,
                    usuarioTokenData = response.body()!!,
                    email = userData.email!!
                )

                redirectToPerfilUsuario(
                    context = context,
                    fullName = response.body()?.nome!!,
                    extras = extras
                )
            } else {
                (context as Activity).runOnUiThread {
                    showToastError(context, toastErrorMessage)
                }
            }
        } catch (e: Exception) {
            Log.e("VERIFY_USER", "ERROR: ${e.message}")
            (context as Activity).runOnUiThread {
                showToastError(context, toastErrorMessage)
            }
        }
    }
}