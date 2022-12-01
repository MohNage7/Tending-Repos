package com.mohnage7.tendingrepos.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.mohnage7.tendingrepos.R

@Composable
fun ErrorState(retryAction: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        LottieView()

        Spacer(modifier = Modifier.height(16.dp))

        val commonModifier = Modifier
            .wrapContentWidth()
            .wrapContentHeight()

        Text(
            text = stringResource(id = R.string.error_message_title),
            fontSize = 16.sp,
            modifier = commonModifier.padding(4.dp),
            textAlign = TextAlign.Start
        )

        Text(
            text = stringResource(id = R.string.error_message_content),
            fontSize = 14.sp,
            modifier = commonModifier.padding(4.dp),
            color = Color.Gray, textAlign = TextAlign.Start
        )

        Spacer(modifier = Modifier.height(50.dp))

        OutlinedButton(
            modifier = Modifier.fillMaxWidth(.8f),
            onClick = { retryAction.invoke() },
            border = BorderStroke(1.dp, colorResource(id = R.color.green_dark)),
            shape = RoundedCornerShape(10),
            // or shape = CircleShape
            colors = ButtonDefaults.outlinedButtonColors(contentColor = colorResource(id = R.color.green_dark))
        ) {
            Text(text = stringResource(id = R.string.error_button_title).uppercase())
        }

    }
}

@Composable
fun LottieView() {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.error_lottie_animation))
    val progress by animateLottieCompositionAsState(composition)
    LottieAnimation(
        modifier = Modifier.fillMaxHeight(0.5f),
        composition = composition,
        progress = { progress },
    )
}
