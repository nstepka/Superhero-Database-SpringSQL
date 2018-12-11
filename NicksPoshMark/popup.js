$(document).ready(function() {
  setUpPopUp();
  sendOffer();
})


console.log("ready to go");

var minimumTime = 3;
var maxShareTime = 5;
var discountPercent = 10;
var shareStatus = 0;
var sendOffers = 0;
//itemNumberInput

function setUpPopUp() {
  chrome.storage.local.get("minimumTime", function(fetchedData) {
    $('#shareTimeMinimum')[0].value = minimumTime;
    $('#shareTimeMaximum')[0].value = maxShareTime;
    $('#discountPercent')[0].value = discountPercent;
  });



}


function startSharing() {
    document.getElementById('startSharing').onclick = function() {

     minimumTime = document.getElementById('shareTimeMinimum').value;
     maxShareTime = document.getElementById('shareTimeMaximum').value;
     shareStatus = 1;

     chrome.storage.local.set({'minimumTime': minimumTime});
     chrome.storage.local.set({'maxShareTime': maxShareTime});
     chrome.storage.local.set({'shareStatus' : shareStatus});

     chrome.tabs.executeScript({
       file: 'js/itemshare.js'
        });


     }
}

function sendOffer() {
  document.getElementById('sendOffers').onclick = function () {
    discountPercent = document.getElementById('discountPercent').value;
    chrome.storage.local.set({'discountPercent': discountPercent});
    sendOffers = 1;
    chrome.storage.local.set({'sendOffers': sendOffers});




    chrome.tabs.executeScript({
      file: 'js/sendOffers.js'
       });





  }

}
