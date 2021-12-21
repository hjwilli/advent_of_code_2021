import static FileUtils.*


//-- setup --
DAY = 15 // number val of the puzzle day, used with file input pathing
def inputColTypes = Long
//--


// -- test input--
def input = getExample(inputColTypes)
printGrid(input)
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
cGrid
grid

iter

def part1(def input) {
	grid = input
	cGrid = []
	iter = 0 

	grid.each { 
		cGrid << it.collect { 10000 }
	}

	//printGrid(cGrid)

	//cGrid[0][0] = 100
	recursiveRadiate(0, 0)

	printGrid(cGrid)
	println "iterations: $iter"

	return cGrid[-1][-1]
}

def part2(def input) {

}

//@groovy.transform.TailRecursive
def recursiveRadiate(def i, def j) {
	iter = iter + 1
	//println "rr - [$i, $j]"
	//printGrid(cGrid)

	if (iter % 100 == 0) {
		println "iteration $iter"
	}


	def cost

	if (i == 0 && j == 0 ) cost = 0
	else {
		//cost = grid[i][j] + getMinCardinalCost(i, j)
		cost = getCost(i, j)
	}

	if ( cost < cGrid[i][j] ) {	
		//println "\t updating cost: [$i, $j]  $cost"
		//printGrid(cGrid)

		cGrid[i][j] = cost


		def iIdx = [1, -1, 0, 0]
	    def jIdx = [0, 0, 1, -1]

	    (0..3).each {
	    	testI = i + iIdx[it]
			testJ = j + jIdx[it]

			if ( inBounds(testI, testJ) ) {
				if ( ( cGrid[testI][testJ] == 10000 ) || ( getCost( testI, testJ ) < cGrid[testI][testJ] ) ) {
					recursiveRadiate( testI, testJ )
				}
			}

		}
	}
}


Long getCost(i, j) {
	def iIdx = [1, -1, 0, 0]
	def jIdx = [0, 0, 1, -1]

	def res = []
	(0..3).each {
		def testI = i + iIdx[it]
		def testJ = j + jIdx[it]
		if ( inBounds(testI, testJ) ) res << cGrid[ testI ][ testJ ]
	}

	return res.min() + grid[i][j]
}

def getMinCardinalCost(i, j) {
	def iIdx = [1, -1, 0, 0]
	def jIdx = [0, 0, 1, -1]

	def res = []
	(0..3).each {
		def testI = i + iIdx[it]
		def testJ = j + jIdx[it]
		if ( inBounds(cGrid, testI, testJ) ) res << cGrid[ testI ][ testJ ]
	}

	return res.min()
}

def inBounds(def i, def j) {
		if ( ( (i < 0) 						) ||
			 ( (i > grid.size()-1 ) 		) ||
			 ( (j < 0) 						) ||
			 ( (j > grid.first().size()-1 ) ) 
			)
		return false

		return true
}

def printGrid(def grid) {
	println "---"
	grid.each {
		println it.join(" ")
	}
}


// ---inputs
/*
*/
def readFile(def filename, def types) {
	
	def res = []

	def file = new File(filename)
	file.withReader { reader ->

		while ((line = reader.readLine()) != null)  {
			coords = line.toList().collect { it as int
			}
			res << coords
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
