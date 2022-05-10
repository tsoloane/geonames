package dev.tsoloane.medium.geonames.model

import javax.validation.constraints.NotNull
import javax.validation.constraints.Size


data class AlternateNameDTO(
    var id: Int? = null,
    @get:Size(max = 7)
    var isoLanguage: String? = null,
    @get:NotNull
    @get:Size(max = 400)
    var alternateName: String? = null,
    var preferredName: Boolean? = null,
    var shortName: Boolean? = null,
    var colloquial: Boolean? = null,
    var historic: Boolean? = null,
    @get:Size(max = 100)
    var fromDate: String? = null,
    @get:Size(max = 100)
    var toDate: String? = null,
    @get:NotNull
    var geoName: Int? = null
)
