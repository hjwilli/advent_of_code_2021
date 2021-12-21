import static FileUtils.*


//-- setup --
DAY = 10 // number val of the puzzle day, used with file input pathing
def inputColTypes = String
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

	def illegal = []

	def rules = [ 
	"(" : ")", 
	"[" : "]", 
	"{" : "}", 
	"<" : ">" ]

	def score = [
	    ")": 3 ,
	    "]": 57 ,
	    "}": 1197 ,
	    ">": 25137
	]

	def res = 0

	input.each { l ->
		def open = []
		
		l.each { c ->
			if (c in rules) open.push(c)
			else {
				def exp = rules[open.pop()]
				if (exp != c) {
					//println "expected $exp, got $c"
					illegal << l
					res = res + score[c]
					return
				}
			}
		}
	}

	return res

}

def part2(def input) {

	def rules = [ 
	"(" : ")", 
	"[" : "]", 
	"{" : "}", 
	"<" : ">" ]

	def score = [
	    ")": 1 ,
	    "]": 2 ,
	    "}": 3 ,
	    ">": 4
	]

	def res = []

	input.each { l ->
		def open = []
		def illegal = false
		l = l.toList()

		while( !illegal && l && (c = l?.pop()) ) {
			if (c in rules) open.push(c)
			else {
				def exp = rules[open.pop()]
				if (exp != c) {
					//println "expected $exp, got $c"
					illegal = true
					open = []
					return
				}
			}
		}

		if (open) {
			def tmp = 0 as Long
			open.each { c ->
				def exp = rules[c]
				tmp = tmp*5 + score[exp]
			}

			res << tmp
		}
	}

	println res
	println res.size()
	println res.sort()[51]

	return res.sort()[(res.size())/2]

}


// ---inputs
/*
*/
def readFile(def filename, def types) {
	
	return formatInput(
		readList(filename, types)
	)

	/*
	def res = []

	def file = new File(filename)
	file.withReader { reader ->

		// 0,9 -> 5,9
		while ((line = reader.readLine()) != null)  {
			coords = line.split(" -> ").collect { 
				it.split(",").collect{ it as int }
			}
			res << coords
		}
	}

	return res
	*/
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
