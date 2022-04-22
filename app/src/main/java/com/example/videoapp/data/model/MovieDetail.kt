package com.example.videoapp.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class MovieDetail (
    val adult: Boolean?= null,

    @SerializedName("backdrop_path")
    val backdropPath: String?= null,

    @SerializedName("belongs_to_collection")
    val belongsToCollection: BelongsToCollection?= null,

    val budget: Long?= null,
    val genres: List<Genre>?= null,
    val homepage: String?= null,
    val id: Long?= null,

    @SerializedName("imdb_id")
    val imdbID: String?= null,

    @SerializedName("original_language")
    val originalLanguage: String?= null,

    @SerializedName("original_title")
    val originalTitle: String?= null,

    val overview: String?= null,
    val popularity: Double?= null,

    @SerializedName("poster_path")
    val posterPath: String?= null,

    @SerializedName("production_companies")
    val productionCompanies: List<ProductionCompany>?= null,

    @SerializedName("production_countries")
    val productionCountries: List<ProductionCountry>?= null,

    @SerializedName("release_date")
    val releaseDate: String?= null,

    val revenue: Long?= null,
    val runtime: Long?= null,

    @SerializedName("spoken_languages")
    val spokenLanguages: List<SpokenLanguage>?= null,

    val status: String?= null,
    val tagline: String?= null,
    val title: String?= null,
    val video: Boolean?= null,

    @SerializedName("vote_average")
    val voteAverage: Double?= null,

    @SerializedName("vote_count")
    val voteCount: Long?= null
):Parcelable

@Parcelize
data class BelongsToCollection (
    val id: Long?,
    val name: String?,

    @SerializedName("poster_path")
    val posterPath: String?,

    @SerializedName("backdrop_path")
    val backdropPath: String?
):Parcelable

@Parcelize
data class Genre (
    val id: Long?,
    val name: String?
):Parcelable

@Parcelize
data class ProductionCompany (
    val id: Long?,

    @SerializedName("logo_path")
    val logoPath: String?,

    val name: String?,

    @SerializedName("origin_country")
    val originCountry: String?
):Parcelable

@Parcelize
data class ProductionCountry (
    @SerializedName("iso_3166_1")
    val iso3166_1: String?,

    val name: String?
):Parcelable

@Parcelize
data class SpokenLanguage (
    @SerializedName("english_name")
    val englishName: String?,

    @SerializedName("iso_639_1")
    val iso639_1: String?,
    val name: String?
): Parcelable
