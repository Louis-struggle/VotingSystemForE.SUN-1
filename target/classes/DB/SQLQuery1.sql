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
VALUES('�q��',0),
	('�ƹ�',0)


/*�w�s�{��*/
CREATE PROCEDURE GetAllVote
	@name VARCHAR(255)
AS
BEGIN
  SELECT id, name, itemId
  FROM vote
  WHERE name = @name;
END