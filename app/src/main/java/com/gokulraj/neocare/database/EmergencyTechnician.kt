package com.gokulraj.neocare.database

data class EmergencyTechnician(
    private val _emtId: String? = null,
    private val _fullName: String? = null,
    private var _contactNumber: String? = null,
    private var _email: String? = null,
    private var _password: String? = null,
    private var _specialization: String? = null,
    private var _userType: String? = "EMT"
) {
    val emtId: String?
        get() = _emtId

    val fullName: String?
        get() = _fullName

    var contactNumber: String?
        get() = _contactNumber
        set(value){
            _contactNumber = value
        }

    var email: String?
        get() = _email
        set(value){
            _email = value
        }

    var password: String?
        get() = _password
        set(value){
            _password = value
        }

    var specialization: String?
        get() = _specialization
        set(value){
            _specialization = value
        }
    var userType: String?
        get() = _userType
        set(value){
            _userType = value
        }
}