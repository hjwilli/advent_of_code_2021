import static FileUtils.readList as inputReader
//import static FileUtils.readListOfLists as inputReader


//-- setup --
DAY = 5// number val of the puzzle day, used with file input pathing
def inputColTypes = String
//--

//def input = getExample(inputColTypes)
def input = getInput(inputColTypes)


def res1 = part1(input)
println "res1: $res1"

def res2 = part2(input)
println "res2: $res2"


// ---
def part1(def input) {

	def points = [:].withDefault( k -> 0 )

	input.each { a, b ->

		if ( (a[0] == b[0])) {
			(a[1]..b[1]).each { y ->
				x = a[0]
				points[ [x,y] ] = points[ [x, y] ] + 1
			}

		}
		else if (a[1] == b[1]) {
			(a[0]..b[0]).each { x ->
				y = a[1]
				points[ [x,y] ] = points[ [x, y] ] + 1
			}
		}

	}

	return points.findAll { k, v -> v > 1 }.size()

}

def part2(def input) {

	def points = [:].withDefault( k -> 0 )	

	input.each { a, b ->


		if ( (a[0] == b[0]) ) {
			(a[1]..b[1]).each { y ->
				x = a[0]
				points[ [x,y] ] = points[ [x, y] ] + 1
			}

		}
		else if (a[1] == b[1]) {
			(a[0]..b[0]).each { x ->
				y = a[1]
				points[ [x,y] ] = points[ [x, y] ] + 1
			}
		}

		else {
			xLst = (a[0]..b[0])
			yLst = (a[1]..b[1])

			(0..(xLst.size() - 1)).each { i ->
				x = xLst[i]
				y = yLst[i]
				points[ [x,y] ] = points[ [x, y] ] + 1
			}
		}
		
	}

	return points.findAll { k, v -> v > 1 }.size()

}


// ---inputs
def formatInput(def filename) {
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
}

def getExample(def types) {
	return formatInput("input/day_$DAY/test.txt")

}

def getInput(def types) {
	return formatInput("input/day_$DAY/input.txt")

}
