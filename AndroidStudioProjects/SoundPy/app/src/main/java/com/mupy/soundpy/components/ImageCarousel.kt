package com.mupy.soundpy.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.mupy.soundpy.ContextMain
import com.mupy.soundpy.R
import com.mupy.soundpy.models.PlayListData
import com.mupy.soundpy.ui.theme.ColorWhite

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ImageCarousel(
    list: ArrayList<PlayListData>, navHostController: NavHostController, viewModel: ContextMain
) {
    val pagerState = rememberPagerState(0, 0.05f) { list.size }
    Title(
        text = stringResource(R.string.new_release),
    )
    if (list.isNotEmpty()) HorizontalPager(
        state = pagerState,
        contentPadding = PaddingValues(0.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) { page ->
        Card(
            colors = CardDefaults.cardColors(Color.Transparent),
            shape = RoundedCornerShape(10.dp),
            elevation = CardDefaults.cardElevation(0.dp),
            modifier = Modifier.fillMaxWidth()
            // .aspectRatio(0.5f)
        ) {
            CardNewRelease(list[page], navHostController = navHostController, viewModel = viewModel)
        }

    }
}

/*
* AsyncImage(
model = Builder(LocalContext.current)
.data(sliderList[page])
.crossfade(true)
.scale(FILL)
.build(),
contentDescription = null,
modifier = Modifier
.offset {
    // Calculate the offset for the current page from the
    // scroll position
    val pageOffset =
        this@HorizontalPager.calculateCurrentOffsetForPage(page)
    // Then use it as a multiplier to apply an offset
    IntOffset(
        x = (70.dp * pageOffset).roundToPx(),
        y = 0,
    )
}*/
