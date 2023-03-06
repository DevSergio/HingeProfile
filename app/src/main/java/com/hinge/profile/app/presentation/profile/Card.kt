package com.hinge.profile.app.presentation.profile

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Female
import androidx.compose.material.icons.filled.Male
import androidx.compose.material.icons.filled.School
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.hinge.profile.app.domain.model.User
import com.hinge.profile.app.R

@Composable
fun Card(
    modifier: Modifier = Modifier,
    user: User
) {
    Box(
        modifier
    ) {
        AsyncImage(
            model = user.photo,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            error = painterResource(R.drawable.ic_launcher_background),
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(10.dp)),
        )
        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(10.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = user.name,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 25.sp
                )
                if (user.gender == "m") {
                    Icon(Icons.Filled.Male, contentDescription = "", tint = Color.Green)
                } else {
                    Icon(Icons.Filled.Female, contentDescription = "", tint = Color.Green)
                }
            }
            user.school?.let {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                ) {
                    Text(
                        text = it,
                        color = Color.White,
                        fontSize = 25.sp
                    )
                    Icon(Icons.Filled.School, contentDescription = "", tint = Color.DarkGray)
                }
            }
            user.about?.let {
                Text(
                    text = user.about,
                    color = Color.White,
                    fontSize = 20.sp
                )
            }
            user.hobbies?.let { ChipGroup(hobbies = it) }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun Chip(
    name: String = "Chip",
    isSelected: Boolean = false,
    onSelectionChanged: (String) -> Unit = {},
) {
    Surface(
        modifier = Modifier.padding(4.dp),
        elevation = 8.dp,
        shape = MaterialTheme.shapes.medium,
        color = if (isSelected) Color.LightGray else MaterialTheme.colors.primary
    ) {
        Row(modifier = Modifier
            .toggleable(
                value = isSelected,
                onValueChange = {
                    onSelectionChanged(name)
                }
            )
        ) {
            Text(
                text = name,
                style = MaterialTheme.typography.body2,
                color = Color.White,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Composable
fun ChipGroup(
    hobbies: List<String>,
) {
    Column(modifier = Modifier.padding(8.dp)) {
        LazyRow {
            items(hobbies.size) {
                Chip(
                    name = hobbies[it],
                )
            }
        }
    }
}