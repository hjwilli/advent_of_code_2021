import static FileUtils.*


//-- setup --
DAY = 9 // number val of the puzzle day, used with file input pathing
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
def part1(def grid) {
	def minIdx = [] // 2d list, row [min indicies]

	def risk = 0


	(0..grid.size()-1).each{ i ->
		(0..grid.first().size()-1).each{ j ->
			/*println "$i $j"
			println grid.size()-1
			println (i+1 < grid.size()-1 ) 
			*/

			if ( ( (i-1 < 0) 					|| ( grid[i][j] < grid[i-1][j] ) ) &&
				 ( (i+1 > grid.size()-1 ) 		|| ( grid[i][j] < grid[i+1][j] ) ) &&
				 ( (j-1 < 0) 					|| ( grid[i][j] < grid[i][j-1] ) ) &&
				 ( (j+1 > grid.first().size()-1 ) || ( grid[i][j] < grid[i][j+1] ) ) 
			) {
				minIdx << [i,j]
				risk = risk + 1 + grid[i][j]
			}

		}
	}

	//println minIdx

	return risk

}


def part2(def grid) {

	def minIdx = [] // 2d list, row [min indicies]

	def risk = 0


	(0..grid.size()-1).each{ i ->
		(0..grid.first().size()-1).each{ j ->

			if ( ( (i-1 < 0) 					|| ( grid[i][j] < grid[i-1][j] ) ) &&
				 ( (i+1 > grid.size()-1 ) 		|| ( grid[i][j] < grid[i+1][j] ) ) &&
				 ( (j-1 < 0) 					|| ( grid[i][j] < grid[i][j-1] ) ) &&
				 ( (j+1 > grid.first().size()-1 ) || ( grid[i][j] < grid[i][j+1] ) ) 
			) {
				minIdx << [i,j]
				risk = risk + 1 + grid[i][j]
			}

		}
	}

	def basinIdx =  []


	minIdx.each { i, j ->
		basinIdx << recursiveRadiate(grid, [], i, j)
	}

	def res =  basinIdx*.size().sort().reverse()
	return res[0] * res[1] * res[2]
}

def recursiveRadiate(def grid, def basin, def i, def j) {
	//println "rr - [$i, $j] : $basin"

	if ( ( grid[i][j] != 9 ) ) {	
		//println "\tadding [$i, $j] : ${grid[i][j]}"
		basin << [i,j]

		[-1, 1].each {iMod ->
			[0].each {jMod ->
				if ((inBounds(grid, i+iMod, j+jMod))  && ( ! ( [i+iMod, j+jMod] in basin ) ))
					basin = recursiveRadiate( grid, basin, i+iMod, j+jMod )
			}

		}

		[0].each {iMod ->
			[-1, 1].each {jMod ->
				if ((inBounds(grid, i+iMod, j+jMod))   && ( ! ( [i+iMod, j+jMod] in basin ) ))
					basin = recursiveRadiate( grid, basin, i+iMod, j+jMod )
			}

		}
	}

	return basin

}

def inBounds(def grid, def i, def j) {
		if ( ( (i < 0) 						) ||
			 ( (i > grid.size()-1 ) 		) ||
			 ( (j < 0) 						) ||
			 ( (j > grid.first().size()-1 ) ) 
			)
		return false

		return true
}


// ---inputs
/*
*/
def readFile(def filename, def types) {
	

	
	def res = []

	def file = new File(filename)
	file.withReader { reader ->

		// 0,9 -> 5,9
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
