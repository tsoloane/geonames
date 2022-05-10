package dev.tsoloane.medium.geonames.service

import dev.tsoloane.medium.geonames.domain.Country
import dev.tsoloane.medium.geonames.domain.GeoName
import dev.tsoloane.medium.geonames.model.GeoNameDTO
import dev.tsoloane.medium.geonames.repos.CountryRepository
import dev.tsoloane.medium.geonames.repos.GeoNameRepository
import kotlin.streams.toList
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException


@Service
class GeoNameService(
    private val geoNameRepository: GeoNameRepository,
    private val countryRepository: CountryRepository
) {

    fun findAll(): List<GeoNameDTO> {
        return geoNameRepository.findAll()
                .stream()
                .map { geoName -> mapToDTO(geoName, GeoNameDTO()) }
                .toList()
    }

    fun `get`(id: Int): GeoNameDTO {
        return geoNameRepository.findById(id)
                .map { geoName -> mapToDTO(geoName, GeoNameDTO()) }
                .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND) }
    }

    fun create(geoNameDTO: GeoNameDTO): Int {
        val geoName = GeoName()
        mapToEntity(geoNameDTO, geoName)
        return geoNameRepository.save(geoName).id!!
    }

    fun update(id: Int, geoNameDTO: GeoNameDTO) {
        val geoName: GeoName = geoNameRepository.findById(id)
                .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND) }
        mapToEntity(geoNameDTO, geoName)
        geoNameRepository.save(geoName)
    }

    fun delete(id: Int) {
        geoNameRepository.deleteById(id)
    }

    fun mapToDTO(geoName: GeoName, geoNameDTO: GeoNameDTO): GeoNameDTO {
        geoNameDTO.id = geoName.id
        geoNameDTO.name = geoName.name
        geoNameDTO.asciiName = geoName.asciiName
        geoNameDTO.alternateNames = geoName.alternateNames
        geoNameDTO.latitude = geoName.latitude
        geoNameDTO.longitude = geoName.longitude
        geoNameDTO.featureClass = geoName.featureClass
        geoNameDTO.featureCode = geoName.featureCode
        geoNameDTO.cc2 = geoName.cc2
        geoNameDTO.admin1Code = geoName.admin1Code
        geoNameDTO.admin2Code = geoName.admin2Code
        geoNameDTO.admin3Code = geoName.admin3Code
        geoNameDTO.admin4Code = geoName.admin4Code
        geoNameDTO.population = geoName.population
        geoNameDTO.elevation = geoName.elevation
        geoNameDTO.dem = geoName.dem
        geoNameDTO.timezone = geoName.timezone
        geoNameDTO.modifyDate = geoName.modifyDate
        geoNameDTO.countryCode = geoName.countryCode?.isoAlpha2
        return geoNameDTO
    }

    fun mapToEntity(geoNameDTO: GeoNameDTO, geoName: GeoName): GeoName {
        geoName.name = geoNameDTO.name
        geoName.asciiName = geoNameDTO.asciiName
        geoName.alternateNames = geoNameDTO.alternateNames
        geoName.latitude = geoNameDTO.latitude
        geoName.longitude = geoNameDTO.longitude
        geoName.featureClass = geoNameDTO.featureClass
        geoName.featureCode = geoNameDTO.featureCode
        geoName.cc2 = geoNameDTO.cc2
        geoName.admin1Code = geoNameDTO.admin1Code
        geoName.admin2Code = geoNameDTO.admin2Code
        geoName.admin3Code = geoNameDTO.admin3Code
        geoName.admin4Code = geoNameDTO.admin4Code
        geoName.population = geoNameDTO.population
        geoName.elevation = geoNameDTO.elevation
        geoName.dem = geoNameDTO.dem
        geoName.timezone = geoNameDTO.timezone
        geoName.modifyDate = geoNameDTO.modifyDate
        if (geoNameDTO.countryCode != null && (geoName.countryCode == null ||
                geoName.countryCode?.isoAlpha2 != geoNameDTO.countryCode)) {
            val countryCode: Country = countryRepository.findById(geoNameDTO.countryCode!!)
                    .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND,
                            "countryCode not found") }
            geoName.countryCode = countryCode
        }
        return geoName
    }

}
