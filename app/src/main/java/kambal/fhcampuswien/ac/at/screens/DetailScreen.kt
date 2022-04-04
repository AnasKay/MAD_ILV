package kambal.fhcampuswien.ac.at

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
    MovieRow(movie = movie)
    Spacer(modifier = Modifier.height(10.dp))
    Divider()
    Text(text = "testText")
}

fun filterMovie(movieId: String?): Movie{
    return getMovies().filter { movie -> movie.id == movieId }[0]
}