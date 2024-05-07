package id.ac.ukdw.skripsilansiaku

object Utils {
    const val CONFIRMATION_DATA = "data-confirmation"
    const val JADWAL_DATA = "data-jadwal"

    val CASHLESS = arrayListOf(
        "Bank BNI",
        "Bank BCA",
        "Bank BRI",
        "GOPAY",
        "DANA",
        "OVO"
    )

    const val BIAYA_CASHLESS = "2.000"

}

fun moneyToInt(inputString: String): Int {
    return inputString.replace(".", "").replace("Rp ", "").toInt()
}

fun intToMoney(amount: Int): String{
    return String.format("Rp. %,d", amount)
}