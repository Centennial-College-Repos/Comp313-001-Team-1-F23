package com.gokulraj.neocare.database

data class EmergencyRequest(
    private val _requestId: String? = null,
    private val _patientName: String? = null,
    private val _location: String? = null,
    private val _emergencyDetails: String? = null,
    private var _status: RequestStatus = RequestStatus.PENDING
) {
    val requestId: String?
        get() = _requestId

    val patientName: String?
        get() = _patientName

    val location: String?
        get() = _location

    val emergencyDetails: String?
        get() = _emergencyDetails

    var status: RequestStatus
        get() = _status
        set(value) {
            _status = value
        }
}

enum class RequestStatus {
    PENDING,
    ACCEPTED,
    IN_PROGRESS,
    ESCALATED,
    REJECTED,
    COMPLETED
}