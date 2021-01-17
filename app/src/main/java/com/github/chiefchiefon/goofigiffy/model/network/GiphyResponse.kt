package com.github.chiefchiefon.goofigiffy.model.network

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
data class GiphyResponse(val data: List<Data>)

/**
 * kotlinx documentation:
 * Instructs the Kotlin compiler to generate `writeToParcel()`,
 * `describeContents()` [android.os.Parcelable] methods,
 * as well as a `CREATOR` factory class automatically.
 *
 * The annotation is applicable only to classes that implements [android.os.Parcelable] (directly or indirectly).
 * Note that only the primary constructor properties will be serialized.
 */
@Parcelize
@Serializable
data class Data(
    val id: String,
    val images: Image,
    val title: String
): Parcelable
/**
 * JAVA documentation:
 * Interface for classes whose instances can be written to
 * and restored from a {@link Parcel}.  Classes implementing the Parcelable
 * interface must also have a non-null static field called <code>CREATOR</code>
 * of a type that implements the {@link Parcelable.Creator} interface.
 */

@Parcelize
@Serializable
data class Image(val downsized_medium: ImageProperties): Parcelable

@Parcelize
@Serializable
data class ImageProperties(val url:String): Parcelable