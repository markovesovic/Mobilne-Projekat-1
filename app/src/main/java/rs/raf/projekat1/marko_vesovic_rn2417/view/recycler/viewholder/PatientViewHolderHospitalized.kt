package rs.raf.projekat1.marko_vesovic_rn2417.view.recycler.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.layout_patient_list_item_hospitalized.*
import kotlinx.android.synthetic.main.layout_patient_list_item_waiting_room.*
import kotlinx.android.synthetic.main.layout_patient_list_item_waiting_room.lastNameTv
import kotlinx.android.synthetic.main.layout_patient_list_item_waiting_room.nameTv
import kotlinx.android.synthetic.main.layout_patient_list_item_waiting_room.patientPictureIv
import rs.raf.projekat1.marko_vesovic_rn2417.model.Patient

class PatientViewHolderHospitalized
    (override val containerView: View,
    onFileButtonClicked: (Int) -> Unit,
    onRecoveredButtonClicked: (Int) -> Unit) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    init {
        fileB.setOnClickListener {
            onFileButtonClicked.invoke(adapterPosition)
        }
        recoveredB.setOnClickListener {
            onRecoveredButtonClicked.invoke(adapterPosition)
        }
    }

    fun bind(patient: Patient) {
        Picasso
            .get()
            .load(patient.picture)
            .into(patientPictureIv)
        nameTv.text = patient.name
        lastNameTv.text = patient.lastName
    }
}