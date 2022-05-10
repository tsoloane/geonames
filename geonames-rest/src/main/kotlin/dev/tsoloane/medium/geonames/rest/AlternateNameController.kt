package dev.tsoloane.medium.geonames.rest

import dev.tsoloane.medium.geonames.model.AlternateNameDTO
import dev.tsoloane.medium.geonames.service.AlternateNameService
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
    value = ["/api/alternateNames"],
    produces = [MediaType.APPLICATION_JSON_VALUE]
)
class AlternateNameController(
    private val alternateNameService: AlternateNameService
) {

    @GetMapping
    fun getAllAlternateNames(): ResponseEntity<List<AlternateNameDTO>> =
            ResponseEntity.ok(alternateNameService.findAll())

    @GetMapping("/{id}")
    fun getAlternateName(@PathVariable id: Int): ResponseEntity<AlternateNameDTO> =
            ResponseEntity.ok(alternateNameService.get(id))

    @PostMapping
    fun createAlternateName(@RequestBody @Valid alternateNameDTO: AlternateNameDTO):
            ResponseEntity<Int> = ResponseEntity(alternateNameService.create(alternateNameDTO),
            HttpStatus.CREATED)

    @PutMapping("/{id}")
    fun updateAlternateName(@PathVariable id: Int, @RequestBody @Valid
            alternateNameDTO: AlternateNameDTO): ResponseEntity<Void> {
        alternateNameService.update(id, alternateNameDTO)
        return ResponseEntity.ok().build()
    }

    @DeleteMapping("/{id}")
    fun deleteAlternateName(@PathVariable id: Int): ResponseEntity<Void> {
        alternateNameService.delete(id)
        return ResponseEntity.noContent().build()
    }

}
