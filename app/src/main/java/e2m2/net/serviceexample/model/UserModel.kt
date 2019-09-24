package e2m2.net.serviceexample.model

import com.squareup.moshi.Json

data class UserModel(
    @Json(name = "gender") var userGender:String,
    @Json(name = "email") var userEmail:String,
    var name:UserNameModel,
    var picture:PictureModel,
    var phone:String
)