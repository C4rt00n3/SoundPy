package com.mupy.soundpy.models

data class Artist(
    val artistName: String, val uriThumb: String, val url: String, val byteImage: ByteArray? = null
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Artist

        if (artistName != other.artistName) return false
        if (uriThumb != other.uriThumb) return false
        if (url != other.url) return false
        if (byteImage != null) {
            if (other.byteImage == null) return false
            if (!byteImage.contentEquals(other.byteImage)) return false
        } else if (other.byteImage != null) return false

        return true
    }

    override fun hashCode(): Int {
        var result = artistName.hashCode()
        result = 31 * result + uriThumb.hashCode()
        result = 31 * result + url.hashCode()
        result = 31 * result + (byteImage?.contentHashCode() ?: 0)
        return result
    }
}