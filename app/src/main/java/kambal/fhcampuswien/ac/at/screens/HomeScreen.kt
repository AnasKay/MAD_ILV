package kambal.fhcampuswien.ac.at

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.example.testapp.models.Movie
import com.example.testapp.models.getMovies


@Composable
fun MainContent(movieList: List<Movie>, navController: NavHostController){
    LazyColumn{
        items(movieList) {
                movie -> MovieRow(movie) { movieId ->
                Log.d("testlog", "Hello Item clicked ${movie.title}")
                navController.navigate("Detailscreen/${movie.id}")
                }
        }
    }
}


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MovieRow(movie: Movie, myFunction: (String) -> Unit = {} ) {
    Card(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .clickable { myFunction(movie.id) },
        //.height(130.dp),
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
        elevation = 6.dp
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Surface(
                modifier = Modifier
                    .size(130.dp)
                    .padding(12.dp),
                elevation = 6.dp
            ) {
                //Icon(imageVector = Icons.Default.AccountBox, contentDescription = "movie pic")
                Image(
                    painter = rememberImagePainter(
                        data = movie.images[0],
                        builder = {
                            transformations(CircleCropTransformation())
                        }
                        ),
                    contentDescription = "movie poster",
                    //modifier = Modifier.size(128.dp)
                )
            }
            Column() {
                /*Text(fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    text = "${movie.title}")*/
                Text(style = MaterialTheme.typography.body2, text = movie.title)
                Text(text ="Director: ${movie.director}")
                Text(text ="Released: ${movie.year}")

                var _menuIcon by remember {
                    mutableStateOf(Icons.Default.KeyboardArrowDown)
                }


                AnimatedVisibility(visible= _menuIcon== Icons.Default.KeyboardArrowUp) {
                    Column() {
                        Text(text ="Plot: ${movie.plot}")
                        Text(text ="Director: ${movie.genre}")
                        Text(text ="Released: ${movie.actors}")
                        Text(text ="Released: ${movie.rating}")
                    }
                }


                IconButton(onClick = {
                    _menuIcon = if(_menuIcon== Icons.Default.KeyboardArrowUp){
                        Icons.Default.KeyboardArrowDown
                    } else{
                        Icons.Default.KeyboardArrowUp
                    }
                }) {
                    Icon(imageVector = _menuIcon , contentDescription = "more")
                }
            }
        }
    }
}

@Composable
fun HeaderMenu(navController: NavHostController) {

    var showMenu by remember {
        mutableStateOf(false)
    }

    Scaffold(
        topBar = {
            TopAppBar( title = { Text(text = "Movies") },
                actions = {
                    IconButton(onClick = { showMenu = !showMenu }) {
                        Icon(imageVector = Icons.Default.MoreVert, contentDescription = "More")
                    }
                    DropdownMenu(expanded = showMenu,
                        onDismissRequest = { showMenu=false },
                        modifier = Modifier.width(150.dp)
                    ) {
                        DropdownMenuItem(onClick = { /*TODO*/ }) {
                            Row(modifier = Modifier.padding(4.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ){
                                Icon(imageVector = Icons.Default.Favorite, contentDescription = "Favorites")
                                Spacer(modifier = Modifier.width(10.dp))
                                Text(text = "Favorites")
                            }
                        }
                    }
                }
            )
        }
    ) {
        MainContent(movieList = getMovies(), navController)
    }
}