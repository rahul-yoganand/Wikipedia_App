import com.google.gson.annotations.SerializedName

data class Json4Kotlin_Base(

    @SerializedName("batchcomplete") val batchcomplete: Boolean,
    @SerializedName("query") val query: Query
)