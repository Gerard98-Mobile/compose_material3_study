package com.example.compose_material3_test.components.image

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size

@Composable
fun UrlImage(
    url: String,
    modifier: Modifier = Modifier
) = UrlImage(
    uri = Uri.parse(url),
    modifier = modifier
)

@Composable
fun UrlImage(
    uri: Uri,
    modifier: Modifier = Modifier,
    contentDescription: String? = null
) {
    AsyncImage(model = ImageRequest.Builder(LocalContext.current)
        .data(uri)
        .crossfade(true)
        .build(),
        contentDescription = contentDescription,
        modifier = modifier
    )
//    val context = LocalContext.current
//    val imageLoader = ImageLoader.Builder(context)
//        .build()
//    Image(
//        painter = rememberAsyncImagePainter(
//            ImageRequest.Builder(context).data(uri).apply(block = {
//                size(Size.ORIGINAL)
//            }).build(), imageLoader = imageLoader
//        ),
//        contentDescription = null,
//        modifier = modifier,
//    )
}