package dev.tsoloane.medium.geonames.model

import javax.validation.constraints.NotNull
import javax.validation.constraints.Size


data class CountryDTO(
    @get:NotNull
    @get:Size(max = 2)
    var isoAlpha2: String? = null,
    @get:NotNull
    @get:Size(max = 3)
    var isoAlpha3: String? = null,
    @get:NotNull
    var isoNumeric: Int? = null,
    var fips: Int? = null,
    @get:Size(max = 200)
    var name: String? = null,
    @get:Size(max = 200)
    var capital: String? = null,
    var area: Int? = null,
    var population: Int? = null,
    @get:Size(max = 2)
    var continent: String? = null,
    @get:Size(max = 15)
    var tld: String? = null,
    @get:Size(max = 3)
    var currencyCode: String? = null,
    @get:Size(max = 50)
    var currencyName: String? = null,
    @get:Size(max = 30)
    var dialingCode: String? = null,
    @get:Size(max = 20)
    var postalCodeFormat: String? = null,
    @get:Size(max = 100)
    var postalCodeRegex: String? = null,
    var languages: String? = null,
    var geoNameId: Int? = null,
    var neighbours: String? = null,
    var equivalentFipsCode: String? = null
)
