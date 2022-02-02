package com.lando.notesappkotlin.data.model.user.create_user

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("nombre")
    var userName: String,
    @SerializedName("correo")
    var userMail: String,
    @SerializedName("pass")
    var userPassword: String
)