package com.cklabs.androidlytics

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cklabs.androidlytics.ui.theme.AndroidlyticsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidlyticsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainContent(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun MainContent(modifier: Modifier = Modifier) {
    var counter by remember { mutableIntStateOf(0) }
    val context = LocalContext.current

    // Definindo as cores personalizadas
    val blueColor = Color(0xFF007AFF)
    val blueBgColor = Color(0x33007AFF) // 20% de opacidade
    val orangeColor = Color(0xFFFF9500)
    val orangeBgColor = Color(0x33FF9500) // 20% de opacidade
    val redColor = Color(0xFFFF3B30)
    val redBgColor = Color(0x33FF3B30) // 20% de opacidade

    val handleControlledExceptionClick: () -> Unit = {
        try {
            throw RuntimeException(context.getString(R.string.exception_message))
        } catch (e: Exception) {
            Log.e("Androidlytics", "Caught exception: ${e.message}", e)
        }
    }

    val handleCrashClick: () -> Unit = {
        throw RuntimeException(context.getString(R.string.crash_message))
    }

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Title Section
        Text(
            text = stringResource(R.string.app_title),
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier
                .padding(top = 24.dp, bottom = 16.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        // Firebase Logo
        Image(
            painter = painterResource(id = R.drawable.firebase_logo),
            contentDescription = "Firebase Logo",
            modifier = Modifier
                .padding(bottom = 24.dp)
                .height(48.dp)
        )

        // Main Container
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .weight(1f),
            color = MaterialTheme.colorScheme.surface,
            shape = MaterialTheme.shapes.medium,
            shadowElevation = 4.dp
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Counter Section
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    color = blueBgColor,
                    shape = MaterialTheme.shapes.medium
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = stringResource(R.string.counter_value, counter),
                            style = MaterialTheme.typography.titleMedium
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Button(
                            onClick = { counter++ },
                            modifier = Modifier.fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = blueColor
                            ),
                            shape = MaterialTheme.shapes.small
                        ) {
                            Text(
                                text = stringResource(R.string.btn_increment),
                                modifier = Modifier.padding(8.dp)
                            )
                        }
                    }
                }

                // Exception Section
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    color = orangeBgColor,
                    shape = MaterialTheme.shapes.medium
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = stringResource(R.string.exception_section_title),
                            style = MaterialTheme.typography.titleMedium
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Button(
                            onClick = handleControlledExceptionClick,
                            modifier = Modifier.fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = orangeColor
                            ),
                            shape = MaterialTheme.shapes.small
                        ) {
                            Text(
                                text = stringResource(R.string.btn_generate_exception),
                                modifier = Modifier.padding(8.dp)
                            )
                        }
                    }
                }

                // Crash Section
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    color = redBgColor,
                    shape = MaterialTheme.shapes.medium
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = stringResource(R.string.crash_section_title),
                            style = MaterialTheme.typography.titleMedium
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Button(
                            onClick = handleCrashClick,
                            modifier = Modifier.fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = redColor
                            ),
                            shape = MaterialTheme.shapes.small
                        ) {
                            Text(
                                text = stringResource(R.string.btn_force_crash),
                                modifier = Modifier.padding(8.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainContentPreview() {
    AndroidlyticsTheme {
        MainContent()
    }
}