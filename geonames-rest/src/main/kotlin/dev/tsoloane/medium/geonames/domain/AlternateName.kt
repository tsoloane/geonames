package dev.tsoloane.medium.geonames.domain

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.SequenceGenerator


@Entity
class AlternateName {

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

    @Column(length = 7)
    var isoLanguage: String? = null

    @Column(
        nullable = false,
        length = 400
    )
    var alternateName: String? = null

    @Column
    var preferredName: Boolean? = null

    @Column
    var shortName: Boolean? = null

    @Column
    var colloquial: Boolean? = null

    @Column
    var historic: Boolean? = null

    @Column(length = 100)
    var fromDate: String? = null

    @Column(length = 100)
    var toDate: String? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "geo_name_id",
        nullable = false
    )
    var geoName: GeoName? = null

}
