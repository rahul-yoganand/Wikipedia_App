import com.example.wikipediaapp.model.Pages
import com.google.gson.annotations.SerializedName

data class Query(

    @SerializedName("pages") val pages: List<Pages>
)