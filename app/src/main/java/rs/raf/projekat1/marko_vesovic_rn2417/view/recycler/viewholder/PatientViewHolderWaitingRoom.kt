package rs.raf.projekat1.marko_vesovic_rn2417.view.recycler.viewholder

import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.layout_patient_list_item_waiting_room.*
import rs.raf.projekat1.marko_vesovic_rn2417.model.Patient
import rs.raf.projekat1.marko_vesovic_rn2417.view.activities.MainActivity

class PatientViewHolderWaitingRoom(
    override val containerView: View,
    onHealthyButtonClicked: (Int) -> Unit,
    onHospitalizeButtonClicked: (Int) -> Unit) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    init {
        healthyB.setOnClickListener {
            if(adapterPosition != RecyclerView.NO_POSITION) {
                onHealthyButtonClicked.invoke(adapterPosition)
            }
        }
        hospitalizationB.setOnClickListener {
            if(adapterPosition != RecyclerView.NO_POSITION) {
                onHospitalizeButtonClicked.invoke(adapterPosition)
            }
        }

    }
    fun bind(patient: Patient) {
        Picasso
            .get()
            .load(patient.picture)
            .into(patientPictureIv)
        nameTv.text = patient.name
        lastNameTv.text = patient.lastName
        symptomsTv.text = patient.symptoms

    }
}