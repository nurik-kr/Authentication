package kg.nurik.auth.data.repository

import androidx.lifecycle.LiveData
import kg.nurik.auth.data.intecartor.RetrofitInteractor
import kg.nurik.auth.data.local.PrefsHelper
import kg.nurik.auth.data.local.ProfileDao
import kg.nurik.auth.data.model.ProfileModel
import kg.nurik.auth.data.model.TokenModel
import retrofit2.Response

interface RetrofitRepositories {
    suspend fun login(email: String, password: String): Response<TokenModel>
    suspend fun loadProfile()
    fun getProfile(): LiveData<ProfileModel>
}

class RetrofitRepositoriesImpl(
    private val network: RetrofitInteractor,
    private val profileDao: ProfileDao
) : RetrofitRepositories {
    override suspend fun login(email: String, password: String): Response<TokenModel> {
        val result = network.login(email = email, password = password)
        result.body()?.token?.let { PrefsHelper.saveToken(it) } //save token , pered otpavkoi

        return result
    }

    override suspend fun loadProfile() {
        val result = network.getProfile()
        result.body()?.let { profileDao.saveProfile(it) } //save in bd
    }

    override fun getProfile() = profileDao.getProfile() //берем из базы
}
