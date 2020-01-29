<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<title>Spring MVC -HelloWorld</title>
		<script type="text/javascript"></script>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/yandexmaptitle.css">
	</head>
	 
	<body>
		<DIV id="bodysite">
			<h1>Укажите адреса: </h1>
			<div id="tabsel" style="align: top;">
				<form action="/springapp/title" method="post">
					<div class="address_input">
						<label style="margin: 0 5px 0 0;">1. Город:</label>
						<input style="max-width: 120px; min-width: 120px" id="city1" name="city1" type="text">
						<label style="margin: 0 5px 0 15px;"> улица:</label>
						<input style="max-width: 200px; min-width: 200px" id="street1" name="street1" type="text">
						<label style="margin: 0 5px 0 15px;">дом:</label>
						<input style="max-width: 50px; min-width: 50px" id="house1" name="house1" type="text">
					</div>
					<div class="address_input">
						<label style="margin: 0 5px 0 0;">2. Город:</label>
						<input style="max-width: 120px; min-width: 120px" id="city2" name="city2" type="text">
						<label style="margin: 0 5px 0 15px;"> улица:</label>
						<input style="max-width: 200px; min-width: 200px" id="street2" name="street2" type="text">
						<label style="margin: 0 5px 0 15px;">дом:</label>
						<input style="max-width: 50px; min-width: 50px" id="house2" name="house2" type="text">
					</div>
					<div class="address_input">
						<label style="margin: 0 5px 0 0;">3. Город:</label>
						<input style="max-width: 120px; min-width: 120px" id="city3" name="city3" type="text">
						<label style="margin: 0 5px 0 15px;"> улица:</label>
						<input style="max-width: 200px; min-width: 200px" id="street3" name="street3" type="text">
						<label style="margin: 0 5px 0 15px;">дом:</label>
						<input style="max-width: 50px; min-width: 50px" id="house3" name="house3" type="text">
					</div>
					<div class="address_input">
						<label style="margin: 0 5px 0 0;">4. Город:</label>
						<input style="max-width: 120px; min-width: 120px" id="city4" name="city4" type="text">
						<label style="margin: 0 5px 0 15px;"> улица:</label>
						<input style="max-width: 200px; min-width: 200px" id="street4" name="street4" type="text">
						<label style="margin: 0 5px 0 15px;">дом:</label>
						<input style="max-width: 50px; min-width: 50px" id="house4" name="house4" type="text">
					</div>
					<div class="address_input">
						<label style="margin: 0 5px 0 0;">5. Город:</label>
						<input style="max-width: 120px; min-width: 120px" id="city5" name="city5" type="text">
						<label style="margin: 0 5px 0 15px;"> улица:</label>
						<input style="max-width: 200px; min-width: 200px" id="street5" name="street5" type="text">
						<label style="margin: 0 5px 0 15px;">дом:</label>
						<input style="max-width: 50px; min-width: 50px" id="house5" name="house5" type="text">
					</div>
					<input type="submit" style="float: right; margin-right: 20px;" value="Показать">
				<form>
			</div>
			<div id="tabsel" >
				<img src="${mapImage}" alt="Яндекс.Карта">
			</div>
		</DIV>
	</body>
	 
	 
</html>