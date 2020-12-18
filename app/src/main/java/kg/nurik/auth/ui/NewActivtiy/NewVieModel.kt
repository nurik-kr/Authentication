package kg.nurik.auth.ui.NewActivtiy

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kg.nurik.auth.data.model.ProfileModel
import kg.nurik.auth.data.repository.RetrofitRepositories
import kotlinx.coroutines.launch

class NewVieModel(private val repository: RetrofitRepositories) : ViewModel() {

    val data = MutableLiveData<ProfileModel>()
    val error = MutableLiveData<String>()

    init {
        loadUser()
    }

    private fun loadUser() {
        viewModelScope.launch {
            runCatching {
                val result = repository.getProfile()
                if (result.isSuccessful)
                    data.postValue(result.body())
                else error.postValue(result.message())
            }.onFailure {
                error.postValue(it.localizedMessage)
            }
        }
    }


}