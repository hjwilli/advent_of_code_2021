


class Matrix {
	
	// pretty print a 2d array
	static def printMatrix(def m) {

		println "Matrix size: ${m.size()} x ${m.first().size()}"

		m.each { 
			println it.join(", ")
		}
	}

	// Wolfram Alpha syntax
	static def printMatrixWolfram(def m) {
		def s =  "{"
		m.each { 
			s+= "{" + it.join(", ") + "},"
		}
		s=s.substring(0, s.size()-1)
		s+= "}"

		println s
	}

	static gridAdd(def grid, def val) {
	(0..(grid.size() - 1)).each { i ->
		(0..(grid.first().size() - 1)).each { j ->
			grid[i][j] = grid[i][j] + val
		}
	}
	return grid
}
}