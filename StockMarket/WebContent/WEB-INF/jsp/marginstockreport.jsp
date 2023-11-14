<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%
	// you can also set the values into request scope same as using c:set
	request.setAttribute("periodhighvalue", 0);
	request.setAttribute("dayhigh", 0);
	request.setAttribute("periodlowvalue", 0);
	request.setAttribute("daylow", 0);
	request.setAttribute("count", 0);
	request.setAttribute("periodstartdatevalue", 0);
	request.setAttribute("periodenddatevalue", 0);
	request.setAttribute("periodrange", 0);
	request.setAttribute("period", 0);
	request.setAttribute("yearlowvalue", 0);
	request.setAttribute("yearhighvalue", 0);
	request.setAttribute("yearlowdate", 0);
	request.setAttribute("yearhighdate", 0);
%>

<style>
.green {
	background-color: green
}

.red {
	background-color: red
}

.blue {
	background-color: d1d1d1;
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
</style>

<form:form method="post" action="index.jsp" align="right">
	<input type="submit" value="Home" class="myHome" />
</form:form>

<form:form method="post" action="marginstocksreportresults">

	<fieldset>
		<legend>Margin Wise Search :</legend>
		<table style="float: center; margin-left: 350px; margin-top: 20px;"
			cellpadding="6px">

			<tr>
				<td>Date:<input type="date" name="date" /></td>
				<td>Margin Value: <select name="margin">
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="5">5</option>
						<option value="8">8</option>
						<option value="10">10</option>
						<option value="11">11</option>
						<option value="12">12</option>
				</select></td>
				<td>52 Week <select name="profitType">
						<option>select</option>
						<option value="low">Low</option>
						<option value="high">High</option>
				</select></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" class="myButton" value="Search" /></td>
			</tr>
		</table>
	</fieldset>

</form:form>

<c:if test="${fn:length(list) > 0}">

	<table border="2" width="100%" cellpadding="2">

		<tr>
			<th>NSE Script</th>
			<th>Graph</th>
			<th>Open</th>
			<th>High</th>
			<th>Low</th>
			<th>Close</th>
			<th>Last</th>
			<th>Previous Close</th>
			<th>Date</th>
			<th>Change</th>
			<th>DownFall</th>
			<th>Upward</th>
			<th>Range</th>
			<th>Mark</th>
		</tr>

		<c:forEach var="stock" items="${list}">
			<tr class="${stock.range > 20 ? 'blue':'white'}">
				<td>${stock.nseScriptCode}</td>
				<td><a href="https://nseguide.com/charts.php?name=${stock.nseScriptCode}" target="_blank" /> Graph</a></td>
				<td>${stock.open}</td>
				<td>${stock.high}</td>
				<td>${stock.low}</td>
				<td>${stock.close}</td>
				<td>${stock.last}</td>
				<td>${stock.previousClose}</td>
				<td>${stock.date}</td>
				<td>${stock.change}</td>
				<td>${stock.downfall}</td>
				<td>${stock.upward}</td>
				<td>${stock.range}</td>
				<td class="${stock.close > stock.open ? 'green':'red'}"></td>
			</tr>


			<c:set var="margin" value="${stock.margin}"></c:set>

			<c:set var="dayhigh" value="${stock.high}"></c:set>
			<c:if test="${periodhighvalue <= dayhigh}">
				<c:set var="periodhighvalue" value="${dayhigh}"></c:set>
			</c:if>

			<c:set var="daylow" value="${stock.low}"></c:set>
			<c:if test="${periodlowvalue == 0}">
				<c:set var="periodlowvalue" value="${daylow}"></c:set>
			</c:if>
			<c:if test="${daylow <= periodlowvalue}">
				<c:set var="periodlowvalue" value="${daylow}"></c:set>
			</c:if>
			<c:if test="${count == 0}">
				<c:set var="periodstartdatevalue" value="${stock.previousClose}"></c:set>
			</c:if>

			<c:set var="periodenddatevalue" value="${stock.close}"></c:set>
			<c:set var="count" value="${count+1}"></c:set>
		</c:forEach>

	</table>  
	Margin : <b><c:out value="${margin}"></c:out></b>
</c:if>