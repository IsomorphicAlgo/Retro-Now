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
import com.retronow.app.BuildConfig

/**
 * Banner ad composable for displaying AdMob banner ads
 * 
 * Ad Unit ID is loaded from BuildConfig, which reads from local.properties
 * This keeps sensitive Ad IDs out of version control.
 */
@Composable
fun BannerAd(
    modifier: Modifier = Modifier,
    adUnitId: String = BuildConfig.ADMOB_BANNER_AD_UNIT_ID
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

