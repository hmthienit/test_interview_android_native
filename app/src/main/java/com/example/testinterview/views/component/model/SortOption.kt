package com.example.testinterview.views.component.model


data class SortOption(val index: Int, val name: String, val sortType: SortType, var isSelected: Boolean = false)

enum class SortType {
    INDEX,
    TITLE,
    DATE
}
