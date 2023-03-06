package com.hinge.profile.app.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hinge.profile.app.R
import com.hinge.profile.app.presentation.profile.ProfileCardScreen
import com.hinge.profile.app.utils.ConnectionState
import com.hinge.profile.app.utils.connectivityState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Scaffold(
                topBar = { TopBar() },
            ) { padding ->
                Column(modifier = Modifier.padding(padding)) {
                    ConnectivityStatus()
                }
            }
        }
    }

    @Composable
    fun TopBar() {
        TopAppBar(
            title = { Text(text = stringResource(R.string.app_name), fontSize = 18.sp) },
            backgroundColor = colorResource(id = R.color.black),
            contentColor = Color.White
        )
    }

    @OptIn(ExperimentalMaterialApi::class)
    @ExperimentalCoroutinesApi
    @Composable
    fun ConnectivityStatus() {
        val connection by connectivityState()
        val isConnected = connection === ConnectionState.Available

        if (!isConnected) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp)
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    backgroundColor = Color.Red,
                    contentColor = Color.White
                ) {
                    Text(
                        text = "No internet connection",
                        modifier = Modifier.padding(20.dp)
                    )
                }
            }
        } else {
            ProfileCardScreen(
                hiltViewModel(),
                modifier = Modifier,
                enableButtons = true,
            )
        }
    }

}