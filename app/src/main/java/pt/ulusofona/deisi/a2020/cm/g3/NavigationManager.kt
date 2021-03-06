package pt.ulusofona.deisi.a2020.cm.g3

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import pt.ulusofona.deisi.a2020.cm.g3.viewmodel.TestDetailViewModel
import pt.ulusofona.deisi.a2020.cm.g3.views.*

abstract class NavigationManager {
    companion object {
        private fun placeFragment(fm: FragmentManager, fragment: Fragment) {
            val transition = fm.beginTransaction()
            transition.replace(R.id.frame, fragment)
            transition.addToBackStack(null)
            transition.commit()
        }

        fun goToDashboard(fm: FragmentManager) {
            placeFragment(fm, DashboardFragment())
        }
        fun goToContacts(fm: FragmentManager) {
            placeFragment(fm, ContactosFragment())
        }
        fun goToList(fm: FragmentManager) {
            placeFragment(fm, ListaTestesFragment())
        }
        fun goToVaccination(fm: FragmentManager) {
            placeFragment(fm, VacinacaoFragment())
        }
        fun registerTest(fm: FragmentManager) {
            placeFragment(fm, RegistoTesteFragment())
        }
        fun testDetail(fm: FragmentManager, uuid: String) {
            placeFragment(fm, TestDetailFragment(uuid))
        }
        fun photoViewer(fm: FragmentManager, vm: TestDetailViewModel) {
            placeFragment(fm, PhotoViewFragment(vm))
        }

    }
}