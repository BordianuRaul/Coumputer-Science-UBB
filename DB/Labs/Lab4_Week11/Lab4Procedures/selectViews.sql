USE Lab4_Week11

CREATE VIEW view1Table
AS
	SELECT * FROM Airport
GO

CREATE VIEW view2Tables
AS
	SELECT Apron.latitude, Apron.longitude, Airplane.airplaneModel
	FROM Apron
	INNER JOIN Airplane ON Apron.airportID = Airplane.apronID;
GO

CREATE VIEW view2TablesGroupBy
AS
    SELECT
        A.airportID,
        AVG(A.longitude) AS avgLongitude,
        AVG(A.latitude) AS avgLatitude,
        COUNT(DISTINCT AP.airplaneID) AS airplaneCount
    FROM
        Apron A
    INNER JOIN
        Airplane AP ON A.apronID = AP.apronID
    GROUP BY
        A.airportID;
GO


GO
CREATE PROCEDURE selectView
	
	@viewName varchar(30)
AS
BEGIN

	SET NOCOUNT ON;

	if @viewName = 'view1Table'
	begin
		select * from view1Table
	end

	if @viewName = 'view2Tables'
	begin
		select * from view2Tables
	end

	if @viewName = 'view2TablesGroupBy'
	begin
		select * from view2TablesGroupBy
	end
END

EXEC selectView 'view2Tables'