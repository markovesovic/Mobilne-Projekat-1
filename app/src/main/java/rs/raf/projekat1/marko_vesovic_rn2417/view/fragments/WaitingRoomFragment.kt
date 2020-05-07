package rs.raf.projekat1.marko_vesovic_rn2417.view.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
import kotlinx.android.synthetic.main.fragment_waiting_room.*
import kotlinx.android.synthetic.main.layout_patient_list_item_waiting_room.*
import rs.raf.projekat1.marko_vesovic_rn2417.R
import rs.raf.projekat1.marko_vesovic_rn2417.model.Patient
import rs.raf.projekat1.marko_vesovic_rn2417.view.recycler.adapter.PatientAdapterHospitalized
import rs.raf.projekat1.marko_vesovic_rn2417.view.recycler.adapter.PatientAdapterWaitingRoom
import rs.raf.projekat1.marko_vesovic_rn2417.view.recycler.diff.PatientDiffItemCallback
import rs.raf.projekat1.marko_vesovic_rn2417.viewmodels.RecyclerViewModel
import timber.log.Timber
import java.util.*

class WaitingRoomFragment : Fragment(R.layout.fragment_waiting_room) {

    private val sharedViewModel : RecyclerViewModel by activityViewModels()

    private lateinit var patientAdapterWaitingRoom: PatientAdapterWaitingRoom


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        initUi()
    }
    private fun initUi() {
        initListeners()
        initRecycler()
        initObservers()
    }

    private val onHealthyButtonClicked : (Patient) -> Unit = {
        it.releasingDate = Date()
        sharedViewModel.removeWaitingRoomPatient(it)
        sharedViewModel.addRecoveredPatient(it)
    }
    private val onHospitalizeButtonClicked : (Patient) -> Unit = {
        it.hospitalizationDate = Date()
        sharedViewModel.removeWaitingRoomPatient(it)
        sharedViewModel.addHospitalizedPatient(it)
    }

    private fun initRecycler() {

        listRv.layoutManager = LinearLayoutManager(this.context)
        patientAdapterWaitingRoom = PatientAdapterWaitingRoom(PatientDiffItemCallback(), onHealthyButtonClicked, onHospitalizeButtonClicked)
        listRv.adapter = patientAdapterWaitingRoom
    }

    private fun initListeners() {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                sharedViewModel.filterWaitingRoomPatients(searchView.query.toString())
                return false
            }
        })
    }
    private fun initObservers() {
        this.activity?.let {
            sharedViewModel.getWaitingRoomPatients().observe(it, Observer {
                patientAdapterWaitingRoom.submitList(it)
            })
        }
    }

}