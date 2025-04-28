package com.example.metmuseum2

import MainViewModel
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.metmuseum2.model.Artwork
import com.example.metmuseum2.ui.theme.AppTheme
import com.example.metmuseum2.ui.theme.PrimaryColor
import com.example.metmuseum2.ui.theme.AccentColor
import com.example.metmuseum2.ui.theme.BackgroundColor
import com.example.metmuseum2.ui.theme.SurfaceColor
import com.example.metmuseum2.ui.theme.body1
import com.example.metmuseum2.ui.theme.body2
import com.example.metmuseum2.ui.theme.button
import com.example.metmuseum2.ui.theme.h1
import com.example.metmuseum2.ui.theme.h2
import com.example.metmuseum2.ui.theme.h3

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                Box(
                    modifier = Modifier
                        .padding(16.dp)
                        .border(
                            width = 4.dp,
                            color = SurfaceColor,
                            shape = MaterialTheme.shapes.medium
                        )
                        .padding(16.dp) // Padding inside the border
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(26.dp)
                            .verticalScroll(rememberScrollState())
                    ) {
                        // Welcome header text
                        Text(
                            text = "Welcome to the Met!",
                            style = MaterialTheme.typography.headlineLarge.copy(
                                color = PrimaryColor,
                                fontWeight = FontWeight.Bold
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 22.dp),
                            textAlign = TextAlign.Center
                        )
                        Text(
                            text = "Step into a World of Art and History",
                            style = MaterialTheme.typography.headlineLarge.copy(
                                color = PrimaryColor,
                                fontWeight = FontWeight.Bold
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 32.dp),
                            textAlign = TextAlign.Center
                        )
                        val isLoading by viewModel.isLoading.collectAsState()

                        // Button to load artworks with improved styling
                        Button(
                            onClick = { viewModel.loadArtworks() },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(75.dp)
                                .padding(bottom = 24.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = PrimaryColor),
                            shape = MaterialTheme.shapes.medium,
                            elevation = ButtonDefaults.buttonElevation(10.dp)
                        ) {
                            Text(text = "Bring the art to life", style = button)
                        }

                        if (isLoading) {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                CircularProgressIndicator(color = PrimaryColor)
                            }
                        } else {
                            viewModel.artworks.forEach { artwork ->
                                ArtworkItem(artwork)
                                Spacer(modifier = Modifier.height(12.dp))
                            }

                        }
                    }
                }
            }

        }
    }
}

@Composable
fun ArtworkItem(artwork: Artwork) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .shadow(8.dp, shape = MaterialTheme.shapes.medium),
        shape = MaterialTheme.shapes.medium,

        colors = CardDefaults.cardColors(
            containerColor = SurfaceColor
        )

    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Artwork Image
            Image(
                painter = rememberAsyncImagePainter(artwork.imageUrl),
                contentDescription = artwork.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(bottom = 8.dp),
                contentScale = ContentScale.Fit
            )

            // Artwork Title
            Text(
                text = artwork.title,
                style = h2,
                modifier = Modifier.padding(bottom = 4.dp)
            )

            // Artist Name
            Text(
                text = "Artist: ${artwork.artist}",
                style = h3,
                color = AccentColor
            )
            Text(
                text = "Date: ${artwork.year}",
                style = h3,
                color = AccentColor
            )
        }
    }
}
