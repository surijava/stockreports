<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<title>Welcome To Stock Market</title>
<style>
* {
	box-sizing: border-box;
}

.img-container {
	float: left;
	width: 25%;
	padding: 5px;
}

.clearfix::after {
	content: "";
	clear: both;
	display: table;
}

.myButton {
	-moz-box-shadow: inset 0px 1px 0px 0px #54a3f7;
	-webkit-box-shadow: inset 0px 1px 0px 0px #54a3f7;
	box-shadow: inset 0px 1px 0px 0px #54a3f7;
	background: -webkit-gradient(linear, left top, left bottom, color-stop(0.05, #007dc1
		), color-stop(1, #0061a7));
	background: -moz-linear-gradient(top, #007dc1 5%, #0061a7 100%);
	background: -webkit-linear-gradient(top, #007dc1 5%, #0061a7 100%);
	background: -o-linear-gradient(top, #007dc1 5%, #0061a7 100%);
	background: -ms-linear-gradient(top, #007dc1 5%, #0061a7 100%);
	background: linear-gradient(to bottom, #007dc1 5%, #0061a7 100%);
	filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#007dc1',
		endColorstr='#0061a7', GradientType=0);
	background-color: #007dc1;
	-moz-border-radius: 14px;
	-webkit-border-radius: 14px;
	border-radius: 14px;
	border: 1px solid #124d77;
	display: inline-block;
	cursor: pointer;
	color: #ffffff;
	font-family: Verdana;
	font-size: 14px;
	padding: 32px 76px;
	text-decoration: none;
	text-shadow: 0px 1px 0px #154682;
}

.myButton:hover {
	background: -webkit-gradient(linear, left top, left bottom, color-stop(0.05, #0061a7
		), color-stop(1, #007dc1));
	background: -moz-linear-gradient(top, #0061a7 5%, #007dc1 100%);
	background: -webkit-linear-gradient(top, #0061a7 5%, #007dc1 100%);
	background: -o-linear-gradient(top, #0061a7 5%, #007dc1 100%);
	background: -ms-linear-gradient(top, #0061a7 5%, #007dc1 100%);
	background: linear-gradient(to bottom, #0061a7 5%, #007dc1 100%);
	filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#0061a7',
		endColorstr='#007dc1', GradientType=0);
	background-color: #0061a7;
}

.myButton:active {
	position: relative;
	top: 1px;
}
</style>
</head>


<body>
	<h2 align="center">Welcome To Stock Analytics</h2>

	<div class="clearfix">
		<div class="img-container">
			<form action="create" method="post">
				<input type="submit" class="myButton" value="Creat Stock" />
			</form>
		</div>
		<div class="img-container">
			<form:form method="post" action="singleStockDayWiseSearch">
				<input type="submit" class="myButton" value="Stockb Wise Report" />
			</form:form>
		</div>
		<div class="img-container">
			<form:form method="post" action="marginstocksreportresults">
				<input type="submit" class="myButton" value="Margin Report" />
			</form:form>
		</div>
		<div class="img-container">
			<form:form method="post" action="profitreportresults">
				<input type="submit" class="myButton" value="Profit Report" />
			</form:form>
		</div>
		<div class="img-container">
			<form:form method="post" action="yearlowhighreportresults">
				<input type="submit" class="myButton" value="52 Week Report" />
			</form:form>
		</div>
		<div class="img-container">
			<form:form method="post" action="yearwisereportadd">
				<input type="submit" class="myButton" value="Year Wise Report Add" />
			</form:form>
		</div>
		
		<div class="img-container">
			<form:form method="post" action="yearwisesearchreport">
				<input type="submit" class="myButton" value="Year Wise Data Report" />
			</form:form>
		</div>
		<div class="img-container">
			<form:form method="post" action="daytrading">
				<input type="submit" class="myButton" value="Day Trading" />
			</form:form>
		</div>
		
		<div class="img-container">
			<form:form method="post" action="derivatiesdailyreport">
				<input type="submit" class="myButton" value="F&O Decision Report" />
			</form:form>
		</div>
		
		<div class="img-container">
			<form:form method="post" action="derivatiesgainerloser">
				<input type="submit" class="myButton" value="Derivaties Gainer losers" />
			</form:form>
		</div>
		
		<div class="img-container">
			<form:form method="post" action="highhighlowerlower">
				<input type="submit" class="myButton" value="HigherHigh LowerLow" />
			</form:form>
		</div>
		
		<div class="img-container">
			<form:form action="treemenu.jsp">
				<input type="submit" class="myButton" value="Group Companies " />
			</form:form>
		</div>
		
		<div class="img-container">
			<form:form action="addevent">
				<input type="submit" class="myButton" value="Add Event" />
			</form:form>
		</div>
		
		<div class="img-container">
			<form:form action="sharecorporatedevent">
				<input type="submit" class="myButton" value="Share Corporate Event" />
			</form:form>
		</div>
		
		<div class="img-container">
			<form:form action="derivatessharesdashboard">
				<input type="submit" class="myButton" value="Derivaties Dashboard" />
			</form:form>
		</div>
		
		
		
	</div>

</body>
</html>



