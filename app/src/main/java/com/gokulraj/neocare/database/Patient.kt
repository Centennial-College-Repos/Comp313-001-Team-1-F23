package com.gokulraj.neocare.database

data class Patient(
    private val _patientId: String?,
    private val _fullName: String?,
    private var _condition: String?,
    private var _location: String?,
) {
    val patientId: String?
        get() = _patientId

    val fullName: String?
        get() = _fullName

    var condition: String?
        get() = _condition
        set(value){
            _condition = value
        }

    var location: String?
        get() = _location
        set(value){
            _location = value
        }
}