package rs.raf.projekat1.marko_vesovic_rn2417.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class Patient (
    val id : Int,
    var name : String,
    var lastName : String,
    var symptoms : String,
    val picture : String = "https://avatarfiles.alphacoders.com/128/128984.png",
    val admissionDate : Date,
    var hospitalizationDate: Date?,
    var releasingDate : Date?
) : Parcelable