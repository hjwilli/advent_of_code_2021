import static FileUtils.readList as inputReader
//import static FileUtils.readListOfLists as inputReader


//-- setup --
DAY = // number val of the puzzle day, used with file input pathing
def inputColTypes = String
//--

def input = getExample(inputColTypes)
//def input = getInput(inputColTypes)



def res1 = part1(input)
println "res1: $res1"

def res2 = part2(input)
println "res2: $res2"


// ---
def part1(def input) {

}

def part2(def input) {

}


// ---inputs
def formatInput(def data) {
	return data
}

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
