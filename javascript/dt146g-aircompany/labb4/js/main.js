/*******************************************************************************
 
* Laboration 4, Kurs: DT146G
 
* File: main.js
 
* Desc: main JavaScript file for laboration 4
 
* 
 
* Iana Kalinichenko
 
* iaka1500
 
* iaka1500@student.miun.se
 
******************************************************************************/

/* Global variables*/
var planeImages = ["img/plane-1.jpg", "img/plane-2.jpg", "img/plane-3.jpeg", "img/plane-4.jpeg"]; //pre-loaded array with plane images
var employeeImages = ["img/employee-1.jpeg", "img/employee-2.jpeg", "img/employee-3.jpeg", "img/employee-4.jpeg"]; //pre-loaded array with employee images
var imagesGallery = [];
var seatsPerRow = [];
var bookingSystem = [];
var arrayForAllSeat = [];
var m = 0;
var positioning;


var thumbOrderNumber = 0;
var thumbIdName = "thumb";

var isFleetPage = false;
var isEmployeePage = false;

var fleetCaptions = [
	"<strong>Godskairbus 227</strong> - with lots of space for really big families",
	"<strong>Godskairbus 334</strong> - tiny, but fast; perfect for budget trips",
	"<strong>GodskeJet Lux27</strong> - for those who would like some extra comfort",
	"<strong>GodskeJet Lux34</strong> - for those who would like a luxurious experience"
]

var employeeCaptions = [
	"<strong>Iana Kalinichenko</strong> - our project manager, making sure no baby cries on board",
	"<strong>Flemming Godskesen</strong> - our first pilot, providing the softest landings",
	"<strong>Didi Godskesen</strong> - our PR manager, spreading the word in social media",
	"<strong>Thor Godskesen</strong> - our second pilot, always getting you to your destination on time"
]

var width = window.innerWidth
|| document.documentElement.clientWidth
|| document.body.clientWidth;

var StateEnum = {
	FREE: "green",
	CLICKED: "blue",
	BOOKED: "red"
};

var TypeEnum = {
	BUSINESS: "B",
	ECONOMY: "E"
};

var canvasFont = "15px Arial";
var canvasTestAlign = "center";
var canvasBorderWidth = "4";
var canvasBorderColor = "blue";
var canvasTextColor = "black";
var rowHeight = 18;
var rowLength = 50;

var seat1;
var seat2;
var seat3;
var rowOnCanvas;

var smallScreen = 480;
var middleScreen = 1024;
var amountOfRows = 6;
var amountOfSeatsPerRow = 3;
var totalAmountOfSeats = 18;
var widthForSeat = 100;
var heightForSeat = 25;
var xForSeat = 0;
var yForSeat = 0;
var previousSeats = 0; //number of previously made seats; used to adjust the numbering in the row that is under making

var tempNumber = 0;
var tempClass = 0;
var tempId = "";
var tempSeat;
var tempWidth;
var tempHeight;
var tempTop;
var tempLeft;
var tempSeatId = "";

/* Functions */
function identifyBrowser() 
{
	var userBrowser = navigator.userAgent;
	var answer = "";

	if(userBrowser.indexOf("Chrome") > -1) 
	{
    		answer += "You are using Google Chrome, aren't you?";
	} else if (userBrowser.indexOf("Safari") > -1) 
	{
    		answer += "You are using Apple Safari, aren't you?";
	} else if (userBrowser.indexOf("Opera") > -1) 
	{
    		answer += "You are using Opera, aren't you?";
	} else if (userBrowser.indexOf("Firefox") !=-1) 
	{
    		answer += "You are using Mozilla Firefox, aren't you?";
	} else if (userBrowser.indexOf("MSIE") !=-1) 
	{
    		answer += "You are using Internet Explorer, aren't you?";
	} else
	{
    		answer += "We cannot identify the browser you are using.";
	}//end else

	document.getElementById("browser").innerHTML = answer;

}//end function identifyBrowser

function start() 
{
	var pathnameArray = window.location.pathname.split( '/' ); //array of parts of the subpage's pathname divided by '/'
	var pageName = pathnameArray[pathnameArray.length-1]; //since the webpage is stored locally, we need only the last part of the pathname for identification of the subpage
	
	if(pageName == "contact.html")
	{
    	identifyBrowser();
	}else 
	{
    	if (pageName == "ourfleet.html") 
		{
    		loadGallery(imagesGallery, planeImages);
			isFleetPage = true;
			showBiggerPicture()
		} else if (pageName == "employee.html") 
		{
    		loadGallery(imagesGallery, employeeImages);
			isEmployeePage = true;
			showBiggerPicture()
		}else if (pageName == "booking.html") 
		{
			showBookings();
			document.getElementById("resetButton").addEventListener("click", reset, false);
			document.getElementById("submitButton").addEventListener("click", submit, false);
		}//end else if

		
	}// end else 
	

}//end function start

function loadGallery(arrayToCopyTo, arrayToCopyFrom)
{
	for(var i = 0; i < arrayToCopyFrom.length; i++) 
	{ 
		thumbIdName = "thumb";
		thumbOrderNumber += 1; //since array indexing starts from 0 and thumbnails order numbering starts from 1
		thumbIdName += thumbOrderNumber;
		arrayToCopyTo[i] = new Image; 
		arrayToCopyTo[i].src = arrayToCopyFrom[i]; 
		arrayToCopyTo[i].alt = document.getElementById(thumbIdName).alt;
	}//end for

}//end function loadGallery

function showBiggerPicture()
{
	document.getElementById("thumb1").addEventListener("click", showPic, false);
	document.getElementById("thumb2").addEventListener("click", showPic, false);
	document.getElementById("thumb3").addEventListener("click", showPic, false);
	document.getElementById("thumb4").addEventListener("click", showPic, false);
		//Adaptation for another type of interaction: if instead of clicking the hover-over-interaction should be implemented,
		//you would need only to change "click" to "mouseOver" in 4 lines above, and uncomment 4 lines below + extra-function.
		//It also means that you would need top adapt the explanatory text - in HTML - that appears from the start when no picture is chosen.
		//document.getElementById("thumb1").addEventListener("mouseout", mouseOut, false);
		//document.getElementById("thumb2").addEventListener("mouseout", mouseOut, false);
		//document.getElementById("thumb3").addEventListener("mouseout", mouseOut, false);
		//document.getElementById("thumb4").addEventListener("mouseout", mouseOut, false);

}//end function showBiggerPicture

function showPic(event)
{
	var image = document.createElement("figure");
	var caption = document.createElement("figcaption");

	var tempString = event.target.id;
	var lastChar = tempString.charAt(tempString.length-1);
	var positionNumber = parseInt(lastChar);
	positionNumber -= 1;

	if (isFleetPage == true) 
	{
    	caption.innerHTML = fleetCaptions[positionNumber];
		
	} else if (isEmployeePage == true) 
	{
    	caption.innerHTML = employeeCaptions[positionNumber];
			
	}//end else if

	image.appendChild(imagesGallery[positionNumber]);
	image.appendChild(caption);
	document.getElementById("realpic").innerHTML = "";
	document.getElementById("realpic").appendChild(image);

}//end function showPic

//Extra function when the hover-over-interaction should be implemented,
//function mouseOut()
//{
//	document.getElementById("realpic").innerHTML = "<p>Hover over a thumbnail to see a bigger picture here!</p>";
//
//}//end function mouseOut

function showBookings()
{
	var canvas = document.getElementById("bookings");
	var ctx = canvas.getContext("2d");
	var ratio = decideRatio(); //times to multiply the initial values if the screen gets bigger
	if (width > smallScreen & width <= middleScreen) 
	{
    	ratio = 3;
	} else if (width > middleScreen)
	{
    	ratio = 6;
	}//end else

	for(var i = 1; i <= amountOfRows; i++)
	{ 
		xForSeat = 0;
 
		for(var j = 1; j <= amountOfSeatsPerRow; j++)
		{ 
			seat = new Seat(xForSeat, yForSeat, widthForSeat, heightForSeat, j+previousSeats);
			positioning = canvas.getBoundingClientRect();
			seat.showSeatOnCanvas(ctx, positioning, i, j);
			seatsPerRow[j-1] = seat; //i-1 in order to account for indexing that starts from 0
			arrayForAllSeat[m] = seat;
			m++;
			xForSeat += widthForSeat;
			if (sessionStorage.getItem(seat.id) != null)
			{
    			seat.state = sessionStorage.getItem(seat.id);
				seat.updateColor();
				seat.drawSeat(ctx);
			} else 
			{
    			sessionStorage.setItem(seat.id, seat.state);
			}//end else
			
		}//end for

		rowOnCanvas = new Row(seatsPerRow[0], seatsPerRow[1], seatsPerRow[2]);
		bookingSystem[i-1] = rowOnCanvas; //i-1 in order to account for indexing that starts from 0
		yForSeat += heightForSeat;
		previousSeats += amountOfSeatsPerRow;
	}//end for

}//end function showBookings

function Row(seat1, seat2, seat3) 
{
	this.firstSeat = seat1;
	this.secondSeat = seat2;
	this.thirdSeat = seat3;
}

function Seat(x, y, width, height, number) 
{
	this.x = x;
	this.y = y;
	this.width = width;
	this.height = height;
	this.borderWidth = canvasBorderWidth;
	this.borderColor = canvasBorderColor;
	this.number = number;
	this.type = "";
	this.assignType();
	this.id = "";
	this.createId();
	this.state = StateEnum.FREE;
	this.color = this.state; //redundant, but good to have if in future there would be functions where state and color for the state would not be synchronised

}

Seat.prototype.createId = function() 
{
	  
	  this.id += this.number;
	  this.id += this.type;

}

Seat.prototype.assignType = function() 
{
	if (this.number <= 6)
	{
    	this.type = TypeEnum.BUSINESS;
	} else 
	{
    	this.type = TypeEnum.ECONOMY;
	}
}

Seat.prototype.updateColor = function() 
{
	this.color = this.state;
}

Seat.prototype.drawSeat = function(ctx) 
{
	ctx.beginPath();
	ctx.lineWidth = this.borderWidth;
	ctx.strokeStyle = this.borderColor;
	ctx.rect(this.x, this.y, this.width, this.height);
	ctx.fillStyle = this.color;
	ctx.fill();
	ctx.stroke();
	ctx.fillStyle = canvasTextColor;
	ctx.font = canvasFont;
	ctx.textAlign = canvasTestAlign;     
	ctx.fillText(this.number, (this.x+rowLength), this.y+rowHeight);
}

Seat.prototype.showSeatOnCanvas = function(ctx, positioning, i, j) 
{
	this.drawSeat(ctx);
	var clickableArea = document.createElement("DIV");
	tempWidth = positioning.width / 3;
	tempHeight = positioning.height / 6;
	tempTop = positioning.top + (tempHeight * (i-1));
	tempLeft = positioning.left + (tempWidth * (j-1));
	clickableArea.style.top = tempTop + "px";
	clickableArea.style.left = tempLeft + "px";
	tempWidth = positioning.width / 3;
	tempHeight = positioning.height / 6;
	clickableArea.style.width = tempWidth + "px";
	clickableArea.style.height = tempHeight + "px";
	clickableArea.id = this.id;
	document.getElementById("inner").appendChild(clickableArea);
	document.getElementById(this.id).addEventListener("click", changeColor, false);
}

function decideRatio()
{
	if (width < 481)
	{
    	return 1;
	} else if (width > 480 & width < 1025)
	{
    	return 3;
	} else if (width > 1024)
	{
    	return 6;
	}//end else

}//end decideRatio

function changeColor(event)
{
	var canvas = document.getElementById("bookings");
	var ctx = canvas.getContext("2d");
	for(var i2 = 0; i2 < totalAmountOfSeats; i2++)
		{ 
			if(event.target.id == arrayForAllSeat[i2].id)
			{ 
				if(sessionStorage.getItem(arrayForAllSeat[i2].id) != StateEnum.BOOKED)
				{ 
					arrayForAllSeat[i2].state = StateEnum.CLICKED;
					arrayForAllSeat[i2].updateColor();
					arrayForAllSeat[i2].drawSeat(ctx);
					document.getElementById("classInput").value = arrayForAllSeat[i2].number < 7 ? "Business" : "Economy";
					document.getElementById("seatInput").value = arrayForAllSeat[i2].number;
				}
			}else
			{ 
				
				arrayForAllSeat[i2].state = sessionStorage.getItem(arrayForAllSeat[i2].id);
				arrayForAllSeat[i2].updateColor();
				arrayForAllSeat[i2].drawSeat(ctx);
				
			}
		
		}//end for

}//end changeColor

function reset(event)
{
	var canvas = document.getElementById("bookings");
	var ctx = canvas.getContext("2d");
	for(var i3 = 0; i3 < totalAmountOfSeats; i3++)
		{ 
			arrayForAllSeat[i3].state = sessionStorage.getItem(arrayForAllSeat[i3].id);
			arrayForAllSeat[i3].updateColor();
			arrayForAllSeat[i3].drawSeat(ctx);
				
		
		}//end for

}//end reset

function submit(event)
{
	var canvas = document.getElementById("bookings");
	var ctx = canvas.getContext("2d");
	tempSeatId = document.getElementById("seatInput").value + (document.getElementById("classInput").value == "Business" ? "B" : "E");
	sessionStorage.setItem(tempSeatId, StateEnum.BOOKED);
	for(var i4 = 0; i4 < totalAmountOfSeats; i4++)
		{ 
			arrayForAllSeat[i4].state = sessionStorage.getItem(arrayForAllSeat[i4].id);
			arrayForAllSeat[i4].updateColor();
			arrayForAllSeat[i4].drawSeat(ctx);	
	}//end for
	var newWindow=window.open();
	var newDocument=newWindow.document;
	var HTMLtext = "";
	HTMLtext += "<!DOCTYPE html><html><head><title>Boarding Pass</title></head>";
	HTMLtext += "<style>body {background-color: white;}@media print {p {color: black;}}</style>";
	HTMLtext += "<body><p>First Name: " + document.getElementById("FN").value;
	HTMLtext += "</p>";
	HTMLtext += "<p>Last Name: " + document.getElementById("LN").value;
	HTMLtext += "</p>";
	HTMLtext += "<p>Personal Number: " + document.getElementById("PN").value;
	HTMLtext += "</p>";
	HTMLtext += "<p>Class: " + document.getElementById("classInput").value;
	HTMLtext += "</p>";
	HTMLtext += "<p>Seat Number: " + document.getElementById("seatInput").value;
	HTMLtext += "</p>";
	HTMLtext += "</body></html>";
	newDocument.write(HTMLtext);
	newDocument.close();


}//end reset

/* Main flow */
window.addEventListener( "load", start, false);