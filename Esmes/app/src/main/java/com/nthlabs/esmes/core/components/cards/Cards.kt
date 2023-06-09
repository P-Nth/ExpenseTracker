package com.nthlabs.esmes.core.components.cards

import androidx.compose.foundation.Canvas
import androidx.compose.ui.unit.sp
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import com.nthlabs.esmes.R
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.graphics.painter.Painter
import com.nthlabs.esmes.feature_authentication.presentation.home.Content
import com.nthlabs.esmes.ui.theme.*

@Composable
fun Cards() {

}

@Composable
fun CategoryCards(
    type: String = "preview",
    image: Painter = painterResource(id = R.drawable.smsused_64),
    content: Content,
    modifier: Modifier,
) {
    when (type) {
        "main" -> MainCategoryCard()
        "preview" -> PreviewCategoryCard(
            //image = image,
            content = content,
            modifier = modifier
        )
        else -> MainCategoryCard()
    }
}

@Composable
fun MainCategoryCard() {}

@Composable
fun PreviewCategoryCard(
    content: Content,
    modifier: Modifier,
) {
    var bgColor by remember { mutableStateOf(primary_blue) }
    bgColor = when (content.state) {
        "onBudget" -> bgColor
        "overSpent" -> red
        else -> white
    }

    Box(
        modifier
            .width(100.dp)
            .fillMaxHeight(1f)
            .padding(end = 5.dp)
            .background(bgColor, Shapes.medium)
            .clickable { /* TODO */ }
    ) {
        Column(
            modifier
                .fillMaxSize()
                .padding(horizontal = 5.dp, vertical = 10.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = modifier.fillMaxWidth(1f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Icon(
                    painter = content.image,
                    modifier = Modifier,
                    contentDescription = "messageUsed",
                )
                Spacer(modifier.padding(horizontal = 3.dp))
                Text(
                    style = MaterialTheme.typography.subtitle1,
                    fontSize = 14.sp,
                    text = "${content.perks}",
                )
            }
            Row(
                modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    style = MaterialTheme.typography.h1,
                    fontWeight = FontWeight.W700,
                    letterSpacing = 1.sp,
                    text = content.name,
                    fontSize = 13.sp,
                )
            }
        }
    }
}

@Composable
fun AddCategoryCard(
    modifier: Modifier,
    borderColor: Color,
) {

    val stroke = Stroke(width = 3f,
        pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
    )

    Box(
        modifier = modifier
            .width(85.dp)
            .fillMaxHeight(.95f)
            .background(Color.Transparent, Shapes.medium)
            .clickable { /* TODO */ }
    ) {
        Canvas(modifier = modifier
            .fillMaxHeight()
            .fillMaxWidth()) {
            drawRoundRect(
                style = stroke,
                color = borderColor,
                cornerRadius = CornerRadius(8.dp.toPx())
            )
        }
        Column(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    painter = painterResource(id = R.drawable.add_64),
                    modifier = modifier.width(50.dp).height(50.dp),
                    contentDescription = "add category"
                )
            }
        }
    }
}

@Composable
fun MessagePoolCards() {}
