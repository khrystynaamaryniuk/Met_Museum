import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.metmuseum2.model.Artwork
import com.example.metmuseum2.service.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.net.URL

class MainViewModel : ViewModel() {

    private val _artworks = mutableStateListOf<Artwork>()
    val artworks: List<Artwork> = _artworks
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> get() = _isLoading
    fun loadArtworks() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                Log.d("MainViewModel", "Making request to fetch object IDs")
                val objectIDsResponse = RetrofitClient.api.getObjectIDs()
                Log.d("MainViewModel", "Response object IDs: $objectIDsResponse")
                if (objectIDsResponse.objectIDs.isEmpty()) {
                    Log.e("MainViewModel", "No object IDs found! Response: $objectIDsResponse")
                    return@launch
                }

                val randomIDs = objectIDsResponse.objectIDs.shuffled().take(50)

                Log.d("MainViewModel", "Random IDs: $randomIDs")

                // Fetch details for each random ID
                randomIDs.forEach { id ->
                    try {

                        val detail = RetrofitClient.api.getObjectDetails(id)

                        // If the image URL is valid, check the URL before adding the artwork
                        if (detail.primaryImage.isNotBlank()) {
                            val imageUrl = detail.primaryImage
                                // Directly add to artworks if imageUrl is not blank
                                _artworks.add(
                                    Artwork(
                                        title = detail.title.takeIf { it.isNotBlank() } ?: "Untitled",
                                        artist = detail.artistDisplayName.takeIf { it.isNotBlank() } ?: "Unknown",
                                        imageUrl = imageUrl,
                                        year =detail.objectDate.takeIf { it.isNotBlank() } ?: "Unknown",
                                    )
                                )
                                _isLoading.value = false

                        }
                    } catch (e: Exception) {
                        Log.e("MainViewModel", "Error loading detail for ID $id", e)
                    }
                }
            } catch (e: Exception) {
                Log.e("MainViewModel", "Error loading object IDs: ${e.localizedMessage}", e)
            }
        }
    }
}
