package dev.tsoloane.medium.geonames.repos

import dev.tsoloane.medium.geonames.domain.PostalCode
import org.springframework.data.jpa.repository.JpaRepository


interface PostalCodeRepository : JpaRepository<PostalCode, String>
