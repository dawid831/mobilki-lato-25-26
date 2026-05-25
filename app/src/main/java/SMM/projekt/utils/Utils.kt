package SMM.projekt.utils

import android.content.Context

fun getDrawableIdByName(context: Context, name: String): Int {
    return context.resources.getIdentifier(
        name,
        "drawable",
        context.packageName
    )
}

fun getRawIdByName(context: Context, name: String): Int {
    return context.resources.getIdentifier(
        name,
        "raw",
        context.packageName
    )
}