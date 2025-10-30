package com.example.slidesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                TeamsApp()
            }
        }
    }
}

data class Team(
    @StringRes val stringResourceId: Int,
    @DrawableRes val imageResourceId: Int
)

class Datasource {
    fun loadTeams(): List<Team> = listOf(
        Team(R.string.team1,  R.drawable.image1),
        Team(R.string.team2,  R.drawable.image2),
        Team(R.string.team3,  R.drawable.image3),
        Team(R.string.team4,  R.drawable.image4),
        Team(R.string.team5,  R.drawable.image5),
        Team(R.string.team6,  R.drawable.image6),
        Team(R.string.team7,  R.drawable.image7),
        Team(R.string.team8,  R.drawable.image8),
        Team(R.string.team9,  R.drawable.image9),
        Team(R.string.team10, R.drawable.image10),
        Team(R.string.team11, R.drawable.image11),
        Team(R.string.team12, R.drawable.image12),
        Team(R.string.team13, R.drawable.image13),
        Team(R.string.team14, R.drawable.image14),
        Team(R.string.team15, R.drawable.image15),
        Team(R.string.team16, R.drawable.image16)
    )
}

/* ---------- UI ---------- */

@Composable
fun TeamsApp() {
    val layoutDirection = LocalLayoutDirection.current
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(
                start = WindowInsets.safeDrawing
                    .asPaddingValues()
                    .calculateStartPadding(layoutDirection),
                end = WindowInsets.safeDrawing
                    .asPaddingValues()
                    .calculateEndPadding(layoutDirection)
            )
    ) {
        // Choose ONE: list or grid
        TeamList(teamList = Datasource().loadTeams(), modifier = Modifier.fillMaxSize())
        // TeamGrid(teamList = Datasource().loadTeams(), modifier = Modifier.fillMaxSize())
    }
}

@Composable
fun TeamCard(team: Team, modifier: Modifier = Modifier) {
    Card(modifier = modifier) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {
            Image(
                painter = painterResource(team.imageResourceId),
                contentDescription = stringResource(team.stringResourceId),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(194.dp),
                contentScale = ContentScale.Crop
            )
            Text(
                text = stringResource(team.stringResourceId),
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.headlineSmall
            )
        }
    }
}

/* Scrollable list */
@Composable
fun TeamList(teamList: List<Team>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(teamList) { team ->
            TeamCard(team = team, modifier = Modifier.padding(8.dp))
        }
    }
}

/* Optional: grid version (commented in TeamsApp) */
@Composable
fun TeamGrid(teamList: List<Team>, modifier: Modifier = Modifier) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier.padding(8.dp)
    ) {
        items(teamList) { team ->
            TeamCard(team = team)
        }
    }
}

/* ---------- Previews ---------- */

@Preview(showBackground = true)
@Composable
fun PreviewTeamsList() {
    MaterialTheme {
        TeamList(Datasource().loadTeams())
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTeamsGrid() {
    MaterialTheme {
        TeamGrid(Datasource().loadTeams())
    }
}
