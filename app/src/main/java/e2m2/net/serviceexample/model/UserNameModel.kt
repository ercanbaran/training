package e2m2.net.serviceexample.model

import com.squareup.moshi.Json

data class UserNameModel
    (
    val title:String,
    @Json(name = "first") val firstName:String,
    val last:String
)