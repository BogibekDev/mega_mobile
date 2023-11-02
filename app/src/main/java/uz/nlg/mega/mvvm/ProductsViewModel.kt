package uz.nlg.mega.mvvm

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import uz.nlg.mega.data.repository.ProductsRepository
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    repository: ProductsRepository
) : ViewModel() {

    private val _loading = mutableStateOf(false)
    val isLoading = _loading

    private val _error = mutableStateOf<String?>(null)
    val errorMessage = _error

    private val _goLogin = mutableStateOf(false)
    val isGoLogin = _goLogin


}