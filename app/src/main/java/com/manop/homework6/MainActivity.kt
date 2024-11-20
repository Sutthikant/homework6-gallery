package com.manop.homework6

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.manop.homework6.ui.theme.Homework6Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Homework6Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Gallery(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun ArtworkWall(
    modifier: Modifier = Modifier,
    picturePic: Int,
    pictureTitle: Int
) {
    Column(
        modifier = modifier
            .padding(16.dp)
            .border(0.5.dp, color = Color.Black)
            .fillMaxWidth(0.8f)
            .aspectRatio(1f)
            .border(1.dp, Color.Gray),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = modifier.padding(20.dp),
            painter = painterResource(picturePic),
            contentDescription = stringResource(pictureTitle)
        )
    }
}

@Composable
fun ArtworkDescriptor(
    modifier: Modifier = Modifier,
    pictureTitle: Int,
    pictureArtist: Int,
    ) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(pictureTitle),
            modifier = modifier
                .padding(4.dp)
                .wrapContentSize(),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = stringResource(pictureArtist),
            modifier = modifier
                .padding(4.dp)
                .wrapContentSize(),
            fontSize = 16.sp,
        )
    }
}

@Composable
fun DisplayController(
    modifier: Modifier = Modifier,
    onPreviousClick: () -> Unit,
    onNextClick: () -> Unit
    ) {
    Row(
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Bottom
    ) {
        Button(
            onClick = onPreviousClick,
            modifier = Modifier.padding(8.dp)
        ) {
            Text("Previous")
        }

        Button(
            onClick = onNextClick,
            modifier = Modifier.padding(8.dp)
        ) {
            Text("Next")
        }
    }
}

@Composable
fun Gallery(modifier: Modifier = Modifier) {
    var pictureNum: Int by remember { mutableStateOf(1) }
    var picturePic = when (pictureNum) {
        1 -> R.drawable.starry_night
        2 -> R.drawable.girl_with_a_pearl_earrings
        3 -> R.drawable.mona_lisa
        4 -> R.drawable.the_birth_of_venus
        else -> R.drawable.summer_evening
    }
    var pictureTitle = when (pictureNum) {
        1 -> R.string.picName1
        2 -> R.string.picName2
        3 -> R.string.picName3
        4 -> R.string.picName4
        else -> R.string.picName5
    }
    var pictureArtist = when (pictureNum) {
        1 -> R.string.picArtist1
        2 -> R.string.picArtist2
        3 -> R.string.picArtist3
        4 -> R.string.picArtist4
        else -> R.string.picArtist5
    }
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ArtworkWall(
            modifier = modifier,
            picturePic = picturePic,
            pictureTitle = pictureTitle
        )
        Spacer(modifier = Modifier.height(16.dp))
        ArtworkDescriptor(
            modifier = modifier,
            pictureTitle = pictureTitle,
            pictureArtist = pictureArtist
        )
        Spacer(modifier = Modifier.height(16.dp))
        DisplayController(
            onPreviousClick = { pictureNum = if (pictureNum > 1) pictureNum - 1 else 5 },
            onNextClick = { pictureNum = if (pictureNum < 5) pictureNum + 1 else 1 }
        )
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GalleryPreview() {
    Homework6Theme {
        Gallery()
    }
}