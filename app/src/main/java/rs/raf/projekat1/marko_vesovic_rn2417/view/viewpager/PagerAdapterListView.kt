package rs.raf.projekat1.marko_vesovic_rn2417.view.viewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import rs.raf.projekat1.marko_vesovic_rn2417.view.fragments.*

class PagerAdapterListView(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    companion object {
        private const val ITEM_COUNT = 3
        const val WAITING_ROOM_FRAGMENT = 0
        const val HOSPITALIZED_FRAGMENT = 1
        const val RECOVERED_FRAGMENT = 2
    }

    override fun getItem(position: Int): Fragment {
        return when(position) {
            WAITING_ROOM_FRAGMENT -> WaitingRoomFragment()
            HOSPITALIZED_FRAGMENT -> HospitalizedFragment()
            else -> RecoveredFragment()
        }
    }

    override fun getCount(): Int {
        return ITEM_COUNT
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position) {
            WAITING_ROOM_FRAGMENT -> "Cekaonica"
            HOSPITALIZED_FRAGMENT -> "Hospitalizovani"
            else -> "Otpusteni"
        }
    }
}