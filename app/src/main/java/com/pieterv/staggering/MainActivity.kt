package com.pieterv.staggering

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pieterv.staggering.ui.theme.StaggeringTheme
import kotlin.random.Random

@OptIn(ExperimentalFoundationApi::class)
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            StaggeringTheme {
                LazyVerticalStaggeredGrid(
                    columns = StaggeredGridCells.Fixed(2),
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalItemSpacing = 16.dp
                ) {
                    items((1..100).map {
                        ListItem(
                            heading = arrayListOf(
                                R.string.heading_1,
                                R.string.heading_2,
                                R.string.heading_3,
                                R.string.heading_4,
                                R.string.heading_5,
                                R.string.heading_6,
                                R.string.heading_7,
                            ).random(),
                            content = arrayListOf(
                                R.string.card_content_1,
                                R.string.card_content_2,
                                R.string.card_content_3,
                                R.string.card_content_4,
                                R.string.card_content_5,
                                R.string.card_content_6,
                                R.string.card_content_7,
                            ).random(),
                            height = Random.nextInt(100, 300).dp,
                        )
                    }) { item ->
                        StaggeredBox(item)
                    }
                }
            }
        }
    }
}

data class ListItem(
    @StringRes val heading: Int,
    @StringRes val content: Int,
    val height: Dp
)

@Composable
fun StaggeredBox(properties: ListItem) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(properties.height)
            .clip(RoundedCornerShape(10.dp))
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Text(
                text = stringResource(properties.heading),
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                ),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = stringResource(properties.content),
                style = TextStyle(
                    fontSize = 16.sp
                ),
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}
