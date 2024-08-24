CREATE TABLE votingItem (
    itemId INT IDENTITY(1,1) PRIMARY KEY,
    itemName VARCHAR(255) NOT NULL,
	numberOfVotes INT
);

CREATE TABLE vote (
    id INT PRIMARY KEY,
    itemId INT,
    name VARCHAR(255) NOT NULL,
);

INSERT INTO votingItem (itemName,numberOfVotes)
VALUES('¹q¸£',0),
	('·Æ¹«',0)


CREATE PROCEDURE GetAllVote
	@name VARCHAR(255)
AS
BEGIN
  SELECT id, name, itemId
  FROM vote
  WHERE name = @name;
END

