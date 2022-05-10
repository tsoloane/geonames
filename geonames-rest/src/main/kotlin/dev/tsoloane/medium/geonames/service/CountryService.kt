package dev.tsoloane.medium.geonames.service

import dev.tsoloane.medium.geonames.domain.Country
import dev.tsoloane.medium.geonames.model.CountryDTO
import dev.tsoloane.medium.geonames.repos.CountryRepository
import kotlin.streams.toList
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException


@Service
class CountryService(
    private val countryRepository: CountryRepository
) {

    fun findAll(): List<CountryDTO> {
        return countryRepository.findAll()
                .stream()
                .map { country -> mapToDTO(country, CountryDTO()) }
                .toList()
    }

    fun `get`(isoAlpha2: String): CountryDTO {
        return countryRepository.findById(isoAlpha2)
                .map { country -> mapToDTO(country, CountryDTO()) }
                .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND) }
    }

    fun create(countryDTO: CountryDTO): String {
        val country = Country()
        mapToEntity(countryDTO, country)
        return countryRepository.save(country).isoAlpha2!!
    }

    fun update(isoAlpha2: String, countryDTO: CountryDTO) {
        val country: Country = countryRepository.findById(isoAlpha2)
                .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND) }
        mapToEntity(countryDTO, country)
        countryRepository.save(country)
    }

    fun delete(isoAlpha2: String) {
        countryRepository.deleteById(isoAlpha2)
    }

    fun mapToDTO(country: Country, countryDTO: CountryDTO): CountryDTO {
        countryDTO.isoAlpha2 = country.isoAlpha2
        countryDTO.isoAlpha3 = country.isoAlpha3
        countryDTO.isoNumeric = country.isoNumeric
        countryDTO.fips = country.fips
        countryDTO.name = country.name
        countryDTO.capital = country.capital
        countryDTO.area = country.area
        countryDTO.population = country.population
        countryDTO.continent = country.continent
        countryDTO.tld = country.tld
        countryDTO.currencyCode = country.currencyCode
        countryDTO.currencyName = country.currencyName
        countryDTO.dialingCode = country.dialingCode
        countryDTO.postalCodeFormat = country.postalCodeFormat
        countryDTO.postalCodeRegex = country.postalCodeRegex
        countryDTO.languages = country.languages
        countryDTO.geoNameId = country.geoNameId
        countryDTO.neighbours = country.neighbours
        countryDTO.equivalentFipsCode = country.equivalentFipsCode
        return countryDTO
    }

    fun mapToEntity(countryDTO: CountryDTO, country: Country): Country {
        country.isoAlpha2 = countryDTO.isoAlpha2
        country.isoAlpha3 = countryDTO.isoAlpha3
        country.isoNumeric = countryDTO.isoNumeric
        country.fips = countryDTO.fips
        country.name = countryDTO.name
        country.capital = countryDTO.capital
        country.area = countryDTO.area
        country.population = countryDTO.population
        country.continent = countryDTO.continent
        country.tld = countryDTO.tld
        country.currencyCode = countryDTO.currencyCode
        country.currencyName = countryDTO.currencyName
        country.dialingCode = countryDTO.dialingCode
        country.postalCodeFormat = countryDTO.postalCodeFormat
        country.postalCodeRegex = countryDTO.postalCodeRegex
        country.languages = countryDTO.languages
        country.geoNameId = countryDTO.geoNameId
        country.neighbours = countryDTO.neighbours
        country.equivalentFipsCode = countryDTO.equivalentFipsCode
        return country
    }

}
