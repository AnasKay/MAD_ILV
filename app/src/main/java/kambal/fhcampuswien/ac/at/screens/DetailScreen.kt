package kambal.fhcampuswien.ac.at

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.testapp.models.Movie
import com.example.testapp.models.getMovies


@Composable
fun DetailHeader(movieId: String?, navController: NavController){
    
    Scaffold(
        topBar = {
            TopAppBar(backgroundColor = Color.Cyan){
                Row{
                    Icon(imageVector = Icons.Default.ArrowBack, 
                        contentDescription = "Arrow Back",
                        modifier = Modifier.clickable { 
                            navController.popBackStack()
                        })
                    
                    Spacer(modifier = Modifier.width(20.dp))
                    val movie = filterMovie(movieId)
                    Text(text = "${movie.title}")
                    
                }
            }
        }
    ) {
        detailContent(movieId)
    }
}

@Composable
fun detailContent(movieId: String?) {
    val movie = filterMovie(movieId)
    //Text(text = "Hello $movieId")

    Surface(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()) {
        Column {
            MovieRow(movie = movie)
            Spacer(modifier = Modifier.height(10.dp))
            Divider()
            Text(text = "${movie.title}")
            movieImages(movie)
        }

    }

}

@Composable
fun movieImages(movie: Movie = getMovies()[0]){
    LazyRow{
        items(movie.images){ image ->

            Card(
                modifier = Modifier.padding(12.dp).size(240.dp),
                elevation = 4.dp
            ){
                Image(
                    painter = rememberImagePainter(data = image),
                    contentDescription = "movieImage")
            }

        }
    }
}

fun filterMovie(movieId: String?): Movie{
    return getMovies().filter { movie -> movie.id == movieId }[0]
}