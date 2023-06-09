package com.nthlabs.esmes.feature_authentication.presentation.home

import com.nthlabs.esmes.R
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.Modifier
import com.nthlabs.esmes.ui.theme.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.*
import androidx.compose.material.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.tooling.preview.Preview
import com.nthlabs.esmes.core.components.cards.AddCategoryCard
import com.nthlabs.esmes.core.components.cards.CategoryCards

data class SectionData(
    val subtitle: String,
    val seeAll: Boolean,
    val bgColor: Color,
//    val content: List<Content>,
    val title: String,
    val time: String,
    val type: String,
    val size: Dp,
)

data class Content(
    val name: String,
    val perks: Number,
    val state: String,
    val image: Painter,
)

@Composable
fun HomeScreen() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Transparent)
    ) {
        Column {
            SectionList(
                sections = listOf(
                SectionData(
                    size = 200.dp,
                    time = "12:42:22 Today",
                    type = "greetings",
                    title = "Good Afternoon",
                    seeAll = false,
                    bgColor = primary_blue,
                    subtitle = "Your most expensive item today is:"
                ),
                SectionData(
                    size = 120.dp,
                    time = "12:42:22 Today",
                    type = "messages",
                    title = "MessagePool",
                    seeAll = true,
                    bgColor = primary_blue,
                    subtitle = "New Messages"
                ),
                SectionData(
                    size = 120.dp,
                    time = "12:42:22 Today",
                    type = "categories",
                    title = "Categories",
                    seeAll = true,
                    bgColor = Color.Transparent,
                    subtitle = "Expenses"
                )
            ), modifier = Modifier)
        }
    }
}

@Composable
fun SectionList(
    sections: List<SectionData>,
    modifier: Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(1),
        contentPadding = PaddingValues(bottom = 1.dp)
    ) {
        items(sections.size) {
            Section(
                modifier = modifier,
                userName = "Ojwang",
                sectionData = sections[it]
            )
        }
    }

}

@Composable
fun Section(
    userName: String?,
    modifier: Modifier,
    sectionData: SectionData,
) {
    Column(
        modifier = modifier.padding(horizontal = 15.dp),
        verticalArrangement = Arrangement.SpaceEvenly) {
        Column(
            modifier = modifier.padding(bottom = 15.dp, top = 20.dp),
            verticalArrangement = Arrangement.SpaceAround) {
            Row(
                modifier = modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    modifier = if (sectionData.type == "greetings") Modifier.fillMaxWidth(.54f) else Modifier,
                    style = MaterialTheme.typography.h2,
                    text = when (sectionData.type) {
                        "greetings" -> "${sectionData.title} $userName"
                        else -> sectionData.title
                    },
                    fontWeight = FontWeight.W800,
                    letterSpacing = 1.sp,
                    fontSize = 25.sp,
                )
                if (sectionData.type == "greetings") {
                    Icon(
                        modifier = modifier.padding(end = 5.dp),
                        painter = painterResource(id = R.drawable.toggle_home_128),
                        contentDescription = "toggle home screens")
                } else {
                    Text(
                        text = if (sectionData.seeAll) "See All" else "",
                        style = MaterialTheme.typography.subtitle1,
                        color = view_blue,
                        fontSize = 13.sp,
                    )
                }
            }
            Text(
                fontSize = 18.sp,
                text = sectionData.subtitle,
                color = black.copy(.4f),
                style = MaterialTheme.typography.subtitle1,
                modifier = if (sectionData.type == "greetings") Modifier.padding(top = 5.dp) else Modifier,
            )
        }
        SpendOverview(
            modifier = modifier,
            size = sectionData.size,
            content = sectionData.type,
            bgColor = sectionData.bgColor, )
    }
}

@Composable
fun SpendOverview(
    modifier: Modifier,
    bgColor: Color,
    content: String,
    size: Dp
) {
    Box(
        modifier = modifier
            .height(size)
            .fillMaxWidth()
            .background(bgColor, Shapes.medium),
        contentAlignment = Alignment.Center
    ) {
        when (content) {
            "greetings" -> Unit
            "messages" -> Unit
            else -> CategoryOverviewContainer(modifier = modifier)
        }
    }
}

@Composable
fun CategoryOverviewContainer(
    modifier: Modifier,
) {
    Row(
        modifier
            .fillMaxSize()
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Box(modifier.fillMaxWidth(.76f)) {
            CategoryOverview(
                modifier, listOf(
                    Content(
                        name = "Shopping",
                        perks = 3000,
                        state = "onBudget",
                        image = painterResource(id = R.drawable.smsused_64)
                    ), Content(
                        name = "Transport",
                        perks = 2110,
                        state = "overSpent",
                        image = painterResource(id = R.drawable.smsused_64)
                    ), Content(
                        name = "Food",
                        perks = 1533,
                        state = "underSpent",
                        image = painterResource(id = R.drawable.smsused_64)
                    ), Content(
                        name = "Groceries",
                        perks = 189,
                        state = "onBudget",
                        image = painterResource(id = R.drawable.smsused_64)
                    ), Content(
                        name = "Invest",
                        perks = 345,
                        state = "onBudget",
                        image = painterResource(id = R.drawable.smsused_64)
                    )
                )
            )
        }
        Spacer(modifier.padding(horizontal = 3.dp))
        Box(modifier.fillMaxWidth(1f)) {
            AddCategoryCard(
                modifier, borderColor = black
            )
        }
    }
}

@Composable
fun CategoryOverview(
    modifier: Modifier,
    content: List<Content>
) {
    LazyRow(
        modifier
            .fillMaxSize()
            .padding(bottom = 3.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        contentPadding = PaddingValues(bottom = 1.dp)
    ) {
        items(content.size) {
            CategoryCards(
                type = "preview",
                content = content[it],
                modifier = modifier
            )
        }
    }
}

@Preview
@Composable
fun prev6() {
    HomeScreen()
}