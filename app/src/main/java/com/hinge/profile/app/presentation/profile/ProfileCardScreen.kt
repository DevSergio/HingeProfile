package com.hinge.profile.app.presentation.profile

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ThumbDownAlt
import androidx.compose.material.icons.filled.ThumbUpAlt
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.loader.content.Loader
import com.hinge.profile.app.domain.model.User
import com.hinge.profile.app.utils.moveTo
import com.hinge.profile.app.utils.visible

@ExperimentalMaterialApi
@Composable
fun ProfileCardScreen(
    viewModel: ProfileCardViewModel,
    modifier: Modifier = Modifier,
    thresholdConfig: (Float, Float) -> ThresholdConfig = { _, _ -> FractionalThreshold(0.2f) },
    enableButtons: Boolean = false,
    onSwipeLeft: (item: User) -> Unit = {},
    onSwipeRight: (item: User) -> Unit = {},
    onEmptyStack: (lastItem: User) -> Unit = {}
) {
    val users = viewModel.users.collectAsState().value

    val loading = viewModel.loading.value

    var i by remember { mutableStateOf(5 ) }

    if (i == -1) {
        onEmptyStack(users!!.last())
    }

    val cardStackController = rememberCardStackController()
    cardStackController.onSwipeLeft = {
        onSwipeLeft(users!![i])
        i--
    }
    cardStackController.onSwipeRight = {
        onSwipeRight(users!![i])
        i--
    }

    ConstraintLayout(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp)
            .visible(!loading)
    ) {
        val (buttons, stack) = createRefs()

        if (enableButtons) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(buttons) {
                        bottom.linkTo(parent.bottom)
                        top.linkTo(stack.bottom)
                    },
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                FloatingActionButton(
                    onClick = { if (i >= 0) cardStackController.swipeLeft() },
                    backgroundColor = Color.White,
                    elevation = FloatingActionButtonDefaults.elevation(5.dp)
                ) {
                    Icon(Icons.Filled.ThumbDownAlt, contentDescription = "", tint = Color.Red)
                }
                Spacer(modifier = Modifier.width(70.dp))
                FloatingActionButton(
                    onClick = { if (i >= 0) cardStackController.swipeRight() },
                    backgroundColor = Color.White,
                    elevation = FloatingActionButtonDefaults.elevation(5.dp)
                ) {
                    Icon(Icons.Filled.ThumbUpAlt, contentDescription = "", tint = Color.Green)
                }
            }
        }

        Box(modifier = Modifier
            .constrainAs(stack) {
                top.linkTo(parent.top)
            }
            .draggableStack(
                controller = cardStackController,
                thresholdConfig = thresholdConfig
            )
            .fillMaxHeight(0.8f)
        ) {
            users?.asReversed()?.forEachIndexed { index, user ->
                Card(
                    modifier = Modifier
                        .moveTo(
                            x = if (index == i) cardStackController.offsetX.value else 0f,
                            y = if (index == i) cardStackController.offsetY.value else 0f
                        )
                        .visible(visible = index == i || index == i - 1)
                        .graphicsLayer(
                            rotationZ = if (index == i) cardStackController.rotation.value else 0f,
                            scaleX = if (index < i) cardStackController.scale.value else 1f,
                            scaleY = if (index < i) cardStackController.scale.value else 1f
                        )
                        .shadow(4.dp, RoundedCornerShape(10.dp)),
                    user
                )
            }
        }
    }
}
