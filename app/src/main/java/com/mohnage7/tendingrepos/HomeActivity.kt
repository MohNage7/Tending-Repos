package com.mohnage7.tendingrepos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush.Companion.linearGradient
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mohnage7.tendingrepos.ui.LoadingShimmerEffect
import com.mohnage7.tendingrepos.ui.ShimmerItem
import com.mohnage7.tendingrepos.ui.theme.RepoItem
import com.mohnage7.tendingrepos.ui.theme.TrendingReposTheme

class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TrendingReposTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    RenderTrendingRepos(
                        listOf(
                            TrendingRepo(
                                image = R.drawable.baseline_account_circle_black_48,
                                author = "Nageh",
                                name = "Trending Repo",
                                description = "Trending Github repositories https://github.com/MohNage7/Tending-Repos",
                                stars = 100,
                                language = "Kotlin"
                            )
                        )
                    )

                    ShowShimmering()
                }
            }
        }
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

@Composable
fun ShowShimmering() {
    Column {
        repeat(15) {
            LoadingShimmerEffect()
        }
    }
}


@Composable
@Preview(showBackground = true)
fun ShimmerPreview() {
    ShimmerItem(
        brush = linearGradient(
            listOf(
                Color.LightGray.copy(alpha = 0.9f),
                Color.LightGray.copy(alpha = 0.4f),
                Color.LightGray.copy(alpha = 0.9f)
            )
        )
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TrendingReposTheme {
        RenderTrendingRepos(
            listOf(
                TrendingRepo(
                    image = R.drawable.baseline_account_circle_black_48,
                    author = "Nageh",
                    name = "Trending Repo",
                    description = "Trending Github repositories https://github.com/MohNage7/Tending-Repos",
                    stars = 100,
                    language = "Kotlin"
                ),
                TrendingRepo(
                    image = R.drawable.baseline_account_circle_black_48,
                    author = "Nageh",
                    name = "Clean Arch",
                    description = "Clean Github repositories https://github.com/MohNage7/Tending-Repos",
                    stars = 10000,
                    language = "Java"
                )
            )
        )
    }
}