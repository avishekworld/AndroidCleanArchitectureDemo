package com.example.data.model

import android.graphics.Bitmap

data class OnBoardData(val description : String,
                       val actionText : String,
                       val image : Bitmap? = null)
