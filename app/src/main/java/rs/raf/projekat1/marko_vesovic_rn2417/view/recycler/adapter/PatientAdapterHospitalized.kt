package rs.raf.projekat1.marko_vesovic_rn2417.view.recycler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import rs.raf.projekat1.marko_vesovic_rn2417.R
import rs.raf.projekat1.marko_vesovic_rn2417.model.Patient
import rs.raf.projekat1.marko_vesovic_rn2417.view.recycler.diff.PatientDiffItemCallback
import rs.raf.projekat1.marko_vesovic_rn2417.view.recycler.viewholder.PatientViewHolderHospitalized
import rs.raf.projekat1.marko_vesovic_rn2417.view.recycler.viewholder.PatientViewHolderWaitingRoom

class PatientAdapterHospitalized(
    PatientDiffItemCallback: PatientDiffItemCallback,
    private val onFileButtonClick: (Patient) -> Unit,
    private val onRecoveredButtonClick: (Patient) -> Unit) : ListAdapter<Patient, PatientViewHolderHospitalized>(PatientDiffItemCallback) {

    private val onFileButtonClicked: (Int) -> Unit = {
        onFileButtonClick.invoke(getItem(it))
    }
    private val onRecoveredButtonClicked: (Int) -> Unit = {
        onRecoveredButtonClick.invoke(getItem(it))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PatientViewHolderHospitalized {
        val layoutInflater = LayoutInflater.from(parent.context)
        val containerView = layoutInflater.inflate(R.layout.layout_patient_list_item_hospitalized, parent, false)
        return PatientViewHolderHospitalized(containerView, onFileButtonClicked, onRecoveredButtonClicked)
    }

    override fun onBindViewHolder(holderWaitingRoom: PatientViewHolderHospitalized, position: Int) {
        val patient = getItem(position)
        holderWaitingRoom.bind(patient)
    }
}