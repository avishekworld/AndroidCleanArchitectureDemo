package com.example.data.api

import com.example.data.model.OnBoardData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

interface OnBoardDataApi {
    suspend fun getData(id: Int): OnBoardData
}

class OnBoardDataApiImpl : OnBoardDataApi {

    // mock local data
    private val localData = arrayOf(
        OnBoardData(
            "Description1: " +
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut risus enim, ornare vitae interdum ut, laoreet sit amet odio. Quisque fringilla, felis vel venenatis ultricies, nisi eros dapibus dolor, in placerat neque ipsum vel diam. In feugiat vitae purus eget venenatis. Morbi id tempus eros, mattis dapibus nunc. Integer rutrum interdum eros sit amet ultricies. Aliquam pretium fringilla erat, non pulvinar risus placerat non. Mauris est eros, viverra eget erat non, dictum pharetra tellus. Sed volutpat lobortis ipsum at convallis. Proin porttitor dignissim ligula, in tincidunt libero pretium eu. Suspendisse potenti. Integer urna neque, dignissim vel risus sed, commodo dignissim nisl. Integer in bibendum ligula, vel eleifend metus. Sed ante lacus, pharetra convallis nisi id, feugiat consequat massa. Aenean ultricies erat et purus interdum elementum.",
            "Next"
        ),
        OnBoardData(
            "Description2: " +
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut risus enim, ornare vitae interdum ut, laoreet sit amet odio. Quisque fringilla, felis vel venenatis ultricies, nisi eros dapibus dolor, in placerat neque ipsum vel diam. In feugiat vitae purus eget venenatis. Morbi id tempus eros, mattis dapibus nunc. Integer rutrum interdum eros sit amet ultricies. Aliquam pretium fringilla erat, non pulvinar risus placerat non. Mauris est eros, viverra eget erat non, dictum pharetra tellus. Sed volutpat lobortis ipsum at convallis. Proin porttitor dignissim ligula, in tincidunt libero pretium eu. Suspendisse potenti. Integer urna neque, dignissim vel risus sed, commodo dignissim nisl. Integer in bibendum ligula, vel eleifend metus. Sed ante lacus, pharetra convallis nisi id, feugiat consequat massa. Aenean ultricies erat et purus interdum elementum.",
            "Next"
        ),
        OnBoardData(
            "Description3: " +
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut risus enim, ornare vitae interdum ut, laoreet sit amet odio. Quisque fringilla, felis vel venenatis ultricies, nisi eros dapibus dolor, in placerat neque ipsum vel diam. In feugiat vitae purus eget venenatis. Morbi id tempus eros, mattis dapibus nunc. Integer rutrum interdum eros sit amet ultricies. Aliquam pretium fringilla erat, non pulvinar risus placerat non. Mauris est eros, viverra eget erat non, dictum pharetra tellus. Sed volutpat lobortis ipsum at convallis. Proin porttitor dignissim ligula, in tincidunt libero pretium eu. Suspendisse potenti. Integer urna neque, dignissim vel risus sed, commodo dignissim nisl. Integer in bibendum ligula, vel eleifend metus. Sed ante lacus, pharetra convallis nisi id, feugiat consequat massa. Aenean ultricies erat et purus interdum elementum.",
            "Next"
        ),
        OnBoardData(
            "Description4: " +
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut risus enim, ornare vitae interdum ut, laoreet sit amet odio. Quisque fringilla, felis vel venenatis ultricies, nisi eros dapibus dolor, in placerat neque ipsum vel diam. In feugiat vitae purus eget venenatis. Morbi id tempus eros, mattis dapibus nunc. Integer rutrum interdum eros sit amet ultricies. Aliquam pretium fringilla erat, non pulvinar risus placerat non. Mauris est eros, viverra eget erat non, dictum pharetra tellus. Sed volutpat lobortis ipsum at convallis. Proin porttitor dignissim ligula, in tincidunt libero pretium eu. Suspendisse potenti. Integer urna neque, dignissim vel risus sed, commodo dignissim nisl. Integer in bibendum ligula, vel eleifend metus. Sed ante lacus, pharetra convallis nisi id, feugiat consequat massa. Aenean ultricies erat et purus interdum elementum.",
            "Next"
        )

    )

    // TODO get data from cloud api
    override suspend fun getData(id: Int): OnBoardData = withContext(Dispatchers.IO) {
        delay(1000)
        when (id) {
            in localData.indices -> { localData[id] }
            else -> {
                OnBoardData("", "")
            }
        }
    }
}
