package rs.raf.projekat1.marko_vesovic_rn2417.view.recycler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import rs.raf.projekat1.marko_vesovic_rn2417.R
import rs.raf.projekat1.marko_vesovic_rn2417.model.Patient
import rs.raf.projekat1.marko_vesovic_rn2417.view.recycler.diff.PatientDiffItemCallback
import rs.raf.projekat1.marko_vesovic_rn2417.view.recycler.viewholder.PatientViewHolderRecovered

class PatientAdapterRecovered (
    PatientDiffItemCallback: PatientDiffItemCallback,
    private val onPatientClicked: (Patient) -> Unit) : ListAdapter<Patient, PatientViewHolderRecovered>(PatientDiffItemCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PatientViewHolderRecovered {
        val layoutInflater = LayoutInflater.from(parent.context)
        val containerView =
            layoutInflater.inflate(R.layout.layout_patient_list_item_recovered, parent, false)
        return PatientViewHolderRecovered(containerView) {
            val patient = getItem(it)
            onPatientClicked.invoke(patient)
        }
    }

    override fun onBindViewHolder(holderRecovered: PatientViewHolderRecovered, position: Int) {
        val patient = getItem(position)
        holderRecovered.bind(patient)
    }
}