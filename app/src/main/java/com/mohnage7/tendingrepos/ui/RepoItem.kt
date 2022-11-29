package com.mohnage7.tendingrepos.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.mohnage7.domain.model.TrendingRepo
import com.mohnage7.tendingrepos.ui.TrendingIcons

@Composable
fun RepoItem(
    item: TrendingRepo,
) {
    val commonModifier = Modifier
        .wrapContentWidth()
        .fillMaxHeight()

    val iconModifier = Modifier
        .width(16.dp)
        .height(16.dp)

    Row(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {

        AsyncImage(
            model = item.image,
            contentDescription = "author_image",
            modifier = Modifier
                .clip(CircleShape)
                .height(40.dp)
                .width(40.dp)
                .offset(y = 4.dp)
        )

        Spacer(modifier = Modifier.width(8.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = item.author,
                fontSize = 12.sp,
                modifier = commonModifier.padding(4.dp),
                color = Color.Gray, textAlign = TextAlign.Start
            )

            Text(
                text = item.name,
                fontSize = 14.sp,
                modifier = commonModifier.padding(4.dp),
                color = Color.Black, textAlign = TextAlign.Start
            )

            Text(
                text = item.description,
                fontSize = 12.sp,
                modifier = commonModifier.padding(4.dp),
                color = Color.Gray, textAlign = TextAlign.Start
            )
            Row(
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxWidth()
            ) {
                item.language?.let {
                    Icon(
                        painter = painterResource(id = TrendingIcons.Language),
                        contentDescription = null,
                        modifier = iconModifier,
                        tint = Color.Unspecified
                    )
                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = it,
                        fontSize = 12.sp,
                        modifier = commonModifier,
                        color = Color.Gray, textAlign = TextAlign.Start
                    )
                }

                Spacer(modifier = Modifier.width(24.dp))

                Icon(
                    imageVector = TrendingIcons.Star, tint = Color.Red,
                    contentDescription = "language_icon",
                    modifier = iconModifier
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = item.stars.toString(),
                    fontSize = 12.sp,
                    modifier = commonModifier,
                    color = Color.Gray, textAlign = TextAlign.Start
                )
            }
        }

    }
}