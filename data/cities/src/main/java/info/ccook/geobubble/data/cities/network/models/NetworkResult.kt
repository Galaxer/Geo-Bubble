package info.ccook.geobubble.data.cities.network.models

sealed class NetworkResult {

    data class Success<T>(val data: T): NetworkResult()

    data class Error(val exception: Exception) : NetworkResult()
}
