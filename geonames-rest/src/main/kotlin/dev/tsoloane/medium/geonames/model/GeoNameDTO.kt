package dev.tsoloane.medium.geonames.model

import com.fasterxml.jackson.annotation.JsonFormat
import io.swagger.v3.oas.annotations.media.Schema
import java.math.BigDecimal
import java.time.LocalDate
import javax.validation.constraints.Digits
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size


data class GeoNameDTO(
    var id: Int? = null,
    @get:NotNull
    @get:Size(max = 200)
    var name: String? = null,
    @get:Size(max = 200)
    var asciiName: String? = null,
    var alternateNames: String? = null,
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
    var featureClass: Boolean? = null,
    @get:Size(max = 10)
    var featureCode: String? = null,
    @get:Size(max = 200)
    var cc2: String? = null,
    @get:Size(max = 20)
    var admin1Code: String? = null,
    @get:Size(max = 80)
    var admin2Code: String? = null,
    @get:Size(max = 20)
    var admin3Code: String? = null,
    @get:Size(max = 20)
    var admin4Code: String? = null,
    var population: Long? = null,
    var elevation: Int? = null,
    var dem: Int? = null,
    @get:Size(max = 40)
    var timezone: String? = null,
    var modifyDate: LocalDate? = null,
    @get:Size(max = 2)
    var countryCode: String? = null
)
