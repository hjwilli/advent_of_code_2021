import static FileUtils.*


//-- setup --
DAY = 14 // number val of the puzzle day, used with file input pathing
def inputColTypes = Long
//--


// -- test input--
def input = getExample(inputColTypes)

println input
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
def part1(def string, def rules) {
	//println string
	//println rules


	def s2

	(1..10).each{ itr ->
		s2 = string[0]
		(1..string.size()-1).each { i ->

			def test = string[i-1] + string[i]
			if (test in rules) {
				s2 = s2 + rules[test]
			}
			s2 = s2 + string[i]

			//println "--$i $test"
			//println s2
		}

		//println "After step $itr: $s2"

		string = s2
	}

	return getScore(string)
}

def part2(def string, def rules) {

	def poly = [:].withDefault{it = 0}
	def ends = [string[0], string[-1]]

	(1..string.size()-1).each { i ->
		def pair = string[i-1] + string[i]
		poly[pair] = poly[pair] + 1
	}

	println poly

	def poly2

	(1..40).each{ itr ->

		poly2 = [:].withDefault{it = 0 as Long}

		poly.each {pair, c ->
			if (pair in rules) { 
				p1 = pair[0] + rules[pair]
				p2 = rules[pair] +  pair[1] 

				poly2[p1] = poly2[p1] + c
				poly2[p2] = poly2[p2] + c
			}
			else poly2[pair] = poly2[pair] + c
		}

		poly = poly2

		//println "After step $itr: $poly"
	}

	return getScoreMap(poly, ends)

}


def getScore(def str) {
	def chars = str.toSet()
	def counts = [:]

	chars.each { c ->
		counts[c] = str.findAll(c).size()
	}

	println counts

	return counts.values().max() - counts.values().min()
}

def getScoreMap(def poly, def ends) {
	def counts = [:].withDefault{it=0 as Long}

	poly.each { p, c ->
		counts[p[0]] = counts[p[0]] + c
		counts[p[1]] = counts[p[1]] + c
	}

	counts[ends[0]] = counts[ends[0]] + 1
	counts[ends[1]] = counts[ends[1]] + 1

	println counts

	return (counts.values().max()/2) - (counts.values().min()/2)
}

// ---inputs
/*
*/
def readFile(def filename, def types) {
	

	def string
	def ruleList = []

	def file = new File(filename)

	

	file.withReader { reader ->
		string = reader.readLine()
		reader.readLine()

		while ((line = reader.readLine()) != null)  {
			ruleList << line.split(" -> ").collect{ it -> it.trim() }
		}
	}

	rules = [:]
	ruleList.each {
		rules[it[0]] = it[1]
	}

	return [string, rules]
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
