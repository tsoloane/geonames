package dev.tsoloane.medium.geonames.repos

import dev.tsoloane.medium.geonames.domain.GeoName
import org.springframework.data.jpa.repository.JpaRepository


interface GeoNameRepository : JpaRepository<GeoName, Int>
