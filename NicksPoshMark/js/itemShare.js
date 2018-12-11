var shareStatus = "";

var status = false;
function getUserCloset() {
	//needs to get all the items in the user closet and assign it to an array
	//Need product name, product ID, and price if its there
	//need to filter out all items that are already sold
  var scrollingElement = (document.scrollingElement || document.body);
  scrollingElement.scrollTop = scrollingElement.scrollHeight;//return the closet



}

function shareCloset() {
	//needs to click the preshare button, then click the share button for each items
	//create a loop that will keep running true as long as there is no captcha
	// get responces for successfull shares, share that to a log that can  be displayed
	//is there a way to send the user a text or email when its captchad???

       //
       //  shareStatus = chrome.storage.local.get('shareStatus', function (items) {
       //    shareStatus = items.shareStatus;
       //    alert(shareStatus);
       //    status = true
       //
       // });


  // var shareStatus = "";
  // chrome.storage.local.get('shareStatus', function (result) {
  //    var shareStatus = result.shareStatus;
  //
  //   if (shareSatus == 1) {
  //     getUserCloset();
  //   }
  //
  // });
  //


}

function offerToLikers() {
	//need to go to each item page ie. https://poshmark.com/listing/Simulated-Morganite-Three-Stone-ring-5ac95bcc72ea887f6bf6b3aa
	//click the "Price Drop" link and a new page will come up
	//click the offer to likers
	//if couldnt get price before, price is in this page
	//in the offer text take the price of the item and calc the price discount.
	//select the first tab -$1.50

}

shareCloset();
