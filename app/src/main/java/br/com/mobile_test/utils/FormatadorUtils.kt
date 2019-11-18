package br.com.mobile_test.utils

import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

object FormatadorUtils {

    fun formataValor(double: Double): String {
        val locale = Locale("pt", "BR")
        val currencyInstance = NumberFormat.getCurrencyInstance(locale)
        return currencyInstance.format(double)
    }

    fun formataData(data: String): String {
        val locale = Locale("pt", "BR")
        val convertedDate = SimpleDateFormat("yyyy-MM-dd",locale).parse(data)
        return SimpleDateFormat("dd/MM/yyyy",locale).format(convertedDate!!)
    }

    fun formataDuracao(duracao:Int):String{
        return "${duracao/60} horas e ${duracao%2} minutos"
    }
}