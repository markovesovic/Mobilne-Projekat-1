package rs.raf.projekat1.marko_vesovic_rn2417.view.activities

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_patient_profile.*
import kotlinx.android.synthetic.main.activity_patient_profile.lastNameEt
import kotlinx.android.synthetic.main.activity_patient_profile.nameEt
import kotlinx.android.synthetic.main.fragment_input.*
import rs.raf.projekat1.marko_vesovic_rn2417.R
import rs.raf.projekat1.marko_vesovic_rn2417.model.Patient
import java.text.SimpleDateFormat

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class PatientProfileActivity : AppCompatActivity(R.layout.activity_patient_profile) {

    companion object {
        const val PATIENT_KEY = "patientKey"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
        initView()
        initListeners()
    }

    @SuppressLint("SimpleDateFormat", "WeekBasedYear")
    private fun initView() {

        lateinit var patient: Patient

        intent?.let {
            patient = it.getParcelableExtra(PATIENT_KEY)


            nameEt.setText(patient.name)
            lastNameEt.setText(patient.lastName)
            stateOnAdmissionEt.setText(patient.symptoms)
            currentStateEt.setText(patient.symptoms)
            dateOfAdmissionTv.text = SimpleDateFormat("dd/MM/YYYY").format(patient.admissionDate)
            dateOfAdmissionTv.isEnabled = false
        }

    }
    private fun initListeners() {

        lateinit var patient: Patient

        intent?.let {

            patient = it.getParcelableExtra(PATIENT_KEY)

            changeB.setOnClickListener {
                if(nameEt.text.toString() != "" &&
                    lastNameEt.text.toString() != "" &&
                    stateOnAdmissionEt.text.toString() != "" &&
                    currentStateEt.text.toString() != "") {

                    patient.name = nameEt.text.toString()
                    patient.lastName = lastNameEt.text.toString()
                    patient.symptoms = stateOnAdmissionEt.text.toString()
                    val returnIntent = Intent()
                    returnIntent.putExtra(PATIENT_KEY, patient)
                    setResult(Activity.RESULT_OK, returnIntent)
                    finish()

                } else {
                    Toast.makeText(this, "Morate popuniti sva polja", Toast.LENGTH_SHORT).show()
                }
            }
        }
        cancelB.setOnClickListener {
            setResult(Activity.RESULT_CANCELED)
            finish()
        }

    }
}
