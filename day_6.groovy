import static FileUtils.*
//import static FileUtils.readListOfLists as inputReader


//-- setup --
DAY = 6 // number val of the puzzle day, used with file input pathing
def inputColTypes = String
//--

def input = getExample(inputColTypes)


println "test"

def res1 = part1(input)
println "res1: $res1"

def res2 = part2(input)
println "res2: $res2"

println "input"
input = getInput(inputColTypes)
res1 = part1(input)
println "res1: $res1"

res2 = part2(input)
println "res2: $res2"

// ---
def part1(def input) {
	def fishCount = [:].withDefault{ k-> 0 }

	input.each { age ->
		fishCount[age] = fishCount[age] + 1
	}

	(1..80).each {
		//println fishCount
		fishCount = incrementDay(fishCount)
	}

	return fishCount.values().sum()

}

def part2(def input) {

	def fishCount = [:].withDefault{ k-> 0 as Long}

	input.each { age ->
		fishCount[age] = fishCount[age] + 1
	}

	(1..256).each {
		//println fishCount
		fishCount = incrementDay(fishCount)
	}

	return fishCount.values().sum()

}

def incrementDay(def fishCount) {

	def newFishCount = [:].withDefault { k-> 0 as Long }

	fishCount.each { age, count ->
		if (age == 0) { 
			newFishCount[8] = newFishCount[8] + count
			newFishCount[6] = newFishCount[6] + count
		}
		else newFishCount[age-1] = newFishCount[age-1] + count
	}


	return newFishCount

}


// ---inputs
/*
*/
def readFile(def filename, def types) {
	
	def lines = new File(filename).readLines()

	return lines.first().split(",").collect { k -> k as Long }

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
