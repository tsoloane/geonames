package dev.tsoloane.medium.geonames.repos

import dev.tsoloane.medium.geonames.domain.Country
import org.springframework.data.jpa.repository.JpaRepository


interface CountryRepository : JpaRepository<Country, String>
