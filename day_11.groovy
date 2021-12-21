import static FileUtils.*
import Matrix


//-- setup --
DAY = 11 // number val of the puzzle day, used with file input pathing
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
def part1(def grid) {
	def count = 0

	(0..1).each {
		def tmp 
		gridAdd(grid, 1)
		(grid, tmp) = flash(grid)
		count = count + tmp

		Matrix.printMatrix(grid)
	}

	return count
}

def part2(def input) {

}

def gridAdd(def grid, def val) {
	(0..(grid.size() - 1)).each { i ->
		(0..(grid.first().size() - 1)).each { j ->
			grid[i][j] = grid[i][j] + val
		}
	}
	return grid
}

def flash(def grid) {
	def fCount = 0

	while ( grid.flatten().find { it > 9} {

	}


	return [grid, fCount]
}

// ---inputs
/*
*/
def readFile(def filename, def types) {
	
	/*return formatInput(
		readList(filename, types)
	)
	*/

	
	def res = []

	def file = new File(filename)
	file.withReader { reader ->

		while ((line = reader.readLine()) != null)  {
			coords = line.toList().collect { it as int }
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


