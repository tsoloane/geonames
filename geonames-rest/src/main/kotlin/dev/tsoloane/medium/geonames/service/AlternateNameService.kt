package dev.tsoloane.medium.geonames.service

import dev.tsoloane.medium.geonames.domain.AlternateName
import dev.tsoloane.medium.geonames.domain.GeoName
import dev.tsoloane.medium.geonames.model.AlternateNameDTO
import dev.tsoloane.medium.geonames.repos.AlternateNameRepository
import dev.tsoloane.medium.geonames.repos.GeoNameRepository
import kotlin.streams.toList
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException


@Service
class AlternateNameService(
    private val alternateNameRepository: AlternateNameRepository,
    private val geoNameRepository: GeoNameRepository
) {

    fun findAll(): List<AlternateNameDTO> {
        return alternateNameRepository.findAll()
                .stream()
                .map { alternateName -> mapToDTO(alternateName, AlternateNameDTO()) }
                .toList()
    }

    fun `get`(id: Int): AlternateNameDTO {
        return alternateNameRepository.findById(id)
                .map { alternateName -> mapToDTO(alternateName, AlternateNameDTO()) }
                .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND) }
    }

    fun create(alternateNameDTO: AlternateNameDTO): Int {
        val alternateName = AlternateName()
        mapToEntity(alternateNameDTO, alternateName)
        return alternateNameRepository.save(alternateName).id!!
    }

    fun update(id: Int, alternateNameDTO: AlternateNameDTO) {
        val alternateName: AlternateName = alternateNameRepository.findById(id)
                .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND) }
        mapToEntity(alternateNameDTO, alternateName)
        alternateNameRepository.save(alternateName)
    }

    fun delete(id: Int) {
        alternateNameRepository.deleteById(id)
    }

    fun mapToDTO(alternateName: AlternateName, alternateNameDTO: AlternateNameDTO):
            AlternateNameDTO {
        alternateNameDTO.id = alternateName.id
        alternateNameDTO.isoLanguage = alternateName.isoLanguage
        alternateNameDTO.alternateName = alternateName.alternateName
        alternateNameDTO.preferredName = alternateName.preferredName
        alternateNameDTO.shortName = alternateName.shortName
        alternateNameDTO.colloquial = alternateName.colloquial
        alternateNameDTO.historic = alternateName.historic
        alternateNameDTO.fromDate = alternateName.fromDate
        alternateNameDTO.toDate = alternateName.toDate
        alternateNameDTO.geoName = alternateName.geoName?.id
        return alternateNameDTO
    }

    fun mapToEntity(alternateNameDTO: AlternateNameDTO, alternateName: AlternateName):
            AlternateName {
        alternateName.isoLanguage = alternateNameDTO.isoLanguage
        alternateName.alternateName = alternateNameDTO.alternateName
        alternateName.preferredName = alternateNameDTO.preferredName
        alternateName.shortName = alternateNameDTO.shortName
        alternateName.colloquial = alternateNameDTO.colloquial
        alternateName.historic = alternateNameDTO.historic
        alternateName.fromDate = alternateNameDTO.fromDate
        alternateName.toDate = alternateNameDTO.toDate
        if (alternateNameDTO.geoName != null && (alternateName.geoName == null ||
                alternateName.geoName?.id != alternateNameDTO.geoName)) {
            val geoName: GeoName = geoNameRepository.findById(alternateNameDTO.geoName!!)
                    .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND,
                            "geoName not found") }
            alternateName.geoName = geoName
        }
        return alternateName
    }

}
