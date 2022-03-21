package kambal.fhcampuswien.ac.at

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.testapp.models.getMovies


@Composable
fun DetailHeader(movieId: String?){

    var showMenu by remember {
        mutableStateOf(false)
    }

    Scaffold(
        topBar = {
            TopAppBar( title = { Text(text = "Detailscreen") },
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
        detailContent(movieId)
    }
}

@Composable
fun detailContent(movieId: String?) {
    Text(text = "Hello $movieId")
}