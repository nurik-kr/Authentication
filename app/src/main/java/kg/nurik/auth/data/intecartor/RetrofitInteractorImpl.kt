package kg.nurik.auth.data.intecartor

import kg.nurik.auth.data.model.ProfileModel
import kg.nurik.auth.data.model.TokenModel
import kg.nurik.auth.data.remote.RetrofitService
import retrofit2.Response

interface RetrofitInteractor {
    suspend fun login(email: String, password: String): Response<TokenModel>
    suspend fun getProfile(): Response<ProfileModel?>
}

class RetrofitInteractorImpl(private val service: RetrofitService) : RetrofitInteractor {
    override suspend fun login(email: String, password: String): Response<TokenModel> {
        val map = mapOf("email" to email, "password" to password) //коллекция МAP
        return service.Login(map)
    }

    override suspend fun getProfile(): Response<ProfileModel?> {
        return service.loadUserProfile()
        // без интерсептора с ручками Header return service.loadUserProfile(PrefsHelper.getToken())
    }
}