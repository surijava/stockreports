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


<form:form method="post" action="profitreportresults">

	<fieldset>
		<legend>Profit Wise Search :</legend>
		<table style="float: center; margin-left: 10px; margin-top: 20px;"
			cellpadding="6px">

			<tr>
				<td>Date:<input type="date" name="date" /></td>
				<td>Period Days:<select name="period">
						<option value="0">select</option>
						<option value="2">Two</option>
						<option value="3">Three</option>
						<option value="4">Four</option>
						<option value="5">Five</option>
						<option value="7">Week</option>
						<option value="10">Ten</option>
						<option value="15">TwoWeeks</option>
						<option value="21">ThreeWeeks</option>
						<option value="30">Month</option>
						<option value="60">TwoMonths</option>
						<option value="90">ThreeMonths</option>
						<option value="180">SixMonths</option>
						<option value="270">NineMonths</option>
						<option value="360">OneYear</option>
				</select></td>
				<td>Profit Type:<select name="profitType">
						<option>select</option>
						<option value="lose">lose</option>
						<option value="gain">gain</option>
				</select></td>
				<td>Profit Value Range:<select name="profit">
						<option value="select">select</option>
						<option value="0-10">0-10</option>
						<option value="10-20">10-20</option>
						<option value="20-50">20-50</option>
						<option value="50-100">50-100</option>
						<option value="100-150">100-150</option>
						<option value="200-500">200-500</option>

				</select>
				</td>
				<td>Stock Price Value Range:<select name="price">
						<option value="select">select</option>
						<option value="0-10">0-10</option>
						<option value="10-20">10-20</option>
						<option value="0-20"><20</option>
						<option value="0-30"><30</option>
						<option value="30-50">30-50</option>
						<option value="0-50"><50</option>
						<option value="50-100">50-100</option>
						<option value="0-100"><100</option>
						<option value="100-150">100-150</option>
						<option value="150-200">150-200</option>
						<option value="0-200"><200</option>
						<option value="200-250">200-250</option>
						<option value="200-300">200-300</option>
						<option value="0-300"><300</option>
						<option value="300-400">300-400</option>
						<option value="400-500">400-500</option>
						<option value="0-500"><500</option>
						<option value="500-10000">>500</option>
						<option value="500-600">500-600</option>
						<option value="600-700">600-700</option>
						<option value="700-800">700-800</option>
						<option value="800-1000">800-1000</option>
						<option value="500-1000">500-1000</option>
						<option value="0-1000"><1000</option>
						<option value="1000-10000">>1000</option>
						<option value="1000-1500">1000-1500</option>
						<option value="1500-2000">1500-2000</option>
						<option value="0-2000"><2000</option>
						<option value="2000-10000">1500-10000</option>
				</select>
				</td>
				
				<td>Margin Value: <select name="margin">
						<option value="100">All</option>
						<option value="0">0</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="5">5</option>
						<option value="8">8</option>
						<option value="10">10</option>
						<option value="11">11</option>
						<option value="12">12</option>
						
				</select></td>
				
				<td>Market Index Type :<select name="indexType">

						<option value="select">select</option>
						<optgroup label="Market Index">
							<option value="NIFTY 50">NIFTY 50</option>
							<option value="NIFTY NEXT 50">NIFTY NEXT 50</option>
							<option value="NIFTY MIDCAP 50">NIFTY MIDCAP 50</option>
							<option value="NIFTY 100">NIFTY 100</option>
							<option value="NIFTY 100">NIFTY 150</option>
							<option value="Observations">Observation Shares</option>
							<option value="PSU">PSU</option>
							<option value="Derivaties">Derivaties Shares</option>
						</optgroup>
						<optgroup label="German Cars">
							<option value="NIFTY AUTO">NIFTY AUTO</option>
							<option value="NIFTY BANK">NIFTY BANK</option>
							<option value="NIFTY ENERGY">NIFTY ENERGY</option>
							<option value="NIFTY FIN SERVICE">NIFTY FIN SERVICE</option>
							<option value="NIFTY FMCG">NIFTY FMCG</option>
							<option value="NIFTY IT">NIFTY IT</option>
							<option value="NIFTY MEDIA">NIFTY MEDIA</option>
							<option value="NIFTY METAL">NIFTY METAL</option>
							<option value="NIFTY PHARMA">NIFTY PHARMA</option>
							<option value="NIFTY PSU BANK">NIFTY PSU BANK</option>
							<option value="NIFTY REALTY">NIFTY REALTY</option>
							<option value="NIFTY PVT BANK">NIFTY PVT BANK</option>
							<option value="NIFTY HEALTHCARE">NIFTY HEALTHCARE</option>
							<option value="NIFTY OILGAS">NIFTY OIL&GAS</option>
						</optgroup>
				</select>
				</td>
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
				<td align="right"><input type="submit" class="myButton" value="Search" /></td>
			</tr>
		</table>
	</fieldset>

</form:form>


<c:if test="${fn:length(list) > 0}">



	<table border="2" width="100%" cellpadding="2">

		<tr>
			<th>NSE Script</th>
			<th>Graph</th>
			<th>Zerodha Margin</th>
			<th>LotSize</th>
			<th>Open</th>
			<th>Low</th>
			<th>High</th>
			<th>PreviousClose</th>
			<th>Close</th>
			<th>Change</th>
			<th>Percentage</th>
			<th>Mark</th>

		</tr>

		<c:forEach var="stock" items="${list}">
			<tr class="${stock.change > 20 ? 'blue':'white'}">
				<td>${stock.nseScriptCode}</td>
				<td><a href="https://nseguide.com/charts.php?name=${stock.nseScriptCode}" target="_blank" /> Graph</a></td>
				<td>${stock.margin}</td>
				<td>${stock.lotsize}</td>
				<td>${stock.open}</td>
				<td>${stock.low}</td>
				<td>${stock.high}</td>
				<td>${stock.previousClose}</td>
				<td>${stock.close}</td>
				<td>${stock.change}</td>
				<td>${stock.percentage}</td>
				<td class="${stock.close > stock.previousClose ? 'green':'red'}"></td>
			</tr>
		</c:forEach>

	</table>

</c:if>