package dev.tsoloane.medium.geonames.rest

import dev.tsoloane.medium.geonames.model.GeoNameDTO
import dev.tsoloane.medium.geonames.service.GeoNameService
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
    value = ["/api/geoNames"],
    produces = [MediaType.APPLICATION_JSON_VALUE]
)
class GeoNameController(
    private val geoNameService: GeoNameService
) {

    @GetMapping
    fun getAllGeoNames(): ResponseEntity<List<GeoNameDTO>> =
            ResponseEntity.ok(geoNameService.findAll())

    @GetMapping("/{id}")
    fun getGeoName(@PathVariable id: Int): ResponseEntity<GeoNameDTO> =
            ResponseEntity.ok(geoNameService.get(id))

    @PostMapping
    fun createGeoName(@RequestBody @Valid geoNameDTO: GeoNameDTO): ResponseEntity<Int> =
            ResponseEntity(geoNameService.create(geoNameDTO), HttpStatus.CREATED)

    @PutMapping("/{id}")
    fun updateGeoName(@PathVariable id: Int, @RequestBody @Valid geoNameDTO: GeoNameDTO):
            ResponseEntity<Void> {
        geoNameService.update(id, geoNameDTO)
        return ResponseEntity.ok().build()
    }

    @DeleteMapping("/{id}")
    fun deleteGeoName(@PathVariable id: Int): ResponseEntity<Void> {
        geoNameService.delete(id)
        return ResponseEntity.noContent().build()
    }

}
