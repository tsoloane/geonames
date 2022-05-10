package dev.tsoloane.medium.geonames.repos

import dev.tsoloane.medium.geonames.domain.AlternateName
import org.springframework.data.jpa.repository.JpaRepository


interface AlternateNameRepository : JpaRepository<AlternateName, Int>
