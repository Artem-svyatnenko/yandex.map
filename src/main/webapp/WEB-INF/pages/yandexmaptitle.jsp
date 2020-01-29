<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<title>Spring Yandex.Map project</title>
		<script type="text/javascript"></script>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/yandexmaptitle.css">
	</head>
	 
	<body>
		<div id="header">
			<h1>Тестовое задание "Отображение адресов на Яндекс.Карте" </h1>
		</div>
		<div id="bodysite">
			<div style="display: inline-block; vertical-align: top; margin-right: 20px;">
				<h1>Укажите адреса: </h1>
				<form action="/springapp/title" method="post">
					<div class="address_input">
						<label style="margin: 0 5px 0 0;">1. Город:</label>
						<input style="max-width: 100px; min-width: 100px" id="city1" name="city1" type="text" value="${city1}">                       
						<label style="margin: 0 5px 0 15px;"> улица:</label>
						<input style="max-width: 150px; min-width: 150px" id="street1" name="street1" type="text" value="${street1}">
						<label style="margin: 0 5px 0 15px;">дом:</label>
						<input style="max-width: 50px; min-width: 50px" id="house1" name="house1" type="text" value="${house1}">
					</div>
					<div class="address_input">
						<label style="margin: 0 5px 0 0;">2. Город:</label>
						<input style="max-width: 100px; min-width: 100px" id="city2" name="city2" type="text" value="${city2}">
						<label style="margin: 0 5px 0 15px;"> улица:</label>
						<input style="max-width: 150px; min-width: 150px" id="street2" name="street2" type="text" value="${street2}">
						<label style="margin: 0 5px 0 15px;">дом:</label>
						<input style="max-width: 50px; min-width: 50px" id="house2" name="house2" type="text" value="${house2}">
					</div>
					<div class="address_input">
						<label style="margin: 0 5px 0 0;">3. Город:</label>
						<input style="max-width: 100px; min-width: 100px" id="city3" name="city3" type="text" value="${city3}">
						<label style="margin: 0 5px 0 15px;"> улица:</label>
						<input style="max-width: 150px; min-width: 150px" id="street3" name="street3" type="text" value="${street3}">
						<label style="margin: 0 5px 0 15px;">дом:</label>
						<input style="max-width: 50px; min-width: 50px" id="house3" name="house3" type="text" value="${house3}">
					</div>
					<div class="address_input">
						<label style="margin: 0 5px 0 0;">4. Город:</label>
						<input style="max-width: 100px; min-width: 100px" id="city4" name="city4" type="text" value="${city4}">
						<label style="margin: 0 5px 0 15px;"> улица:</label>
						<input style="max-width: 150px; min-width: 150px" id="street4" name="street4" type="text" value="${street4}">
						<label style="margin: 0 5px 0 15px;">дом:</label>
						<input style="max-width: 50px; min-width: 50px" id="house4" name="house4" type="text" value="${house4}">
					</div>
					<div class="address_input">
						<label style="margin: 0 5px 0 0;">5. Город:</label>
						<input style="max-width: 100px; min-width: 100px" id="city5" name="city5" type="text" value="${city5}">
						<label style="margin: 0 5px 0 15px;"> улица:</label>
						<input style="max-width: 150px; min-width: 150px" id="street5" name="street5" type="text" value="${street5}">
						<label style="margin: 0 5px 0 15px;">дом:</label>
						<input style="max-width: 50px; min-width: 50px" id="house5" name="house5" type="text" value="${house5}">
					</div>
					<input type="submit" style="float: right;" value="Показать">
				</form>
			</div>
			<div style="display: inline-block; vertical-align: top;">
				<h1>${mapTitle}</h1>
				<img align="right" src="${mapImage}" alt="Яндекс.Карта">
			</div>
		</div>
	</body>
	 
	 
</html>