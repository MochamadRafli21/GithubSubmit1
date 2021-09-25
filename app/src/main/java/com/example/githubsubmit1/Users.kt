package com.example.githubsubmit1

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Suppress("DEPRECATED_ANNOTATION")
@Parcelize
data class Users(
        var Username: String? = "",
        var Name: String? = "",
        var City: String? = "",
        var Company: String? = "",
        var Following: Int? = 0,
        var Follower: Int? = 0,
        var Repository: Int? = 0,
        var profilePic: Int = 0
):Parcelable
