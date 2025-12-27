package com.retronow.app.ui.ads

import android.widget.FrameLayout
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView

/**
 * Banner ad composable for displaying AdMob banner ads
 * 
 * Note: Replace the test ad unit ID with your actual AdMob ad unit ID
 * Test ID: ca-app-pub-3940256099942544/6300978111
 */
@Composable
fun BannerAd(
    modifier: Modifier = Modifier,
    adUnitId: String = "ca-app-pub-3940256099942544/6300978111" // Test ad unit ID
) {
    val context = LocalContext.current
    
    AndroidView(
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp), // Standard banner height
        factory = { ctx ->
            AdView(ctx).apply {
                setAdSize(AdSize.BANNER)
                this.adUnitId = adUnitId
                loadAd(AdRequest.Builder().build())
            }
        },
        update = { adView ->
            // AdView will automatically refresh
        }
    )
}

