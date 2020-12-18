package kg.nurik.auth.data.remote

import kg.nurik.auth.data.model.ProfileModel
import kg.nurik.auth.data.model.TokenModel
import retrofit2.Response
import retrofit2.http.*

interface RetrofitService {

    @POST("api/v1/users/auth")
    suspend fun Login(@Body dat: Map<String, String>): Response<TokenModel> //для того чтобы для 2х перемен не создовать целый класс,испол Коллекц MAP

    @GET("api/v1/users/profile")
    suspend fun loadUserProfile(): Response<ProfileModel?>


    @GET("api/v1/users/profile")
    suspend fun loadUserProfile1(@Header("token") token: String): Response<ProfileModel?>

}
