package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArtSpaceTheme {
                ArtSpaceApp()
            }
        }
    }
}

@Composable
fun ArtSpaceApp() {
    var currentImage by remember { mutableIntStateOf(0) }
    val onPreviousButtonClick = { currentImage = (currentImage + 8 - 1) % 8 }
    val onNextButtonClick = { currentImage = (currentImage + 1) % 8 }

    val imageResource = when (currentImage) {
        0 -> R.drawable.image1
        1 -> R.drawable.image2
        2 -> R.drawable.image3
        3 -> R.drawable.image4
        4 -> R.drawable.image5
        5 -> R.drawable.image6
        6 -> R.drawable.image7
        7 -> R.drawable.image8
        else -> R.drawable.image1
    }

    val artName = when (currentImage) {
        0 -> R.string.art_name1
        1 -> R.string.art_name2
        2 -> R.string.art_name3
        3 -> R.string.art_name4
        4 -> R.string.art_name5
        5 -> R.string.art_name6
        6 -> R.string.art_name7
        7 -> R.string.art_name8
        else -> R.string.art_name1
    }

    val artistName = when (currentImage) {
        0 -> R.string.artist_name1
        1 -> R.string.artist_name2
        2 -> R.string.artist_name3
        3 -> R.string.artist_name4
        4 -> R.string.artist_name5
        5 -> R.string.artist_name6
        6 -> R.string.artist_name7
        7 -> R.string.artist_name8
        else -> R.string.artist_name1
    }

    ArtSpaceLayout(
        imageResource = imageResource,
        artName = artName,
        artistName = artistName,
        onPreviousButtonClick = onPreviousButtonClick,
        onNextButtonClick = onNextButtonClick
    )
}

@Composable
fun ArtSpaceLayout(
    imageResource: Int,
    artName: Int,
    artistName: Int,
    onPreviousButtonClick: () -> Unit = {},
    onNextButtonClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.Center,
    ) {
        Surface(
            modifier = Modifier
                .widthIn(max = 400.dp, min = 200.dp)
                .height(450.dp)
                .align(Alignment.CenterHorizontally),
            shadowElevation = 8.dp
        ) {
            Image(
                painter = painterResource(id = imageResource),
                contentDescription = null,
                modifier = Modifier
                    .padding(32.dp),
                contentScale = ContentScale.Crop
            )
        }
        Spacer(
            modifier = Modifier
                .height(32.dp)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = colorResource(id = R.color.purple_200)),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = artName),
                fontSize = 18.sp,
                modifier = Modifier
                    .padding(top = 8.dp),
            )
            Text(
                text = stringResource(id = artistName),
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(bottom = 8.dp),
            )
        }
        Spacer(
            modifier = Modifier
                .height(32.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = onPreviousButtonClick,
                colors = ButtonDefaults.buttonColors(
                    colorResource(id = R.color.purple_700)
                )
            ) {
                Text(
                    text = stringResource(id = R.string.previous)
                )
            }
            Button(
                onClick = onNextButtonClick,
                colors = ButtonDefaults.buttonColors(
                    colorResource(id = R.color.purple_700)
                )
            ) {
                Text(
                    text = stringResource(id = R.string.next)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArtSpaceAppPreview() {
    ArtSpaceTheme {
        ArtSpaceApp()
    }
}