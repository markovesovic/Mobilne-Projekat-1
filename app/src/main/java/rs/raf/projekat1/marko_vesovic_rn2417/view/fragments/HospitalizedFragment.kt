package rs.raf.projekat1.marko_vesovic_rn2417.view.fragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_waiting_room.*
import kotlinx.coroutines.yield
import rs.raf.projekat1.marko_vesovic_rn2417.R
import rs.raf.projekat1.marko_vesovic_rn2417.model.Patient
import rs.raf.projekat1.marko_vesovic_rn2417.view.activities.MainActivity
import rs.raf.projekat1.marko_vesovic_rn2417.view.activities.PatientProfileActivity
import rs.raf.projekat1.marko_vesovic_rn2417.view.recycler.adapter.PatientAdapterHospitalized
import rs.raf.projekat1.marko_vesovic_rn2417.view.recycler.diff.PatientDiffItemCallback
import rs.raf.projekat1.marko_vesovic_rn2417.viewmodels.RecyclerViewModel
import timber.log.Timber
import java.util.*

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class HospitalizedFragment : Fragment(R.layout.fragment_hospitalized) {
    private val sharedViewModel : RecyclerViewModel by activityViewModels()

    private lateinit var patientAdapterHospitalized: PatientAdapterHospitalized

    companion object {
        const val PATIENT_CHANGE_PROFILE_REQUEST_CODE = 1
    }

    private lateinit var patient: Patient

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }
    private fun init() {
        initUi()
        initObservers()
    }
    private fun initUi() {
        initListeners()
        initRecycler()
    }

    private val onFileButtonClick: (Patient) -> Unit = {
        this.patient = it
        val intent = Intent(this.context, PatientProfileActivity::class.java)
        intent.putExtra(PatientProfileActivity.PATIENT_KEY, it)
        startActivityForResult(intent, PATIENT_CHANGE_PROFILE_REQUEST_CODE)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode) {
            PATIENT_CHANGE_PROFILE_REQUEST_CODE -> {
                if (resultCode == Activity.RESULT_OK) {

                    val receivedPatient: Patient? = data?.getParcelableExtra(PatientProfileActivity.PATIENT_KEY)
                    Timber.e(receivedPatient?.name)
                    this.patient.name = receivedPatient?.name ?: ""
                    this.patient.lastName = receivedPatient?.lastName ?: ""
                    this.patient.symptoms = receivedPatient?.symptoms ?: ""
                    Toast.makeText(this.context, "Uspesno sacuvano", Toast.LENGTH_SHORT).show()
                } else if(resultCode == Activity.RESULT_CANCELED) {
                    Toast.makeText(this.context, "Ponisteno", Toast.LENGTH_SHORT).show()
                }
            }
        }
        init()
    }

    private val onRecoveredButtonClick: (Patient) -> Unit = {
        it.releasingDate = Date()
        sharedViewModel.removeHospitalizedPatient(it)
        sharedViewModel.addRecoveredPatient(it)
    }

    private fun initRecycler() {
        listRv.layoutManager = LinearLayoutManager(this.context)
        patientAdapterHospitalized = PatientAdapterHospitalized(PatientDiffItemCallback(), onFileButtonClick, onRecoveredButtonClick)
        listRv.adapter = patientAdapterHospitalized
    }


    private fun initListeners() {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                sharedViewModel.filterHospitalizedPatients(searchView.query.toString())
                return false
            }
        })
    }
    private fun initObservers() {
        this.activity?.let {
            sharedViewModel.getHospitalizedPatients().observe(it, Observer {
                patientAdapterHospitalized.submitList(it)
            })
        }
    }
}