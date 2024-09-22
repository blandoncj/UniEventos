package com.example.unieventos.ui.screens.customer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.unieventos.R
import com.example.unieventos.enums.EmailError
import com.example.unieventos.ui.components.utils.CustomTopAppBar
import com.example.unieventos.ui.components.EmailField
import com.example.unieventos.ui.components.utils.PrimaryButton
import com.example.unieventos.utils.validateEmailFormat

/**
 * RecoverPassword screen composable
 * This composable is used to display the recover password screen
 * It contains a text field to input the email and a button to send the recovery code
 */
@Composable
fun RecoverPasswordScreen(
    onBack: () -> Unit,
    onNavigateToChangePassword: () -> Unit
) {
    var email by rememberSaveable { mutableStateOf("") }
    var emailError by rememberSaveable { mutableStateOf(EmailError.NONE) }

    Scaffold(
        topBar = {
             CustomTopAppBar(title = "Recuperar Contraseña", onBack = onBack)
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(horizontal = 16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                Spacer(modifier = Modifier.height(70.dp))

                Text(text = stringResource(id = R.string.recover_password))

                Spacer(modifier = Modifier.height(50.dp))

                EmailField(
                    email = email,
                    onEmailChange = {
                        email = it
                        emailError = validateEmailFormat(it)
                    },
                    emailError = emailError,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                )

                Spacer(modifier = Modifier.height(30.dp))

                PrimaryButton(
                    text = stringResource(id = R.string.send_code_btn),
                    onClick = {
                        onNavigateToChangePassword()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    enabled = emailError == EmailError.NONE && email.isNotEmpty()
                )
            }
        }
    }

}