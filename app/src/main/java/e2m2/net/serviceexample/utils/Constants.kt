package e2m2.net.userphonebook.utils

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

val moshi = Moshi.Builder()
    // ... add your own JsonAdapters and factories ...
    .add(KotlinJsonAdapterFactory())
    .build()



fun sumNumbers( a:Int?, b:Int? = 0, c:Int?= 0 )
{

}