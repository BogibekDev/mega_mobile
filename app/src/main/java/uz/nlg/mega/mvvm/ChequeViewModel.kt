package uz.nlg.mega.mvvm

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import uz.nlg.mega.data.repository.ChequeRepository
import uz.nlg.mega.model.Cheque
import uz.nlg.mega.model.ErrorResponse
import uz.nlg.mega.model.Pagination
import uz.nlg.mega.utils.printError
import javax.inject.Inject

@HiltViewModel
class ChequeViewModel @Inject constructor(
    private val repository: ChequeRepository
) : ViewModel() {

    private val _loading = mutableStateOf(false)
    val isLoading = _loading

    private val _data = mutableStateOf<Pagination<Cheque>?>(null)
    val data = _data


    private val _error = mutableStateOf<String?>(null)
    val errorMessage = _error

    fun getCheques(status: String = "", page: Int = 1, pageSize: Int = 15) = viewModelScope.launch {
        _loading.value = true

        try {
            val response = repository.getCheques(status, page, pageSize)

            if (response.isSuccessful) {
                data.value=response.body()
            } else {
                val errorResponse = Gson().fromJson(response.errorBody()!!.string(), ErrorResponse::class.java)
                _error.value = errorResponse.detail ?: "No Connection"
            }
            _loading.value = false
        } catch (e: HttpException) {
            _loading.value = false
            printError(e)
        } catch (e: Exception) {
            _loading.value = false
            printError(e)
        }

    }


}