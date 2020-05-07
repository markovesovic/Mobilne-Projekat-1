package rs.raf.projekat1.marko_vesovic_rn2417.view.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
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

    override fun onResume() {
        super.onResume()
        initListeners()
    }
    private fun init() {
        initListeners()
    }
    private fun initListeners() {
        brojPacijenataTv1.text = recyclerViewModel.getWaitingRoomPatientsLength().toString()
        brojHospitalizovanihPacijenataTv1.text = recyclerViewModel.getHospitalizedPatientsLength().toString()
        brojOtpustenihPacijenataTv1.text = recyclerViewModel.getRecoveredPatientsLength().toString()
    }
}