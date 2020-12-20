package kg.nurik.auth

import android.app.Application
import kg.nurik.auth.data.local.AppDatabase
import kg.nurik.auth.data.local.PrefsHelper
import kg.nurik.auth.di.appModules
import org.koin.android.ext.android.startKoin

class AuthApp : Application() {

    private var db: AppDatabase? = null

    override fun onCreate() {
        super.onCreate()
        startKoin(this, appModules)
        PrefsHelper.init(this) //заиниц на старте прилы
    }
}