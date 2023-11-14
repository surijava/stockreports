<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

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

<form:form method="post" action="index.jsp" align="right">
	<input type="submit" value="Home" class="myHome" />
</form:form>


<form:form method="post" action="highhighlowerlower">

	<fieldset>
		<legend>${query.choice} Daily Decision Report :</legend>
		<table style="float: center; margin-left: 10px; margin-top: 20px;"
			cellpadding="6px">

			<tr>
				<td>Period Days:<select name="year">
						<option value="0">Select</option>
						<option value="2023">2023</option>
						<option value="2022">2022</option>
						<option value="2021">2021</option>
						<option value="2020">2020</option>
						<option value="2019">2019</option>
						<option value="2018">2018</option>
				</select></td>
				<td>Month:<select name="month">
						<option value="0">Select</option>
						<option value="1">January</option>
						<option value="2">February</option>
						<option value="3">March</option>
						<option value="4">April</option>
						<option value="5">May</option>
						<option value="6">June</option>
						<option value="7">July</option>
						<option value="8">August</option>
						<option value="9">Sep</option>
						<option value="10">October</option>
						<option value="11">November</option>
						<option value="12">December</option>
				</select>
				</td>
				<td>Market Index Type :<select name="indexType">

						<option value="select">select</option>
						<optgroup label="Market Index">
							<option value="NIFTY 50">NIFTY 50</option>
							<option value="NIFTY NEXT 50">NIFTY NEXT 50</option>
							<option value="NIFTY MIDCAP 50">NIFTY MIDCAP 50</option>
						</optgroup>
						<optgroup label="Derivaties Index">
							<option value="Derivaties">All Derivaties Shares</option>
							<option value="NIFTY NO INDEX">NIFTY NO INDEX</option>
							
						</optgroup>
						<optgroup label="Other Index">
							<option value="NIFTY AUTO">NIFTY AUTO</option>
							<option value="NIFTY BANK">NIFTY BANK</option>
							<option value="NIFTY ENERGY">NIFTY ENERGY</option>
							<option value="NIFTY FIN SERVICE">NIFTY FIN SERVICE</option>
							<option value="NIFTY FMGC">NIFTY FMCG</option>
							<option value="NIFTY IT">NIFTY IT</option>
							<option value="NIFTY MEDIA">NIFTY MEDIA</option>
							<option value="NIFTY METAL">NIFTY METAL</option>
							<option value="NIFTY PHARMA">NIFTY PHARMA</option>
							<option value="NIFTY PSU BANK">NIFTY PSU BANK</option>
							<option value="NIFTY REALTY">NIFTY REALTY</option>
							<option value="NIFTY PVT BANK">NIFTY PVT BANK</option>
							<option value="NIFTY DURABLES">NIFTY DURABLES</option>
							<option value="NIFTY PVT BANK">NIFTY PVT BANK</option>
						</optgroup>
					</select>
				</td>
				<td>Oscillation :<select name="oscillation">
				
				            <option value="0">select</option>
				            <option value="0.6">0.6</option>
							<option value="1">1</option>
							<option value="1.5">1.5</option>
							<option value="2">2</option>
							<option value="2.5">2.5</option>
							<option value="4">4</option>
							<option value="5">5</option>
							<option value="8">8</option>
							<option value="10">10</option>
							<option value="12">12</option>
							<option value="15">15</option>
							<option value="50">50</option>
							<option value="100">100</option>
							<option value="250">250</option>
							<option value="500">500</option>
				</select></td>
				
				<td>RSI :<select name="rsi">
				
				            <option value="0">select</option>
							<option value="<10">Less Than 10</option>
							<option value="<30">Less Than 30</option>
							<option value="<50">Less Than 50</option>
							<option value=">40">Greater Than 40</option>
							<option value=">50">Greater Than 50</option>
							<option value=">60">Greater Than 60</option>
							<option value=">70">Greater Than 70</option>
							<option value=">80">Greater Than 80</option>
							<option value=">90">Greater Than 90</option>
							
				</select></td>
				
				<td>Type :<select name="choice">

						<option value="0">select</option>
						<option value="high">Higher High</option>
						<option value="lower">Lower Low</option>
						<option value="uptrend">Up Trend</option>
						<option value="lowtrend">Low Trend</option>
						<option value="breakout">Break Out</option>
						<option value="hightoday">High On Today</option>
						<option value="lowtoday">Low On Today</option>
						<option value="hightodayfall">High On Today To Close Negative</option>
						<option value="lowtodayup">Low On Today To Close Positive</option>
						<option value="hightrades">High Volume Shares</option>
						<option value="lowtrades">Low Volume Shares</option>
						<option value="reverstrend">Trend Reverse</option>

				</select></td>
				
			</tr>
			<tr>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td align="right"><input type="submit" class="myButton"
					value="Search" /></td>
			</tr>
			
		</table>
		<table style="float: center; margin-left: 10px; margin-top: 20px;"
			cellpadding="6px" >
		
		 <c:if test="${fn:length(list) > 0}">
			<tr>
				<td>Year: ${query.year}</td><td></td>
				<td>Month:${query.month}</td><td></td>
				<td>Index Type:${query.indexType}</td><td></td>
				<td>Oscillation:${query.oscillation} </td>
				<td>RSI:${query.rsi} </td>
				<td>Type:${query.choice} </td>
				<td>Size:${size} </td>
				
			</tr>
			</c:if>
            <c:if test="${fn:length(list) == 0  && result != 0}">
			<tr>
				<td>Year: ${query.year}</td><td></td>
				<td>Month:${query.month}</td><td></td>
				<td>Index Type:${query.indexType}</td><td></td>
				<td>Oscillation:${query.oscillation} </td>
				<td>RSI:${query.rsi} </td>
				<td>Type:${query.choice} </td>
				<td>Size:${size} </td>
				
			</tr>
				<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td align="right">No Results</td>
			</tr>
			</c:if>
			
		</table>
	</fieldset>

</form:form>
<c:if test="${fn:length(list) > 0}">

	<table border="2" width="100%" cellpadding="2">
        
       <%--  <tr>
        <th>NSE Script</th>
		<th>Graph</th>
		
        <c:set var="headrecordtwo" value="null" />
		<c:forEach var="stock" items="${list}">
			<c:set var="headrecordone" value="${stock.nseScriptCode}" />
			<c:set var="color" value="red" />
			<c:if test="${headrecordone != headrecordtwo}">
					<th>${stock.date}</th>
			</c:if>
			<c:set var="secondrecord" value="${stock.nseScriptCode}" />
		</c:forEach>
        </tr> --%>

		<tr>
			<th>NSE Script</th>
			<th>Graph</th>
			<th>Session 1</th>
			<th>Session 2</th>
			<th>Session 3</th>
			<th>Session 4</th>
			<th>Session 5</th>
			<th>Session 6</th>
			<th>Session 7</th>
			<th>Session 8</th>
			<th>Session 9</th>
			<th>Session 10</th>
			<th>Session 11</th>
			<th>Session 12</th>
			<th>Session 13</th>
			<th>Session 14</th>
			<th>Session 15</th>
			<th>Session 16</th>
			<th>Session 17</th>
			<th>Session 18</th>
			<th>Session 19</th>
			<th>Session 20</th>
			<th>Session 21</th>
			<th>Session 22</th>
			<th>Session 23</th>
			<th>Session 24</th>
		</tr>
 		<c:set var="secondrecord" value="null" />
		<c:forEach var="stock" items="${list}">
			<c:set var="firstrecord" value="${stock.nseScriptCode}" />
			<c:set var="color" value="red" />
			<c:if test="${firstrecord != secondrecord}">
				<tr>
					<td>${stock.nseScriptCode}</td>
					<td><a href="https://nseguide.com/charts.php?name=${stock.nseScriptCode}" target="_blank" /> Graph</a></td>
					<c:forEach var="india" items="${list}">
						<c:set var="testone" value="${india.nseScriptCode}" />
						<c:if test="${firstrecord == testone}">
							<c:choose>
								<c:when test="${india.range > 10}">
									<c:set var="color" value="blue" />
								</c:when>
								<c:when test="${india.range < -10}">
									<c:set var="color" value="black" />
								</c:when>
								<c:when test="${india.change > 0}">
									<c:set var="color" value="green" />
								</c:when>
								<c:otherwise>
									<c:set var="color" value="red" />
								</c:otherwise>
							</c:choose>
							<td class="<c:out value = "${color}"/>">
							<div class="tooltip">${india.close}
									<span class="tooltiptext">
									Lot Size :  ${india.lotsize} <br />
									Period High : ${india.periodhigh} <br />
									Period Low : ${india.periodlow}<br />
									-----------------------------------<br/>
									 Date :${india.date}<br />
									 Previous Close :${india.previousClose}<br />
									 Open : ${india.open}<br />
									 High : ${india.high}<br />
									 Low : ${india.low}<br />
									 Close : ${india.close}<br />
									 No Of Shares : ${india.noshares}<br />
									 No Of Trades : ${india.noTrades}<br />
									 RSI : ${india.rsi}<br />
									 Change : <span class="${india.change > 0 ? 'green':'red'}">${india.change}</span><br />
									</span>
								</div>
							</td>
						</c:if>
					</c:forEach>
				</tr>
			</c:if>
			<c:set var="secondrecord" value="${stock.nseScriptCode}" />

		</c:forEach>

	</table>

</c:if>