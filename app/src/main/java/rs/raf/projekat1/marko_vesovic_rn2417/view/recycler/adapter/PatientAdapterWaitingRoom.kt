package rs.raf.projekat1.marko_vesovic_rn2417.view.recycler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import rs.raf.projekat1.marko_vesovic_rn2417.model.Patient
import rs.raf.projekat1.marko_vesovic_rn2417.view.recycler.diff.PatientDiffItemCallback
import rs.raf.projekat1.marko_vesovic_rn2417.view.recycler.viewholder.PatientViewHolderWaitingRoom
import androidx.recyclerview.widget.ListAdapter
import rs.raf.projekat1.marko_vesovic_rn2417.R

class PatientAdapterWaitingRoom(
    PatientDiffItemCallback: PatientDiffItemCallback,
    private val onHealthyButtonClick: (Patient) -> Unit,
    private val onHospitalizeButtonClick: (Patient) -> Unit) : ListAdapter<Patient, PatientViewHolderWaitingRoom>(PatientDiffItemCallback) {

    private val onHealthyButtonClicked: (Int) -> Unit = {
        onHealthyButtonClick.invoke(getItem(it))
    }
    private val onHospitalizedButtonClicked: (Int) -> Unit = {
        onHospitalizeButtonClick.invoke(getItem(it))
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PatientViewHolderWaitingRoom {
        val layoutInflater = LayoutInflater.from(parent.context)
        val containerView = layoutInflater.inflate(R.layout.layout_patient_list_item_waiting_room, parent, false)
        return PatientViewHolderWaitingRoom(containerView, onHealthyButtonClicked, onHospitalizedButtonClicked)
    }

    override fun onBindViewHolder(holderWaitingRoom: PatientViewHolderWaitingRoom, position: Int) {
        val patient = getItem(position)
        holderWaitingRoom.bind(patient)
    }

}