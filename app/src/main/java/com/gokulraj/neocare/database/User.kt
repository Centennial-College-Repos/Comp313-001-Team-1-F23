package com.gokulraj.neocare.database

/**
 * Group 1
 * Team Members: GokulRaj, Lea, Husna, Johny, Vinny, Zhiyang
 * User details
 *  */

data class User(
    val id: String? = null,
    val userType: String?=null,
    val fullName: String? = null,
    val healthCardNumber: String? = null,
    val email: String? = null,
    val password: String? = null,
    val isTermsAccepted: Boolean? = false
)
