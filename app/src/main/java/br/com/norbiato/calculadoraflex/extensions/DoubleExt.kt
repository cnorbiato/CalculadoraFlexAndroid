package br.com.norbiato.calculadoraflex.extensions

fun Double.format(digits: Int) =
    java.lang.String.format("%.${digits}f", this)