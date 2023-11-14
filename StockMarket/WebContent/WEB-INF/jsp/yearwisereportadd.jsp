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

<form:form method="post" action="yearwisereportadd">

	<fieldset>
		<legend>Weekly Wise Data :</legend>
		<table style="float: center; margin-left: 350px; margin-top: 16px;"
			cellpadding="6px">

			<tr>
				<td></td><td>Year:<input type="date" name="year" /></td>
			</tr>
		</table>
					<table>
						<tr>
						<td>Day1:</td>
						<td><textarea name="day1" rows="4" cols="16"></textarea></td>
						<td>Day2:</td>
						<td><textarea name="day2" rows="4" cols="16"></textarea></td>
						<td>Day3:</td>
						<td><textarea name="day3" rows="4" cols="16"></textarea></td>
						<td>Day4:</td>
						<td><textarea name="day4" rows="4" cols="16"></textarea></td>
						<td>Day5:</td>
						<td><textarea name="day5" rows="4" cols="16"></textarea></td>
						<td>Day6:</td>
						<td><textarea name="day6" rows="4" cols="16"></textarea></td>
						<td>Day7:</td>
						<td><textarea name="day7" rows="4" cols="16"></textarea></td>
						</tr>
						<tr>
						<td>Day8:</td>
						<td><textarea name="day8" rows="4" cols="16"></textarea></td>
						<td>Day9:</td>
						<td><textarea name="day9" rows="4" cols="16"></textarea></td>
						<td>Day10:</td>
						<td><textarea name="day10" rows="4" cols="16"></textarea></td>
						<td>Day11:</td>
						<td><textarea name="day11" rows="4" cols="16"></textarea></td>
						<td>Day12:</td>
						<td><textarea name="day12" rows="4" cols="16"></textarea></td>
						<td>Day13:</td>
						<td><textarea name="day13" rows="4" cols="16"></textarea></td>
						<td>Day14:</td>
						<td><textarea name="day14" rows="4" cols="16"></textarea></td>
						</tr>
						<tr>
						<td>Day15:</td>
						<td><textarea name="day15" rows="4" cols="16"></textarea></td>
						<td>Day16:</td>
						<td><textarea name="day16" rows="4" cols="16"></textarea></td>
						<td>Day17:</td>
						<td><textarea name="day17" rows="4" cols="16"></textarea></td>
						<td>Day18:</td>
						<td><textarea name="day18" rows="4" cols="16"></textarea></td>
						<td>Day19:</td>
						<td><textarea name="day19" rows="4" cols="16"></textarea></td>
						<td>Day20:</td>
						<td><textarea name="day20" rows="4" cols="16"></textarea></td>
						<td>Day21:</td>
						<td><textarea name="day21" rows="4" cols="16"></textarea></td>
						</tr>
						<tr>
						<td>Day22:</td>
						<td><textarea name="day22" rows="4" cols="16"></textarea></td>
						<td>Day23:</td>
						<td><textarea name="day23" rows="4" cols="16"></textarea></td>
						<td>Day24:</td>
						<td><textarea name="day24" rows="4" cols="16"></textarea></td>
						<td>Day25:</td>
						<td><textarea name="day25" rows="4" cols="16"></textarea></td>
						<td>Day26:</td>
						<td><textarea name="day26" rows="4" cols="16"></textarea></td>
						<td>Day27:</td>
						<td><textarea name="day27" rows="4" cols="16"></textarea></td>
						<td>Day28:</td>
						<td><textarea name="day28" rows="4" cols="16"></textarea></td>
						</tr>
						<tr>
						<td>Day29:</td>
						<td><textarea name="day29" rows="4" cols="16"></textarea></td>
						<td>Day30:</td>
						<td><textarea name="day30" rows="4" cols="16"></textarea></td>
						<td>Day31:</td>
						<td><textarea name="day31" rows="4" cols="16"></textarea></td>
						<td>Comments:</td>
						<td><textarea name="day32" rows="4" cols="16"></textarea></td>
						</tr>
					</table>
	 		<table align="center">
			<tr>
				<td ></td>
				<td><input type="submit" class="myButton" value="Add" /></td>
			</tr>
		</table>
	</fieldset>

</form:form>


