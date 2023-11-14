<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


<%
	// you can also set the values into request scope same as using c:set
	request.setAttribute("periodhighvalue", 0);
	
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


.parent-div{
    width: 70%; 
    border: 1px solid #f1f1f1; 
    padding:0; 
    margin: 0; 
    background: #D3D3D3;
}
.accordion-heading{
    background: #287276; 
    padding: 5px 10px; 
    margin: 0; 
    cursor: pointer; 
    color: #fff;
}
.accordion-heading span{
    float: right;
}
.accordion-body{
display: none;
    padding: 0 10px;
}

</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<script language="javascript"> 
    $(document).ready(function(){
        $(".accordion-heading").click(function(){  
              if($(".accordion-body").is(':visible')){  /* check the condition accordion-body is visible or not */
                  $(".accordion-body").slideUp(600);  /*if content is visible then close accordion-body with specific time duration */
                $(".plusminus").text('+')    /* add plus sign */
            }
            else{
                $(this).next(".accordion-body").slideDown(600); /*if content not visible then 
                                                                                                            show the accordion-body */
                $(this).children(".plusminus").text('-');  /* add minus sign */
            }
        });
    });
    
    function update(day){
    	
    	document.getElementById("dbtext"+day).style.display = "block";
    	document.getElementById("dbsubmit"+day).style.display = "block"
        document.getElementById("dbupdate"+day).style.display = "none";
    	document.getElementById("daynumber").value = day;
    	
    }
    
    function cancel(day){
    	
    	document.getElementById("dbsubmit"+day).style.display = "none"
        document.getElementById("dbupdate"+day).style.display = "block";
    	document.getElementById("dbtext"+day).style.display = "none";
    	document.getElementById("dayvalue").value ="";
    	document.getElementById("daynumber").value = "";
    }
    function submit(day){
    	document.getElementById("dayvalue").value = null;
    	document.getElementById("dayvalue").value = document.getElementById("dbtext"+day).value;
    	document.getElementById("myForm").submit();
    }
    
</script>

<form:form method="post" action="index.jsp" align="right">
	<input type="submit" value="Home" class="myHome" />
</form:form>

<form:form method="post" action="yearwisesearchreport">

	<fieldset>
		<legend>Year Wise Data :</legend>
		<table style="float: center; margin-left: 350px; margin-top: 16px;"
			cellpadding="6px">
			<tr>
				<td></td><td></td><td>Year:<input type="date" name="year" /></td>
			</tr>
			<tr><td></td><td></td></tr>
			<tr>
				<td></td><td></td>
				<td><input type="submit" class="myButton" value="Search" /></td>
			</tr>
		</table>
	</fieldset>
</form:form>



<c:if test="${fn:length(list) > 0}">

<c:forEach var="pojo" items="${list}">

<form method="post" action="yearwisesearchreportupdate" id="myForm">
<input type="hidden" name="year" id="year" value="${pojo.year}"/>
<input type="hidden" name="dayvalue" id="dayvalue"/>
<input type="hidden" name="daynumber" id="daynumber"/>
</form>

<p><b>selected year:</b> ${pojo.year}</p>
<p><b> Comments:</b> <br/> ${pojo.day32}</p>

<div class="parent-div">

   <h3 class="accordion-heading">Week1 <span class="plusminus">+</span></h3>
   <div class="accordion-body">
   <table border="1" width="100%" cellpadding="2">


					<tr>
						<td>Day1</td>
						<td>${pojo.day1}</td>
						<td>

							<table>
								<tr>
									<td><textarea rows="20" id='dbtextday1'
											style="display: none">${pojo.day1}</textarea></td>
									<td>
										<button class="myButton" id="dbsubmitday1"
											style="display: none" onclick="submit('day1')">Submit</button>
									</td>
									<td>
										<button class="myButton" id="dbupdateday1"
											style="display: blank" onclick="update('day1')">Update</button>
									</td>
									<td><button class="myButton" id="dbcancelday1"
											onclick="cancel('day1')">Cancel</button></td>
								</tr>
							</table>

						</td>
					</tr>
					<tr>
			<td>Day2</td>
			<td>${pojo.day2}</td>
			
			<td>

							<table>
								<tr>
									<td><textarea rows="20" id='dbtextday2'
											style="display: none">${pojo.day2}</textarea></td>
									<td>
										<button class="myButton" id="dbsubmitday2"
											style="display: none" onclick="submit('day2')">Submit</button>
									</td>
									<td>
										<button class="myButton" id="dbupdateday2"
											style="display: blank" onclick="update('day2')">Update</button>
									</td>
									<td><button class="myButton" id="dbcancelday2"
											onclick="cancel('day2')">Cancel</button></td>
								</tr>
							</table>

						</td>
			</tr>
			<tr>
			<td>Day3</td>
			<td>${pojo.day3}</td>
			<td>

							<table>
								<tr>
									<td><textarea rows="20" id='dbtextday3'
											style="display: none">${pojo.day3}</textarea></td>
									<td>
										<button class="myButton" id="dbsubmitday3"
											style="display: none" onclick="submit('day3')">Submit</button>
									</td>
									<td>
										<button class="myButton" id="dbupdateday3"
											style="display: blank" onclick="update('day3')">Update</button>
									</td>
									<td><button class="myButton" id="dbcancelday3"
											onclick="cancel('day3')">Cancel</button></td>
								</tr>
							</table>

						</td>
			</tr>
			<tr>
			<td>Day4</td>
			<td>${pojo.day4}</td>
			<td>

							<table>
								<tr>
									<td><textarea rows="20" id='dbtextday4'
											style="display: none">${pojo.day4}</textarea></td>
									<td>
										<button class="myButton" id="dbsubmitday4"
											style="display: none" onclick="submit('day4')">Submit</button>
									</td>
									<td>
										<button class="myButton" id="dbupdateday4"
											style="display: blank" onclick="update('day4')">Update</button>
									</td>
									<td><button class="myButton" id="dbcancelday4"
											onclick="cancel('day4')">Cancel</button></td>
								</tr>
							</table>

						</td>
			</tr>
			<tr>
			<td>Day5</td>
			<td>${pojo.day5}</td>
			<td>

							<table>
								<tr>
									<td><textarea rows="20" id='dbtextday5'
											style="display: none">${pojo.day5}</textarea></td>
									<td>
										<button class="myButton" id="dbsubmitday5"
											style="display: none" onclick="submit('day5')">Submit</button>
									</td>
									<td>
										<button class="myButton" id="dbupdateday5"
											style="display: blank" onclick="update('day5')">Update</button>
									</td>
									<td><button class="myButton" id="dbcancelday5"
											onclick="cancel('day5')">Cancel</button></td>
								</tr>
							</table>

						</td>
			</tr>
			<tr>
			<td>Day6</td>
			<td>${pojo.day6}</td>
			<td>

							<table>
								<tr>
									<td><textarea rows="20" id='dbtextday6'
											style="display: none">${pojo.day6}</textarea></td>
									<td>
										<button class="myButton" id="dbsubmitday6"
											style="display: none" onclick="submit('day6')">Submit</button>
									</td>
									<td>
										<button class="myButton" id="dbupdateday6"
											style="display: blank" onclick="update('day6')">Update</button>
									</td>
									<td><button class="myButton" id="dbcancelday6"
											onclick="cancel('day6')">Cancel</button></td>
								</tr>
							</table>

						</td>
			</tr>
			<tr>
			<td>Day7</td>
			<td>${pojo.day7}</td>
			<td>

							<table>
								<tr>
									<td><textarea rows="20" id='dbtextday7'
											style="display: none">${pojo.day7}</textarea></td>
									<td>
										<button class="myButton" id="dbsubmitday7"
											style="display: none" onclick="submit('day7')">Submit</button>
									</td>
									<td>
										<button class="myButton" id="dbupdateday7"
											style="display: blank" onclick="update('day7')">Update</button>
									</td>
									<td><button class="myButton" id="dbcancelday7"
											onclick="cancel('day7')">Cancel</button></td>
								</tr>
							</table>

						</td>
		</tr>
	</table>
	</div>
</div>
<div class="parent-div">
	 <h3 class="accordion-heading">Week2 <span class="plusminus">+</span></h3>
	 <div class="accordion-body">
   <table border="1" width="100%" cellpadding="2">


					<tr>
						<td>day8</td>
						<td>${pojo.day8}</td>
						<td>

							<table>
								<tr>
									<td><textarea rows="20" id='dbtextday8'
											style="display: none">${pojo.day8}</textarea></td>
									<td>
										<button class="myButton" id="dbsubmitday8"
											style="display: none" onclick="submit('day8')">Submit</button>
									</td>
									<td>
										<button class="myButton" id="dbupdateday8"
											style="display: blank" onclick="update('day8')">Update</button>
									</td>
									<td><button class="myButton" id="dbcancelday8"
											onclick="cancel('day8')">Cancel</button></td>
								</tr>
							</table>

						</td>
					</tr>
					<tr>
			<td>day9</td>
			<td>${pojo.day9}</td>
			
			<td>

							<table>
								<tr>
									<td><textarea rows="20" id='dbtextday9'
											style="display: none">${pojo.day9}</textarea></td>
									<td>
										<button class="myButton" id="dbsubmitday9"
											style="display: none" onclick="submit('day9')">Submit</button>
									</td>
									<td>
										<button class="myButton" id="dbupdateday9"
											style="display: blank" onclick="update('day9')">Update</button>
									</td>
									<td><button class="myButton" id="dbcancelday9"
											onclick="cancel('day9')">Cancel</button></td>
								</tr>
							</table>

						</td>
			</tr>
			<tr>
			<td>day10</td>
			<td>${pojo.day10}</td>
			<td>

							<table>
								<tr>
									<td><textarea rows="20" id='dbtextday10'
											style="display: none">${pojo.day10}</textarea></td>
									<td>
										<button class="myButton" id="dbsubmitday10"
											style="display: none" onclick="submit('day10')">Submit</button>
									</td>
									<td>
										<button class="myButton" id="dbupdateday10"
											style="display: blank" onclick="update('day10')">Update</button>
									</td>
									<td><button class="myButton" id="dbcancelday10"
											onclick="cancel('day10')">Cancel</button></td>
								</tr>
							</table>

						</td>
			</tr>
			<tr>
			<td>day11</td>
			<td>${pojo.day11}</td>
			<td>

							<table>
								<tr>
									<td><textarea rows="20" id='dbtextday11'
											style="display: none">${pojo.day11}</textarea></td>
									<td>
										<button class="myButton" id="dbsubmitday11"
											style="display: none" onclick="submit('day11')">Submit</button>
									</td>
									<td>
										<button class="myButton" id="dbupdateday11"
											style="display: blank" onclick="update('day11')">Update</button>
									</td>
									<td><button class="myButton" id="dbcancelday11"
											onclick="cancel('day11')">Cancel</button></td>
								</tr>
							</table>

						</td>
			</tr>
			<tr>
			<td>day12</td>
			<td>${pojo.day12}</td>
			<td>

							<table>
								<tr>
									<td><textarea rows="20" id='dbtextday12'
											style="display: none">${pojo.day12}</textarea></td>
									<td>
										<button class="myButton" id="dbsubmitday12"
											style="display: none" onclick="submit('day12')">Submit</button>
									</td>
									<td>
										<button class="myButton" id="dbupdateday12"
											style="display: blank" onclick="update('day12')">Update</button>
									</td>
									<td><button class="myButton" id="dbcancelday12"
											onclick="cancel('day12')">Cancel</button></td>
								</tr>
							</table>

						</td>
			</tr>
			<tr>
			<td>day13</td>
			<td>${pojo.day13}</td>
			<td>

							<table>
								<tr>
									<td><textarea rows="20" id='dbtextday13'
											style="display: none">${pojo.day13}</textarea></td>
									<td>
										<button class="myButton" id="dbsubmitday13"
											style="display: none" onclick="submit('day13')">Submit</button>
									</td>
									<td>
										<button class="myButton" id="dbupdateday13"
											style="display: blank" onclick="update('day13')">Update</button>
									</td>
									<td><button class="myButton" id="dbcancelday13"
											onclick="cancel('day13')">Cancel</button></td>
								</tr>
							</table>

						</td>
			</tr>
			<tr>
			<td>day14</td>
			<td>${pojo.day14}</td>
			<td>

							<table>
								<tr>
									<td><textarea rows="20" id='dbtextday14'
											style="display: none">${pojo.day14}</textarea></td>
									<td>
										<button class="myButton" id="dbsubmitday14"
											style="display: none" onclick="submit('day14')">Submit</button>
									</td>
									<td>
										<button class="myButton" id="dbupdateday14"
											style="display: blank" onclick="update('day14')">Update</button>
									</td>
									<td><button class="myButton" id="dbcancelday14"
											onclick="cancel('day14')">Cancel</button></td>
								</tr>
							</table>

						</td>
		</tr>
	</table>
	</div>
</div>
<div class="parent-div">
	  <h3 class="accordion-heading">Week3 <span class="plusminus">+</span></h3>
	 <div class="accordion-body">
   <table border="1" width="100%" cellpadding="2">


					<tr>
						<td>day15</td>
						<td>${pojo.day15}</td>
						<td>

							<table>
								<tr>
									<td><textarea rows="20" id='dbtextday15'
											style="display: none">${pojo.day15}</textarea></td>
									<td>
										<button class="myButton" id="dbsubmitday15"
											style="display: none" onclick="submit('day15')">Submit</button>
									</td>
									<td>
										<button class="myButton" id="dbupdateday15"
											style="display: blank" onclick="update('day15')">Update</button>
									</td>
									<td><button class="myButton" id="dbcancelday15"
											onclick="cancel('day15')">Cancel</button></td>
								</tr>
							</table>

						</td>
					</tr>
					<tr>
			<td>day16</td>
			<td>${pojo.day16}</td>
			
			<td>

							<table>
								<tr>
									<td><textarea rows="20" id='dbtextday16'
											style="display: none">${pojo.day16}</textarea></td>
									<td>
										<button class="myButton" id="dbsubmitday16"
											style="display: none" onclick="submit('day16')">Submit</button>
									</td>
									<td>
										<button class="myButton" id="dbupdateday16"
											style="display: blank" onclick="update('day16')">Update</button>
									</td>
									<td><button class="myButton" id="dbcancelday16"
											onclick="cancel('day16')">Cancel</button></td>
								</tr>
							</table>

						</td>
			</tr>
			<tr>
			<td>day17</td>
			<td>${pojo.day17}</td>
			<td>

							<table>
								<tr>
									<td><textarea rows="20" id='dbtextday17'
											style="display: none">${pojo.day17}</textarea></td>
									<td>
										<button class="myButton" id="dbsubmitday17"
											style="display: none" onclick="submit('day17')">Submit</button>
									</td>
									<td>
										<button class="myButton" id="dbupdateday17"
											style="display: blank" onclick="update('day17')">Update</button>
									</td>
									<td><button class="myButton" id="dbcancelday17"
											onclick="cancel('day17')">Cancel</button></td>
								</tr>
							</table>

						</td>
			</tr>
			<tr>
			<td>day18</td>
			<td>${pojo.day18}</td>
			<td>

							<table>
								<tr>
									<td><textarea rows="20" id='dbtextday18'
											style="display: none">${pojo.day18}</textarea></td>
									<td>
										<button class="myButton" id="dbsubmitday18"
											style="display: none" onclick="submit('day18')">Submit</button>
									</td>
									<td>
										<button class="myButton" id="dbupdateday18"
											style="display: blank" onclick="update('day18')">Update</button>
									</td>
									<td><button class="myButton" id="dbcancelday18"
											onclick="cancel('day18')">Cancel</button></td>
								</tr>
							</table>

						</td>
			</tr>
			<tr>
			<td>day19</td>
			<td>${pojo.day19}</td>
			<td>

							<table>
								<tr>
									<td><textarea rows="20" id='dbtextday19'
											style="display: none">${pojo.day19}</textarea></td>
									<td>
										<button class="myButton" id="dbsubmitday19"
											style="display: none" onclick="submit('day19')">Submit</button>
									</td>
									<td>
										<button class="myButton" id="dbupdateday19"
											style="display: blank" onclick="update('day19')">Update</button>
									</td>
									<td><button class="myButton" id="dbcancelday19"
											onclick="cancel('day19')">Cancel</button></td>
								</tr>
							</table>

						</td>
			</tr>
			<tr>
			<td>day20</td>
			<td>${pojo.day20}</td>
			<td>

							<table>
								<tr>
									<td><textarea rows="20" id='dbtextday20'
											style="display: none">${pojo.day20}</textarea></td>
									<td>
										<button class="myButton" id="dbsubmitday20"
											style="display: none" onclick="submit('day20')">Submit</button>
									</td>
									<td>
										<button class="myButton" id="dbupdateday20"
											style="display: blank" onclick="update('day20')">Update</button>
									</td>
									<td><button class="myButton" id="dbcancelday20"
											onclick="cancel('day20')">Cancel</button></td>
								</tr>
							</table>

						</td>
			</tr>
			<tr>
			<td>day21</td>
			<td>${pojo.day21}</td>
			<td>

							<table>
								<tr>
									<td><textarea rows="20" id='dbtextday21'
											style="display: none">${pojo.day21}</textarea></td>
									<td>
										<button class="myButton" id="dbsubmitday21"
											style="display: none" onclick="submit('day21')">Submit</button>
									</td>
									<td>
										<button class="myButton" id="dbupdateday21"
											style="display: blank" onclick="update('day21')">Update</button>
									</td>
									<td><button class="myButton" id="dbcancelday21"
											onclick="cancel('day21')">Cancel</button></td>
								</tr>
							</table>

						</td>
		</tr>
	</table>
	</div>
	</div>
	<div class="parent-div">
	 <h3 class="accordion-heading">Week4 <span class="plusminus">+</span></h3>
	<div class="accordion-body">
   <table border="1" width="100%" cellpadding="2">


					<tr>
						<td>day22</td>
						<td>${pojo.day22}</td>
						<td>

							<table>
								<tr>
									<td><textarea rows="20" id='dbtextday22'
											style="display: none">${pojo.day22}</textarea></td>
									<td>
										<button class="myButton" id="dbsubmitday22"
											style="display: none" onclick="submit('day22')">Submit</button>
									</td>
									<td>
										<button class="myButton" id="dbupdateday22"
											style="display: blank" onclick="update('day22')">Update</button>
									</td>
									<td><button class="myButton" id="dbcancelday22"
											onclick="cancel('day22')">Cancel</button></td>
								</tr>
							</table>

						</td>
					</tr>
					<tr>
			<td>day23</td>
			<td>${pojo.day23}</td>
			
			<td>

							<table>
								<tr>
									<td><textarea rows="20" id='dbtextday23'
											style="display: none">${pojo.day23}</textarea></td>
									<td>
										<button class="myButton" id="dbsubmitday23"
											style="display: none" onclick="submit('day23')">Submit</button>
									</td>
									<td>
										<button class="myButton" id="dbupdateday23"
											style="display: blank" onclick="update('day23')">Update</button>
									</td>
									<td><button class="myButton" id="dbcancelday23"
											onclick="cancel('day23')">Cancel</button></td>
								</tr>
							</table>

						</td>
			</tr>
			<tr>
			<td>day24</td>
			<td>${pojo.day24}</td>
			<td>

							<table>
								<tr>
									<td><textarea rows="20" id='dbtextday24'
											style="display: none">${pojo.day24}</textarea></td>
									<td>
										<button class="myButton" id="dbsubmitday24"
											style="display: none" onclick="submit('day24')">Submit</button>
									</td>
									<td>
										<button class="myButton" id="dbupdateday24"
											style="display: blank" onclick="update('day24')">Update</button>
									</td>
									<td><button class="myButton" id="dbcancelday24"
											onclick="cancel('day24')">Cancel</button></td>
								</tr>
							</table>

						</td>
			</tr>
			<tr>
			<td>day25</td>
			<td>${pojo.day25}</td>
			<td>

							<table>
								<tr>
									<td><textarea rows="20" id='dbtextday25'
											style="display: none">${pojo.day25}</textarea></td>
									<td>
										<button class="myButton" id="dbsubmitday25"
											style="display: none" onclick="submit('day25')">Submit</button>
									</td>
									<td>
										<button class="myButton" id="dbupdateday25"
											style="display: blank" onclick="update('day25')">Update</button>
									</td>
									<td><button class="myButton" id="dbcancelday25"
											onclick="cancel('day25')">Cancel</button></td>
								</tr>
							</table>

						</td>
			</tr>
			<tr>
			<td>day26</td>
			<td>${pojo.day26}</td>
			<td>

							<table>
								<tr>
									<td><textarea rows="20" id='dbtextday26'
											style="display: none">${pojo.day26}</textarea></td>
									<td>
										<button class="myButton" id="dbsubmitday26"
											style="display: none" onclick="submit('day26')">Submit</button>
									</td>
									<td>
										<button class="myButton" id="dbupdateday26"
											style="display: blank" onclick="update('day26')">Update</button>
									</td>
									<td><button class="myButton" id="dbcancelday26"
											onclick="cancel('day26')">Cancel</button></td>
								</tr>
							</table>

						</td>
			</tr>
			<tr>
			<td>day27</td>
			<td>${pojo.day27}</td>
			<td>

							<table>
								<tr>
									<td><textarea rows="20" id='dbtextday27'
											style="display: none">${pojo.day27}</textarea></td>
									<td>
										<button class="myButton" id="dbsubmitday27"
											style="display: none" onclick="submit('day27')">Submit</button>
									</td>
									<td>
										<button class="myButton" id="dbupdateday27"
											style="display: blank" onclick="update('day27')">Update</button>
									</td>
									<td><button class="myButton" id="dbcancelday27"
											onclick="cancel('day27')">Cancel</button></td>
								</tr>
							</table>

						</td>
			</tr>
			<tr>
			<td>day28</td>
			<td>${pojo.day28}</td>
			<td>

							<table>
								<tr>
									<td><textarea rows="20" id='dbtextday28'
											style="display: none">${pojo.day28}</textarea></td>
									<td>
										<button class="myButton" id="dbsubmitday28"
											style="display: none" onclick="submit('day28')">Submit</button>
									</td>
									<td>
										<button class="myButton" id="dbupdateday28"
											style="display: blank" onclick="update('day28')">Update</button>
									</td>
									<td><button class="myButton" id="dbcancelday28"
											onclick="cancel('day28')">Cancel</button></td>
								</tr>
							</table>

						</td>
		</tr>
	</table>
	</div>
	</div>
	<div class="parent-div">
	 <h3 class="accordion-heading">Week5 <span class="plusminus">+</span></h3>
	<div class="accordion-body">
  <table border="1" width="100%" cellpadding="2">


					<tr>
						<td>day29</td>
						<td>${pojo.day29}</td>
						<td>

							<table>
								<tr>
									<td><textarea rows="20" id='dbtextday29'
											style="display: none">${pojo.day29}</textarea></td>
									<td>
										<button class="myButton" id="dbsubmitday29"
											style="display: none" onclick="submit('day29')">Submit</button>
									</td>
									<td>
										<button class="myButton" id="dbupdateday29"
											style="display: blank" onclick="update('day29')">Update</button>
									</td>
									<td><button class="myButton" id="dbcancelday29"
											onclick="cancel('day29')">Cancel</button></td>
								</tr>
							</table>

						</td>
					</tr>
					<tr>
			<td>day30</td>
			<td>${pojo.day30}</td>
			
			<td>

							<table>
								<tr>
									<td><textarea rows="20" id='dbtextday30'
											style="display: none">${pojo.day30}</textarea></td>
									<td>
										<button class="myButton" id="dbsubmitday30"
											style="display: none" onclick="submit('day30')">Submit</button>
									</td>
									<td>
										<button class="myButton" id="dbupdateday30"
											style="display: blank" onclick="update('day30')">Update</button>
									</td>
									<td><button class="myButton" id="dbcancelday30"
											onclick="cancel('day30')">Cancel</button></td>
								</tr>
							</table>

						</td>
			</tr>
			<tr>
			<td>day31</td>
			<td>${pojo.day31}</td>
			<td>

							<table>
								<tr>
									<td><textarea rows="20" id='dbtextday31'
											style="display: none">${pojo.day31}</textarea></td>
									<td>
										<button class="myButton" id="dbsubmitday31"
											style="display: none" onclick="submit('day31')">Submit</button>
									</td>
									<td>
										<button class="myButton" id="dbupdateday31"
											style="display: blank" onclick="update('day31')">Update</button>
									</td>
									<td><button class="myButton" id="dbcancelday31"
											onclick="cancel('day31')">Cancel</button></td>
								</tr>
							</table>

						</td>
			</tr>
			<tr>
			<td>Comments</td>
			<td>${pojo.day32}</td>
			<td>

							<table>
								<tr>
									<td><textarea rows="20" id='dbtextday32'
											style="display: none">${pojo.day32}</textarea></td>
									<td>
										<button class="myButton" id="dbsubmitday32"
											style="display: none" onclick="submit('day32')">Submit</button>
									</td>
									<td>
										<button class="myButton" id="dbupdateday32"
											style="display: blank" onclick="update('day32')">Update</button>
									</td>
									<td><button class="myButton" id="dbcancelday32"
											onclick="cancel('day32')">Cancel</button></td>
								</tr>
							</table>

						</td>
			</tr>
			
	</table>
	</div>
	</div>

</c:forEach>
	

</c:if>