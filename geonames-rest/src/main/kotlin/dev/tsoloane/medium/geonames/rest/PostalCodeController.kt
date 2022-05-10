package dev.tsoloane.medium.geonames.rest

import dev.tsoloane.medium.geonames.model.PostalCodeDTO
import dev.tsoloane.medium.geonames.service.PostalCodeService
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
    value = ["/api/postalCodes"],
    produces = [MediaType.APPLICATION_JSON_VALUE]
)
class PostalCodeController(
    private val postalCodeService: PostalCodeService
) {

    @GetMapping
    fun getAllPostalCodes(): ResponseEntity<List<PostalCodeDTO>> =
            ResponseEntity.ok(postalCodeService.findAll())

    @GetMapping("/{postalCode}")
    fun getPostalCode(@PathVariable postalCode: String): ResponseEntity<PostalCodeDTO> =
            ResponseEntity.ok(postalCodeService.get(postalCode))

    @PostMapping
    fun createPostalCode(@RequestBody @Valid postalCodeDTO: PostalCodeDTO): ResponseEntity<Void> {
        postalCodeService.create(postalCodeDTO)
        return ResponseEntity(HttpStatus.CREATED)
    }

    @PutMapping("/{postalCode}")
    fun updatePostalCode(@PathVariable postalCode: String, @RequestBody @Valid
            postalCodeDTO: PostalCodeDTO): ResponseEntity<Void> {
        postalCodeService.update(postalCode, postalCodeDTO)
        return ResponseEntity.ok().build()
    }

    @DeleteMapping("/{postalCode}")
    fun deletePostalCode(@PathVariable postalCode: String): ResponseEntity<Void> {
        postalCodeService.delete(postalCode)
        return ResponseEntity.noContent().build()
    }

}
