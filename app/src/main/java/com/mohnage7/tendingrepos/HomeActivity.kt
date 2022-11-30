package com.mohnage7.tendingrepos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush.Companion.linearGradient
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mohnage7.domain.model.TrendingRepo
import com.mohnage7.tendingrepos.ui.*
import com.mohnage7.tendingrepos.ui.theme.TrendingReposTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : ComponentActivity() {

    private val viewModel: TrendingReposViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TrendingReposTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Column {
                        RenderToolbar()
                        RenderMainContent()
                    }

                }
            }
        }
        fetchRepos()
    }

    @Composable
    private fun RenderMainContent() {
        viewModel.trendingRepoViewState.observeAsState().value?.apply {
            RenderViewState(this)
        }
    }

    @Composable
    private fun RenderToolbar() {
        TopAppBar(
            title = {
                Text(
                    text = stringResource(R.string.app_name),
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.h5
                )
            },
            actions = {
                OverflowMenu {
                    DropdownMenuItem(onClick = {
                        fetchRepos(forceRemote = true)
                    }) {
                        Text(stringResource(R.string.refresh))
                    }
                }
            }
        )
    }

    @Composable
    fun OverflowMenu(content: @Composable () -> Unit) {
        var showMenu by remember { mutableStateOf(false) }

        IconButton(onClick = {
            showMenu = !showMenu
        }) {
            Icon(
                imageVector = Icons.Outlined.MoreVert,
                contentDescription = stringResource(R.string.more_button),
            )
        }
        DropdownMenu(
            expanded = showMenu,
            onDismissRequest = { showMenu = false }
        ) {
            content()
        }
    }

    private fun fetchRepos(forceRemote: Boolean = false) {
        viewModel.fetchTrendingRepos(forceRemote)
    }

    @Composable
    private fun RenderViewState(viewState: ViewState<List<TrendingRepo>>) {
        when (viewState) {
            is ViewState.Failure -> ShowErrorState()
            ViewState.Loading -> ShowShimmering()
            is ViewState.Success -> RenderTrendingRepos(viewState.data)
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
fun ShowErrorState() {
    ErrorState {
//        fetchRepos(forceRemote = true)
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

@Composable
@Preview(showBackground = true)
fun ErrorStatePreview() {
    ShowErrorState()
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
                ),
                TrendingRepo(
                    id = 2,
                    image = "R.drawable.baseline_account_circle_black_48",
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