import static FileUtils.*


//-- setup --
DAY = 8 // number val of the puzzle day, used with file input pathing
def inputColTypes = Long
//--


// -- test input--
def input = getExample(inputColTypes)

println input.first()

println "--test"
def res1 = part1(input)
println "res1: $res1"

def res2 = part2(input)
println "res2: $res2"


// -- real input --
println "\n--input"
input = getInput(inputColTypes)
res1 = part1(input)
println "res1: $res1"

res2 = part2(input)
println "res2: $res2"


// ---
def part1(def input) {
	def result = ""

	input.each { rule, display ->
		def lookup = getDigitLookup(rule)
		def val = ""

		display.each {
			if ( it in lookup ) val = val + lookup[it]

		}

		result = result + val + " "
	}

	return result.findAll("1").size() + 
		result.findAll("4").size() + 
		result.findAll("7").size() + 
		result.findAll("8").size() 
}

def part2(def input) {
	def result = []

	input.each { rule, display ->
		def lookup = getDigitLookup2(rule)
		def val = ""

		display.each {
			if ( it in lookup ) val = val + lookup[it]

		}

		/*
		println "---"
		println lookup
		println "$display -> $val"
		*/
		result << (val as int)
	}

	return result.sum()
}



def getDigitLookup(def input) {
	def digits = [:]

	input.each {
		if 		(it.size() == 2 ) digits[it] = "1"
		else if (it.size() == 4 ) digits[it] = "4" 
		else if (it.size() == 3 ) digits[it] = "7" 
		else if (it.size() == 7 ) digits[it] = "8" 
	}

	return digits
}

/*
*
0 = abc efg  	: 6	:
1 =   c  f  	: 2	: *
2 = a cde g  	: 5	:
3 = a cd fg  	: 5	:
4 =  bcd f  	: 4	: *
5 = ab d fg  	: 5	:
6 = ab defg  	: 6	:
7 = a c  f   	: 3	: *
8 = abcdefg  	: 7	: *
9 = abcd fg 	: 6 :
*/
def getDigitLookup2(def input) {
	def digits = [:]

	input.each {
		if 		(it.size() == 2 ) digits["1"] = it 
		else if (it.size() == 4 ) digits["4"] = it 
		else if (it.size() == 3 ) digits["7"] = it 
		else if (it.size() == 7 ) digits["8"] = it 
	}

	//input.remove(digits.keys)

	input.each {
		// 0, 6, 9
		if (it.size() == 6 ) {

			if 		(it.containsAll(digits["4"])) digits["9"] = it 
			else if (it.containsAll(digits["7"])) digits["0"] = it 
			else								  digits["6"] = it 
		}
		
	}

	//input.remove(digits.keys)

	input.each {
		// 2, 3, 5
		if (it.size() == 5) {
			def test = digits["6"].minus(digits["9"])

			if 		(it.containsAll(digits["1"])) digits["3"] = it 
			else if (it.containsAll(test) )		  digits["2"] = it 
			else								  digits["5"] = it 

		}
	}


	return digits.collectEntries { e -> [(e.value): e.key] }
}


// ---inputs
/*
*/
def readFile(def filename, def types) {
	

	def res = []

	def file = new File(filename)
	file.withReader { reader ->

		while ((line = reader.readLine()) != null)  {
			row = line.split("\\|").collect { it
				it.split().collect{ it.trim().toSet() } 
			}
			res << row
		}
	}

	return res
	
}

/*  use if formatting a basis list or LoL
*/
def formatInput(def data) {
	return data
}

def getExample(def types) {
	readFile("input/day_$DAY/test.txt", types)
}

def getInput(def types) {
	readFile("input/day_$DAY/input.txt", types)
}
