package info.ccook.geobubble.domain

import info.ccook.geobubble.data.cities.models.DataCity
import info.ccook.geobubble.domain.models.DomainCity

internal fun DataCity.toDomainModel(): DomainCity {
    return DomainCity(fullName = this.fullName)
}

internal fun List<DataCity>.toDomainModel(): List<DomainCity> {
    return this.map { dataCity -> dataCity.toDomainModel() }
}
