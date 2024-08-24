CREATE TABLE votingItem (
    id INT IDENTITY(1,1) PRIMARY KEY,
    itemName VARCHAR(255) NOT NULL,
	numberOfVotes INT
);

CREATE TABLE vote (
    id INT IDENTITY(1,1) PRIMARY KEY,
    itemId INT,
    name VARCHAR(255) NOT NULL,
);

INSERT INTO votingItem (itemName,numberOfVotes)
VALUES('電腦',0),
	('滑鼠',0)


/*預存程序*/
CREATE PROCEDURE GetAllVote
	@name VARCHAR(255)
AS
BEGIN
  SELECT id, name, itemId
  FROM vote
  WHERE name = @name;
END