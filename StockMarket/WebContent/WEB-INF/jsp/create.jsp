<html>
<head>
	<title>Add the Stock Information </title>
<style type="text/css">
	

*,
*:after,
*:before {
	-webkit-box-sizing: border-box;
	-moz-box-sizing: border-box;
	box-sizing: border-box;
}

.clearfix:before,
.clearfix:after {
	content: " ";
	display: table;
}

.clearfix:after {
	clear: both;
}

body {
	font-family: sans-serif;
	background: #f6f9fa;
}

h1 {
	text-align: center;
}

a {
  color: #ccc;
  text-decoration: none;
  outline: none;
}

/*Fun begins*/
.tab_container {
	width: 97%;
	margin: 0 auto;
	padding-top: 70px;
	position: relative;
}

section {
  clear: both;
  padding-top: 10px;
  display: none;
}

input[type="radio"]{
  clear: both;
  padding-top: 10px;
  display: none;
}

input[type="submit"]{
margin-top: 50px;
margin-left: 500px;
}

label {
  font-weight: 700;
  font-size: 18px;
  display: block;
  float: left;
  width: 25%;
  padding: 0.8em;
  color: #757575;
  cursor: pointer;
  text-decoration: none;
  text-align: center;
  background: #f0f0f0;
}

#tab1:checked ~ #content1,
#tab2:checked ~ #content2,
#tab3:checked ~ #content3,
#tab4:checked ~ #content4,
#tab5:checked ~ #content5 {
  display: block;
  padding: 25px;
  background: #fff;
  color: #999;
  border-bottom: 2px solid #f0f0f0;
}

.tab_container .tab-content p,
.tab_container .tab-content h3 {
  -webkit-animation: fadeInScale 0.7s ease-in-out;
  -moz-animation: fadeInScale 0.7s ease-in-out;
  animation: fadeInScale 0.7s ease-in-out;
}
.tab_container .tab-content h3  {
  text-align: center;
}

.tab_container [id^="tab"]:checked + label {
  background: #fff;
  box-shadow: inset 0 3px #0CE;
}

.tab_container [id^="tab"]:checked + label .fa {
  color: #0CE;
}

label .fa {
  font-size: 1.3em;
  margin: 0 0.4em 0 0;
}

/*Media query*/
@media only screen and (max-width: 900px) {
  label span {
    display: none;
  }
  
  .tab_container {
    width: 95%;
  }
}

/*Content Animation*/
@keyframes fadeInScale {
  0% {
  	transform: scale(0.9);
  	opacity: 0;
  }
  
  100% {
  	transform: scale(1);
  	opacity: 1;
  }
}

.no_wrap {
  text-align:center;
  color: #0ce;
}
.link {
  text-align:center;
}

#table1 {
     border-spacing: 10px;
}
#dynamicInput {
     border-spacing: 5px;
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

select { 
width:174px; }

.date { 
width:174px; }

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

<script>
function myDividend(value) {

    for (i = 0; i < value; i++) {
    	var newdiv = document.createElement('tr');
    	
        newdiv.innerHTML = 
        "<td>Dividend Per Share:</td><td><input type='text' name='dividendpershare[]'>  </td>"
        +"<td>Dividend Type:</td><td><input type='text' name='dividendtype[]'> </td>"
        +"<td>Dividend Declare Month:</td><td><input type='date' name='dividenddeclaremonth[]'></td>"
        +" <td>Dividend Effective Month:</td><td><input type='date' name='dividendeffectivemonth[]'>"
       
        document.getElementById("dividendInput").appendChild(newdiv);
       
    }
}

function myBonus(value) {

    for (i = 0; i < value; i++) {
    	var newdiv = document.createElement('tr');
    	
        newdiv.innerHTML = "<td>Bonus Ratio:</td><td><input type='text' name='bonusratio[]'> </td>"
        +"<td>Bonus Declare Month:</td><td><input type='date' name='bonusdeclareyear[]'></td>"
        +" <td>Bonus Effective Month:</td><td><input type='date' name='bonuseffectiveyear[]'>"
        document.getElementById("bonusInput").appendChild(newdiv);
    }
   
    
}

function mySplit(value) {

    for (i = 0; i < value; i++) {
    	
		var newdiv = document.createElement('tr');
        newdiv.innerHTML = "<td>Split Ratio:</td><td><input type='text' name='splitratio[]'> </td>"
        +"<td>Split Declare Month:</td><td><input type='date' name='splitdeclareyear[]'></td>"
        +" <td>Split Effective Month:</td><td><input type='date' name='spliteffectiveyear[]'>"
        document.getElementById("splitInput").appendChild(newdiv);
        
    }
   
    
}

function myResults(value) {

    for (i = 0; i < value; i++) {
    	var newdiv = document.createElement('tr');
        newdiv.innerHTML = "<td>Quarter Results Date :</td><td><input type='date' name='quarterresultsdate[]'>"
        document.getElementById("resultsInput").appendChild(newdiv);
        
    }
   
    
}
</script>
	
</head>
<body>
	<%-- ${message} --%>
	<form method="post" action="index.jsp" align="right">
	<input type="submit" value="Home" class="myHome" />
</form>
	
	<h1> Add Stock Details </h1>
		<div class="tab_container">
			<input id="tab1" type="radio" name="tabs" checked>
			<label for="tab1"><i class="fa fa-code"></i><span>Stock Details</span></label>

			<input id="tab2" type="radio" name="tabs">
			<label for="tab2"><i class="fa fa-pencil-square-o"></i><span>Dividend</span></label>

			<input id="tab3" type="radio" name="tabs">
			<label for="tab3"><i class="fa fa-bar-chart-o"></i><span>Bonus</span></label>

			<input id="tab4" type="radio" name="tabs">
			<label for="tab4"><i class="fa fa-folder-open-o"></i><span>Split</span></label>

			<section id="content1" class="tab-content">
			<jsp:include page="addstock.jsp"></jsp:include>
			</section>

			<section id="content2" class="tab-content">
				<jsp:include page="dividend.jsp"></jsp:include>
			</section>

			<section id="content3" class="tab-content">
				<jsp:include page="bonus.jsp"></jsp:include>
			</section>

			<section id="content4" class="tab-content">
				<jsp:include page="split.jsp"></jsp:include>
			</section>
		</div>

     
</body>
</html>


