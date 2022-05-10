package dev.tsoloane.medium.geonames.domain

import java.math.BigDecimal
import java.time.LocalDate
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.OneToMany
import javax.persistence.SequenceGenerator


@Entity
class GeoName {

    @Id
    @Column(
        nullable = false,
        updatable = false
    )
    @SequenceGenerator(
        name = "primary_sequence",
        sequenceName = "primary_sequence",
        allocationSize = 1,
        initialValue = 10000
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "primary_sequence"
    )
    var id: Int? = null

    @Column(
        nullable = false,
        length = 200
    )
    var name: String? = null

    @Column(length = 200)
    var asciiName: String? = null

    @Column(columnDefinition = "text")
    var alternateNames: String? = null

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
    var featureClass: Boolean? = null

    @Column(length = 10)
    var featureCode: String? = null

    @Column(length = 200)
    var cc2: String? = null

    @Column(length = 20)
    var admin1Code: String? = null

    @Column(length = 80)
    var admin2Code: String? = null

    @Column(length = 20)
    var admin3Code: String? = null

    @Column(length = 20)
    var admin4Code: String? = null

    @Column
    var population: Long? = null

    @Column
    var elevation: Int? = null

    @Column
    var dem: Int? = null

    @Column(length = 40)
    var timezone: String? = null

    @Column
    var modifyDate: LocalDate? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_code_id")
    var countryCode: Country? = null

    @OneToMany(mappedBy = "geoName")
    var geoNameAlternateNames: MutableSet<AlternateName>? = null

}
