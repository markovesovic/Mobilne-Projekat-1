package rs.raf.projekat1.marko_vesovic_rn2417.view.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import kotlinx.android.synthetic.main.fragment_input.*
import rs.raf.projekat1.marko_vesovic_rn2417.R
import rs.raf.projekat1.marko_vesovic_rn2417.model.Patient
import rs.raf.projekat1.marko_vesovic_rn2417.viewmodels.RecyclerViewModel
import java.util.*

class InputFragment : Fragment(R.layout.fragment_input) {

    private val sharedViewModel : RecyclerViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }
    private fun init() {
        initListeners()
    }
    private fun initListeners() {
        addB.setOnClickListener {

            val name = nameEt.text.toString()
            val lastName = lastNameEt.text.toString()
            val symptoms = symptomsEt.text.toString()
            if(name != "" && lastName != "" && symptoms != "") {
                val id = sharedViewModel.getPatientsLength()

                val patient = Patient(
                    id + 1,
                    name,
                    lastName,
                    symptoms,
                    "https://avatarfiles.alphacoders.com/128/128984.png",
                    Date(),
                    null,
                    null
                )
                sharedViewModel.addWaitingRoomPatient(patient)
                Toast.makeText(this.context, "Uspesno dodat", Toast.LENGTH_SHORT).show()
                nameEt.setText("")
                lastNameEt.setText("")
                symptomsEt.setText("")
            } else {
                Toast.makeText(this.context, "Morate popuniti sva polja", Toast.LENGTH_SHORT).show()
            }

        }
    }
}