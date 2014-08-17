use mongoLog

db.board.insert( {
	title: "Test posting",
	content: "Hello World",
	writer: "Popi",
	postTime : new TimeStamp()
} )

db.board.insert( {
	title: "Test posting 2",
	content: "Hello World 2",
	writer: "Popi",
	postTime : new TimeStamp()
} )
