package pt.ulusofona.deisi.a2020.cm.g3.data.battery

interface OnBatteryCurrentListener {
    fun onCurrentChanged (current: Float)
}