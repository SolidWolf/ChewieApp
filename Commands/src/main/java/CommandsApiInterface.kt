import models.CommandsApiModel
import models.CommandsApiModelItem
import models.CommandsModel
import retrofit2.Call
import retrofit2.http.GET

interface CommandsApiInterface {
    @GET("api/commandlist")
    fun getCommandsList(): Call<List<CommandsApiModelItem>>
}