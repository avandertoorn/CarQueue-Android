package com.carqueue.ui.components

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.carqueue.models.Driver
import com.carqueue.models.Student
import kotlinx.coroutines.flow.*
import java.util.*

class DriverViewModel : ViewModel() {

    private val driversList: MutableList<Driver> =
        mutableListOf(
            Driver(
                UUID.randomUUID(), "Andrew", "VanderToorn", listOf(
                Student(UUID.randomUUID(), "Evelyn", "VanderToorn"),
                Student(UUID.randomUUID(), "Sam", "VanderToorn")
            )),
            Driver(
                UUID.randomUUID(), "Bob", "Johnson", listOf(
                Student(UUID.randomUUID(), "Billy", "Johnson"),
                Student(UUID.randomUUID(), "Susie", "Johnson")
            )),
            Driver(
                UUID.randomUUID(), "Samantha", "Porkney", listOf(
                Student(UUID.randomUUID(), "Lilly", "Porkney"),
                Student(UUID.randomUUID(), "Chad", "Porkney"),
                Student(UUID.randomUUID(), "Rosie", "Porkney")
            )),
            Driver(
                UUID.randomUUID(), "Martha", "Smeakly", listOf(
                Student(UUID.randomUUID(), "Johny", "Smeakly")
            )),
            Driver(
                UUID.randomUUID(), "Tammy", "Hearty", listOf(
                Student(UUID.randomUUID(), "Phil", "Hearty"),
                Student(UUID.randomUUID(), "Eric", "Hearty")
            )),
            Driver(
                UUID.randomUUID(), "Julie", "Wellwisher", listOf(
                Student(UUID.randomUUID(), "Rose", "Wellwisher"),
                Student(UUID.randomUUID(), "Mark", "Wellwisher"),
                Student(UUID.randomUUID(), "Tom", "Wellwisher")
            )),
            Driver(
                UUID.randomUUID(), "Andrew", "VanderPloon", listOf(
                    Student(UUID.randomUUID(), "Evelyn", "VanderPloon"),
                    Student(UUID.randomUUID(), "Sam", "VanderPloon")
                )),
            Driver(
                UUID.randomUUID(), "Bob", "Jackson", listOf(
                    Student(UUID.randomUUID(), "Billy", "Jackson"),
                    Student(UUID.randomUUID(), "Susie", "Jackson")
                )),
            Driver(
                UUID.randomUUID(), "Samantha", "Peterson", listOf(
                    Student(UUID.randomUUID(), "Lilly", "Peterson"),
                    Student(UUID.randomUUID(), "Chad", "Peterson"),
                    Student(UUID.randomUUID(), "Rosie", "Peterson")
                )),
            Driver(
                UUID.randomUUID(), "Martha", "Smith", listOf(
                    Student(UUID.randomUUID(), "Johny", "Smith")
                )),
            Driver(
                UUID.randomUUID(), "Tammy", "Blackburn", listOf(
                    Student(UUID.randomUUID(), "Phil", "Blackburn"),
                    Student(UUID.randomUUID(), "Eric", "Blackburn")
                )),
            Driver(
                UUID.randomUUID(), "Julie", "Wilson", listOf(
                    Student(UUID.randomUUID(), "Rose", "Wilson"),
                    Student(UUID.randomUUID(), "Mark", "Wilson"),
                    Student(UUID.randomUUID(), "Tom", "Wilson")
                )),
        )

    private val _drivers = mutableStateListOf<Driver>()
    val drivers: List<Driver> = _drivers


    private var _prevSearchText = ""
    private val _searchText = MutableStateFlow("")

    init {
        driversList.sortBy { d -> d.LastName }
        _drivers.addAll(driversList)
    }

    fun onSearchTextChanged(searchText: String){
        _prevSearchText = _searchText.value
        _searchText.value = searchText

        if(_prevSearchText.length < _searchText.value.length){
            _drivers.retainAll(driversList.filter {driver -> driver.LastName.startsWith(searchText, true)})
        }
        else{
            driversList.filter {driver -> driver.LastName.startsWith(searchText, true)}.forEach { d -> if (!_drivers.contains(d)) _drivers.add(d) }
            _drivers.sortBy { d -> d.LastName }
        }
    }

    fun onQueueDriver(driver: Driver){
        driversList.remove(driver) //TODO api call
        _drivers.remove(driver)
    }
}