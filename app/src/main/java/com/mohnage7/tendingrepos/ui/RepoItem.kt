package com.mohnage7.tendingrepos.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension.Companion.fillToConstraints
import coil.compose.AsyncImage
import com.mohnage7.domain.model.TrendingRepo
import com.mohnage7.tendingrepos.ui.theme.TrendingReposTheme

@Composable
fun RepoItem(
    item: TrendingRepo,
) {

    val iconModifier = Modifier
        .width(16.dp)
        .height(16.dp)

    ConstraintLayout(modifier = Modifier.padding(8.dp)) {
        val (image, author, name, description, languageIcon, language, starIcon, stars) = createRefs()

        AsyncImage(
            model = item.image,
            contentDescription = "author_image",
            modifier = Modifier
                .constrainAs(image) {
                    top.linkTo(parent.top, margin = 16.dp)
                    start.linkTo(parent.start, margin = 8.dp)
                }
                .size(40.dp)
                .clip(CircleShape)
                .height(40.dp)
                .width(40.dp)
        )

        Text(
            text = item.author,
            fontSize = 12.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
                .constrainAs(author) {
                    top.linkTo(parent.top)
                    start.linkTo(image.end, margin = 8.dp)
                },
            color = Color.Gray, textAlign = TextAlign.Start
        )

        Text(
            text = item.name,
            fontSize = 14.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
                .constrainAs(name) {
                    top.linkTo(author.bottom)
                    start.linkTo(author.start)
                },
            color = Color.Black, textAlign = TextAlign.Start
        )

        Text(
            text = item.description,
            fontSize = 12.sp,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth()
                .constrainAs(description) {
                    top.linkTo(name.bottom)
                    start.linkTo(author.start)
                    end.linkTo(parent.end)
                    width = fillToConstraints
                },
            color = Color.Gray, textAlign = TextAlign.Start
        )

        item.language?.let {
            Icon(
                painter = painterResource(id = TrendingIcons.Language),
                contentDescription = null,
                modifier = iconModifier.constrainAs(languageIcon) {
                    top.linkTo(description.bottom, margin = 8.dp)
                    start.linkTo(author.start)
                },
                tint = Color.Unspecified
            )
            Text(
                text = it,
                fontSize = 12.sp,
                modifier = Modifier.constrainAs(language) {
                    top.linkTo(description.bottom, margin = 8.dp)
                    start.linkTo(languageIcon.end, margin = 8.dp)
                },
                color = Color.Gray, textAlign = TextAlign.Start
            )
        }

        Icon(
            imageVector = TrendingIcons.Star, tint = Color.Red,
            contentDescription = "language_icon",
            modifier = iconModifier.constrainAs(starIcon) {
                top.linkTo(description.bottom, margin = 8.dp)
                if (item.language == null) start.linkTo(author.start) else start.linkTo(
                    language.end,
                    margin = 24.dp
                )
            }
        )

        Text(
            text = item.stars.toString(),
            fontSize = 12.sp,
            modifier = Modifier.constrainAs(stars) {
                top.linkTo(description.bottom, margin = 8.dp)
                start.linkTo(starIcon.end, margin = 8.dp)
            },
            color = Color.Gray, textAlign = TextAlign.Start
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TrendingReposTheme {
        RenderTrendingRepos(
            listOf(
                TrendingRepo(
                    id = 1,
                    image = "R.drawable.baseline_account_circle_black_48",
                    author = "Nageh",
                    name = "Trending Repo",
                    description = "Trending Github repositories https://github.com/MohNage7/Tending-Repos",
                    stars = 100,
                    language = "Kotlin"
                )
            )
        )
    }
}


@Composable
fun RenderTrendingRepos(reposList: List<TrendingRepo>) {
    LazyColumn {
        items(reposList) { item ->
            RepoItem(item = item)
            Divider(color = Color.LightGray, thickness = 0.5.dp)
        }
    }
}