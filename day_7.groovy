import static FileUtils.*



//-- setup --
DAY = 7 // number val of the puzzle day, used with file input pathing
def inputColTypes = Long
//--



// -- test input--
def input = getExample(inputColTypes)
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

	def cost = [:]
	
	( input.min()..input.max() ).each { l ->
		cost[l] = getCost(input, l)
	}

	return cost.values().min()

}

def part2(def input) {
	def cost = [:]
	
	( (input.min())..(input.max()) ).each { l ->
		cost[l] = getCost2(input, l)
	}

	return cost.values().min()

}

def getCost(def input, def l) {
	def res = 0

	input.each { i ->
		res = res + Math.abs(i - l)
	}

	return res
}

def getCost2(def input, def l) {
	Long res = 0

	input.each { i ->
		a = Math.abs(i - l) 
		res = res + ( ( a * (a+1) )/2 )
	}

	return res
}


// ---inputs
/*
*/
def readFile(def filename, def types) {
	
	return formatInput(
		readSingleLineList(filename, types)
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
