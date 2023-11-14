<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>
.green {
	background-color: green
}

.red {
	background-color: red
}

.blue {
	background-color: #0052CC;
}

.white {
	background-color: white
}

.low {
	color: red;
	font-weight: bold;
}

.high {
	color: blue;
	font-weight: bold;
}

.black {
	color: black;
}

.myButton {
	-moz-box-shadow: 0px 10px 14px -7px #276873;
	-webkit-box-shadow: 0px 10px 14px -7px #276873;
	box-shadow: 0px 10px 14px -7px #276873;
	background: -webkit-gradient(linear, left top, left bottom, color-stop(0.05, #599bb3
		), color-stop(1, #408c99));
	background: -moz-linear-gradient(top, #599bb3 5%, #408c99 100%);
	background: -webkit-linear-gradient(top, #599bb3 5%, #408c99 100%);
	background: -o-linear-gradient(top, #599bb3 5%, #408c99 100%);
	background: -ms-linear-gradient(top, #599bb3 5%, #408c99 100%);
	background: linear-gradient(to bottom, #599bb3 5%, #408c99 100%);
	filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#599bb3',
		endColorstr='#408c99', GradientType=0);
	background-color: #599bb3;
	-moz-border-radius: 6px;
	-webkit-border-radius: 6px;
	border-radius: 6px;
	display: inline-block;
	cursor: pointer;
	color: #ffffff;
	font-family: Arial;
	font-size: 14px;
	font-weight: bold;
	padding: 10px 29px;
	text-decoration: none;
	text-shadow: 0px 1px 0px #3d768a;
}

.myButton:hover {
	background: -webkit-gradient(linear, left top, left bottom, color-stop(0.05, #408c99
		), color-stop(1, #599bb3));
	background: -moz-linear-gradient(top, #408c99 5%, #599bb3 100%);
	background: -webkit-linear-gradient(top, #408c99 5%, #599bb3 100%);
	background: -o-linear-gradient(top, #408c99 5%, #599bb3 100%);
	background: -ms-linear-gradient(top, #408c99 5%, #599bb3 100%);
	background: linear-gradient(to bottom, #408c99 5%, #599bb3 100%);
	filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#408c99',
		endColorstr='#599bb3', GradientType=0);
	background-color: #408c99;
}

.myButton:active {
	position: relative;
	top: 1px;
}

.myHome {
	-moz-box-shadow: inset 0px 1px 0px 0px #caefab;
	-webkit-box-shadow: inset 0px 1px 0px 0px #caefab;
	box-shadow: inset 0px 1px 0px 0px #caefab;
	background: -webkit-gradient(linear, left top, left bottom, color-stop(0.05, #77d42a
		), color-stop(1, #5cb811));
	background: -moz-linear-gradient(top, #77d42a 5%, #5cb811 100%);
	background: -webkit-linear-gradient(top, #77d42a 5%, #5cb811 100%);
	background: -o-linear-gradient(top, #77d42a 5%, #5cb811 100%);
	background: -ms-linear-gradient(top, #77d42a 5%, #5cb811 100%);
	background: linear-gradient(to bottom, #77d42a 5%, #5cb811 100%);
	filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#77d42a',
		endColorstr='#5cb811', GradientType=0);
	background-color: #77d42a;
	-moz-border-radius: 8px;
	-webkit-border-radius: 8px;
	border-radius: 8px;
	border: 1px solid #268a16;
	display: inline-block;
	cursor: pointer;
	color: #306108;
	font-family: Arial;
	font-size: 16px;
	font-weight: bold;
	padding: 4px 27px;
	text-decoration: none;
	text-shadow: 0px 1px 0px #aade7c;
}

.myHome:hover {
	background: -webkit-gradient(linear, left top, left bottom, color-stop(0.05, #5cb811
		), color-stop(1, #77d42a));
	background: -moz-linear-gradient(top, #5cb811 5%, #77d42a 100%);
	background: -webkit-linear-gradient(top, #5cb811 5%, #77d42a 100%);
	background: -o-linear-gradient(top, #5cb811 5%, #77d42a 100%);
	background: -ms-linear-gradient(top, #5cb811 5%, #77d42a 100%);
	background: linear-gradient(to bottom, #5cb811 5%, #77d42a 100%);
	filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#5cb811',
		endColorstr='#77d42a', GradientType=0);
	background-color: #5cb811;
}

.myHome:active {
	position: relative;
	top: 1px;
}

/* Tooltip container */
.tooltip {
	position: relative;
	display: inline-block;
}

/* Tooltip text */
.tooltip .tooltiptext {
	visibility: hidden;
	width: 250px;
	bottom: 100%;
	left: 50%;
	margin-left: -60px;
	background-color: #109C9E;
	color: white;
	text-align: left;
	padding: 5px 0;
	border-radius: 10px;
	/* Position the tooltip text - see examples below! */
	position: absolute;
	z-index: 1;
}

/* Show the tooltip text when you mouse over the tooltip container */
.tooltip:hover .tooltiptext {
	visibility: visible;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Derivatives DashBoard</title>
</head>
<body>


	<table border="2" cellpadding="2">
		<tr>
			<th>Event Type</th>
			<th>Event Date</th>
		</tr>
		<tr>
			<c:forEach var="event" items="${eventList}">

				<tr>
					<td>${event.eventtype}</td>
					<td>${event.eventdate}</td>
			</c:forEach>
		</tr>
	</table>
	<br/>
	
	<table border="2" cellpadding="2">
		<tr>
			<th>Date</th>
			<th>Long</th>
			<th>Short</th>
		</tr>
		<tr>
			<c:forEach var="oi" items="${openInterestList}">

				<tr>
					<td>${oi.date}</td>
					<td>${oi.totalLong}</td>
					<td>${oi.totalShort}</td>
			</c:forEach>
		</tr>
	</table>
	<br/>
	
	<table border="2" cellpadding="2">
		<tr>
			<th>NSE Script</th>
			<th>Event Type</th>
			<th>Event Date</th>
			<th>Quarter</th>
		</tr>
		<tr>
			<c:forEach var="shareEvent" items="${sharesEventsList}">

				<tr>
					<td>${shareEvent.nsescriptcode}</td>
					<td>${shareEvent.eventtype}</td>
					<td>${shareEvent.eventdate}</td>
					<td>${shareEvent.quarter}</td>
			</c:forEach>
		</tr>
	</table>

<br/>
	
	<table border="2" cellpadding="2">
		<tr>
			<th>Date</th>
			<th>Category</th>
			<th>BuyValue</th>
			<th>SellValue</th>
			<th>NetValue</th>
		</tr>
		<tr>
			<c:forEach var="fiidiidata" items="${fiiDiiList}">

				<tr>
					<td>${fiidiidata.date}</td>
					<td>${fiidiidata.category}</td>
					<td>${fiidiidata.buyValue}</td>
					<td>${fiidiidata.sellValue}</td>
					<td>${fiidiidata.netValue}</td>
			</c:forEach>
		</tr>
	</table>
	
	
	
</body>
</html>