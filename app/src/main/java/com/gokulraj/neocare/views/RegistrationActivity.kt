package com.gokulraj.neocare.views

import android.app.Application
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import com.gokulraj.neocare.R
import com.gokulraj.neocare.database.User
import com.gokulraj.neocare.databinding.ActivityRegistrationBinding
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.core.Context

class RegistrationActivity : AppCompatActivity(), View.OnFocusChangeListener {

    private lateinit var mBinding: ActivityRegistrationBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var databaseReference: DatabaseReference
    private var userType: String = "Patient"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityRegistrationBinding.inflate(LayoutInflater.from(this))
        setContentView(mBinding.root)

        firebaseAuth = FirebaseAuth.getInstance()
        FirebaseApp.initializeApp(this)
        firebaseDatabase = FirebaseDatabase.getInstance()
        databaseReference = firebaseDatabase.reference.child("users")


        mBinding.fullNameEt.onFocusChangeListener = this
        mBinding.healthCardNumberEt.onFocusChangeListener = this
        mBinding.emailAddressEt.onFocusChangeListener = this
        mBinding.passwordEt.onFocusChangeListener = this
        mBinding.termsCb.onFocusChangeListener = this

        mBinding.radioButtonPatient.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                userType = "Patient"
            }
        }

        mBinding.radioButtonHealthcareProfessional.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                userType = "Healthcare Professional"
            }
        }


        mBinding.btnRegister.setOnClickListener {
            val fullName = mBinding.fullNameEt.text.toString()
            val healthCard = mBinding.healthCardNumberEt.text.toString()
            val email = mBinding.emailAddressEt.text.toString()
            val password =  mBinding.passwordEt.text.toString()
            val isTermsAccepted = mBinding.termsCb.isChecked

            println(isTermsAccepted)

            if (validateFullName() && validateHealthCardNumber() && validateEmail() && validatePassword() && validateTerms()){
                signUpUser(fullName, healthCard, email, password, isTermsAccepted,userType)
            }
        }

        mBinding.loginRedirect.setOnClickListener {
            startActivity(Intent(this@RegistrationActivity, LoginActivity::class.java))
            finish()
        }
    }

    private fun signUpUser(fullName: String, healthCard: String, email: String, password: String, isTermsAccepted: Boolean, userType: String) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val firebaseUser = task.result?.user
                    val uid = firebaseUser?.uid
                    saveUserDataToDatabase(uid, userType, fullName, healthCard, email, password, isTermsAccepted)
                } else {
                    val exception = task.exception
                    Toast.makeText(this@RegistrationActivity, "Registration Failed: ${exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun saveUserDataToDatabase(uid: String?, userType: String, fullName: String, healthCard: String, email: String, password: String, isTermsAccepted: Boolean) {
        if (uid != null) {
            databaseReference.orderByChild("email").equalTo(email)
                .addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(emailSnapshot: DataSnapshot) {
                        databaseReference.orderByChild("healthCardNumber").equalTo(healthCard)
                            .addListenerForSingleValueEvent(object : ValueEventListener {
                                override fun onDataChange(healthCardSnapshot: DataSnapshot) {
                                    if (!emailSnapshot.exists() && !healthCardSnapshot.exists()) {
                                        val id = databaseReference.push().key
                                        val userData = User(
                                            id,
                                            userType,
                                            fullName,
                                            healthCard,
                                            email,
                                            password,
                                            isTermsAccepted
                                        )
                                        databaseReference.child(uid).setValue(userData)

                                        Toast.makeText(
                                            this@RegistrationActivity,
                                            "Signup Successful!",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                        startActivity(
                                            Intent(
                                                this@RegistrationActivity,
                                                LoginActivity::class.java
                                            )
                                        )
                                        finish()
                                    } else {
                                        if (emailSnapshot.exists()) {
                                            Toast.makeText(
                                                this@RegistrationActivity,
                                                "Email is already taken",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        }
                                        if (healthCardSnapshot.exists()) {
                                            Toast.makeText(
                                                this@RegistrationActivity,
                                                "Health Card number is already registered",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        }
                                    }
                                }

                                override fun onCancelled(healthCardError: DatabaseError) {
                                    Toast.makeText(
                                        this@RegistrationActivity,
                                        "Database Error: ${healthCardError.message}",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            })
                    }

                    override fun onCancelled(emailError: DatabaseError) {
                        Toast.makeText(
                            this@RegistrationActivity,
                            "Database Error: ${emailError.message}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                })
        }
    }

    private fun validateFullName(): Boolean {
        var errorMsg: String? = null
        val value: String = mBinding.fullNameEt.text.toString()

        if(value.isEmpty()){
            errorMsg = "Full name is required."
        }

        if (errorMsg != null){
            mBinding.fullNameTil.apply {
                isErrorEnabled = true
                error = errorMsg
            }
        }

        return errorMsg == null
    }

    private fun validateHealthCardNumber(): Boolean {
        var errorMsg: String? = null
        val value = mBinding.healthCardNumberEt.text.toString()

        if(value.isEmpty()){
            errorMsg = "Health card number is required."
        } else if(value.length < 12){
            errorMsg = "Health card number is in the wrong format. Should be greater than or equal 12"
        }

        if (errorMsg != null){
            mBinding.healthCardNumberTil.apply {
                isErrorEnabled = true
                error = errorMsg
            }
        }

        return errorMsg == null
    }

    private fun validateEmail(): Boolean {
        var errorMsg: String? = null
        val value = mBinding.emailAddressEt.text.toString()

        if(value.isEmpty()) {
            errorMsg = "Email is required."
        } else if(!Patterns.EMAIL_ADDRESS.matcher(value).matches()){
            errorMsg = "Email address is invalid."
        }

        if (errorMsg != null){
            mBinding.emailAddressTil.apply {
                isErrorEnabled = true
                error = errorMsg
            }
        }

        return errorMsg == null
    }

    private fun validatePassword(): Boolean {
        var errorMsg: String? = null
        val value = mBinding.passwordEt.text.toString()

        if(value.isEmpty()) {
            errorMsg = "Password is required."
        } else if(value.length < 6){
            errorMsg = "Password must be at least 6 characters long."
        }

        if (errorMsg != null){
            mBinding.passwordTil.apply {
                isErrorEnabled = true
                error = errorMsg
            }
        }

        return errorMsg == null
    }

    private fun validateTerms(): Boolean{
        var errorMsg: String? = null
        val value = mBinding.termsCb.isChecked


        if (!value){
            errorMsg = "Please accept the terms and conditions to continue."
            Toast.makeText(this, errorMsg, Toast.LENGTH_SHORT).show()
        }

        if (errorMsg != null){
            mBinding.termsTil.apply {
                isErrorEnabled = true
                error = errorMsg
            }
        }

        return errorMsg == null
    }

    override fun onFocusChange(view: View?, hasFocus: Boolean) {
        if(view != null){
            when(view.id){
                R.id.fullNameEt -> {
                    if(hasFocus){
                        if(mBinding.fullNameTil.isErrorEnabled){
                            mBinding.fullNameTil.isErrorEnabled = false
                        }
                    } else {
                        validateFullName()
                    }
                }
                R.id.healthCardNumberEt -> {
                    if(hasFocus){
                        if(mBinding.healthCardNumberTil.isErrorEnabled){
                            mBinding.healthCardNumberTil.isErrorEnabled = false
                        }
                    } else {
                        validateHealthCardNumber()
                    }
                }
                R.id.emailAddressEt -> {
                    if(hasFocus){
                        if(mBinding.emailAddressTil.isErrorEnabled){
                            mBinding.emailAddressTil.isErrorEnabled = false
                        }
                    } else {
                        validateEmail()
                    }
                }
                R.id.passwordEt -> {
                    if(hasFocus){
                        if(mBinding.passwordTil.isErrorEnabled){
                            mBinding.passwordTil.isErrorEnabled = false
                        }
                    } else {
                        if(validatePassword()){
                            if(mBinding.passwordTil.isErrorEnabled){
                                mBinding.passwordTil.isErrorEnabled = false
                            }
                            mBinding.passwordTil.setStartIconDrawable(R.drawable.checkmark)
                        }
                    }
                }
                R.id.termsCb -> {
                    if(hasFocus){
                        if(mBinding.termsTil.isErrorEnabled){
                            mBinding.termsTil.isErrorEnabled = false
                        }
                    } else {
                        validateTerms()
                    }
                }
            }
        }
    }

    override fun onBackPressed() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}