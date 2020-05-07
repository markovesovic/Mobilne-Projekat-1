package rs.raf.projekat1.marko_vesovic_rn2417.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import rs.raf.projekat1.marko_vesovic_rn2417.model.Patient
import java.time.LocalDateTime
import java.util.*

class RecyclerViewModel : ViewModel() {
    private val waitingRoomPatients : MutableLiveData<List<Patient>> = MutableLiveData()
    private val hospitalizedPatients : MutableLiveData<List<Patient>> = MutableLiveData()
    private val recoveredPatients : MutableLiveData<List<Patient>> = MutableLiveData()


    private val waitingRoomPatientsList : MutableList<Patient> = mutableListOf()
    private val hospitalizedPatientsList : MutableList<Patient> = mutableListOf()
    private val recoveredPatientsList : MutableList<Patient> = mutableListOf()

    init {
        for(i in 1..10) {
            val patient = Patient(
                i,
                "Mare $i",
                "Care $i",
                "Symptom $i",
                "https://avatarfiles.alphacoders.com/128/128984.png",
                Date(),
                null,
                null)
            waitingRoomPatientsList.add(patient)
        }

        val listToSubmit = mutableListOf<Patient>()
        listToSubmit.addAll(waitingRoomPatientsList)
        waitingRoomPatients.value = listToSubmit
    }

    fun getWaitingRoomPatients() : LiveData<List<Patient>>{
        return waitingRoomPatients
    }
    fun getHospitalizedPatients() : LiveData<List<Patient>>{
        return hospitalizedPatients
    }
    fun getRecoveredPatients() : LiveData<List<Patient>>{
        return recoveredPatients
    }


    fun getPatientsLength(): Int {
        return (waitingRoomPatients.value?.size ?: 0) +
                (hospitalizedPatients.value?.size ?: 0) +
                (recoveredPatients.value?.size ?: 0)
    }

    fun getWaitingRoomPatientsLength(): Int {
        return waitingRoomPatients.value?.size ?: 0
    }
    fun getHospitalizedPatientsLength(): Int {
        return hospitalizedPatients.value?.size ?: 0
    }
    fun getRecoveredPatientsLength(): Int {
        return recoveredPatients.value?.size ?: 0
    }


    fun filterWaitingRoomPatients(filter : String) {
        waitingRoomPatients.value = waitingRoomPatientsList.filter {
            it.name.toLowerCase().startsWith(filter.toLowerCase())
        }
    }
    fun filterHospitalizedPatients(filter : String) {
        hospitalizedPatients.value = hospitalizedPatientsList.filter {
            it.name.toLowerCase().startsWith(filter.toLowerCase())
        }
    }
    fun filterRecoveredPatients(filter : String) {
        recoveredPatients.value = recoveredPatientsList.filter {
            it.name.toLowerCase().startsWith(filter.toLowerCase())
        }
    }


    fun removeWaitingRoomPatient(patient: Patient) {
        waitingRoomPatientsList.remove(patient)
        val listToSubmit = mutableListOf<Patient>()
        listToSubmit.addAll(waitingRoomPatientsList)
        waitingRoomPatients.value = listToSubmit
    }
    fun removeHospitalizedPatient(patient: Patient) {
        hospitalizedPatientsList.remove(patient)
        val listToSubmit = mutableListOf<Patient>()
        listToSubmit.addAll(hospitalizedPatientsList)
        hospitalizedPatients.value = listToSubmit
    }
    fun removeRecoveredPatient(patient: Patient) {
        recoveredPatientsList.remove(patient)
        val listToSubmit = mutableListOf<Patient>()
        listToSubmit.addAll(recoveredPatientsList)
        recoveredPatients.value = listToSubmit
    }

    fun addWaitingRoomPatient(patient : Patient) {
        waitingRoomPatientsList.add(patient)
        val listToSubmit = mutableListOf<Patient>()
        listToSubmit.addAll(waitingRoomPatientsList)
        waitingRoomPatients.value = listToSubmit
    }
    fun addHospitalizedPatient(patient : Patient) {
        hospitalizedPatientsList.add(patient)
        val listToSubmit = mutableListOf<Patient>()
        listToSubmit.addAll(hospitalizedPatientsList)
        hospitalizedPatients.value = listToSubmit
    }
    fun addRecoveredPatient(patient : Patient) {
        recoveredPatientsList.add(patient)
        val listToSubmit = mutableListOf<Patient>()
        listToSubmit.addAll(recoveredPatientsList)
        recoveredPatients.value = listToSubmit
    }
}