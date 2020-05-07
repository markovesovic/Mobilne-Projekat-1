package rs.raf.projekat1.marko_vesovic_rn2417.view.recycler.viewholder

import android.annotation.SuppressLint
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.layout_patient_list_item_recovered.*
import kotlinx.android.synthetic.main.layout_patient_list_item_waiting_room.*
import kotlinx.android.synthetic.main.layout_patient_list_item_waiting_room.lastNameTv
import kotlinx.android.synthetic.main.layout_patient_list_item_waiting_room.nameTv
import kotlinx.android.synthetic.main.layout_patient_list_item_waiting_room.patientPictureIv
import rs.raf.projekat1.marko_vesovic_rn2417.model.Patient
import java.text.SimpleDateFormat

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class PatientViewHolderRecovered
    (override val containerView: View,
    onItemClicked: (Int) -> Unit) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    init {
        patientPictureIv.setOnClickListener {
            onItemClicked.invoke(adapterPosition)
        }
    }

    @SuppressLint("SimpleDateFormat", "WeekBasedYear")
    fun bind(patient: Patient) {
        Picasso
            .get()
            .load(patient.picture)
            .into(patientPictureIv)
        nameTv.text = patient.name
        lastNameTv.text = patient.lastName
        recoveredTv.text = SimpleDateFormat("dd/MM/YYYY").format(patient.releasingDate).toString()
    }
}