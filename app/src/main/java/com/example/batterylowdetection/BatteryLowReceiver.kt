package com.example.batterylowdetection

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.compose.runtime.MutableState


class BatteryLowReceiver(
    private val batteryLowImageState: MutableState<Int>
) : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == Intent.ACTION_BATTERY_LOW) {
            batteryLowImageState.value =
                R.drawable.battery_low // Replace with your low battery image resource
        } else if (intent.action == Intent.ACTION_BATTERY_OKAY) {
            batteryLowImageState.value =
                R.drawable.battery_full // Replace with your full battery image resource
        }
    }

}
