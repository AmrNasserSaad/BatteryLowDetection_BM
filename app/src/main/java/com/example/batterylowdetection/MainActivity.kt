package com.example.batterylowdetection

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.batterylowdetection.ui.theme.BatteryLowDetectionTheme

class MainActivity : ComponentActivity() {

    private val batteryLowImageState = mutableIntStateOf(R.drawable.battery_full)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BatteryLowDetectionTheme {

                BatteryStatusImage(imageResId = batteryLowImageState.intValue)


            }
        }
        registerReceiver(this, batteryLowImageState)
    }
}


@Composable
fun BatteryStatusImage(imageResId: Int) {
    Image(
        painter = painterResource(id = imageResId),
        contentDescription = null,
        modifier = Modifier.fillMaxSize()
    )
}

fun registerReceiver(
    context: Context,
    batteryLowImageState: MutableState<Int>
) {
    val intentFilter = IntentFilter().apply {
        addAction(Intent.ACTION_BATTERY_LOW)
        addAction(Intent.ACTION_BATTERY_OKAY)
    }
    val receiver = BatteryLowReceiver(batteryLowImageState)
    context.registerReceiver(receiver, intentFilter)
}
