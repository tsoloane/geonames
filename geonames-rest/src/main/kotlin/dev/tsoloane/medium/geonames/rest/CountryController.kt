package dev.tsoloane.medium.geonames.rest

import dev.tsoloane.medium.geonames.model.CountryDTO
import dev.tsoloane.medium.geonames.service.CountryService
import java.lang.Void
import javax.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping(
    value = ["/api/countrys"],
    produces = [MediaType.APPLICATION_JSON_VALUE]
)
class CountryController(
    private val countryService: CountryService
) {

    @GetMapping
    fun getAllCountrys(): ResponseEntity<List<CountryDTO>> =
            ResponseEntity.ok(countryService.findAll())

    @GetMapping("/{isoAlpha2}")
    fun getCountry(@PathVariable isoAlpha2: String): ResponseEntity<CountryDTO> =
            ResponseEntity.ok(countryService.get(isoAlpha2))

    @PostMapping
    fun createCountry(@RequestBody @Valid countryDTO: CountryDTO): ResponseEntity<Void> {
        countryService.create(countryDTO)
        return ResponseEntity(HttpStatus.CREATED)
    }

    @PutMapping("/{isoAlpha2}")
    fun updateCountry(@PathVariable isoAlpha2: String, @RequestBody @Valid countryDTO: CountryDTO):
            ResponseEntity<Void> {
        countryService.update(isoAlpha2, countryDTO)
        return ResponseEntity.ok().build()
    }

    @DeleteMapping("/{isoAlpha2}")
    fun deleteCountry(@PathVariable isoAlpha2: String): ResponseEntity<Void> {
        countryService.delete(isoAlpha2)
        return ResponseEntity.noContent().build()
    }

}
