package kg.nurik.auth.data.remote

class AuthServiceHolder { // перемычка

    private var apiService: RetrofitService? = null

    fun getApiService(): RetrofitService? {
        return apiService
    }

    fun setApiService(apiService: RetrofitService?) {
        this.apiService = apiService
    }
}