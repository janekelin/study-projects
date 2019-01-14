/*
** Iana Kalinichenko
** iaka1500
** iaka1500@student.miun.se
** DT146G Projektarbete, script
** December 2017 - Januari 2018
*/

/* Global variables*/
//Boolean
var ignoreCase = false;

//Objects
var generalTime;
var tempTime;

//Audio
var errorSound = new Audio('./audio/error.wav');

//Integers
var totalAmountOfTexts = 6;
var averageWordLength = 5;
var grossWPM = 0;
var netWPM = 0;
var elapsedTime = 0;
var errors = 0;
var accuracy = 0;

//Indexes and counters
var indexForSwedishTexts = 0;
var indexForEnglishTexts = 0;
var tempCounter = 1;
var tempIndex = 0;
var wordsCounter = 0;
var textInputCounter = -1;
var indexForCurrentText = 0;

//Strings
var tempId = "";
var buttonState = "";
var tempText = "";
var newText = "";
var charInFocus = "";
var tempString = "";
var charToCompare = "";

//Constant Strings
var guidelines = "Choose the non-selected text in the above menu. If you would like to choose the first text, you need to choose some other text first and then click back to the first text.";
var instruction = "Type here...";


// Constant Arrays
//   indexes in below-mentioned arrays are synchronised to provide information about one and the same item
//   that is dynamically put in the custom object when the page loads
var allTitles = [
    "Förändringens Tid",
    "Moln",
    "Jag har en dröm",
    "Katherine",
    "Love and Weirdness",
    "Integrity"
];

var allAuthors = [
    "Erik Ström",
    "Karin Boye",
    "Martin Luther King Jr.",
    "Abraham Lincoln",
    "Dr. Seuss",
    "Francis Bacon"
];

var allLanguages = [
    "sv",
    "sv",
    "sv",
    "en",
    "en",
    "en"
];

var allTextContents = [
    "Vinden viner över sällsamma ruiner, över berg och slätter, dagar som nätter. Ger världen form inför den kommande storm, likt gudars sång, skall bli dess undergång. Svart som natten, blank likt vatten, i skyn du häver då Allfader kräver. Åter resas skall nu han, som i misteln döden fann. Sonas med sin ene broder, den blinde född av samma moder. Satt att råda är de båda, bröders hand över evigt land.",
    "Se de mäktiga moln, vilkas fjärran höga toppar stolta, skimrande resa sig, vita som vit snö! Lugna glida de fram för att slutligen lugnt dö sakta lösande sig i en skur av svala droppar. Majestätiska moln - genom livet, genom döden gå de leende fram i en strålande sols sken utan skymmande oro i eter så klart ren, gå med storstilat, stilla förakt för sina öden.",
    "Så säger jag er, mina vänner, att jag trots dagens och morgondagens svårigheter har en dröm. Det är en dröm med djupa rötter i den amerikanska drömmen om att denna nation en dag kommer att resa sig och leva ut den övertygelsens innersta mening, som vi håller för självklar: Att alla människor är skapade med samma värde.",
    "I am not bound to win, but I am bound to be true. I am not bound to succeed, but I am bound to live by the light that I have. I must stand with anybody that stands right, and stand with him while he is right, and part with him when he goes wrong.",
    "We are all a little weird and life's a little weird, and when we find someone whose weirdness is compatible with ours, we join up with them and fall in mutual weirdness and call it love.",
    "It's not what we eat but what we digest that makes us strong; not what we gain but what we save that makes us rich; not what we read but what we remember that makes us learned; and not what we profess but what we practice that gives us integrity."
];

//Temporary Arrays
var swedishTexts = [];
var englishTexts = [];
var currentTextArray = [];

/* Functions */

//Starts the time counter, preloads available texts, and activates the play/stop button
function start() {
    generalTime = new Date();
    createTextArray();
    document.getElementById("playButton").addEventListener('click', buttonControl, false);

}//end function start

//Creates objects for each available text and sorts them in two seperate arrays depending on the langauge of the texts
function createTextArray() {
    for(var i1 = 0; i1 < totalAmountOfTexts; i1++){
        var tempText = new Text(allTitles[i1], allAuthors[i1], allLanguages[i1], allTextContents[i1]);
        if (tempText.language === "sv"){
            swedishTexts[indexForSwedishTexts] = tempText;
            indexForSwedishTexts++;
        }else if (tempText.language === "en"){
            englishTexts[indexForEnglishTexts] = tempText;
            indexForEnglishTexts++;
        }
    }
}//end function createTextArray

//Gives appropriate texts to choose depending on the chosen language
function loadTextChooser(event){
    document.getElementById("textTitle").innerText = "";
    document.getElementById("textAuthor").innerText = "";
    document.getElementById("textContent").innerText = guidelines;
    if(event.target.value === "sv"){
        currentTextArray = swedishTexts;
        document.getElementById("englishTexts").checked = false;
    }else if(event.target.value === "en"){
        document.getElementById("swedishTexts").checked = false;
        currentTextArray = englishTexts;
    }

    for(var i2 = 0; i2 < currentTextArray.length; i2++){
        tempCounter += i2;
        tempId = "text" + tempCounter;
        document.getElementById(tempId).innerText = currentTextArray[i2].title;
        tempCounter = 1;
    }
}//end function loadTextChooser

//Loads the text details in the main part, depending on the chosen text
function updateTextDetails(event) {
    tempIndex = parseInt(event.target.value.substr(event.target.value.length-1)) - 1;
    indexForCurrentText = tempIndex;
    document.getElementById("textTitle").innerText = currentTextArray[tempIndex].title;
    document.getElementById("textAuthor").innerText = currentTextArray[tempIndex].author + " (" + countWords(currentTextArray[tempIndex].content) + " words, " + currentTextArray[tempIndex].content.length + " chars)";
    document.getElementById("textContent").innerText = currentTextArray[tempIndex].content;
    document.getElementById("playButton").value = "start";
}//end function updateTextDetails

//Counts words in the chosen text
function countWords(text){
    wordsCounter = 0;
    tempText = text.split(" ");
    for (var i3 = 0; i3 < tempText.length; i3++){
        if (tempText[i3] !== "")
            wordsCounter += 1;
    }

    return wordsCounter;
}//end function countWords

//Refreshes the text content, so that the highlighter returns to the beginning and the previous progress is erased
function startSession(){
    tempText = currentTextArray[indexForCurrentText].content;
    newText = "";
    for(var i4 = 0; i4 < tempText.length; i4++){
        if(i4 === 0){
            newText += "<span class='marked' ";
            newText += "id='" + i4 + "'>";
            newText += tempText.charAt(i4);
            newText += "</span>";
        }else{
            newText += "<span ";
            newText += "id='" + i4 + "'>";
            newText += tempText.charAt(i4);
            newText += "</span>";
        }
    }
    document.getElementById("textContent").innerHTML = newText;
}//end function startSession

//Erases statistical information
function eraseStatistics(){
    document.getElementById("grossWPM").innerHTML = "";
    document.getElementById("netWPM").innerHTML = "";
    document.getElementById("errors").innerHTML = "";
    document.getElementById("accuracy").innerHTML = "";
    grossWPM = 0;
    netWPM = 0;
    errors = 0;
    accuracy = 0;
}//end function eraseStatistics

//Controls user input, provides feedback on typing, and updates statistical information
function getInput(event){
    tempTime = new Date();
    textInputCounter++;
    charInFocus = event.target.value.charAt(event.target.value.length-1);
    if(charInFocus === " "){
        document.getElementById("textInput").value = "";
    }

    tempIndex = textInputCounter + 1;
    document.getElementById(tempIndex).classList.add('marked');
    document.getElementById(textInputCounter).classList.remove('marked');


    elapsedTime = (tempTime.getTime() - generalTime.getTime()) / 60000;
    grossWPM = parseInt((textInputCounter / averageWordLength) / elapsedTime);
    document.getElementById("grossWPM").innerHTML = grossWPM;

    charToCompare = document.getElementById(textInputCounter).innerText;
    if(ignoreCase){
        charInFocus = charInFocus.toLowerCase();
        charToCompare = charToCompare.toLowerCase();
    }

    if(charInFocus !== charToCompare){
        errorSound.play();
        errors++;
        document.getElementById(textInputCounter).classList.add('incorrect');
    }else{
        document.getElementById(textInputCounter).classList.add('correct');
    }
    document.getElementById("errors").innerHTML = errors;

    netWPM = parseInt(((textInputCounter - errors) / averageWordLength) / elapsedTime);
    document.getElementById("netWPM").innerHTML = netWPM;

    accuracy = parseInt((netWPM / grossWPM) * 100);
    tempString = "";
    tempString = accuracy + "%";
    document.getElementById("accuracy").innerHTML = tempString;
}//end function getInput

//Launches different scenatios depending on whether it is Play or Stop that is clicked by the user
function buttonControl(){
    buttonState = document.getElementById("playButton").value;
    if(buttonState === "start"){
        document.getElementById("textInput").removeAttribute("disabled");
        document.getElementById("playButton").value = "stop";
        startSession();
        eraseStatistics();
        document.getElementById("textInput").addEventListener("input", getInput, false)
    }else if(buttonState === "stop"){
        textInputCounter = -1;
        document.getElementById("playButton").value = "start";
        startSession();
        document.getElementById("textInput").setAttribute("disabled", true);
    }
}//end function buttonControl

//Erases the text input field once it gets focus
function prepareForTyping(){
    document.getElementById("textInput").value = "";
}//end function prepareForTyping

//Provides instructions for the text input once it is out of focus
function provideInstruction(){
    document.getElementById("textInput").value = instruction;
}//end function provideInstruction

//Gets user input whether the typing control should be case sensitive or not
function adjustCaseSensitivity(){
    this.checked ?  ignoreCase = true : ignoreCase = false;
}//end function adjustCaseSensitivity

/* Custom objects */
function Text(title, author, language, content) {
    this.title = title;
    this.author = author;
    this.language = language;
    this.content = content;
}//end custom object Text

/* Main flow */

//Starts the session activity
window.addEventListener('load', start, false);

//Controls inputs from different settings and adjusts the page accordingly
document.addEventListener('DOMContentLoaded',function() {
    document.querySelector('input[name=swedish]').addEventListener('change', loadTextChooser);
    document.querySelector('input[name=english]').addEventListener('change', loadTextChooser);
    document.querySelector('input[name=case-sensitivity]').addEventListener('change', adjustCaseSensitivity);
    document.querySelector('select[name="texts"]').onchange=updateTextDetails;
    document.querySelector('textarea[name=textInput]').addEventListener('focusin', prepareForTyping, false);
    document.querySelector('textarea[name=textInput]').addEventListener('focusout', provideInstruction, false);
},false);