

<form action="create" name="shareinformation" method="post">

	<h3>Stock Information</h3>
	<table id="table1">
		<tr>
			<td>Company Name:</td>
			<td><input type="text" name="stockname"></td>
			<td>BSE Script Id:</td>
			<td><input type="text" name="bsescriptid"></td>
			<td>BSE Script Code:</td>
			<td><input type="text" name="bsescriptcode"></td>
			<td>NSE Script Code:</td>
			<td><input type="text" name="nsescriptcode"></td>
		</tr>
		<tr>
		</tr>
		<tr>
			<td>Sector:</td>
			<td><input type="text" name="sector"></td>
			<td>IndustryType:</td>
			<td><input type="text" name="industrytype"></td>
			<td>Cap Type:</td>
			<td><select name="captype">
					<option value="Select">Select</option>
					<option value="Large Cap">Large Cap</option>
					<option value="Mid Cap">Mid Cap</option>
					<option value="Small Cap">Small Cap</option>
					<option value="Penny Stock">Penny Stock</option>
			</select></td>
			<td>Company Type:</td>
			<td><select name="companytype">
					<option value="Select">Select</option>
					<option value="Governmentpsu">Government PSU</option>
					<option value="Private">Private</option>
			</select></td>
		</tr>
		<tr>
		</tr>
		<tr>
			<td>Capital Value:</td>
			<td><input type="text" name="capitalvalue"></td>
			<td>Face Value:</td>
			<td><input type="text" name="facevalue"></td>
			<td>Book Value:</td>
			<td><input type="text" name="bookvalue"></td>
			<td>P/E:</td>
			<td><input type="text" name="pevalue"></td>
		</tr>
		<tr>
		</tr>
		<tr>
			<td>Parent Group Name:</td>
			<td><input type="text" name="parentgroupname"></td>
			<td>IPO Year:</td>
			<td><input type="date" class="date" name="ipoyear"></td>
			<td>NumberOfShareIssued:</td>
			<td><input type="text" name="numberofsharesissued"></td>
			<td>Promoters Stake:</td>
			<td><input type="text" name="promoterstake"></td>

		</tr>
		<tr>
			<td>Public Stake:</td>
			<td><input type="text" name="publicshares"></td>
			<td>Indian Shareholders Stake:</td>
			<td><input type="text" name="indianpromoters"></td>
			<td>Foreign Shareholders Stake:</td>
			<td><input type="text" name="forgienpromoters"></td>
			<td>FII & DII Stake:</td>
			<td><input type="text" name="fiidii"></td>
		</tr>
	</table>
	<table id="table1">
		<tr>
			<td>Important Points:</td>
			<td><textarea name="importantpoints" rows="4" cols="50"></textarea></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td>Reference:</td>
			<td><textarea name="reference" rows="4" cols="50"></textarea></td>

		</tr>
	</table>
	 <input type="submit" align="center" class="myButton" name="Submit" value="Submit" >
</form>
