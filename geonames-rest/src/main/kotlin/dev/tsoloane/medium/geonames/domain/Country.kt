package dev.tsoloane.medium.geonames.domain

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.OneToMany


@Entity
class Country {

    @Id
    @Column(
        nullable = false,
        updatable = false,
        length = 2
    )
    var isoAlpha2: String? = null

    @Column(
        nullable = false,
        length = 3
    )
    var isoAlpha3: String? = null

    @Column(nullable = false)
    var isoNumeric: Int? = null

    @Column
    var fips: Int? = null

    @Column(length = 200)
    var name: String? = null

    @Column(length = 200)
    var capital: String? = null

    @Column
    var area: Int? = null

    @Column
    var population: Int? = null

    @Column(length = 2)
    var continent: String? = null

    @Column(length = 15)
    var tld: String? = null

    @Column(length = 3)
    var currencyCode: String? = null

    @Column(length = 50)
    var currencyName: String? = null

    @Column(length = 30)
    var dialingCode: String? = null

    @Column(length = 20)
    var postalCodeFormat: String? = null

    @Column(length = 100)
    var postalCodeRegex: String? = null

    @Column(columnDefinition = "text")
    var languages: String? = null

    @Column
    var geoNameId: Int? = null

    @Column(columnDefinition = "text")
    var neighbours: String? = null

    @Column(columnDefinition = "text")
    var equivalentFipsCode: String? = null

    @OneToMany(mappedBy = "countryCode")
    var countryCodeGeoNames: MutableSet<GeoName>? = null

    @OneToMany(mappedBy = "countryCode")
    var countryCodePostalCodes: MutableSet<PostalCode>? = null

}
