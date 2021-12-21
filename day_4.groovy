import static FileUtils.readList as inputReader
//import static FileUtils.readListOfLists as inputReader


//-- setup --
DAY = 4 // number val of the puzzle day, used with file input pathing
def inputColTypes = String
//--

//def input = getExample(inputColTypes)
def input = getInput(inputColTypes)



def res1 = part1(input)
println "res1: $res1"

def res2 = part2(input)
println "res2: $res2"


// ---
def part1(def numbers, def boards) {
	
	def matches = ( 1..boards.size() ).collect { k -> [] }

	def winScore = 0

	numbers.each { n ->

		if (winScore) return

		boards.eachWithIndex { board, i ->
			matches[i] = matches[i] + findHits(board, n)

			if ( checkWin( matches[i] ) ) {
				winScore = getScore( board, matches[i], n )
				return
			}
		}

	}

	return winScore
	

}

def part2(def numbers, def boards) {

	def matches = ( 1..boards.size() ).collect { k -> [] }

	def winScore = 0
	def winners = [] as Set

	numbers.each { n ->

		if ( winners.size() == boards.size() ) return

		boards.eachWithIndex { board, i ->
			matches[i] = matches[i] + findHits(board, n)

			if ( !(i in winners ) && checkWin( matches[i] ) ) {
				winners << i
				winScore = getScore( board, matches[i], n )
				return
			}
		}

	}

	return winScore

}


def findHits(def board, def n) {
	hits = []

	(0..4).each { x ->
		(0..4).each { y -> 
			if ( board[x][y] == n ) hits << [x,y]
		}
	}

	return hits
	
}

def checkWin(def match) {
	def winner = false
	(0..4).each { chk ->
		if ( match.findAll{ x, y -> x == chk }.size() == 5 ) {
			winner = true
		}

		if ( match.findAll{ x, y -> y == chk }.size() == 5 ) {
			winner = true
		}
	}

	return winner
}

def getScore(board, match, lastNum) {

	def sum = 0

	(0..4).each { x ->
		(0..4).each { y -> 
			if ( !( [x, y] in match ) ) sum = sum + board[x][y]
		}
	}

	return sum * lastNum
}

// ---inputs
def formatInput(def data) {
	return data
}

def getExample(def types) {
	readBoards("input/day_$DAY/test.txt")
}

def getInput(def types) {
	readBoards("input/day_$DAY/input.txt")
}

def readBoards(def filename) {
	def numbers
	def boards = []
	def masks = []

	def file = new File(filename)

	file.withReader { reader ->

		numbers = reader.readLine().split(",").collect{ it as int }

		while ((line = reader.readLine()) != null)  {
			board = []
			mask = []

			(1..5).each {
				line =  reader.readLine()
				row = (line =~ /\d+/).collect{ it as int }

				board << row
				mask << [ 0,0,0,0,0 ]
			}

			boards << board
			masks << mask
		}
	}

	return [numbers, boards]
}