package com.codigo.aplios.sdk.core.kotlin

fun main(args: Array<String>) {

	val list = mutableListOf(1, 2, 3, 1, 2, 223, 23)
	//list.swap(0, 2) // 'this' inside 'swap()' will hold the value of 'l'

	println(list);

	var array: Array<String> = arrayOf("dupa", "dupa1", "cycuszki", "cipezczka", "brzuszek")
    var array1: Array<Int> = arrayOf(1,9,2)
	
	array1.swap(1,2)
		
	
	var iter = arrayIteratorK<String>(array)

	while (iter.hasNext())
		println(iter.next())

}

fun Array<Int>.swap(index1: Int, index2: Int) {

	var swap = this[index1]

	this[index1] = index2;
	this[index2] = swap;
}