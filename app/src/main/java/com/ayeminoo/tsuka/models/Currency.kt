package com.ayeminoo.tsuka.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Currency(
    val currencyCode : String ,
    val amount : String
) : Parcelable{

}