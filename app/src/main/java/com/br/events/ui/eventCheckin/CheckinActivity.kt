package com.br.events.ui.eventCheckin

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.br.events.R
import com.br.events.databinding.ActivityCheckinBinding
import com.br.events.ui.eventDetails.EventDetailsActivity
import com.br.events.ui.util.Validation
import com.br.events.ui.util.isVisible
import org.koin.androidx.viewmodel.ext.android.viewModel

class CheckinActivity: AppCompatActivity() {

    private val mViewModel : CheckinViewModel by viewModel()
    private lateinit var binding : ActivityCheckinBinding




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheckinBinding.inflate(layoutInflater)
        setContentView(binding.root)


        initLoading()
        initErrorNetwork()
        initCheckin()
        initCancel()

    }

    private fun initCheckin() {
        binding.buttonCheckin.setOnClickListener {
            val name = binding.editTextCheckinNome.text.toString()
            val email = binding.editTextCheckinEmail.text.toString()
            val id = intent.getStringExtra(EventDetailsActivity.EVENT_ID)

            validateName()
            validateEmail()

            if (mViewModel.validateText(name) && mViewModel.validateEmail(email)){
                val toast = Toast.makeText(applicationContext, R.string.toast_checkin, Toast.LENGTH_SHORT)
                toast.show()
                if (id != null) {
                    mViewModel.postCheckIn(id, name, email)
                }

                finish()
            }

        }
    }

    private fun initCancel(){
        binding.buttonCancel.setOnClickListener {
            finish()
        }
    }
    private fun validateName(){
        val name = binding.editTextCheckinNome.text.toString()
        binding.inputCheckinNome.error =
            if(Validation.validateText(name).not()) getString(R.string.valida_name) else null
    }
    private fun validateEmail(){
        val email = binding.editTextCheckinEmail.text.toString()
        binding.inputCheckinEmail.error =
            if(Validation.isEmailValid(email).not()) getString(R.string.valida_email) else null
    }


    private fun initLoading() {
        mViewModel.loadingDetailsLiveData.observe(this, Observer { isLoading ->
            binding.progressLoadingCheckin.root.isVisible(isLoading)
        })
    }


    private fun initErrorNetwork() {
        mViewModel.checkinResultLiveData.observe(this, Observer { isError ->
            binding.errorNetworkCheckin.root.isVisible(isError)
        })
    }

}