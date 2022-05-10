package dev.tsoloane.medium.geonames.service

import dev.tsoloane.medium.geonames.domain.Country
import dev.tsoloane.medium.geonames.domain.PostalCode
import dev.tsoloane.medium.geonames.model.PostalCodeDTO
import dev.tsoloane.medium.geonames.repos.CountryRepository
import dev.tsoloane.medium.geonames.repos.PostalCodeRepository
import kotlin.streams.toList
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException


@Service
class PostalCodeService(
    private val postalCodeRepository: PostalCodeRepository,
    private val countryRepository: CountryRepository
) {

    fun findAll(): List<PostalCodeDTO> {
        return postalCodeRepository.findAll()
                .stream()
                .map { postalCode -> mapToDTO(postalCode, PostalCodeDTO()) }
                .toList()
    }

    fun `get`(postalCode: String): PostalCodeDTO {
        return postalCodeRepository.findById(postalCode)
                .map { postalCode -> mapToDTO(postalCode, PostalCodeDTO()) }
                .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND) }
    }

    fun create(postalCodeDTO: PostalCodeDTO): String {
        val postalCode = PostalCode()
        mapToEntity(postalCodeDTO, postalCode)
        return postalCodeRepository.save(postalCode).postalCode!!
    }

    fun update(postalCode: String, postalCodeDTO: PostalCodeDTO) {
        val postalCode: PostalCode = postalCodeRepository.findById(postalCode)
                .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND) }
        mapToEntity(postalCodeDTO, postalCode)
        postalCodeRepository.save(postalCode)
    }

    fun delete(postalCode: String) {
        postalCodeRepository.deleteById(postalCode)
    }

    fun mapToDTO(postalCode: PostalCode, postalCodeDTO: PostalCodeDTO): PostalCodeDTO {
        postalCodeDTO.postalCode = postalCode.postalCode
        postalCodeDTO.placeName = postalCode.placeName
        postalCodeDTO.state = postalCode.state
        postalCodeDTO.stateCode = postalCode.stateCode
        postalCodeDTO.county = postalCode.county
        postalCodeDTO.countyCode = postalCode.countyCode
        postalCodeDTO.community = postalCode.community
        postalCodeDTO.communityCode = postalCode.communityCode
        postalCodeDTO.latitude = postalCode.latitude
        postalCodeDTO.longitude = postalCode.longitude
        postalCodeDTO.accuracy = postalCode.accuracy
        postalCodeDTO.countryCode = postalCode.countryCode?.isoAlpha2
        return postalCodeDTO
    }

    fun mapToEntity(postalCodeDTO: PostalCodeDTO, postalCode: PostalCode): PostalCode {
        postalCode.postalCode = postalCodeDTO.postalCode
        postalCode.placeName = postalCodeDTO.placeName
        postalCode.state = postalCodeDTO.state
        postalCode.stateCode = postalCodeDTO.stateCode
        postalCode.county = postalCodeDTO.county
        postalCode.countyCode = postalCodeDTO.countyCode
        postalCode.community = postalCodeDTO.community
        postalCode.communityCode = postalCodeDTO.communityCode
        postalCode.latitude = postalCodeDTO.latitude
        postalCode.longitude = postalCodeDTO.longitude
        postalCode.accuracy = postalCodeDTO.accuracy
        if (postalCodeDTO.countryCode != null && (postalCode.countryCode == null ||
                postalCode.countryCode?.isoAlpha2 != postalCodeDTO.countryCode)) {
            val countryCode: Country = countryRepository.findById(postalCodeDTO.countryCode!!)
                    .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND,
                            "countryCode not found") }
            postalCode.countryCode = countryCode
        }
        return postalCode
    }

}
