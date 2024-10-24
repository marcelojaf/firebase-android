package com.cklabs.analyticstestlab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.firebase.crashlytics.ktx.crashlytics
import com.google.firebase.ktx.Firebase
import androidx.compose.runtime.mutableIntStateOf
import java.util.*

class MainActivity : ComponentActivity() {
    private lateinit var analytics: FirebaseAnalytics
    private lateinit var crashlytics: FirebaseCrashlytics

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize Firebase
        analytics = Firebase.analytics
        crashlytics = Firebase.crashlytics

        // Log app_open event
        val params = Bundle().apply {
            putString("platform", "Android")
            putString("app_version", packageName)
            putLong("timestamp", Date().time)
        }
        analytics.logEvent(FirebaseAnalytics.Event.APP_OPEN, params)

        setContent {
            MaterialTheme {
                FirebaseTestApp(
                    onCounterIncrement = { value -> logCounterIncrement(value) },
                    onControlledError = { logControlledException() },
                    onForceCrash = { forceCrash() }
                )
            }
        }
    }

    private fun logCounterIncrement(value: Int) {
        val params = Bundle().apply {
            putLong("counter_value", value.toLong())
            putLong("timestamp", Date().time)
        }
        analytics.logEvent("counter_increment_droid", params)
    }

    private fun logControlledException() {
        try {
            throw RuntimeException("Controlled test exception")
        } catch (e: Exception) {
            // Log to Crashlytics
            crashlytics.recordException(e)

            // Log to Analytics
            val params = Bundle().apply {
                putString("error_description", e.message ?: "Unknown error")
                putLong("error_code", -1L)
                putLong("timestamp", Date().time)
            }
            analytics.logEvent("controlled_exception", params)

            println("Caught exception: ${e.message}")
        }
    }

    private fun forceCrash() {
        throw RuntimeException("Intentional app crash")
    }
}

@Composable
fun FirebaseTestApp(
    onCounterIncrement: (Int) -> Unit,
    onControlledError: () -> Unit,
    onForceCrash: () -> Unit
) {
    var counter by remember { mutableIntStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Title
        Text(
            text = "Firebase Analytics Test Lab",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 24.dp)
        )

        // Firebase Logo
        Image(
            painter = painterResource(id = R.drawable.firebase_logo),
            contentDescription = "Firebase Logo",
            modifier = Modifier
                .height(48.dp)
                .padding(bottom = 24.dp)
        )

        // Counter Section
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
            )
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "Counter: $counter",
                    style = MaterialTheme.typography.headlineSmall
                )
                Button(
                    onClick = {
                        counter++
                        onCounterIncrement(counter)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Text("Increment Counter")
                }
            }
        }

        // Exception Section
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFFFA500).copy(alpha = 0.1f)
            )
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "Exception Test",
                    style = MaterialTheme.typography.headlineSmall
                )
                Button(
                    onClick = { onControlledError() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFFFA500)
                    )
                ) {
                    Text("Generate Controlled Exception")
                }
            }
        }

        // Crash Section
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.Red.copy(alpha = 0.1f)
            )
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "Danger Zone",
                    style = MaterialTheme.typography.headlineSmall
                )
                Button(
                    onClick = { onForceCrash() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Red
                    )
                ) {
                    Text("Force App Crash")
                }
            }
        }
    }
}