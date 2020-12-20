package kg.nurik.auth.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kg.nurik.auth.data.model.ProfileModel

@Dao
interface ProfileDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE) //если вдруг айдишки совпадут,он его заменит
    fun saveProfile(user: ProfileModel)

    @Query("SELECT * FROM ProfileModel")
    fun getProfile(): LiveData<ProfileModel>
}