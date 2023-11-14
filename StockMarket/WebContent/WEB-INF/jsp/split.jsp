<h3>Stock Split Information</h3>

<form action="split" name="split" method="post">

<table id="table2">
	<tr>
		<td>Number Of Times Per Year: <select
			onchange="mySplit(this.value)">
				<option value="0">0</option>
				<option value="1">1</option>
				<option value="2">2</option>
				<option value="3">3</option>
				<option value="4">4</option>
		</select>
		</td>
	</tr>
</table>
<table id="splitInput">
</table>
<table id="table1">
	<tr>
		<td>Comments :</td>
		<td><textarea name="splitcomments" rows="4" cols="50"></textarea></td>
	</tr>
</table>

 <input type="submit"  class="myButton" name="Submit" value="Submit">
</form>