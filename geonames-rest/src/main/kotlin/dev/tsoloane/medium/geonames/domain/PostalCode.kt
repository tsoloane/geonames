package dev.tsoloane.medium.geonames.domain

import java.math.BigDecimal
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne


@Entity
class PostalCode {

    @Id
    @Column(
        nullable = false,
        updatable = false,
        length = 20
    )
    var postalCode: String? = null

    @Column(
        nullable = false,
        length = 200
    )
    var placeName: String? = null

    @Column(length = 100)
    var state: String? = null

    @Column(length = 20)
    var stateCode: String? = null

    @Column(length = 100)
    var county: String? = null

    @Column(length = 20)
    var countyCode: String? = null

    @Column(length = 100)
    var community: String? = null

    @Column(length = 20)
    var communityCode: String? = null

    @Column(
        precision = 10,
        scale = 2
    )
    var latitude: BigDecimal? = null

    @Column(
        precision = 10,
        scale = 2
    )
    var longitude: BigDecimal? = null

    @Column
    var accuracy: Int? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "country_code_id",
        nullable = false
    )
    var countryCode: Country? = null

}
