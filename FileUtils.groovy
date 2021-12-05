@Grab('tech.tablesaw:tablesaw-core:0.42.0')

import static tech.tablesaw.aggregate.AggregateFunctions.*
import tech.tablesaw.api.*
import tech.tablesaw.columns.*
import tech.tablesaw.io.csv.CsvReadOptions

import groovy.json.JsonSlurper
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter



class FileUtils {

	/* read comma seperated input into a list of lists
	*
	* @parm type - class or collection of classes for casting the file values.
	*				if a collection, size should equal number of cols in the file. 
	*/
	static def readListOfLists(def filename, def type, def sep = ",") {
		def data = []

		new File(filename).eachLine { line ->
			def row = []

			line.split(sep).eachWithIndex {val, i ->
				if(type instanceof Collection) row << val.asType(type[i])
				else row << val.asType(type)
			}

			data.add(row)
		}
		return data
	}

	static def readList(def filename, def type = String) {
		def data = []

		new File(filename).eachLine { line ->
			if(line) data.add( line.trim().asType(type) )
		}
		return data
	}


	/*
	* Read a headerless CSV file into a tablesaw dataframe
	* 
	* @See https://github.com/jtablesaw/tablesaw
	*/
	static def readToTablesaw(def filename)
	{

		Table table = Table.read().usingOptions(CsvReadOptions
	    .builder(filename)
	    .header(false)
		)

		return table
	}

}