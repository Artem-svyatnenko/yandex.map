package com.spring.springapp.controller;
 
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
 
@Controller
public class MainController {
 
	//Контроллер главнойо страницы без запросов
    @RequestMapping("/title")
    public String yandexMapTitle(Model model) {
    	
    	//Передача параметров карты в Представление
    	model.addAttribute("mapTitle", "Здесь будут адреса на Яндекс.Карте:");
		model.addAttribute("mapImage", "https://static-maps.yandex.ru/1.x/?ll=37.620070,55.753630&size=600,450&z=13&l=map");
        return "yandexmaptitle";
    }
    
    //Контроллер главнойо страницы с запросом POST
    @RequestMapping(value = "/title",  method = RequestMethod.POST)
    public String yandexMapTitlePost(HttpServletRequest request, Model model) {
    	
    	
    	ArrayList<Address> addresses = new ArrayList<>();
    	//Заполнение списка адресов, полученных из пост запроса
    	for(int i = 0; i < 5; i++) {
    		addresses.add(new Address(request.getParameter("city" + (i + 1)), 
					    			  request.getParameter("street" + (i + 1)),
					    			  request.getParameter("house" + (i + 1))));
    	}
    	
    	ArrayList<Coordinate> coordinates = new ArrayList<>();
    	//Заполнение списка координат из адресов
    	for(int i = 0; i < 5; i++) {
    		//Если адрес корректен - добавление его координат в ArrayList, иначе - обработка исключения
    		try {
    			Coordinate coord = getCoord(addresses.get(i), i + 1);
    			coordinates.add(coord);
    		} catch (NullPointerException e) {
    			System.out.println("Ошибка получения координат");
    		}
    	}
    	
    	//Переменная для сбора url-запроса строки для получения карты из яндекс api
        String mapLink;
        //Если список координат не пустой
        if(coordinates.size() > 0) {
        	//Создание объекта с параметрами области отображения карты на основе списка координат
        	MapParam mapParam = new MapParam(coordinates);
	    	mapLink = "https://static-maps.yandex.ru/1.x/?l=map&bbox=";
	    	//Добавление в url-запрос параметров области отображения
	    	mapLink = mapLink + mapParam.getMinCoord().getLongitude() + "," + mapParam.getMinCoord().getLatitude() 
	    			+ "~" + mapParam.getMaxCoord().getLongitude() + "," + mapParam.getMaxCoord().getLatitude() + "&size=600,450&z=13&l=map&pt=";
	    	
	    	boolean isFirstCoord = true;
	    	//Добавление в url-запрос долготы и шиорты каждой координаты из списка
	    	for(Coordinate coord: coordinates) {
	    		//При добавлении первой координаты не добавляется промежуточный знак "~"
	    		if (isFirstCoord) {
	    			mapLink = mapLink	+ coord.getLongitude() + "," + coord.getLatitude() + ",pm2rdm" + coord.getNumber();
	    			isFirstCoord = false;
	    		} else mapLink = mapLink + "~" + coord.getLongitude() + "," + coord.getLatitude() + ",pm2rdm" + coord.getNumber();
	    	}
	    	System.out.println(mapLink);
	    //Если список координат пуст - вывести стандартную карту
        } else {
        	mapLink = "https://static-maps.yandex.ru/1.x/?ll=37.620070,55.753630&size=600,450&z=13&l=map";

        }
        
        //Переменная-заголовок карты
    	String mapTitle = "";
    	//Формирование переменной в зависимости от количества координат
    	switch(coordinates.size()) {
    		case 0: mapTitle = "Нет адресов для отображения"; break;
    		case 1: mapTitle = "Показан " + coordinates.size() + " адрес на Яндекс.Карте:"; break;
    		case 2: mapTitle = "Показаны " + coordinates.size() + " адреса на Яндекс.Карте:"; break;
    		case 3: mapTitle = "Показаны " + coordinates.size() + " адреса на Яндекс.Карте:"; break;
    		case 4: mapTitle = "Показаны " + coordinates.size() + " адреса на Яндекс.Карте:"; break;
    		case 5: mapTitle = "Показаны " + coordinates.size() + " адресов на Яндекс.Карте:"; break;
    	}
    	//Добавление переменных для отправки в html-страницу
    	model.addAttribute("mapTitle", mapTitle);
		model.addAttribute("mapImage", mapLink);
		//Добавление переменных для возврата значений полей адресов обратно на html-страницу
		for(int i = 0; i < 5; i++) {
			model.addAttribute("city" + (i + 1), addresses.get(i).getCity());
			model.addAttribute("street" + (i + 1), addresses.get(i).getStreet());
			model.addAttribute("house" + (i + 1), addresses.get(i).getHouse());
		}
        return "yandexmaptitle";
    }
    
    //Метод для получения координат по адресу
	public Coordinate getCoord(Address address, int number) throws NullPointerException {
    	
    	String pos = "";
    	URL url;
    	HttpURLConnection urlconn = null;
		try {	
			//Установка соединения с яндекс api для получения xml-файла с координатами адреса
			url = new URL("https://geocode-maps.yandex.ru/1.x/?apikey=b638afc9-49b1-4d1b-8f56-80baeb0ae752&geocode=" + 
					URLEncoder.encode(address.getCity(), "utf-8") + "+" + 
					URLEncoder.encode(address.getStreet(), "utf-8") + "+" + 
					URLEncoder.encode(address.getHouse(), "utf-8") +"&results=1");
			urlconn = (HttpURLConnection) url.openConnection();
			urlconn.setRequestMethod("GET");
	        urlconn.connect();
	        
	        try(InputStream in = urlconn.getInputStream(); 
	    		OutputStream writer = new FileOutputStream("C:\\Users\\Incubis\\Desktop\\BookCatalog.xml")) {
	        	
	        	//Чтение байт из потока ввода и запись в xml-файл
    	        int c = in.read();
    	        while (c != -1) {
    	            writer.write(c);
    	            c = in.read();
    	        }
    	        
    	        //Получение документа для работы с xml-файлом
    			DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
    			Document document = documentBuilder.parse("C:\\Users\\Incubis\\Desktop\\BookCatalog.xml");
    			//Извлечение координат из элемента по тегу
    			NodeList books2 = document.getElementsByTagName("pos");
    			Node book2 = books2.item(0);
    			//Строковое представление координат
    			pos = book2.getTextContent();    			
    		} catch (SAXException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		} catch (ParserConfigurationException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		} catch (NullPointerException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			urlconn.disconnect();
		}
		
		//Если строковое значение ненулевое - вернуть объект координат, иначе - вызвать ошибку
		if(!pos.equals("")) {
			return new Coordinate(pos, number);
		} else {
			throw new NullPointerException(); 
		}
 
    }
	
	//Класс для хранения параметров отображения карты
	class MapParam {
		
		//Углы прямоугольника отображаемой области
		private final Coordinate minCoord;
		private final Coordinate maxCoord;
		//Центр прямоугольника отображаемой области
		private final Coordinate centerCoord;
		
		MapParam(ArrayList<Coordinate> coordList){
	    	float minLon = coordList.get(0).longitude;
	    	float minLat = coordList.get(0).latitude;
	    	float maxLon = coordList.get(0).longitude;
	    	float  maxLat = coordList.get(0).latitude;
	    	//Парсинг листа с координатами для получения широты и долготы минимальных и максимальных координат
	    	for(Coordinate coordinate: coordList) {
				minLon = minLon > coordinate.longitude ? coordinate.longitude : minLon;
				minLat = minLat > coordinate.latitude ? coordinate.latitude : minLat;
				maxLon = maxLon < coordinate.longitude ? coordinate.longitude : maxLon;
				maxLat = maxLat < coordinate.latitude ? coordinate.latitude : maxLat;
	    	}
	    	//Расчёт углов прямоугольника отображаемой области на основе широты и долготы + добавление небольшого запаса
	    	minCoord = new Coordinate(minLon - ((maxLon - minLon) * 0.05f) - 0.001f, minLat - ((maxLat - minLat) * 0.05f) - 0.001f);
	    	maxCoord = new Coordinate(maxLon + ((maxLon - minLon) * 0.05f) + 0.001f, maxLat + ((maxLat - minLat) * 0.05f) + 0.001f);
	    	centerCoord = new Coordinate(minLon + ((maxLon - minLon) / 2),
	    								 minLat + ((maxLat - minLat) / 2));
		}
		
    	public Coordinate getMinCoord() {
    		return this.minCoord;
    	}
    	
    	public Coordinate getMaxCoord() {
    		return this.maxCoord;
    	}
    	
    	public Coordinate getCenterCoord() {
    		return this.centerCoord;
    	}
	}
    
	//Класс для хранения данных адреса
    class Address {
    	
    	//Названия города, улицы и дома
    	private final String city;
    	private final String street;
    	private final String house;
    	
    	Address(String city, String street, String house){
    		this.city = city;
    		this.street = street;
    		this.house = house;
    	}
    	
    	public String getCity() {
    		return this.city;
    	}
    	
    	public String getStreet() {
    		return this.street;
    	}
    	
    	public String getHouse() {
    		return this.house;
    	}
    }
    
    //Класс для хранения координат
    class Coordinate {
    	
    	//Долгота и широта
    	private final float longitude;
    	private final float latitude;
    	//Номер адреса (для маркера на карте)
    	private int number = 0;
    	
    	//Конструктор для вспомогательных координат класса MapParam
    	Coordinate(float longitude, float latitude) {
	   		this.longitude = longitude;
	   		this.latitude = latitude;
    	}
    	
    	//Конструктор координат через строку с координатами
    	Coordinate(String pos, int number) {
    		 //извлечение долготы и широты из строки с координатами
    		 longitude = Float.valueOf(pos.substring(0, pos.indexOf(' ')));
    		 latitude = Float.valueOf(pos.substring(pos.indexOf(' ') + 1, pos.length() - 1));
    		 this.number = number;
    	}
    	
    	public float getLongitude() {
    		return this.longitude;
    	}
    	
    	public float getLatitude() {
    		return this.latitude;
    	}
    	
    	public int getNumber() {
    		return this.number;
    	}
    }
    
    
}