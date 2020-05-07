package rs.raf.projekat1.marko_vesovic_rn2417.view.fragments

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_waiting_room.*
import rs.raf.projekat1.marko_vesovic_rn2417.R
import rs.raf.projekat1.marko_vesovic_rn2417.view.recycler.adapter.PatientAdapterHospitalized
import rs.raf.projekat1.marko_vesovic_rn2417.view.recycler.adapter.PatientAdapterRecovered
import rs.raf.projekat1.marko_vesovic_rn2417.view.recycler.diff.PatientDiffItemCallback
import rs.raf.projekat1.marko_vesovic_rn2417.viewmodels.RecyclerViewModel
import timber.log.Timber

class RecoveredFragment : Fragment(R.layout.fragment_recovered) {
    private val sharedViewModel : RecyclerViewModel by activityViewModels()

    private lateinit var patientAdapterRecovered: PatientAdapterRecovered

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }
    private fun init() {
        initUi()
        initObservers()
    }
    private fun initUi() {
        initListeners()
        initRecycler()
    }

    private fun initRecycler() {
        listRv.layoutManager = GridLayoutManager(this.context, 2)
        patientAdapterRecovered = PatientAdapterRecovered(PatientDiffItemCallback()) {}
        listRv.adapter = patientAdapterRecovered
    }

    private fun initListeners() {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                //
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                sharedViewModel.filterRecoveredPatients(searchView.query.toString())
                return false
            }
        })
    }
    private fun initObservers() {
        this.activity?.let {
            sharedViewModel.getRecoveredPatients().observe(it, Observer {
                patientAdapterRecovered.submitList(it)
            })
        }
    }
}