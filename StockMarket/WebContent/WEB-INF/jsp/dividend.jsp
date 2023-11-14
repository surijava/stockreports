<h3>Stock Dividend Information</h3>

<form action="dividend" name="dividend" method="post">

<table id="table1">
	<tr>
		<td>Number Of Times Per Year: <select name="dividendtimes"
			onchange="myDividend(this.value)">
				<option value="0">0</option>
				<option value="1">1</option>
				<option value="2">2</option>
				<option value="3">3</option>
				<option value="4">4</option>
		</select>
		</td>
	</tr>
</table>
<table id="dividendInput">
</table>
<table id="table1">
	<tr>
		<td>Comments :</td>
		<td><textarea name="divcomments" rows="4" cols="50"></textarea></td>
	</tr>
</table>
 <input type="submit" align="center" class="myButton" name="Submit" value="Submit" >
</form>