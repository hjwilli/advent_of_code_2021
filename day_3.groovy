import static FileUtils.readList as inputReader
//import static FileUtils.readListOfLists as inputReader


//-- setup --
DAY = 3 // number val of the puzzle day, used with file input pathing
def inputColTypes = String
//--


/*
* returns map [colIdx:[values]]
*/
def formatInput(def data) {

	def res = [:].withDefault( k -> [] )

	data.each { row ->
		row.eachWithIndex { val, c ->
			res[c].add(val as int)
		}
	}

	return res
}


//def input = getExample(inputColTypes)
def input = getInput(inputColTypes)

//println input

def res1 = part1(input)
println "res1: $res1"


def res2 = part2(input)
println "res2: $res2"


// ---
def part1(def input) {
	def g = 0
	def e = 0

	def p = input.size() - 1
	def t = ""

	input.each {col, vals ->
		if (vals.sum() > vals.size()/2) {
			g = g + (2 ** (p - col) )
			//println "$g, $t"
			t = t + "1"
		}
		else {
			e = e + (2 ** (p - col) )
			t = t + "0"
		}
	}

	def res = g * e

	return res

}


def part2(def input) {

	def o = 0..input[0].size() - 1
	def c = 0..input[0].size() - 1

	input.each {col, vals ->
		def oCommon
		def cCommon

		if ( o.size() > 1 ) {
			if (vals[o].sum() >= o.size()/2) oCommon = 1
			else oCommon = 0

			o = o - vals.findIndexValues { v -> v != oCommon}
		}

		if ( c.size() > 1 ) {
			if (vals[c].sum() >= c.size()/2) cCommon = 1
			else cCommon = 0
		
			c = c - vals.findIndexValues { v -> v == cCommon}
		}

		/*
		println "\ncol: $col"
		println "common: $cCommon, c: $c"
		println vals
		println vals.findIndexValues { v -> v == cCommon}
		*/
	}

	def oStr = ""
	def cStr = ""
	input.each { k,v -> 
		oStr = oStr + v[o.first()] as String 
		cStr = cStr + v[c.first()] as String 
	}

	return binToDec(oStr) * binToDec(cStr)

}

def binToDec(def val) {
	res = 0
	val.reverse().eachWithIndex { v, i ->
		res = res + (2**i) * (v as int)
	}

	return res
}



//-----
def getExample(def types) {
	return formatInput(
		inputReader("input/day_$DAY/test.txt", types)
	)
}

def getInput(def types) {
	return formatInput(
		inputReader("input/day_$DAY/input.txt", types)
	)
}
