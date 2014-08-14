use mongoLog

db.board.insert( {
	title: "Test posting",
	content: "Hello World",
	postTime : new TimeStamp()
} )

db.board.insert( {
	title: "Test posting 2",
	content: "Hello World 2",
	postTime : new TimeStamp()
} )
