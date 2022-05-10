package dev.tsoloane.medium.geonames.model

import com.fasterxml.jackson.annotation.JsonFormat
import io.swagger.v3.oas.annotations.media.Schema
import java.math.BigDecimal
import javax.validation.constraints.Digits
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size


data class PostalCodeDTO(
    @get:NotNull
    @get:Size(max = 20)
    var postalCode: String? = null,
    @get:NotNull
    @get:Size(max = 200)
    var placeName: String? = null,
    @get:Size(max = 100)
    var state: String? = null,
    @get:Size(max = 20)
    var stateCode: String? = null,
    @get:Size(max = 100)
    var county: String? = null,
    @get:Size(max = 20)
    var countyCode: String? = null,
    @get:Size(max = 100)
    var community: String? = null,
    @get:Size(max = 20)
    var communityCode: String? = null,
    @get:Digits(
        integer = 10,
        fraction = 2
    )
    @get:JsonFormat(shape = JsonFormat.Shape.STRING)
    @get:Schema(
        type = "string",
        example = "88.08"
    )
    var latitude: BigDecimal? = null,
    @get:Digits(
        integer = 10,
        fraction = 2
    )
    @get:JsonFormat(shape = JsonFormat.Shape.STRING)
    @get:Schema(
        type = "string",
        example = "35.08"
    )
    var longitude: BigDecimal? = null,
    var accuracy: Int? = null,
    @get:NotNull
    @get:Size(max = 2)
    var countryCode: String? = null
)
