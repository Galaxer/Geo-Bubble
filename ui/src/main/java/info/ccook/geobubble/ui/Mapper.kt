package info.ccook.geobubble.ui

import info.ccook.geobubble.domain.models.DomainCity
import info.ccook.geobubble.ui.models.City

fun DomainCity.toUiModel(): City {
    return City(fullName = this.fullName)
}

fun List<DomainCity>.toUiModel(): List<City> {
    return this.map { domainCity -> domainCity.toUiModel() }
}
