package rs.raf.projekat1.marko_vesovic_rn2417.view.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_state.*
import rs.raf.projekat1.marko_vesovic_rn2417.R
import rs.raf.projekat1.marko_vesovic_rn2417.viewmodels.RecyclerViewModel

class StateFragment: Fragment(R.layout.fragment_state) {

    private val recyclerViewModel : RecyclerViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        initObservers()
    }

    private fun initObservers() {
        recyclerViewModel.getWaitingRoomPatients().observe(viewLifecycleOwner, Observer {
            brojPacijenataTv1.text = recyclerViewModel.getWaitingRoomPatientsList().size.toString()
        })
        recyclerViewModel.getHospitalizedPatients().observe(viewLifecycleOwner, Observer {
            brojHospitalizovanihPacijenataTv1.text = recyclerViewModel.getHospitalizedPatientList().size.toString()
        })
        recyclerViewModel.getRecoveredPatients().observe(viewLifecycleOwner, Observer {
            brojOtpustenihPacijenataTv1.text = recyclerViewModel.getRecoveredPatientsList().size.toString()
        })
    }
}